import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
	private Node first;
	private Node last;
	private int N;
	private class Node
	{
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

	public void enqueue(Item item)
	{
		Node oldLast = last;
		last = new Node();
		last.item = item;
		if (isEmpty()) first = last;
		else oldLast.next = last;
		N++;
	}

	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> 
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
		Queue<String> q = new Queue<String>();
		while (!StdIn.isEmpty())
		{
			String item = StdIn.readString();
			q.enqueue(item);
		}
		for (String item : q) { StdOut.print(item + " "); }
	}
}