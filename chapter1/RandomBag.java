import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item>
{
	private Item[] array = (Item[]) new Object[1];
	private int N = 0;

	public boolean isEmpty()
	{
		return N == 0;
	}

	public int size()
	{
		return N;
	}

	public void add(Item item)
	{
		if (array.length == N)
			resize(N*2);
		array[N] = item;
		N++;
	}

	private void resize(int n)
	{
		Item[] newArray = (Item[]) new Object[n];
		for (int i = 0; i < array.length; i++)
		{
			newArray[i] = array[i];
		}
		array = newArray;
	}

	private void exchange()
	{
		for (int idx = N - 1; idx > 0; idx--)
		{
			int n = StdRandom.uniformInt(idx+1);
			Item temp = array[idx];
			array[idx] = array[n];
			array[n] = temp;
		}
	}

	public Iterator<Item> iterator()
	{
		return new BagIterator();
	}

	private class BagIterator implements Iterator<Item>
	{
		private int n = 0;

		public BagIterator()
		{
			exchange();
		}

		public boolean hasNext() { return !(n == N); }
		public Item next() 
		{
			Item item = array[n];
			n++;
			return item;
		}
		public void remove() {}
	}

	public static void main(String[] args)
	{
		RandomBag<Integer> bag = new RandomBag<Integer>();
		for (int i = 0; i < 10; i++)
		{
			bag.add(i);
		}

		StdOut.println("size: " + bag.size());

		StdOut.println("第一次：");
		for (int i : bag)
		{
			StdOut.print(i + ",");
		}
		StdOut.println("");
		StdOut.println("第一次：");
		for (int i : bag)
		{
			StdOut.print(i + ",");
		}
	}
}