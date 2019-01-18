package hardcorefp;
import java.util.*;
import java.io.*;
//Clase hecha en colaboracion con Agustin <3

/**
 * Clase para la lectura del archivo txt
 * @author evinracer ft Anietog1
 */
public class HFPRead {

    private BufferedReader br;
    private int curr;
    private int currLvl;

    HFPRead(String filename) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(filename));
    }
/**
 * Retorna el home y aplica recursividad para las otras carpetas
 * @return Folder que será el home
 * @throws IOException Porque usamos BufferedReader
 */
    public Carpeta load() throws IOException {
        jumpBlanks();
        //Home no tiene padre :(
        Carpeta home = new Carpeta(br.readLine());
        System.out.println("Home: "+home.getNombre());
        //Folder home = new Folder(br.readLine());
        jumpBlanks();
        calcLvl();
        loadTo(home, currLvl);
        return home;
    }

    
    private boolean isLetter(int a) {
        return (a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z');
    }

    private boolean isDigit(int a) {
        return a >= '0' && a <= '9';
    }

    private void calcLvl() throws IOException {
        if (curr == '[') {//Si el actual es '[', el nivel ya fue calculado
            return;
        }

        currLvl = 0;
        while ((curr = br.read()) != '[') {
            ++currLvl;
        }
    }

    private void loadTo(Carpeta fold, int fLvl) throws IOException {
        Stack<AbstractClass> files = new Stack<>();
        while (!isDigit(curr)) {
            calcLvl();
            if (currLvl == fLvl) {
                files.push(make(fold));
                jumpBlanks();
            } else if (currLvl > fLvl) {
                Archivo temp = (Archivo)files.pop();
                Carpeta child = new Carpeta(temp.getNombre(), fold);
                loadTo(child, currLvl);
                files.push(child);
            } else {//<
                break;
            }
        }

        fold.generadorFinal(files);
    }

    private void jumpBlanks() throws IOException {
        do {
            br.mark(1);
        } while ((curr = br.read()) == ' ' || curr == '\n' || curr == '\t');
        br.reset();
    }

    private Archivo make(Carpeta padre) throws IOException {
        while (br.read() != ']');//Puede buggearse si se llama desde "]"
        jumpBlanks();
        return new Archivo(br.readLine(), padre);
    }
}