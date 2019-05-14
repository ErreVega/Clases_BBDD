package Main;

import Clases.Animal;
import Clases.BBDDAnimales;
import Clases.Cuidador;

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

    private static boolean si_o_no(Scanner sc){
        boolean res = false,
                fin = false;
        String input;
        do {
            input = sc.nextLine();

            switch (input){
                case "S":
                case "s":
                    res = true;
                    fin = true;
                    break;
                case "N":
                case "n":
                    fin = true;
                    break;
                default:
                    System.out.println("Caracter no reconocido");

            }
        } while (!fin);
        return res;

    }

    public static void main(String[] args) {

        try {
            BBDDAnimales bd = new BBDDAnimales();
            Scanner sc = new Scanner(System.in);

            String[] menuPrincipal = {( //0
                    "   Seleccione opción.\n" +
                            "1. Consultar BBDD.\n" +
                            "2. Editar  animales de BBDD.\n" +
                            "3. Editar  cuidadores de BBDD.\n" +
                            "4. Fin\n"
            ), (//1

                    "   Seleccione opción.\n" +
                            "1. Introducir nuevo animal\n" +
                            "2. Modificar animal existente.\n" +
                            "3. Borrar animal\n" +
                            "4. Volver.\n"
            ), (//2

                    "   Seleccione opción.\n" +
                            "1. Introducir nuevo cuidador\n" +
                            "2. Modificar cuidador existente.\n" +
                            "3. Borrar cuidador\n" +
                            "4. Volver.\n"
            ), (//3

                    "   Seleccione opción.\n" +
                            "1. Animales por ubicacion\n" +
                            "2. Comidas y cantidades.\n" +
                            "3. Animales a cargo de cuidador\n" +
                            "4. Cuidadores veterinarios\n" +
                            "5. Numero de cuidadores veterinarios por ubicacion\n" +
                            "6. Todos los animales.\n" +
                            "7. Todos los cuidadores\n" +
                            "8. Volver.\n"
            ), (//4
                    "   Seleccione opción.\n" +
                            "1. Buscar por nombre.\n" +
                            "2. Buscar por ID.\n" +
                            "3. Listar.\n" +
                            "4. Volver."
            ), (//5
                    "   Seleccione opción.\n" +
                            "1. Buscar por nombre.\n" +
                            "2. Listar.\n" +
                            "3. Volver."
            )
            };
            String opNovalida = "Opcion no válida\n",
                    introUbi = "Introduce ubicación\n",
                    introCuida = "Introduce Cuidador\n",
                    input,
                    vet;

            int op1, op2, op3;

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
                                case 6: // todos animal
                                    System.out.println(bd.consultaAnimalesTodos());
                                    break;
                                case 7: // todos cuida
                                    System.out.println(bd.consultaCuidadoresTodos());
                                    break;
                                case 8: //volver
                                    break;
                                default:
                                    System.out.println(opNovalida);
                                    break;
                            }
                        } while (op2 != 8);
                        break;
                    case 2: // edit animales
                        do {
                            System.out.println(menuPrincipal[1]);
                            op2 = leeInt(sc);
                            switch (op2) {
                                case 1: //Intr animal
                                    //(String nombre, int edad, String color, String tipoComida, int cantidadComida,
                                    //                              String cuidador, String carEspeciales, String ubicación)

                                    Animal a = new Animal();
                                    System.out.println("Introduce nombre del animal");
                                    a.setNombre(sc.nextLine());
                                    System.out.println("Introduce edad del animal");
                                    a.setEdad(leeInt(sc));
                                    System.out.println("Introduce color del animal");
                                    a.setColor(sc.nextLine());
                                    System.out.println("Introduce el tipo de comida que consume");
                                    a.setTipoComida(sc.nextLine());
                                    System.out.println("Introduce cuantos kilos de comida consume al dia");
                                    a.setCantidadComida(leeInt(sc));
                                    System.out.println("Introduce nombre del cuidador(Ya debe estar registrado)");
                                    a.setCuidador(sc.nextLine());
                                    System.out.println("Introduce caracteristicas especiales");
                                    a.setCarEspeciales(sc.nextLine());
                                    System.out.println("Introduce ubicación");
                                    a.setUbicación(sc.nextLine());

                                    bd.insertaAnimal(a);
                                    break;
                                case 2: // mod animal
                                    do {
                                        System.out.println(menuPrincipal[4]);
                                        op3 = leeInt(sc);
                                        switch (op3) {
                                            case 1: //Buscar animal a mod por nombre
                                                String nombre = sc.nextLine();

                                                System.out.println( bd.consultaAnimal(nombre)+
                                                        "\n¿Es este el animal que quiere modificar? [S]i/[N]o");
                                                if (si_o_no(sc)) {
                                                    System.out.println("Introduzca opcion de campo a modificar.\n"
                                                            + bd.consultaCampos("animales"));
                                                    int op4 = leeInt(sc);
                                                    System.out.println("Introduce nuevo campo:\n");
                                                    String campo = sc.nextLine();

                                                    bd.updateAnimal(nombre, op4, campo);
                                                }
                                                break;
                                            case 2: // buscar por id
                                                int id = leeInt(sc);
                                                System.out.println( bd.consultaAnimal(id)+
                                                        "\n¿Es este el animal que quiere modificar? [S]i/[N]o");
                                                if (si_o_no(sc))

                                                System.out.println(bd.consultaCampos("animales"));
                                                break;
                                            case 3: //lista
                                                System.out.println(bd.consultaAnimalesTodos());
                                                break;
                                            case 4: //volver
                                                break;
                                            default:
                                                System.out.println(opNovalida);
                                                break;
                                        }
                                    } while (op3 != 4);
                                    break;
                                case 3: // borra animal
                                    do {
                                        System.out.println(menuPrincipal[4]);
                                        op3 = leeInt(sc);
                                        switch (op3) {
                                            case 1: //buscar por nombre
                                                String nombre = sc.nextLine();

                                                System.out.println( bd.consultaAnimal(nombre)+
                                                "\n¿Es este el animal que quiere borrar? [S]i/[N]o");
                                                if (si_o_no(sc))
                                                    bd.borraAnimalNombre(nombre);
                                                break;
                                            case 2: // id
                                                int id = leeInt(sc);

                                                System.out.println( bd.consultaAnimal(id)+
                                                        "\n¿Es este el animal que quiere borrar? [S]i/[N]o");
                                                if (si_o_no(sc))
                                                    bd.borraAnimalId(id);
                                                break;
                                            case 3: //lista
                                                System.out.println(bd.consultaAnimalesTodos());
                                                break;
                                            case 4: //volver
                                                break;
                                            default:
                                                System.out.println(opNovalida);
                                                break;
                                        }
                                    } while (op3 != 4);
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
                        do {
                            System.out.println(menuPrincipal[2]);
                            op2 = leeInt(sc);
                            switch (op2) {
                                case 1: //Intr cui
                                    Cuidador cui = new Cuidador();

                                    System.out.println("Introduce nombre del cuidador");
                                    cui.setNombre(sc.nextLine());
                                    System.out.println("¿Es veterinario este cuidador? ([S]i/[N]o)");
                                    if (si_o_no(sc))
                                        cui.setVeterinario(true);
                                    else
                                        cui.setVeterinario(false);
                                    bd.insertaCuidador(cui);
                                    break;
                                case 2: // mod cui
                                    do {
                                        System.out.println(menuPrincipal[5]);
                                        op3 = leeInt(sc);
                                        switch (op3) {
                                            case 1: //buscar por nombre
                                                break;
                                            case 2: //lista
                                                break;
                                            case 3: //volver
                                                break;
                                            default:
                                                System.out.println(opNovalida);
                                                break;
                                        }
                                    } while (op3 != 3);
                                    break;
                                case 3: // borra cui
                                    do {
                                        System.out.println(menuPrincipal[5]);
                                        op3 = leeInt(sc);
                                        switch (op3) {
                                            case 1: //buscar por nombre
                                                break;
                                            case 2: //lista
                                                break;
                                            case 3: //volver
                                                break;
                                            default:
                                                System.out.println(opNovalida);
                                                break;
                                        }
                                    } while (op3 != 3);
                                    break;
                                case 4: // volver
                                    break;
                                default:
                                    System.out.println(opNovalida);
                                    break;
                            }
                        } while (op2 != 4);

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
