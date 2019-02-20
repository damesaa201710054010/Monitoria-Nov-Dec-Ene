/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; //por teclado
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import javafx.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Kevin Arley Parra
 * @author Daniel Mesa
 */
public class GraphAlgorithms {

    //Variables
    int maxn;
    int size;
    Digraph g;
    colors[] color;
    /**
     * Metodo para inicializar la componentes
     *
     * @param u Digraph que sera util para las implemetaciones de DFS
     *
     */
    public GraphAlgorithms(Digraph u) {
        maxn = u.size() + 10;
        this.size = u.size();
        color = new colors[maxn];
        g = u;
    }
    public enum colors {
        white, gray, black
    };
    
    /**
     * 2.1: version modificada del DFS para el camino más corto desde el vertice
     * 1 (inicial) hasta el n (final)
     * 
     * 1.6: camino más corto entre dos vertices.
     *
     * @param n numero de vertices
     * @param fin será -1 si es hasta el vertice n
     * @return el costo minimo de ir de n a fin.
     *
     */
    public int caminos(int n, int fin) {
        if(fin == -1)
            fin = g.size();
        ArrayList<Integer> suma = new ArrayList<>();
        int start = 0;
        ArrayList<Integer> camino = new ArrayList<>();
        camino.add(start);
        for (int i = 0; i < n; ++i) {
            color[i] = colors.white;
        }
        for (int i = start; i < n; ++i) {
            if (color[i] == colors.white) {
                DFSCami(i, suma, camino, fin);
            }
        }
        
        System.out.println(" "+ camino.toString());
        int sumaMin = 0;
        for(int i = 0; i < suma.size();++i){
            sumaMin+= suma.get(i);
        }
        return sumaMin;
    }

    public void DFSCami(int u, ArrayList<Integer> suma, ArrayList<Integer> camino, int finish) {
        color[u] = colors.gray;
        for (int i = 0; i < size; ++i) {
            int v = g.getSuc(u, i);
            if (v != -1) {
                if (color[v] == colors.white) {
                    suma.add(g.getWeight(u, v));
                    camino.add(v);
                    DFSCami(v, suma, camino, finish);
                }
                //Hasta el vertice n
                if (v == finish){
                    break;
                }
            }
        }
        color[u] = colors.black;
        
    }

    
    /**
     * Metodo principal del programa
     * @param args un array de argumentos
     */
    public static void main(String[] args)
    {
	DigraphAM grafo = new DigraphAM(4);
	grafo.addArc(0, 1, 7);
	grafo.addArc(0, 3, 6);
	grafo.addArc(0, 2 , 15);
	grafo.addArc(3, 0, 10);
	grafo.addArc(3, 1, 4);
	grafo.addArc(3, 2, 8);
	grafo.addArc(1, 0, 2);
	grafo.addArc(1, 3, 3);
	grafo.addArc(1, 2, 7);
	grafo.addArc(2, 0, 9);
	grafo.addArc(2, 1, 6);
	grafo.addArc(2, 3, 12);
	System.out.println(grafo.getWeight(0,3));
	GraphAlgorithms solucion = new GraphAlgorithms(grafo);
	System.out.println(solucion.caminos(4, 2));
    }
}
