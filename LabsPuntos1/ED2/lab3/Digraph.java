//package Graph;

import java.util.ArrayList;

/**
 * Clase abstracta para la implementacion de grafos dirigidos En la pareja
 * (Pair): first = destino (es un vertice) second = peso
 *
 * @author Mauricio Toro, Mateo Agudelo
 */
public abstract class Digraph {

    protected int size;

    public Digraph(int vertices) {
        size = vertices;
    }

    public abstract void addArc(int source, int destination, int weight);

    public abstract ArrayList<Integer> getSuccessors(int vertex);

    /**
     * El peso de una arista entre dos vertices
     *
     * @param source
     * @param destination
     * @return peso de la arista
     *
     */
    public abstract int getWeight(int source, int destination);

    /**
     * numero de sucesores de un vertices
     *
     * @param curr vertice a hallar los sucesores
     * @return numero de sucesores
     *
     */
    public abstract int getSize(int curr);

    /**
     * Obtiene un sucesor especifico
     *
     * @param curr vertice al cual se le sacará un sucesor especifico
     * @param i posicion de la que se quiere sacar el sucesor
     * @return sucesor
     *
     */
    public abstract int getSuc(int curr, int i);

    /**
     * Cantida de vertices que un grafo tiene
 *
     */
    public abstract int size();
}
