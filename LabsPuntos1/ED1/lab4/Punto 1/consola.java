/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hardcorefp;

import static hardcorefp.HardcoreFP.obtRutaAux;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Clase consola que nos permite tener una interfaz para las operaciones con la
 * estructura de datos que hemos implementado, la cual podria tratarse como dos
 * estructuras, por una parte se tiene un treeMap el cual se usa para la
 * busqueda de los archivos, y por otra se tiene una implementacion en la cual
 * hay dos clases, Archivo y Carpeta, que heredan a su vez de una clase llamada
 * AbstractClass
 *
 * @author evinracher
 * @author Daniel Mesa
 */
public class consola {

    private String sDirectorio;
    private String OS;
    private String OSArch;
    private String OSversion;
    private String OSdataModel;
    private HardcoreFP structure;
    private BufferedReader teclado;

    /**
     * Metodo constructor, el cual inicializa la consola y crea un objeto de la
     * clase HardcoreFP
     *
     * @throws IOException Porque usamos BufferedReader
     */
    public consola() throws IOException {
        definiendoSistema();
        loading();
        structure = new HardcoreFP();
        sDirectorio = structure.getHome();
    }

    /**
     * Metodo en el que esta el funcionamiento principal de la consola, la cual
     * tiene estas funciones: - ls: Lista en forma de arbol los archivos y las
     * carpetas - see: Muestra la ruta de un archivo o carpeta - see dir:
     * Permite funciones para realizar dentro de una determinada carpeta -
     * buscar: permite saber si un archivo o carpeta existe - imprimir: Muestra
     * si un archivo o carpeta existe - dibu: Muestra un codigo para la pagina:
     * http://www.webgraphviz.com/ - exit: Cierra la consola - help: Muestra
     * esta descripcion
     *
     * @throws IOException
     */
    public void consola() throws IOException {
        teclado = new BufferedReader(new InputStreamReader(System.in));
        String comando = teclado.readLine();
        while (!(comando.equals("exit"))) {
            if (comando.equals("ls")) {
                System.out.println("\nListando elementos del directorio: " + sDirectorio);
                structure.listar();
            }

            if (comando.equals("see")) {
                System.out.println("Ingrese el nombre del archivo o carpeta a "
                        + "buscar ruta");
                String name = teclado.readLine();
                structure.obtRutaC(name);//cambiar lo del parentesis por name
            }

            if (comando.equals("buscar")) {
                System.out.println("Ingrese el nombre del archivo o carpeta a "
                        + "buscar");
                String name = teclado.readLine();
                boolean resultado = structure.buscar(name);
                if (!resultado) {
                    System.out.println("El archivo no existe");
                } else {
                    System.out.println(resultado);
                }
            }

            if (comando.equals("imprimir")) {
                System.out.println("Ingrese el nombre del archivo o carpeta a "
                        + "imprimir datos");

                String name = teclado.readLine();
                structure.buscarIm(name);
            }
            /**
             * if (comando.equals("rm")) { System.out.println("Ingrese el nombre
             * del archivo o carpeta a " + "eliminar"); String name = ""; //=
             * teclado.readLine(); if (!prueba.eliminar(name)) {
             * System.out.println("El archvio no existe\n"); } }
             */

            if (comando.equals("dibu")) {
                structure.dibujarArbol();
            }

            if (comando.equals("see dir")) {
                System.out.println("Ingrese el nombre del carpeta: ");

                String name = teclado.readLine();
                Object carp = structure.buscarR(name);
                if (carp != null) {
                    if (carp instanceof Carpeta) {
                        System.out.println("Ingrese la opcion que quiere realizar dentro de la carpeta\n"
                                + "help para ayuda, exit para volver al directorio principal");
                        consolaCarp(((Carpeta) carp).getNombre(), (Carpeta) carp);
                    } else if (carp instanceof LinkedList) {
                        int cont = 0;
                        Carpeta defec = null;
                        for (int i = 0; i < ((LinkedList) carp).size(); ++i) {

                            if (((LinkedList) carp).get(i) instanceof Carpeta) {
                                cont++;

                                defec = (Carpeta) ((LinkedList) carp).get(i);
                            }
                            if (cont > 2) {
                                break;
                            }

                        }

                        if (cont == 1) {
                            System.out.println("Ingrese la opcion que quiere realizar dentro de la carpeta\n"
                                    + "help para ayuda, exit para volver al directorio principal");

                            consolaCarp(defec.getNombre(), defec);

                        } else {
                            System.out.println("Se encontraron multiples carpetas con este nombre dentro del sistema"
                                    + ", por favor elija una ingresando el numero que la acompaña");
                            int id = 0;
                            for (int i = 0; i < ((LinkedList) carp).size(); ++i) {
                                Object obj = ((LinkedList) carp).get(i);
                                if (obj instanceof Carpeta) {
                                    String ruta = "/";
                                    String impr = obtRutaAux((AbstractClass) obj, ruta, 0);
                                    System.out.println(i + " - " + impr);
                                    id++;
                                }
                            }
                            int resul = -1;

                            while (resul == -1) {
                                resul = Integer.parseInt(teclado.readLine());
                                if (resul > id) {
                                    System.out.println("Opcion no valida");
                                    resul = -1;
                                } else {
                                    Carpeta busqueda = (Carpeta) ((LinkedList) carp).get(resul);
                                    System.out.println("Ingrese la opcion que quiere realizar dentro de la carpeta\n"
                                            + "help para ayuda, exit para volver al directorio principal");

                                    consolaCarp(busqueda.getNombre(), busqueda);
                                }
                            }
                        }
                    }

                } else {
                    System.out.println("La carpeta no existe");
                }
            }

            if (comando.equals("help")) {
                System.out.println("\nFunciones de la consola:\n"
                        + "      - ls: Lista en forma de arbol los archivos y las carpetas\n"
                        + "      - see: Muestra la ruta de un archivo o carpeta HELEP\n"
                        + "      - buscar: permite saber si un archivo o carpeta existe\n"
                        + "      - imprimir: Muestra si un archivo o carpeta existe\n"
                        + "      - dibu: Muestra un codigo para la pagina: http://www.webgraphviz.com/\n"
                        + "      - exit: Cierra la consola\n"
                        + "      - help: Muestra esta descripcion");
            }
            //Limpiar bufer
            comando = teclado.readLine();
        }
        System.out.println("logout");
    }

