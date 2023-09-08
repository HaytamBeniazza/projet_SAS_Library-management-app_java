import DAO.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        testDatabaseConnection();
    }

    private static void testDatabaseConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Check if the connection is not null
            if (connection != null) {
                try {
                    String sql = "SELECT * FROM librarian";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        System.out.println(resultSet);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to establish a database connection.");
            }

            // Close the connection (always close it when done)
            DatabaseConnection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while testing the database connection.");
        }
    }
}