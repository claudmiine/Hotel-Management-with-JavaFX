import java.sql.*;

public class Database {
    public Connection conn;
    public Statement statement;
    public ResultSet resultSet;
    // this points to your database name, I had to create my own one. Just change hotel_database to your database name
    public String url = "jdbc:mysql://localhost:3308/tutorial";

    public Database() {
        conn = null;
        statement = null;
        resultSet = null;

        //A lot of problems can come from changing the password on phpMyAdmin, just leave them as root and no password for now :)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            statement = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}