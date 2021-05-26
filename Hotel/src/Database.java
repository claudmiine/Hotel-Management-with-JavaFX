import java.sql.*;

//Creating mySQL database connection to PHPMyAdmin
public class Database {
    public Connection conn;
    public Statement statement;
    public ResultSet resultSet;
    // this points to database name.
    public String url = "jdbc:mysql://localhost:3308/tutorial";

    public Database() {
        conn = null;
        statement = null;
        resultSet = null;

        //A lot of problems can come from changing the password on phpMyAdmin, so the it has been left as root without the password
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