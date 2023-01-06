import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack<Item> implements Iterable<Item>
{
	private Item[] a = (Item[]) new Object[1];
	private int N = 0;

	private int stackSize()
	{
		int size = a.length;
		return size;
	}

	private void resize(int size)
	{
		Item[] b = (Item[]) new Object[size];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i];
		a = b;
	}

	public void push(Item i)
	{
		if (N < a.length)
		{
			a[N++] = i;
			return;
		}

		resize(N*2);
		push(i);
	}

	public Item pop()
	{
		Item i = a[--N];
		if (N < a.length/2)
		{
			resize(a.length/2);
		}
		return i;
	}

	public Iterator<Item> iterator()
	{
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item>
	{
		private int i = N;
		public boolean hasNext() { return i > 0; }
		public Item next() { return a[--i]; }
		public void remove() {}
	}

	public static void main(String[] args)
	{
		ResizingArrayStack<Integer> s = new ResizingArrayStack<Integer>();
		s.push(1);
		StdOut.println("len: " + s.stackSize()); // 2
		int i = s.pop();
		StdOut.println(i);
		StdOut.println("len: " + s.stackSize()); // 1
		s.push(2);
		s.push(3);
		s.push(4);
		StdOut.println("len: " + s.stackSize()); // 4
		i = s.pop();
		StdOut.println(i); // 4
		s.push(5);
		s.push(6);
		s.push(7);
		s.push(8);
		StdOut.println("len: " + s.stackSize());

		for (int item : s)
			StdOut.println(item);
	}
}