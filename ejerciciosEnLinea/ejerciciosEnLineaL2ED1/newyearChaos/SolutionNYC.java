import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SolutionNYC {
    
    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q)
    {
	int count = 0 ;
	int midOfThree = Integer.MAX_VALUE;
        int maxOfThree =  Integer.MAX_VALUE;
        int minOfThree =  Integer.MAX_VALUE ;
        
    
        for(int i=q.length-1 ; i >= 0 ; i--)
	    {
		
		if((q[i]-i) > 3 )
		    {
			System.out.println("Too chaotic");
			return;
		    }
		else
		    {
			if(q[i] > maxOfThree)
			    {
				System.out.println("Too chaotic");
				return;
			    }
			else if(q[i] > midOfThree)
			    { 
				count=count+2;
			    }
			else if(q[i] > minOfThree)
			    { 
				count=count+1;
			    }
                
                
			if(q[i]<minOfThree)
			    {
				maxOfThree=midOfThree;
				midOfThree=minOfThree;
				minOfThree = q[i];
			    }
			else if(q[i]<midOfThree)
			    {
				maxOfThree=midOfThree;
				midOfThree = q[i];
			    }
			else if(q[i]<maxOfThree)
			    {
				maxOfThree = q[i];
			    }
		    }
	    }
        System.out.println(count);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++)
	    {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] q = new int[n];

		String[] qItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++)
		    {
			int qItem = Integer.parseInt(qItems[i]);
			q[i] = qItem;
		    }

		minimumBribes(q);
	    }

        scanner.close();
    }
}