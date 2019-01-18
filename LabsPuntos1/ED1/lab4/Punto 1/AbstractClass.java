/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hardcorefp;

import java.io.File;

/**
 * Clase abstracta que usamos para que las clases Archivo y Carpeta extiendan de
 * esta
 *
 * @author evinracher
 * @author Daniel Mesa
 */
public abstract class AbstractClass {

    private File identificador;
    private String nombre;
    private Carpeta padre;

    /**
     * Metodo constructor, este metodo no es usado en la estructura principal
     *
     * @param file Archivo a almacenar en la clase
     */
    public AbstractClass(File file) {
        this.identificador = file;
    }

    /**
     * Metodo constructor, que crea un archivo con el nombre que le ingresen,
     * este metodo no se usa en la estructura principal
     *
     * @param nombre Del archivo a crear
     */
    public AbstractClass(String nombre) {
        File archivo = new File("./" + nombre);
        this.identificador = archivo;
    }

    /**
     * Metodo constructor que se usa en la estructura principal
     *
     * @param nombre Del archivo a crear
     * @param padre De la carpeta contenedora
     */
    public AbstractClass(String nombre, Carpeta padre) {
        File archivo = new File("./" + nombre);
        this.identificador = archivo;
        this.padre = padre;
    }

    /**
     * Permite obtener el nombre del archivo o carpeta
     *
     * @return El nombre del archivo o carpeta
     */
    public String getNombre() {
        return identificador.getName();
    }

    /**
     * Permite obtener la carpeta contenedora del archivo o carpeta
     *
     * @return La carpeta contenedora del archivo o carpeta
     */
    public Carpeta getPadre() {
        return this.padre;
    }

    public File getFile() {
        return this.identificador;
    }

}
