package hardcorefp;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Clase para encapsular las carpetas
 *
 * @author evinracher
 * @author Daniel Mesa
 */
public class Carpeta extends AbstractClass {

    public String space = "";
    private LinkedList<AbstractClass> archivos;
    private String lastCarp; //<- no se usa

    /**
     * Metodo para crear una carpeta en disco
     *
     * @param file
     * @param space
     */
    public Carpeta(File file, String space) {
        super(file);
        archivos = new LinkedList<>();
        generadorLinked(file.listFiles());
        this.space = space;
    }

    /**
     * Este metodo ya no se usará
     *
     * @deprecated para versiones con el sistema antiguo
     * @param file nombre del archivo
     * @param space espaciador para las ramas
     */
    public Carpeta(String file, String space) {
        super(file);
        archivos = new LinkedList<>();
        generarLinkedArch(file);
        this.space = space;
    }

    /**
     * Metodo para crear una carpeta con lectura de archivo .txt
     *
     * @param file nombre del archivo
     * @param padre Carpeta contenedora
     */
    public Carpeta(String file, Carpeta padre) {
        super(file, padre);
        archivos = new LinkedList<>();
    }

    /**
     * Metodo constructor del la carpeta home
     *
     * @param file El archivo que representa la carpeta raiz, esta carpeta
     * tendra el valor nulo en la variable padre
     */
    public Carpeta(String file) {
        super(file, null);
        archivos = new LinkedList<>();
    }

    /**
     * Para generar la linked list desde el disco, no se usa en la estrutura
     * principal
     *
     * @param file
     */
    public void generadorLinked(File[] file) {
        for (int x = 0; x < file.length; x++) {
            if (file[x].isFile()) {
                archivos.add(new Archivo(file[x]));
            } else if (file[x].isDirectory()) {
                archivos.add(new Carpeta(file[x], space));
            }
        }
    }

    /**
     * Generar la linked list usando el metodo de lectura del archivo hardcore
     * final project (y)
     *
     * @param Files
     */
    public void generadorFinal(Stack Files) {
        while (!Files.isEmpty()) {
            archivos.addFirst((AbstractClass) Files.pop());
        }
    }

    public LinkedList<AbstractClass> getList() {
        return archivos;
    }

    /**
     * Metodo viejo, servia pero es obsoleto y faltaba la parte de las carpetas,
     * asi que está incompleto
     *
     * @param archivo
     */
    private void generarLinkedArch(String archivo) {
        int cont = 0;
        for (int i = 0; i < archivo.length(); i++) {
            if (archivo.charAt(i) == '<') {
                if (archivo.charAt(i + 1) == 'A') {
                    String nombre = "";
                    int j = i + 6;
                    for (; j < archivo.length() && archivo.charAt(j) != '\n'; ++j) {
                        nombre = nombre + archivo.charAt(j);
                    }
                    i = j;
                    Archivo n = new Archivo(nombre);
                    archivos.add(n);
                    System.out.println(archivos.get(cont).getNombre());
                    cont++;
                    nombre = "";
                } else if (archivo.charAt(i + 1) == 'C') {
                    String nombre = "";
                    int j = i + 6;
                    for (; j < archivo.length() && archivo.charAt(j) != '\n'; ++j) {
                        nombre = nombre + archivo.charAt(j);
                    }
                    i = j;
                    Carpeta carp = new Carpeta(nombre, space);
                    archivos.add(carp);
                    System.out.println(archivos.get(cont).getNombre());
                    cont++;
                    nombre = "";
                }
            }
        }
    }

    /**
     * Lista los elementos almacenados en una carpeta
     */
    public void listarSimple() {
        for (int i = 0; i < archivos.size(); ++i) {
            System.out.println(archivos.get(i).getNombre());
        }
    }

    /**
     * Lista en forma de arbol los elementos almacenados en una carpeta
     *
     * @param space Espacion para las ramas
     */
    public void listar(String space) {
        for (int x = 0; x < archivos.size(); ++x) {
            if (archivos.get(x) instanceof Archivo) {
                System.out.println(space + "Archivo: " + archivos.get(x).getNombre());
            } else if (archivos.get(x) instanceof Carpeta) {
                System.out.println(space + "Carpeta: " + archivos.get(x).getNombre());
                String esp = space + "-";
                Carpeta p = (Carpeta) archivos.get(x);
                p.listar(esp);
            }
        }
    }

    /**
     * Lista los elementos almacenados en una carpeta indicando si son carpetas
     * o archivos
     */
    public void listarSinR() {
        for (int x = 0; x < archivos.size(); ++x) {
            if (archivos.get(x) instanceof Archivo) {
                System.out.println(space + "Archivo: " + archivos.get(x).getNombre());
            } else if (archivos.get(x) instanceof Carpeta) {
                System.out.println(space + "Carpeta: " + archivos.get(x).getNombre());
            }
        }
    }

    public boolean eliminar(String name) {
        for (int x = 0; x < archivos.size(); ++x) {
            if (archivos.get(x) instanceof Archivo) {
                if (archivos.get(x).getNombre().equals(name)) {
                    System.out.println("Eliminando el archvio en:\n " + archivos.get(x).getFile());
                    File aBorrar = archivos.get(x).getFile();
                    aBorrar.delete();
                    archivos.remove(x);
                    return true;
                }
            } else if (archivos.get(x) instanceof Carpeta) {
                if (archivos.get(x).getNombre().equals(name)) {
                    System.out.println("Eliminando el archvio en:\n " + archivos.get(x).getFile());
                    File aBorrar = archivos.get(x).getFile();
                    aBorrar.delete();
                    archivos.remove(x);
                    return true;
                } else {
                    Carpeta p = (Carpeta) archivos.get(x);
                    p.eliminar(name);
                }
            }

        }
        return false;
    }

    public String buscar(String name) {
        String re = "";
        for (int x = 0; x < archivos.size(); ++x) {
            if (archivos.get(x) instanceof Archivo) {
                if (archivos.get(x).getNombre().equals(name)) {
                    lastCarp = archivos.get(x).getFile().getParent();
                    re = ("El archvio existe:\n " + archivos.get(x).getFile());
                }
            } else if (archivos.get(x) instanceof Carpeta) {
                if (archivos.get(x).getNombre().equals(name)) {
                    lastCarp = archivos.get(x).getFile().toString();
                    re = ("El archvio existe:\n " + archivos.get(x).getFile());
                    break;

                } else {
                    Carpeta p = (Carpeta) archivos.get(x);
                    re = p.buscar(name);
                }
            }

        }
        return re;
    }

}
