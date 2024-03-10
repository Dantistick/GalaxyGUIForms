package MyWindowApp.menu;

import DAO.Impl.GalaxyDAOImpl;
import DAO.Impl.PlanetDAOImpl;
import DAO.Impl.SatelliteDAOImpl;
import MyWindowApp.forms.Add.addGalaxyForm;
import MyWindowApp.forms.Add.addPlanetForm;
import MyWindowApp.forms.Add.addSatelliteForm;

import java.awt.*;
import java.sql.SQLException;

import static MyWindowApp.menu.CreateMenu.currentTable;
import static MyWindowApp.menu.CreateMenu.table;

public class ActionFromButtons {
    public void setActionListenerOnAddButton(){
        switch (currentTable){
            case GALAXY: {
                new addGalaxyForm();
                break;
            }
            case PLANET: {
                new addPlanetForm();
                break;
            }
            case SATELLITE: {
                new addSatelliteForm();
                break;
            }
            default:
                break;
        }
    }

    public void setActionListenerOnEditButton(){
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        switch (currentTable){
            case GALAXY: {
                GalaxyDAOImpl galaxyDAO = new GalaxyDAOImpl();
                try {
                    galaxyDAO.update();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case PLANET: {
                PlanetDAOImpl planetDAO = new PlanetDAOImpl();
                try {
                    planetDAO.update();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case SATELLITE: {
                SatelliteDAOImpl satelliteDAO = new SatelliteDAOImpl();
                try {
                    satelliteDAO.update();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public void setActionListenerOnDeleteButton(){
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        switch (currentTable){
            case GALAXY: {
                try {
                    GalaxyDAOImpl galaxyDAO = new GalaxyDAOImpl();
                    galaxyDAO.delete();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case PLANET: {
                try {
                    PlanetDAOImpl planetDAO = new PlanetDAOImpl();
                    planetDAO.delete();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case SATELLITE: {
                try {
                    SatelliteDAOImpl satelliteDAO = new SatelliteDAOImpl();
                    satelliteDAO.delete();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
}
