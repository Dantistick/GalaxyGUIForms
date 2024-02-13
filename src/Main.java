import MyWindowApp.forms.mainForm;
import database.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection.getInstance();

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        try(Connection connection = databaseConnection.getConnection()){
            SwingUtilities.invokeLater(mainForm::new);
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
