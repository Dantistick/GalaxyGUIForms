import MyWindowApp.forms.mainForm;
import database.DatabaseConnection;
import hibernate.HibernateUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        try(Connection connection = databaseConnection.getConnection()){
            SwingUtilities.invokeLater(mainForm::new);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection();
        }
    }
}
