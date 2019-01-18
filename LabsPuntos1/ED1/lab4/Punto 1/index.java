package hardcorefp;

import java.io.IOException;

/**
 * Clase para ejecutar el proyecto desarrollado para la materia de Estructuras
 * de datos y algoritmos I
 * Docente: Mauricio Toro
 * @author Kevin Arley Parra(evinracher)
 * @author Daniel Mesa
 */
public class index {
    
    public static void main(String[] args) throws IOException{
        consola Probando = new consola();  
        try {
            Probando.consola();
        } catch (Exception e) {
            System.out.println("Falló");
        }
    }
}
