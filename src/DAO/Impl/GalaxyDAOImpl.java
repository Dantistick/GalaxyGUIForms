package DAO.Impl;

import DAO.Interfaces.GalaxyDAO;
import MyWindowApp.forms.Add.addGalaxyForm;
import MyWindowApp.forms.Edit.editGalaxyForm;
import MyWindowApp.forms.mainForm;
import entity.Galaxy;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

import static MyWindowApp.menu.CreateMenu.table;

public class GalaxyDAOImpl implements GalaxyDAO {

    @Override
    public void getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Galaxy> query = session.createQuery("FROM Galaxy", Galaxy.class);
        List<Galaxy> galaxies = query.getResultList();

        transaction.commit();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");

        for (Galaxy galaxy : galaxies) {
            Object[] row = {galaxy.getNameGalaxy()};
            model.addRow(row);
        }

        table = new JTable(model);

        mainForm.scrollPane.setViewportView(new JScrollPane(table));

        session.close();
    }

    @Override
    public void insert() throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String galaxyName = addGalaxyForm.textFieldName.getText().trim();

            if (galaxyName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a galaxy name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            transaction = session.beginTransaction();

            Galaxy newGalaxy = new Galaxy();
            newGalaxy.setNameGalaxy(galaxyName);
            session.save(newGalaxy);

            transaction.commit();

            JOptionPane.showMessageDialog(null, "Galaxy inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            addGalaxyForm.textFieldName.setText("");
            getAll();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            throw new SQLException("Failed to insert galaxy", ex);
        }
    }

    @Override
    public void update() throws SQLException {
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) table.getValueAt(selectedRow, 0);

                    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                        Query<Galaxy> query = session.createQuery("from Galaxy where nameGalaxy = :name", Galaxy.class);
                        query.setParameter("name", name);
                        List<Galaxy> galaxies = query.list();

                        if (!galaxies.isEmpty()) {
                            Galaxy galaxy = galaxies.get(0);
                            new editGalaxyForm(galaxy);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Override
    public void delete() throws SQLException {
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) table.getValueAt(selectedRow, 0);

                    int confirmResult = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить галактику " + name + "?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);

                    if (confirmResult == JOptionPane.YES_OPTION) {
                        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                            Transaction transaction = session.beginTransaction();

                            Query<Galaxy> query = session.createQuery("from Galaxy where nameGalaxy = :name", Galaxy.class);
                            query.setParameter("name", name);
                            List<Galaxy> galaxies = query.list();

                            if (!galaxies.isEmpty()) {
                                Galaxy galaxy = galaxies.get(0);
                                session.delete(galaxy);
                                transaction.commit();
                                getAll();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void acceptEditable(Galaxy galaxy){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            galaxy.setNameGalaxy(editGalaxyForm.textFieldName.getText());
            session.update(galaxy);

            transaction.commit();
            JOptionPane.showMessageDialog(null, "Galaxy updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            getAll();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
