package Clases;

import java.sql.*;

public class BaseDatos {

    private Connection con;


    public BaseDatos(String nombreBBDD) throws SQLException {
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombreBBDD, "root", "");
        System.out.println("Conexión exitosa!");

    }

    public void creaTablaCoches() {
        Statement stmn = null;
        try {
            stmn = con.createStatement();

            stmn.executeUpdate("CREATE TABLE COCHES (" +
                    "Matrícula VARCHAR(8) PRIMARY KEY, " +
                    "Marca VARCHAR (40) NOT NULL, " +
                    "Modelo VARCHAR (40) NOT NULL, " +
                    "Color VARCHAR (40) NOT NULL, " +
                    "Anno INTEGER NOT NULL, " +
                    "Precio INTEGER NOT NULL)");
            stmn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void insertaLista(Lista datos) {

        if (!datos.isEmpty()) {
            int i = 0;
            try {
                Statement stmn = con.createStatement();
                for ( i = 0; i < datos.size(); i++) {
                    Coche coche = new Coche(datos.getCoche(i).getMatrícula(), datos.getCoche(i).getMarca(),
                            datos.getCoche(i).getModelo(), datos.getCoche(i).getColor(), datos.getCoche(i).getAnno(),
                            datos.getCoche(i).getPrecio());
                    stmn.executeUpdate("INSERT INTO COCHES VALUES (\'" +
                            datos.getCoche(i).getMatrícula() + "\', \'" +
                            datos.getCoche(i).getMarca() + "\', \'" +
                            datos.getCoche(i).getModelo() + "\', \'" +
                            datos.getCoche(i).getColor() + "\'," +
                            datos.getCoche(i).getAnno() + "," +
                            datos.getCoche(i).getPrecio() + ");"
                    );
                }
                stmn.executeUpdate("COMMIT ;");
                stmn.close();
            } catch (SQLIntegrityConstraintViolationException e){
                i++;
            }catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public void insertaCoche(String matricula, String marca, String modelo, String color, int anno, int precio) {
        try {
            Statement stmn = con.createStatement();
            try {
                stmn.executeUpdate("INSERT INTO COCHES VALUES (\'" +
                        matricula + "\', \'" +
                        marca + "\', \'" +
                        modelo + "\', \'" +
                        color + "\'," +
                        anno + "," +
                        precio + ");"
                );
            } catch (SQLIntegrityConstraintViolationException e) {
                System.err.println("Matricula ya existe " + e.getSQLState());
            } finally {
                stmn.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
    }
    public void insertaCoche(Coche c) {
        try {
            Statement stmn = con.createStatement();
            try {
                stmn.executeUpdate("INSERT INTO COCHES VALUES (\'" +
                        c.getMatrícula() + "\', \'" +
                        c.getMarca() + "\', \'" +
                        c.getModelo() + "\', \'" +
                        c.getColor() + "\'," +
                        c.getAnno() + "," +
                        c.getPrecio() + ");"
                );
            } catch (SQLIntegrityConstraintViolationException e) {
                System.err.println("Matricula ya existe " + e.getSQLState());
            } finally {
                stmn.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
    }

    public String consultaMatriculasYModelo() {
        String res = "";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT MATRÍCULA, MARCA, MODELO FROM COCHES;");
            int cont = 0;
            while (rs.next()) {
                res = res + "COCHE " + cont + ": " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return res;
    }

    public String consultaTodo() {
        String res = "";
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT * FROM COCHES;");
            int cont = 0;
            while (rs.next()) {
                res = res + "COCHE " + cont + ": " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) +
                        " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + "\n";
                cont++;
            }
            stmn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return res;
    }

    public boolean borrarCoche(int i) {
        String res = "";
        Boolean borrado = false;
        try {
            Statement stmn = con.createStatement();

            ResultSet rs = stmn.executeQuery("SELECT MATRÍCULA FROM COCHES;");
            int cont = 0;
            while (rs.next() && !borrado) {
                if (i == cont) {
                    res = rs.getString(1);
                    stmn.executeUpdate("DELETE  FROM COCHES WHERE MATRÍCULA LIKE \'" + res + "\';");
                    borrado = true;
                }
                cont++;
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return borrado;
    }

    public boolean borrarCoche(String matricula) {
        Boolean borrado = false;
        try {
            Statement stmn = con.createStatement();

            stmn.executeUpdate("DELETE  FROM COCHES WHERE MATRÍCULA LIKE \'" + matricula.trim() + "\';");
            if (stmn.getUpdateCount() >= 1)
                borrado = true;
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return borrado;
    }

    public void close() throws SQLException {
            this.con.close();
    }

}
