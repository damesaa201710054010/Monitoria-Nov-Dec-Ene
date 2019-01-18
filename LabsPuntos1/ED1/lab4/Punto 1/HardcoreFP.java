/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hardcorefp;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Clase que contiene las estructuras principales que se implementaron
 * Consideraciones: 
 * anietog1 es nuestro camarada del grupo de competitiva
 *
 * @author evinracher 
 * @author anietog1
 * @author Daniel Mesa
 */
public class HardcoreFP {

    private static Carpeta home;
    public static TreeMap arbol;

    /**
     * Metodo constructor, el cual instancia un objeto de la clase HFPRead, el 
     * cual lee un archivo de texto que contiene la estructura a procesar y luego 
     * genera un objeto de la clase TreeMap
     * @throws IOException Porque usamos BufferedReader
     */
    public HardcoreFP() throws IOException {
        HFPRead p = new HFPRead("juegos.txt");
        home = p.load();
        //tree(home, "-");
        arbol = new TreeMap();
        generar(home);
    }

    /**
     * Permite obtender el directorio raiz
     * @return raiz
     */
    public String getHome() {
        return home.getNombre();
    }

    /**
     * Metodo para generar el codigo que dibuja el arbol en la pagina: 
     * http://www.webgraphviz.com/
     * Este metodo tiene algunas inconsistencias
     */
    public void dibujarArbol() {
        System.out.println("/* Para visualizar el arbol de busqueda,"
                + "ingrese el siguiente codigo en: http://www.webgraphviz.com/ */");
        System.out.println("digraph arbolito {");
        System.out.println("size=\"6,6\";");
        System.out.println("node [color=aquamarine, style=filled];");
        soutP();
        System.out.println("}");
    }

    private void soutP() {
        //   sout(arbol);
        subtree(arbol);
    }

    private void subtree(TreeMap start) {
        Set list = start.keySet();
        Iterator it = list.iterator();
        String actual = "";
        if (it.hasNext()) {
            actual = (String) it.next();
        }
        subTree2(it, actual);
    }

    private void subTree2(Iterator it, String act) {
        String actual = act;
        if (it.hasNext()) {
            int cont = 0;
            String[] nodos;
            nodos = new String[2];
            while (it.hasNext() && cont < 2) {
                nodos[cont] = (String) it.next();
                cont++;
            }
            System.out.println("\"" + actual + "\" -> \"" + nodos[0] + "\";");
            subTree2(it, nodos[1]);
            System.out.println("\"" + actual + "\" -> \"" + nodos[1] + "\";");
            subTree2(it, nodos[0]);
        }
    }

    private void tree(Carpeta start, String bars) {
        LinkedList<AbstractClass> list = start.getList();

        list.forEach((f) -> {
            System.out.println(bars + f.getNombre() + " Padre: " + f.getPadre().getNombre());
            if (f instanceof Carpeta) {
                tree((Carpeta) f, bars + "-");
            }
        });
    }

    private void generar(Carpeta start) {
        LinkedList<AbstractClass> list = start.getList();
        list.forEach((AbstractClass f) -> {
            if (arbol.get(f.getNombre()) == null && !(arbol.get(f.getNombre()) instanceof LinkedList)) {
                arbol.put(f.getNombre(), f);
                if (f instanceof Carpeta) {
                    generar((Carpeta) f);
                }
            } else if (arbol.get(f.getNombre()) instanceof LinkedList) {

                LinkedList temporal = (LinkedList) arbol.get(f.getNombre());
                temporal.add(f);
                arbol.put(f.getNombre(), temporal);
                if (f instanceof Carpeta) {
                    generar((Carpeta) f);
                }
            } else {
                LinkedList ella = new LinkedList();
                AbstractClass temporalA = (AbstractClass) arbol.get(f.getNombre());
                ella.add(temporalA);
                ella.add(f);
                arbol.put(f.getNombre(), ella);
                if (f instanceof Carpeta) {
                    generar((Carpeta) f);
                }
            }
        });
    }
    
    /**
     * Metodo para listar, en forma de arbol, los archivos y carpetas del TreeMap 
     */
    public void listar() {
        listarAux(home, "-");
    }

    private void listarAux(Carpeta start, String space) {
        LinkedList<AbstractClass> list = start.getList();

        list.forEach((AbstractClass f) -> {
            System.out.println(space + f.getNombre());//Esto pa despues: + " Padre: "+f.getPadre().getNombre()

            if (f instanceof Carpeta) {
                listarAux((Carpeta) f, space + "-");
            }
        });
    }

    /**
     * Metodo para saber si un archivo o carpeta existe
     * @param nombre del archivo a busar
     * @return true si el archivo existe o false en caso contrario
     */
    public boolean buscar(String nombre) {
        return arbol.containsKey(nombre);
    }

    /**
     * Metodo que busca y devuelve un objeto del treeMap
     * @param nombre del Archivo a buscar
     * @return El objeto buscado
     */
    public Object buscarR(String nombre) {
        return arbol.get(nombre);
    }

    /**
     * Método que busca e imprime los datos de un archivo o carpeta
     * @param nombre del archivo o carpeta a imprimir sus datos
     */
    public void buscarIm(String nombre) {
        Object r = arbol.get(nombre);
        if (r instanceof LinkedList) {
            System.out.println("Existen varios archivos o carpetas con ese nombre. "
                    + "Listando todos los que se han encontrado:");
            LinkedList<AbstractClass> temporal = (LinkedList) arbol.get(nombre);
            temporal.forEach((AbstractClass f) -> {
                System.out.println("Nombre: " + f.getNombre() + "\nCarpeta contenedora: " + f.getPadre().getNombre());
            });
        } else if(r instanceof AbstractClass ){
            AbstractClass tem = (AbstractClass) r;
            System.out.println("Nombre: " + tem.getNombre() + "\nCarpeta contenedora: " + tem.getPadre().getNombre());
        }else{
            System.out.println("Error en el nombre del archivo o carpeta");
        }
    }
    /**
     * Metodo para obtener la ruta de un archivo o una carpet
     * @param nombre del archivo o la carpeta de la cual se quiere saber 
     * la ruta
     */
    public void obtRutaC(String nombre) {
        Object resul = buscarR(nombre);
        if (resul instanceof LinkedList) {

            System.out.println("Existen varios archivos o carpetas con ese nombre. "
                    + "Listando todos los que se han encontrado:");
            for (int i = 0; i < ((LinkedList) resul).size(); ++i) {
                obtRuta((AbstractClass) ((LinkedList) resul).get(i));
            }
        } else if(resul instanceof AbstractClass) {
            obtRuta((AbstractClass) resul);
        }else{
            System.out.println("Error en el nombre del archivo o carpeta");
        }
    }

    private void obtRuta(AbstractClass resul) {
        String ruta = "/";
        String impr = obtRutaAux(resul, ruta, 0);
        if (!impr.equals("")) {
            System.out.println("Ruta: " + impr);
        } else {
            System.out.println("No existe el archivo para la ruta");
        }
    }

    
    /**
     * Permite obtener la ruta de un archivo o carpeta
     * @param archivo  Archivo a obtener ruta
     * @param ruta '/' para la estructura
     * @param cont variable para debug
     */
    public static String obtRutaAux(AbstractClass archivo, String ruta, int cont) {

        AbstractClass padre;
        if (archivo != null) {
            padre = archivo.getPadre();
            cont++;
            return obtRutaAux(padre, "/", cont) + ruta + archivo.getNombre();
        } else {

            return "";
        }
    }
}