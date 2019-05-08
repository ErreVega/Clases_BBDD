package Main;

import Clases.Animal;
import Clases.BBDDAnimales;

import java.sql.SQLException;
import java.util.Scanner;

public class Main13_3 {
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
            BBDDAnimales bd = new BBDDAnimales();
            Scanner sc = new Scanner(System.in);

            String[] menuPrincipal = {(
                    "   Seleccione opción.\n" +
                            "1. Consultar BBDD.\n" +
                            "2. Editar  animales de BBDD.\n" +
                            "3. Editar  cuidadores de BBDD.\n" +
                            "4. Fin\n"
            ), (

                    "   Seleccione opción.\n" +
                            "1. Introducir nuevo animal\n" +
                            "2. Modificar animal existente.\n" +
                            "3. Borrar animal\n" +
                            "4. Volver.\n"
            ), (

                    "   Seleccione opción.\n" +
                            "1. Introducir nuevo cuidador\n" +
                            "2. Modificar cuidador existente.\n" +
                            "3. Borrar cuidador\n" +
                            "4. Volver.\n"
            ), (

                    "   Seleccione opción.\n" +
                            "1. Animales por ubicacion\n" +
                            "2. Comidas y cantidades.\n" +
                            "3. Animales a cargo de cuidador\n" +
                            "4. Cuidadores veterinarios\n" +
                            "5. Numero de cuidadores veterinarios por ubicacion\n" +
                            "6. Volver.\n"
            )
            };
            String  opNovalida = "Opcion no válida\n",
                    introUbi = "Introduce ubicación\n",
                    introCuida = "Introduce Cuidador\n",
                    input;
            int op1, op2;

            do {
                System.out.println(menuPrincipal[0]);
                op1 = leeInt(sc);

                switch (op1) {
                    case 1: // consultas

                        do {
                            System.out.println(menuPrincipal[3]);
                            op2 = leeInt(sc);

                            switch (op2) {
                                case 1: //animales ubi
                                    System.out.println(introUbi);
                                    input = sc.nextLine();
                                    System.out.println(bd.consultaAnimalesPorUbicacion(input));
                                    break;
                                case 2: //comida cant
                                    System.out.println(bd.consultaComida());
                                    break;
                                case 3: // Animales a carga de cuidador X
                                    System.out.println(introCuida);
                                    input = sc.nextLine();
                                    System.out.println(bd.consultaAnimalesCuidador(input));
                                    break;
                                case 4: //Veterinarios
                                    System.out.println(bd.consultaVet());
                                    break;
                                case 5: // num vet en ubicacion
                                    System.out.println(introUbi);
                                    input = sc.nextLine();
                                    System.out.println(bd.consultaVetPorUbicacion(input));
                                    break;
                                case 6: //volver
                                    break;
                                default:
                                    System.out.println(opNovalida);
                                    break;
                            }
                        } while (op2 != 6);
                        break;
                    case 2: // edit animales
                        do {
                            System.out.println(menuPrincipal[1]);
                            op2 = leeInt(sc);
                            switch (op2){
                                case 1: //Intr animal
                                    break;
                                case 2: // mod animal
                                    break;
                                case 3: // borra animal
                                    break;
                                case 4: // volver
                                    break;
                                default:
                                    System.out.println(opNovalida);
                                    break;
                            }
                        } while (op2 != 4);
                        break;
                    case 3: // edit cuidadores
                        break;
                    case 4: // fin
                        break;
                    default:
                        System.out.println(opNovalida);
                        break;
                }

            } while (op1 != 4);

            bd.close();
        } catch (SQLException e) {
            System.err.println(e.getSQLState() + " " + e.getMessage());
        }
    }
}
