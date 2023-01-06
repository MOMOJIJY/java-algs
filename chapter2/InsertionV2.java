import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class InsertionV2
{
	public static void sort(Comparable[] a)
	{
		int N = a.length;
		int min = 0;
		for (int i = 1; i < N; i++)
		{
			if (less(a[i], a[min])) min = i;
		}
		Comparable t = a[0];
		a[0] = a[min];
		a[min] = t;

		for (int i = 1; i < N; i++)
		{
			for (int j = i; less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
		}
	}

	private static boolean less(Comparable v, Comparable w)
	{ return v.compareTo(w) < 0; }

	private static void exch(Comparable[] a, int i, int j)
	{ Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

	private static void show(Comparable[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}

	public static boolean isSorted(Comparable[] a)
	{
		for (int i = 1; i < a.length; i++)
		{
			if (less(a[i], a[i-1])) return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}