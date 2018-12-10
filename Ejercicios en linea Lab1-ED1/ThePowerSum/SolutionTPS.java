import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SolutionTPS {

    public static int powerSum(int X, int N)
    {
	return calc(X,N,1);
    }
    public static int calc(int x, int n, int num)
    {
	int numToN= (int) Math.pow(num,n);
	if (numToN > x)
	    {
		return 0;
	    } else if (numToN == x) {
	    return 1;
	} else
	    {
		return (calc(x,n,num+1) + calc(x-numToN,n,num+1));
	    }
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException
    {
	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
	
	int X = scanner.nextInt();
	scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	int N = scanner.nextInt();
	scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	int result = powerSum(X, N);

	bufferedWriter.write(String.valueOf(result));
	bufferedWriter.newLine();

	bufferedWriter.close();

	scanner.close();
    }
}
