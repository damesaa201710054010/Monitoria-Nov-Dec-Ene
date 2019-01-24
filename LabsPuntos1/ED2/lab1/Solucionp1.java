/**
 * Esta clase debe de contener la solucion al problema planteado en el punto 1
 * del laboratorio#1 del curso de estructura de datos y algoritmos II
 * @author Daniel Mesa, Mauricio Toro
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; //por teclado
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import javafx.util.*;
public class Solucionp1
{
    long id = 0;
    long id2 = 0;
    double distancia = 0;
    String line = "";
    /**
     * El metodo debe de leer el archivo y construir la estrcutura de datos con el mapa
     *
     */
    HashMap <Long, LinkedList<Pair<Long, Double>>> grafo = new
	HashMap <Long, LinkedList<Pair<Long, Double>>>();
    public void makeMap() throws IOException, FileNotFoundException
    {
	FileReader archivo =  new FileReader("medellin_colombia-grande.txt");
	BufferedReader lector =  new BufferedReader(archivo);
	//System.out.println(separar[0]);
	line = lector.readLine();
	String ids [] = line.split(" ");
	line = lector.readLine();
	ids = line.split(" ");
	//System.out.println(ids[2]);
	while(!ids[0].equals("Arcos."))
	    {
		if(ids[0].length() > 0)
		    {
			//	       	System.out.println(ids[0]+"t");
			id = Long.valueOf(ids[0]);
			grafo.put(id, new LinkedList<>());
		    }
		line = lector.readLine();
		ids = line.split(" ");
		//System.out.println(ids[0]);
	    }
	//	System.out.println("out");
	id = 0;
	id2 = 0;
	distancia = 0;
	line = lector.readLine();
	line = lector.readLine();
	while(line  != null)
	    {
		ids = line.split(" ");
		id= Long.valueOf(ids[0]);
		//System.out.println(id+" ");
		id2 = Long.valueOf(ids[1]);
		//System.out.print(id2+" ");
		distancia = Double.parseDouble(ids[2]);
		//System.out.println(distancia);
		System.out.println(id);
		Pair pareja = new Pair(id2, distancia);
	        grafo.get(id).add(pareja);
		//System.out.println();
		line = lector.readLine();
	    }
    }

    /**
     * Metodo principal del programa
     * @param args un array de argumentos
     */
    public static void main(String[] args)
    {
	Solucionp1 solucion = new Solucionp1();
	try{

	    solucion.makeMap();
	}catch (IOException e){
	    System.out.println("Error al leer el archivo");
	}
	System.out.println("check");
    }
}
