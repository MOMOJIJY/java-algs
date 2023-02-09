import java.util.Comparator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class MaxPQWithoutExchange<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    private Comparator c;

    public MaxPQWithoutExchange(int maxN) {
        pq = (Key[]) new Comparable[maxN];
    }

    public MaxPQWithoutExchange(int maxN, Comparator cc)
    {
        pq = (Key[]) new Comparable[maxN];
        c = cc;
    }

    public MaxPQWithoutExchange(Key[] a)
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

    public MaxPQWithoutExchange(Key[] a, Comparator cc)
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
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        if (N == 1)
            return pq[N--];
        Key v = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return v;
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
        Key aux = pq[k];
        boolean exchange = false;
        while (k / 2 >= 1) {
            if (pq[k/2].compareTo(aux) < 0) {
                exchange = true;
                pq[k] = pq[k/2];
            } else {
                break;
            }
            k = k/2;
        }
        if (exchange) {
            pq[k] = aux;
        }
    }

    private void sink(int k) {
        Key aux = pq[k];
        boolean exchange = false;
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!(pq[j].compareTo(aux) > 0))
                break;
            pq[j] = pq[k];
            k = j;
            exchange = true;
        }
        if (exchange) {
            pq[k] = aux;
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
        MaxPQ<Transaction> pq = new MaxPQ<Transaction>(a);
        StdOut.println(pq.delMax());
    }
}
