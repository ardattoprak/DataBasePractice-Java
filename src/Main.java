import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {

            connection = helper.getConnection();
            String sql = "update city set population=100000, disctrict = 'Düzce' where id = ?";
            System.out.println("Bağlantı oluştu");
            statement = connection.prepareStatement(sql);
            statement.setInt(1,4082);
            int result = statement.executeUpdate();
            System.out.println("Kayıt güncellendi");

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }
    }

    public static void selectDemo()throws SQLException {
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

    public static void insertData() throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {

            connection = helper.getConnection();
            String sql = "insert into city (Name,CountryCode,District,Population) values(?,?,?,?)";
            System.out.println("Bağlantı oluştu");
            statement = connection.prepareStatement(sql);
            statement.setString(1,"Düzce");
            statement.setString(2 ,"TUR");
            statement.setString(3,"Turkey");
            statement.setInt(4,70000);
            int result = statement.executeUpdate();
            System.out.println("Kayıt eklendi");

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }
    }
}