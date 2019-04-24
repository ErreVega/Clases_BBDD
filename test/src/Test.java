import java.sql.*;

public class Test {

    public static void printSQLException(SQLException ex){
        ex.printStackTrace(System.err);
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("Message: " + ex.getMessage());
        Throwable t = ex.getCause();
        while(t != null)
        {
            System.out.println("Cause: " + t);
            t = t.getCause();
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DDBB1", "root", "");
            System.out.println("Conexión exitosa!");
        } catch (SQLException e) {
            printSQLException(e);
        }

    }
}

