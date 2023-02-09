import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergeUB {
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
        // sort(a, 0, a.length-1);
        sort2(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi)
    {
        if (lo >= hi) return;
        int mid = (hi + lo) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    public static void sortInsert(Comparable[] a, int lo, int hi)
	{
		int N = hi + 1;
		int min = lo;
		for (int i = lo + 1; i < N; i++)
		{
			if (less(a[i], a[min])) min = i;
		}
		Comparable t = a[0];
		a[0] = a[min];
		a[min] = t;

		for (int i = lo + 1; i < N; i++)
		{
			for (int j = i; less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
		}
	}

    // sort2 小数组实用插入排序
    public static void sort2(Comparable[] a, int lo, int hi)
    {
        if ((hi - lo) < 9)
        {
            sortInsert(a, lo, hi);
            return;
        }
        if (lo >= hi) return;
        int mid = (hi + lo) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        if (less(a[mid], a[mid+1])) return;
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args)
    {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
