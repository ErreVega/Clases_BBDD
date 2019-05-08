package Clases;

import java.sql.*;

public class BBDDAnimales {

    private Connection con;


    public BBDDAnimales() throws SQLException {
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Zoo", "root", "");
    }

    public void insertaAnimal(String nombre, int edad, String color, String tipoComida, int cantidadComida,
                              String cuidador, String carEspeciales, String ubicación){

        Animal a = new Animal( nombre,  edad,  color,  tipoComida,  cantidadComida, cuidador,  carEspeciales,  ubicación);
        insertaAnimal(a);
    }



    public void insertaAnimal(Animal a) {
        try {
            //(String nombre, int edad, String color, String tipoComida, int cantidadComida,
            //                  String cuidador, String carEspeciales, String ubicación)
            Statement stmn = con.createStatement();
            try {
                stmn.executeUpdate("INSERT INTO ANIMALES VALUES (\'" +
                        a.getNombre() + "\', \'" +
                        a.getEdad() + "\', \'" +
                        a.getColor() + "\', \'" +
                        a.getTipoComida() + "\'," +
                        a.getCuidador() + "," +
                        a.getCarEspeciales() + "," +
                        a.getUbicación() + "," +
                        a.getNumID() + "," +
                        a.getCantidadComida() + ");"
                );
            } catch (SQLIntegrityConstraintViolationException e) {
                System.err.println(e.getSQLState() + " "+ e.getMessage());
            } finally {
                stmn.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
    }

    public String consultaAnimalesPorUbicacion(String ubi) {
        String res = "    nombre  edad  color  tipoComida  cuidador  carEspeciales  ubicación  numID  cantidadComida\n";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM ANIMALES WHERE UBICACIÓN LIKE " + ubi +";");
            int cont = 0;
            while (rs.next()) {
                res = cont + ": " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)+
                                 rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) +
                                 rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " "+ e.getMessage());
        }
        return res;
    }

    public String consultaComida() {
        String res = "    NUMID  nombre  tipoComida  cantidadComida\n";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT NUMID, NOMBRE, TIPOCOMIDA, CANTIDADCOMIDA FROM ANIMALES;");
            int cont = 0;
            while (rs.next()) {
                res = cont + ": " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)+
                        rs.getString(4) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " "+ e.getMessage());
        }
        return res;
    }

    public String consultaAnimalesCuidador(String cuidador) {
        String res = "    NUMID   nombre   cuidador\n";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT NUMID, NOMBRE, CUIDADOR FROM ANIMALES;");
            int cont = 0;
            while (rs.next()) {
                res = cont + ": " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)+"\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " "+ e.getMessage());
        }
        return res;
    }

    public String consultaAnimalesTodos() {
        String res = "    nombre  edad  color  tipoComida  cuidador  carEspeciales  ubicación  numID  cantidadComida\n";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM ANIMALES;");
            int cont = 0;
            while (rs.next()) {
                res = cont + ": " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)+
                        rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) +
                        rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " "+ e.getMessage());
        }
        return res;
    }


    public String consultaVet(){

        String res = "";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery(   "SELECT NOMBRE FROM CUIDADOR" +
                    "WHERE VETERINARIO = TRUE;");
            int cont = 0;
            while (rs.next()) {
                res = cont + ": " + rs.getString(1) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " "+ e.getMessage());
        }
        return res;
    }

    public String consultaVetPorUbicacion( String ubi){

        String res = "";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery(   "SELECT NOMBRE FROM CUIDADOR" +
                                                    "WHERE VETERINARIO = TRUE AND " +
                                                    "NOMBRE IN (SELECT CUIDADOR FROM ANIMALES" +
                                                                "WHERE UBICACION LIKE "+ ubi +")   ;");
            int cont = 0;
            while (rs.next()) {
                res = cont + ": " + rs.getString(1) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " "+ e.getMessage());
        }
        return res;
    }


    public boolean borrarAnimal(int i) {
        String res = "";
        Boolean borrado = false;
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT NUMID FROM ANIMALES;");
            int cont = 0;
            while (rs.next() && !borrado) {
                if (i == cont) {
                    res = rs.getString(8);
                    stmn.executeUpdate("DELETE  FROM ANIMALES WHERE NUMID LIKE \'" + res + "\';");
                    borrado = true;
                }
                cont++;
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " "+ e.getMessage());
        }
        return borrado;
    }


    public void close() throws SQLException {
            this.con.close();
    }

}
