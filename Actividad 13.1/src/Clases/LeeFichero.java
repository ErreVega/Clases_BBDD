package Clases;

import java.io.*;
import java.util.ArrayList;

public class LeeFichero {

    private File file;

    public LeeFichero(File file){
        this.file = file;
    }

    public Lista devuelveDatos(){
        Lista res = new Lista();
        Coche coche;
        try{
            FileReader fr = new FileReader(this.file);
            try {
                BufferedReader br = new BufferedReader(fr);
                String linea;

                String [] datos;

                while ((linea = br.readLine()) != null){
                    datos = linea.split(" ");
                    try {
                        coche = new Coche( datos[0].replaceAll("-", ""), datos[1],datos[2], datos[3], Integer.parseInt(datos[4]), Integer.parseInt(datos[5]));
                        res.insertar(coche);
                    } catch (NumberFormatException e) {
                        System.err.println( e.getStackTrace());
                    }

                }

            } catch (IOException e){
                System.err.println( e.getStackTrace());
            }

        } catch (FileNotFoundException e){
            System.err.println( e.getStackTrace());
        } finally {
            return res;
        }

    }

}
