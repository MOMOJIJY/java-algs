import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class MergeNode {
    static class Node
    {
        Comparable item;
        Node next;
    }

    private static boolean less(Comparable v, Comparable w)
	{ return v.compareTo(w) < 0; }

	private static void show(Node a)
	{
        Node b = a;
        while (b != null)
        {
            StdOut.print(b.item + " ");
            b = b.next;
        }
		StdOut.println();
	}

	public static boolean isSorted(Node a)
	{
        Node b = a;
        Node c = b.next;
        while (c != null)
        {
            if (less(c.item, b.item)) return false;
            c = c.next;
            b = b.next;
        }
		return true;
	}   

    public static Node sort(Node head)
    {
        if (head == null || head.next == null)
            return head;
        Node slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null )
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow.next;
        slow.next = null;
        Node left = sort(head), right = sort(mid);
        Node h = new Node();
        Node res = h;
        while (left != null && right != null)
        {
            if (less(left.item, right.item))
            {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        if (left != null)
            h.next = left;
        else
            h.next = right;
        return res.next;
    }

    public static void main(String[] args)
    {
        // String[] a = StdIn.readAllStrings();
        // sort(a);
        // assert isSorted(a);
        // show(a);
        Node a = new Node();
        Node b = a;
        b.item = 1;
        b.next = new Node();
        b = b.next;
        b.item = 4;
        b.next = new Node();
        b = b.next;
        b.item = 2;
        b.next = new Node();
        b = b.next;
        b.item = 3;
        
        // 1 -4-2-3
        show(a);
        a = sort(a);
        show(a);
    }
}
