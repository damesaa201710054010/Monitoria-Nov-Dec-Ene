//package Graph;

import java.util.ArrayList;

/**
 * Implementacion de un grafo dirigido usando matrices de adyacencia. Los nodos 
 * que no estan relacionados tiene peso 0
 *
 * @author Mauricio Toro, Mateo Agudelo, <su nombre>
 */
public class DigraphAM extends Digraph {

    private int[][] grafo;

    public DigraphAM(int size) {
        super(size);
        grafo = new int[size][size];
    }

    @Override
    public void addArc(int source, int destination, int weight) {
        grafo[source][destination] = weight;
    }

    @Override
    public ArrayList<Integer> getSuccessors(int vertex) {
        ArrayList<Integer> successors = new ArrayList<Integer>();
        //int k = 0;
        for (int i = 0; i < size; i++) {
            if (grafo[vertex][i] != 0) {
                successors.add(i);
            }
        }

        if (successors.isEmpty()) {
            return null;
        }
        return successors;
    }

    @Override
    public int getWeight(int source, int destination) {
        return grafo[source][destination];
    }

    public void imprimir() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grafo[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*public static void main(String[] args) {
        DigraphAM grafo1 = new DigraphAM(12);
        grafo1.addArc(5, 11, 1);
        grafo1.addArc(7, 8, 1);
        grafo1.addArc(7, 11, 1);
        grafo1.addArc(3, 8, 1);
        grafo1.addArc(3, 10, 1);
        grafo1.addArc(8, 9, 1);
        grafo1.addArc(11, 2, 1);
        grafo1.addArc(11, 9, 1);
        grafo1.addArc(11, 10, 1);

        grafo1.imprimir();

    }*/

    @Override
    public int getSize(int curr) {
        return grafo[curr].length;
    }
    
    /**
     * Numero de vertices
     * @return 
     **/
    public int size(){
        return size;
    }

    @Override
    public int getSuc(int curr, int i) {
        if(grafo[curr][i] != 0){
            return i;
        }
        //Retorna -1 por qué no están relacionados
        return -1;
    }

}
