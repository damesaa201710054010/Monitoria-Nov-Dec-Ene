import java.util.*;
/**
 * Laboratorio 2 de Estructuras de Datos 1. 
 * Esta clase contiene los algoritmos de InsertionSort y mergeSort, para calcular la suma de los elementos 
 * de un arreglo
 * @author Los algoritmos ArraySum, ArrayMax, InsertionSort y mergeSort fueron tomados del github proporcionado por el preoferor
 * Toro, M (2017) Labpratorio 2 de Estrusturas de Datos (Version 1.0) 
 * https://github.com/mauriciotoro/ST0245-Eafit/tree/master/laboratorios/lab02/codigo/Java/Laboratory1
 * @author: Kevin Arley Parra Henao - Codigo: 201710093010, Daniel Alejandro Mesa Arango - Codigo: 201710054010 
 * @version: 2.0 10/09/2017
 */
public class Solucion {

    /**
     * Ordena de menos a mayor los elementos del arreglo
     * @param A el arreglo para el cual se ordenaran los elementos, el arreglo es de tipo entero
     */
    public static void InsertionSort(int[] A) {
        int temp, j;
        for (int i = 0; i < A.length; i++) {
            j = i;
            while (j > 0 && A[j - 1] > A[j]) {
                temp = A[j];
                A[j] = A[j - 1];
                A[j - 1] = temp;
                j = j - 1;
            }
        }
        
    }

    /**
     * Este metodo hace uso de mergeSort para ordenar un arreglo
     * @param a es el arreglo para el cual se quieren ordenar sus elementos y es de tipo entero
     */
    public static void mergeSort(int[] a) {
        int[] tmp = new int[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }
        
    /**
     * Es un metodo recursivo que divide el problema de ordenamiento
     * @param a arreglo de tipo entero
     * @param tmp una copia del arreglo original es decir de "a" tambien de tipo entero
     * @param left posicion inicial a la izquierda de tipo entero
     * @param right posicion final a la derecha, de tipo entero
     */
    private static void mergeSort(int[] a, int[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    /**
     * Este metodo ó funcion se encarga de ordenar el arreglo por partes y en la copia del original
     * haciendo la operacion mas eficiente
     * @param a arreglo de enteros
     * @param tmp copia del arreglo "a" tambien de enteros
     * @param left posicion inicial mas a la izquierda 
     * @param right una posisicon o longitud es de tipo entero
     * @param rightEnd longitud del arreglo de tipo entero
     */
    private static void merge(int[] a, int[] tmp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if (a[left] <= a[right]) {
                tmp[k++] = a[left++];
            } else {
                tmp[k++] = a[right++];
            }
        }

        while (left <= leftEnd) // Copy rest of first half
        {
            tmp[k++] = a[left++];
        }

        while (right <= rightEnd) // Copy rest of right half
        {
            tmp[k++] = a[right++];
        }

        // Copy tmp back
        for (int i = 0; i < num; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }

    /**
     * Toma los tiempos de ejecucion de un algoritmo
     * @param n es un entero, el cual nos indica el tamaño del problema, en este caso especifico ya que siempre 
     * se habla de arreglos, siendo entonces el tamaño del arreglo
     * @return retorna un entero, el cual es el tiempo en milisegundos que se tardo un algoritmo en especifico en ejecutar
     */
    private static long tomarTiempos(int n)
    {
        int[] a = generarArregloDeTamanoN(n);
        long startTime = System.currentTimeMillis();
        /*System.out.println("Valor : "+  */ArrayMax(a); //reemplazar por la funcion a tomar tiempo
        long estimatedTime = System.currentTimeMillis() - startTime;
        return estimatedTime;
    }

    /**
     * Genera arreglos de tamaño n con numeros aleatorios entre 0 y 4999
     * @param n es un entero, el cual indica el tamaño del arreglo a generar con numeros aleatorios
     * @return un arreglo de enteros de tamaño n, con numeros aleatorios
     */
    private static int[] generarArregloDeTamanoN(int n) 
    {
        int max = 5000;
        int[] array = new int[n];
        Random generator = new Random();
        for (int i = 0; i < n; i++) 
        {
            array[i] = generator.nextInt(max);
        }
        return array;
    }

    /**
     * Metodo principal desde el cual se implementan los metodos de esta clase
     * @param args 
     */
    public static void main(String[] args) {
        for(int i = 100000; i <= 100000000; i = i*10)
	    {
		System.out.println("tiempo: "+i+": " + tomarTiempos(i));
	    }
    }

}
