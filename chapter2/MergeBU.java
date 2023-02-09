import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
    private static Comparable[] aux;

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

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        for(int k = lo; k <= mid; k++)
        {
            aux[k] = a[k];
        }
        for (int k = hi; k > mid; k--) {
            aux[hi - k + mid + 1] = a[k];
        }
        int i = lo, j = hi, k = lo;
        while (i <= j) {
            if (less(aux[i], aux[j])) {
                a[k++] = aux[i++];
            } else {
                a[k++] = aux[j--];
            }
        }
    }


    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        int N = a.length;
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a, lo, lo + sz - 1, Math.min(N-1, lo + sz + sz -1));
    }

    public static void main(String[] args)
    {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

