import java.sql.*;

public class Test {

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
            System.out.println("Conexión exitosa!");

            createEQUIPO(con, "prueba");
            createJUGADORES(con, "prueba");
        } catch (SQLException ex) {
            printSQLException(ex);
        }
    }


    public static void printSQLException(SQLException ex) {
        ex.printStackTrace(System.err);
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("Message: " + ex.getMessage());
        Throwable t = ex.getCause();
        while (t != null) {
            System.out.println("Cause: " + t);
            t = t.getCause();
        }
    }

    public static void createEQUIPO(Connection con, String BDNombre) throws SQLException {
        String createString = "create table " + BDNombre + ".EQUIPO " +
                "(TEAM_ID integer NOT NULL," +
                "EQ_NOMBRE varchar (40) NOT NULL," +
                "ESTADIO varchar (40) NOT NULL, " +
                "POBLACION varchar(20) NOT NULL," +
                "PROVINCIA varchar(20) NOT NULL," +
                "COD_POSTAL char(5)," +
                "PRIMARY KEY (TEAM_ID))";
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            stmt.close();
        }
    }

    public static void createJUGADORES(Connection con, String BDNombre) throws SQLException {
        String createString = "create table " + BDNombre + ".JUGADORES " +
                "(PLAYER_ID integer NOT NULL," +
                "TEAM_IDE integer NOT NULL," +
                "NOMBRE varchar (40) NOT NULL, " +
                "DORSAL integer NOT NULL," +
                "PRIMARY KEY (PLAYER_ID)" +
                ")";
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            stmt.close();
        }

    }
}