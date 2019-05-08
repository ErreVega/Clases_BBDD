package Main;

import Clases.BaseDatos;
import Clases.LeeFichero;
import Clases.Lista;

import java.io.File;
import java.sql.SQLException;

public class Main13_1 {

    public static void main(String[] args) {
        File input = new File("coches.txt");

        LeeFichero lf = new LeeFichero(input);
        Lista list =  lf.devuelveDatos();
        try {
            BaseDatos bd = new BaseDatos("Concesionario");
            bd.creaTablaCoches();
            bd.insertaLista(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}
