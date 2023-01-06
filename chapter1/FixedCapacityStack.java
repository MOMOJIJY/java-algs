import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item>
{
	private Item[] a;
	private int N;

	public FixedCapacityStack(int n)
	{
		a = (Item[]) new Object[n];
		N = 0;
	}

	public boolean isEmpty() { return N == 0; }
	public boolean isFull() { return N == a.length; }
	public void push(Item i) { a[N++] = i; }
	public Item pop() { return a[--N]; }

	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		FixedCapacityStack<Integer> list = new FixedCapacityStack<Integer>(n);
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