    private void loading() {
        System.out.println("BIENVENIDO\nConsola inspirada en nuestra terminal favorita\n"
                + "Datos del sistema:\n"
                + "Sistema Operativo: " + OS
                + "\nArquitectura: " + OSArch
                + "\nVersion: " + OSversion
                + "\nData Model: " + OSdataModel);
    }

    private void consolaCarp(String name, Carpeta carp) throws IOException {
        String comando = teclado.readLine();
        while (!(comando.equals("exit"))) {
            if (comando.equals("ls")) {
                System.out.println("\nListando elementos de la carpeta: " + name);
                carp.listar("");
            }

            if (comando.equals("ls -l")) {
                System.out.println("\nListando elementos de la carpeta: " + name);
                carp.listarSimple();
            }

            if (comando.equals("ls -a")) {
                System.out.println("\nListando elementos de la carpeta: " + name);
                carp.listarSinR();
            }
            if (comando.equals("buscar")) {
                System.out.println("Ingrese el nombre del archivo o carpeta a "
                        + "buscar");
                String nombre = teclado.readLine();
                String resultado = carp.buscar(nombre);
                if (resultado == null) {
                    System.out.println("El archivo no existe");
                } else {
                    System.out.println("El archivo: " + resultado + " se encuentra dentro de la carpeta");
                }
            }

            if (comando.equals("help")) {
                System.out.println("\nFunciones de la consola:\n"
                        + "      - ls: Lista en forma de arbol los archivos y las carpetas\n"
                        + "      - ls -l: Lista simplemente los archivos y las carpetas\n"
                        + "      - ls -a: Lista simplemente los archivos y las carpetas indicando cuales son carpetas"
                        + "o archivos\n"
                        + "      - buscar: permite saber si un archivo o carpeta existe\n"
                        + "      - imprimir: Muestra si un archivo o carpeta existe\n"
                        + "      - exit: Vuelve a la raiz\n"
                        + "      - help: Muestra esta descripcion");
            }
            comando = teclado.readLine();
        }
        System.out.println("Home: " + sDirectorio);
    }

    private void definiendoSistema() {
        OS = System.getProperty("os.name").toLowerCase();
        OSArch = System.getProperty("os.arch").toLowerCase();
        OSversion = System.getProperty("os.version").toLowerCase();
        OSdataModel = System.getProperty("sun.arch.data.model");

        /**
         * Posible implementación con carpetas del sistema if
         * (OS.equals("linux")) { sDirectorio = "/home/evinracher/prueba"; }
         * else if (OS.substring(0, 7).equals("windows")) { sDirectorio =
         * "C:\\Prueba"; } else if (OS.equals("mac")) { System.out.println("No
         * tenemos mac :c"); }
         *
         * }
         */
    }

}
