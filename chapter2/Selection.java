import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class Selection
{
	public static void sort(Comparable[] a)
	{
		double xMax = a.length;
		double yMax = 0;
		for (int i = 0; i < a.length; i++)
		{
			if (less(yMax, a[i])) yMax = Double.parseDouble(a[i].toString());
		}
		double drawTime = 0;
		yMax += 8;
		StdDraw.setXscale(-1, xMax);
		StdDraw.setYscale(-1, yMax * 1.5 * a.length);
		draw(a, yMax * drawTime);

		int N = a.length;
		for (int i = 0; i < N-1; i++)
		{
			int min = i;
			for (int j = i+1; j < N; j++)
			{
				if (less(a[j], a[min])) min = j;
			}
			exch(a, i, min);
			drawTime++;
			draw(a, yMax * drawTime);
		}
	}

	public static void draw(Comparable[] a, double y)
	{
		double xWidth = 0.5;
		for (int i = 0; i < a.length; i++)
		{
			StdDraw.filledRectangle(Double.parseDouble(""+i), y, xWidth, Double.parseDouble(a[i].toString()));
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
		// String[] a = In.readStrings();
		Double[] a = {2.0, 3.0, 1.0, 4.0, 5.0, 9.0, 8.0, 5.0};
		sort(a);
		assert isSorted(a);
		show(a);
	}
}