import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

// MaxPQStackV1 实用数组实现优先队列，无序
public class MinPQStackV1<Key extends Comparable<Key>> {
	private Key[] a;
	private int N;

	public MinPQStackV1(int n)
	{
		a = (Key[]) new Comparable[n];
		N = 0;
	}

    private boolean less(Key v, Key w)
	{ return v.compareTo(w) < 0; }

	private void exch(Key[] a, int i, int j)
	{ Key t = a[i]; a[i] = a[j]; a[j] = t; }


    // sort 选择排序，降序
    private void sort()
    {
        for (int i = 0; i < N-1; i++)
        {
            int p = i;
            for (int j = i+1; j < N; j++)
            {
                if (less(a[p], a[j])) p = j;
            }
            exch(a, i, p);
        }
    }

    public void insert(Key v)
    {
        a[N++] = v;
    }

    public Key delMin()
    {
        if (N == 1)
        {
            return a[--N];
        }
        sort();
        return a[--N];
    }

    public Key min()
    {
        if (N == 1)
            return a[0];
        sort();
        return a[N-1];
    }

	public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    public static void main(String[] args)
    {
        int M = Integer.parseInt(args[0]);
        MinPQStackV1<Transaction> pq = new MinPQStackV1<Transaction>(M+1);
        while (StdIn.hasNextLine())
        {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M)
            {
                pq.delMin();
            }
        }
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t : stack) StdOut.println(t);

    }
}
