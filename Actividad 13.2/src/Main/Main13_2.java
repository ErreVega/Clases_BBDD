package Main;

import Clases.BaseDatos;

import java.sql.SQLException;
import java.util.Scanner;

public class Main13_2 {

    private static int leeInt(Scanner sc) {
        boolean leido = false;
        int res = -1;
        do {
            try {
                res = Integer.parseInt(sc.nextLine());
                leido = true;
            } catch (NumberFormatException e) {
                System.err.println("Caracter error." + e.getMessage());
                System.out.println("Reintroduce opcion");
            }
        } while (!leido);
        return res;
    }


    public static void main(String[] args) {
        try {

            BaseDatos bd = new BaseDatos("Concesionario");
            Scanner sc = new Scanner(System.in);

            String[] menu = {(
                    "Seleccione opción\n" +
                            "1. Listar vehiculos.\n" +
                            "2. Introducir nuevo vehiculo.\n" +
                            "3. Borrar Vehiculo.\n" +
                            "4. Fin."),
                    (
                            "1. Selecionar por lista.\n" +
                                    "2. Introducir matricula.\n"),
                    (
                            "Introduce numero de coche a borrar"),
                    ("Introduzca matrícula")
            };

            int op, subop;
            do {
                System.out.println(menu[0]);
                op = leeInt(sc);
                switch (op) {
                    case 1:
                        System.out.println(bd.consultaTodo());
                        break;
                    case 2:
                        System.out.println("Introduce matrícula");
                        String mat = sc.nextLine().trim();
                        System.out.println("Introduce marca");
                        String marca = sc.nextLine().trim();
                        System.out.println("Introduce modelo");
                        String mod = sc.nextLine().trim();
                        System.out.println("Introduce color");
                        String color = sc.nextLine().trim();
                        System.out.println("Introduce año de fabricación");
                        int ano = leeInt(sc);
                        System.out.println("Introduce precio");
                        int precio = leeInt(sc);
                        bd.insertaCoche(mat, marca, mod, color, ano, precio);

                        break;
                    case 3:
                        int iCoche;
                        String coche;

                        System.out.println(menu[1]);
                        subop = leeInt(sc);

                        switch (subop) {
                            case 1:
                                System.out.println(bd.consultaMatriculasYModelo());
                                System.out.println(menu[2]);
                                iCoche = leeInt(sc);
                                if (bd.borrarCoche(iCoche))
                                    System.out.println("Vehículo eliminado de la base de datos.");
                                else
                                    System.out.println("Error al eliminar vehículo.");
                                break;
                            case 2:
                                System.out.println(menu[3]);
                                coche = sc.nextLine();
                                if (bd.borrarCoche(coche))
                                    System.out.println("Vehículo eliminado de la base de datos.");
                                else
                                    System.out.println("Error al eliminar vehículo.");
                                break;
                            default:
                                System.out.println("Opcion no válida");
                        }


                    case 4:
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } while (op != 4);
            sc.close();
            bd.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
    }
}
