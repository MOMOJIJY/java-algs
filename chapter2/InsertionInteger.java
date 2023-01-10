import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class InsertionInteger
{
	public static void sort(int[] a)
	{
		int N = a.length;
		for (int i = 1; i < N; i++)
		{
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
		}
	}

	private static boolean less(int v, int w)
	{ return v < w; }

	private static void exch(int[] a, int i, int j)
	{ int t = a[i]; a[i] = a[j]; a[j] = t; }

	private static void show(int[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}

	public static boolean isSorted(int[] a)
	{
		for (int i = 1; i < a.length; i++)
		{
			if (less(a[i], a[i-1])) return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		int[] a = In.readInts();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
