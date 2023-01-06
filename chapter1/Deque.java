import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Deque<Item> implements Iterable<Item>
{
	private Node first;
	private Node last;
	private int N;
	private class Node
	{
		Node pre;
		Item item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public int size()
	{
		return N;
	}

	public void pushLeft(Item item)
	{
		N++;
		Node newFirst = new Node();
		newFirst.item = item;
		if (last == null)
		{
			first = newFirst;
			last = first;
			return;
		}
		newFirst.next = first;
		first.pre = newFirst;
		first = newFirst;
	}

	public void pushRight(Item item)
	{
		N++;
		Node newLast = new Node();
		newLast.item = item;
		if (first == null)
		{
			last = newLast;
			first = last;
			return;
		}
		newLast.pre = last;
		last.next= newLast;
		last = newLast;
	}

	public Item popLeft()
	{
		N--;
		Node newFirst = first.next;
		Item item = first.item;
		newFirst.pre = null;
		first = newFirst;
		if (first == null) last = null;
		return item;
	}

	public Item popRight()
	{
		N--;
		Node newLast = last.pre;
		Item item = last.item;
		newLast.next = null;
		last = newLast;
		if (last == null) first = null;
		return item;
	}

	public Iterator<Item> iterator()
	{
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item>
	{
		private Node current = first;
		public boolean hasNext() { return current != null; }
		public Item next()
		{
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove() {}
	}

	public static void main(String[] args)
	{
		Deque<String> q = new Deque<String>();
		while (!StdIn.isEmpty())
		{
			String item = StdIn.readString();
			q.pushRight(item);
		}
		for (String item : q) { StdOut.print(item + " "); }
	}
}