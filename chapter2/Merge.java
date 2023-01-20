import edu.princeton.cs.algs4.StdOut;

public class Merge {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void mergeV2(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[a.length];
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

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[a.length];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
    }

    public static void main(String[] args) {
        Integer[] a = { 1, 3, 5, 8, 2, 4, 6, 7 };
        for (int elem : a)
            StdOut.print(elem + " ");
        StdOut.println();

        merge(a, 0, 3, 7);
        for (int elem : a)
            StdOut.print(elem + " ");
        StdOut.println();

        Integer[] b = { 1, 3, 5, 8, 2, 4, 6, 7 };
        mergeV2(b, 0, 3, 7);
        for (int elem : b)
            StdOut.print(elem + " ");

    }
}