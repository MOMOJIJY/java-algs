import java.util.Comparator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    private Comparator c;
    private Key min;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN];
    }

    public MaxPQ(int maxN, Comparator cc)
    {
        pq = (Key[]) new Comparable[maxN];
        c = cc;
    }

    public MaxPQ(Key[] a)
    {
        pq = (Key[]) new Comparable[a.length+1];
        N = a.length;
        for (int i = 0; i < N; i++)
        {
            pq[i+1] = a[i];
        }
        for (int i = N/2; i > 0; i--)
        {
            sink(i);
        }
    }

    public MaxPQ(Key[] a, Comparator cc)
    {
        c = cc;
        pq = (Key[]) new Comparable[a.length+1];
        N = a.length;
        for (int i = 0; i < N; i++)
        {
            pq[i+1] = a[i];
        }
        for (int i = N/2; i > 0; i--)
        {
            sink(i);
        }
    }


    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        if (min != null) {
            min = v;
        } else if (min.compareTo(v) < 0) {
            min = v;
        }
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        if (N == 1)
            return pq[N--];
        Key v = pq[1];
        if (v == min) {
            min = null;
        }
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return v;
    }

    public Key min() {
        return  min;
    }

    private boolean less(int i, int j) {
        if (c != null) {
            return c.compare(pq[i], pq[j]) < 0;
        }
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args)
    {
        int M = Integer.parseInt(args[0]);
        Transaction[] a = new Transaction[M];
        int i = 0;
        while (StdIn.hasNextLine())
        {
            a[i++] = new Transaction(StdIn.readLine());
        }
        MaxPQ<Transaction> pq = new MaxPQ<Transaction>(a, new Transaction.WhoOrder());
        StdOut.println(pq.delMax());
        StdOut.println(pq.min());
    }
}
