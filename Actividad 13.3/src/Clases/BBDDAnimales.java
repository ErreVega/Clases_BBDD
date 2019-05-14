package Clases;

import java.sql.*;

public class BBDDAnimales {

    private Connection con;


    public BBDDAnimales() throws SQLException {
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Zoo", "root", "");
    }

    public void insertaAnimal(String nombre, int edad, String color, String tipoComida, int cantidadComida,
                              String cuidador, String carEspeciales, String ubicación) {

        Animal a = new Animal(nombre, edad, color, tipoComida, cantidadComida, cuidador, carEspeciales, ubicación);
        insertaAnimal(a);
    }


    public void insertaAnimal(Animal a) {
        try {
            Statement stmn = con.createStatement();
            try {
                stmn.executeUpdate("INSERT INTO ANIMALES () VALUES (\'" +
                        a.getNombre() + "\', " +
                        a.getEdad() + ", \'" +
                        a.getColor() + "\', \'" +
                        a.getTipoComida() + "\',\'" +
                        a.getCuidador() + "\',\'" +
                        a.getCarEspeciales() + "\',\'" +
                        a.getUbicación() + "\', DEFAULT ," +
                        a.getCantidadComida() + ");"
                );
            } catch (SQLIntegrityConstraintViolationException e) {
                System.err.println(e.getSQLState() + " " + e.getMessage());
            } finally {
                stmn.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
    }

    public void insertaCuidador(Cuidador c) {
        try {
            Statement stmn = con.createStatement();
            try {
                stmn.executeUpdate("INSERT INTO CUIDADOR VALUES (\'" +
                        c.getNombre() + "\', " +
                        c.isVeterinario() + ");"
                );
            } catch (SQLIntegrityConstraintViolationException e) {
                System.err.println(e.getSQLState() + " " + e.getMessage());
            } finally {
                stmn.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }

    }

    public String consultaAnimalesPorUbicacion(String ubi) {
        String res = "    nombre  edad  color  tipoComida  cuidador  carEspeciales  ubicación  numID  cantidadComida\n";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM ANIMALES WHERE UBICACIÓN LIKE \'" + ubi + "\';");
            int cont = 0;
            while (rs.next()) {
                res = res + cont + ": " + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " +
                        rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + ", " +
                        rs.getString(7) + ", " + rs.getString(8) + ", " + rs.getString(9) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
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
                res = res + cont + ": " + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " +
                        rs.getString(4) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public String consultaAnimalesCuidador(String cuidador) {
        String res = "    NUMID   nombre   cuidador\n";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT NUMID, NOMBRE, CUIDADOR FROM ANIMALES WHERE CUIDADOR LIKE \'" + cuidador + "\';");
            int cont = 0;
            while (rs.next()) {
                res = res + cont + ": " + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
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
                res = res + cont + ": " + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " +
                        rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + ", " +
                        rs.getString(7) + ", " + rs.getString(8) + ", " + rs.getString(9) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public String consultaCuidadoresTodos() {
        String res = "    nombre  Vet\n",
                si_no = "";

        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM CUIDADOR;");

            while (rs.next()) {
                if (rs.getString(2).equalsIgnoreCase("0"))
                    si_no = "No";
                else
                    si_no = "Sí";

                res = res + rs.getString(1) + ", " + si_no + "\n";
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public String consultaAnimal(int id) {

        String res = "    nombre  edad  color  tipoComida  cuidador  carEspeciales  ubicación  numID  cantidadComida\n";

        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM ANIMALES WHERE NUMID = " + id + ";");
            int cont = 0;
            while (rs.next()) {
                res = res + cont + ": " + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " +
                        rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + ", " +
                        rs.getString(7) + ", " + rs.getString(8) + ", " + rs.getString(9) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;

    }

    public String consultaAnimal(String nombre) {

        String res = "    nombre  edad  color  tipoComida  cuidador  carEspeciales  ubicación  numID  cantidadComida\n";

        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM ANIMALES WHERE NOMBRE LIKE \'" + nombre + "\';");
            int cont = 0;
            while (rs.next()) {
                res = res + cont + ": " + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " +
                        rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + ", " +
                        rs.getString(7) + ", " + rs.getString(8) + ", " + rs.getString(9) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;

    }

    public String consultaCampos(String tabla) {
        String res = "";

        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = \'" + tabla + "\';");
            int cont = 0;
            while (rs.next()) {
                res = res + cont + ": " + rs.getString(1) + ",\n";
                cont++;
            }
            stmn.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public String consultaCuidador(String nombre) {
        String res = "    nombre  Vet\n",
                si_no = "";

        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM CUIDADOR WHERE NOMBRE LIKE \'" + nombre + "\';");

            while (rs.next()) {
                if (rs.getString(2).equalsIgnoreCase("0"))
                    si_no = "No";
                else
                    si_no = "Sí";

                res = res + rs.getString(1) + ", " + si_no + "\n";
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public boolean borraAnimalNombre(String nombre) {

        boolean res = false;
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("delete FROM ANIMALES " +
                    "WHERE NOMBRE LIKE \'" + nombre + "\';");

            if (stmn.getUpdateCount() >= 1)
                res = true;
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public boolean borraAnimalId(int id) {
        boolean res = false;
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("delete FROM ANIMALES " +
                    "WHERE NUMID LIKE " + id + ";");

            if (stmn.getUpdateCount() >= 1)
                res = true;
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }


    public String consultaVet() {

        String res = "";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT NOMBRE FROM CUIDADOR " +
                    "WHERE VETERINARIO = TRUE;");
            int cont = 0;
            while (rs.next()) {
                res = res + cont + ": " + rs.getString(1) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public String consultaVetPorUbicacion(String ubi) {

        String res = "";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT NOMBRE FROM CUIDADOR " +
                    "WHERE VETERINARIO = TRUE AND " +
                    "NOMBRE IN (SELECT CUIDADOR FROM ANIMALES " +
                    "WHERE UBICACIÓN = \'" + ubi + "\')   ;");
            int cont = 0;
            while (rs.next()) {
                res = cont + ": " + rs.getString(1) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return res;
    }

    public void updateAnimal(String animal, int campo, String nuevoDato) {
        try {

            PreparedStatement stmn;
            String sql = "";


            switch (campo) {

                case 0:
                    sql = ("UPDATE ANIMALES SET NOMBRE = ? WHERE NOMBRE = ?;");
                    break;
                case 1:
                    sql = ("UPDATE ANIMALES SET EDAD = ? WHERE NOMBRE = ?;");
                    break;
                case 2:
                    sql = ("UPDATE ANIMALES SET COLOR =  ?  WHERE NOMBRE = ? ;");
                    break;
                case 3:
                    sql = ("UPDATE ANIMALES SET TIPOCOMIDA =  ?  WHERE NOMBRE = ?;");
                    break;
                case 4:
                    sql = ("UPDATE ANIMALES SET CUIDADOR =  ?  WHERE NOMBRE = ?;");
                    break;
                case 5:
                    sql = ("UPDATE ANIMALES SET CARESPECIALES =  ?  WHERE NOMBRE = ?;");
                    break;
                case 6:
                    sql = ("UPDATE ANIMALES SET UBICACIÓN =  ?  WHERE NOMBRE = ?;");
                    break;
                case 7:
                    sql = ("UPDATE ANIMALES SET NUMID = ? WHERE NOMBRE = ?;");
                    break;
                case 8:
                    sql = ("UPDATE ANIMALES SET CANTIDADCOMIDA = ?  WHERE NOMBRE = ?  ?");
                    break;
                default:
                    System.out.println("Campo incorrecto. Modificacion no realizada.");

            }
            stmn = con.prepareStatement(sql);
            stmn.setString(2, animal);

            switch (campo) {
                case 0:
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                    stmn.setString(1, nuevoDato);

                    break;

                case 2:
                case 7:
                case 8:
                    try {
                        stmn.setInt(1, Integer.parseInt(nuevoDato));

                    } catch (NumberFormatException e){
                        System.err.println("Formato introducido incorrecto, necesario un numero entero " + e.getMessage());
                    }
                    break;
            }
            stmn.executeUpdate();


            stmn.close();

        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }


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
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
        return borrado;
    }


    public void close() throws SQLException {
        this.con.close();
    }

}
