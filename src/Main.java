import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        try {

            connection = helper.getConnection();
            System.out.println("Bağlantı oluştu");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Code,Name,Continent,Region from country ");
            ArrayList<Country> countries = new ArrayList<>();
            while (resultSet.next()) {
               countries.add(new Country(
                       resultSet.getString("Code"),
                       resultSet.getString("Name"),
                       resultSet.getString("Continent"),
                       resultSet.getString("Region")));
            }
            System.out.println(countries.size());

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        finally {
            connection.close();
        }

    }
}