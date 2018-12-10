public class Solucion
{
    public static int veces(int n)
    {
	if(n <= 2) return 1;
	return veces(n-1) + veces(n-2);
    }

    public static void main(String[] args)
    {
	System.out.println(veces(7));
	
    }
}
