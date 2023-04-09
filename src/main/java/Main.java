import java.sql.*;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/pets";
    private static final String username = "root";
    private static final String password = "159753An";
    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args)  {
        try {
            createDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static Connection getDBConnection() {

        Connection dbconnection = null;
        try {
            Class.forName(DB_Driver);}
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try
        { dbconnection = DriverManager.getConnection(url, username, password);
            return dbconnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbconnection;
    }

    private static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String deleteTableSQL = "DELETE FROM pets WHERE colour = 'brown';";
        String selectTableSQL = "SELECT * FROM pets;";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(deleteTableSQL);
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String appellation = rs.getString("appellation");
                String name = rs.getString("name");
                String age = rs.getString("age");
                String colour = rs.getString("colour");
                String owner = rs.getString("owner");
                String id = rs.getString("id");
                System.out.println("appellation : " + appellation);
                System.out.println("name: " + name);
                System.out.println("age : " + age);
                System.out.println("colour: " + colour);
                System.out.println("owner : " + owner);
                System.out.println("id: " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
