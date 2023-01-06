import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FixedCapacityStackOfInts
{
	private int[] a;
	private int N;

	public FixedCapacityStackOfInts(int n)
	{
		a = new int[n];
		N = 0;
	}

	public boolean isEmpty() { return N == 0; }
	public boolean isFull() { return N == a.length; }
	public void push(int i) { a[N++] = i; }
	public int pop() { return a[--N]; }

	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		FixedCapacityStackOfInts list = new FixedCapacityStackOfInts(n);
		for (int i = 0; i < n; i++)
		{
			list.push(i);
		}
		while (!list.isEmpty())
		{
			StdOut.print(list.pop() + " ");
		}
	}
}