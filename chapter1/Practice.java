import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.BinarySearch;
import java.util.Arrays;


public class Practice
{
	private static class Node
	{
		int item;
		Node next;
	}

	public static void a1()
	{
		Counter[] a = new Counter[1];
		a[0] = new Counter("test");
	}

	public static void p1(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < n; i++)
		{
			double x = StdRandom.random();
			double y = StdRandom.random();
			points[i] = new Point2D(x, y);
			points[i].draw();
		}
		double r = Double.MAX_VALUE;
		for (int i = 0; i < n; i++)
		{
			Point2D p = points[i];
			for (int j = 0; j < n; j++)
			{
				if (i != j && p.distanceTo(points[j]) < r)
					r = p.distanceTo(points[j]);
			}
		}
		StdOut.println("dist: " + r);
	}

	public static String mystery(String s)
	{
		int N = s.length();
		if (N <= 1) return s;
		String a = s.substring(0, N/2);
		String b = s.substring(N/2, N);
		return mystery(b) + mystery(a);
	}

	public static void p134(String[] args)
	{
		Stack<Character> s = new Stack<Character>();
		In in = new In();
		String origin = in.readAll().trim();
		for (int i = 0; i < origin.length(); i++)
		{
			char item = origin.charAt(i);
			if (item == '{' || item == '(' || item == '[')
			{
				s.push(item);
				continue;
			}
			if (item == '}' && s.pop() != '{')
			{
				StdOut.println("false");
				return;
			}
			if (item == ')' && s.pop() != '(')
			{
				StdOut.println("false");
				return;
			}
			if (item == ']' && s.pop() != '[')
			{
				StdOut.println("false");
				return;
			}
		}
		if (!s.isEmpty()) StdOut.println("false");
		else StdOut.println("true");
	}


	public static void p139()
	{
		Stack<String> s1 = new Stack<String>();
		Stack<String> s2 = new Stack<String>();
		In in = new In();
		String origin = in.readAll().trim();
		for (int i = 0; i < origin.length(); i++)
		{
			char item = origin.charAt(i);
			if (item == '+' || item == '*' || item == '-' || item == '/')
				s2.push(Character.toString(item));
			else if (item == ')') 
			{
				String num2 = s1.pop();
				String num1 = s1.pop();
				String c = s2.pop();
				s1.push("(" + num1 + c + num2 + ")");
			}
			else s1.push(Character.toString(item));
		}
		StdOut.println("size:" + s1.size());
		StdOut.println(s1.pop());
	}

	public static Node build(int[] array)
	{
		Node first = new Node();
		Node oldFirst;
		for (int i = 0; i < array.length; i++)
		{
			oldFirst = first;
			oldFirst.item = array[array.length-1-i];
			first = new Node();
			first.next = oldFirst;
		}
		return first.next;
	}

	public static void printNode(Node head)
	{
		while (head != null)
		{
			StdOut.print(head.item);
			if (head.next != null) StdOut.print("->");
			head = head.next;
		}
		StdOut.println("");
	}

	public static Node p1320(Node head, int n) 
	{
		int i = 0;
		Node cur = new Node();
		cur.next = head;
		Node pre = cur;
		while (cur != null && cur.next != null)
		{
			i++;
			if (i == n)
			{
				cur.next = cur.next.next;
				break;
			}
			cur = cur.next;
		}
		return pre.next;
	}

	public static int fourSum(int[] a)
	{
		int cnt = 0;
		for (int i = 0; i < a.length; i++)
		{
			for (int j = i+1; j < a.length; j++)
			{
				for (int p = j+1; p < a.length; p++)
				{
					for (int z = p+1; z < a.length; z++)
					{
						if (a[i] + a[j] + a[p] + a[z] == 0) cnt++;
					}
				}
			}
		}
		return cnt;
	}

	public static int p1415(int[] a)
	{
		Arrays.sort(a);
		int i = 0;
		int j = a.length - 1;
		int cnt = 0;
		while (i < j)
		{
			if (a[i] + a[j] == 0) {cnt++; j++; i++;}
			else if (a[i] + a[j] > 0) j--;
			else i++;
		}
		return cnt;
	}

	public static int p1415d2(int[] a)
	{
		Arrays.sort(a);
		int N = a.length;
		int cnt = 0;
		for (int i = 0; i < N; i++)
		{
			int j = i;
			int p = N-1;
			while (j < p)
			{
				if (a[i] + a[j] + a[p] == 0) {cnt++; j++; p--;}
				else if (a[i] + a[j] + a[p] > 0) p--;
				else j++;
			}
		}
		return cnt;
	}

	public static int twoSum(int[] a)
	{
		Arrays.sort(a);
		int j = a.length;
		int cnt = 0;
		for (int i = 0; i < j; i++)
			if (BinarySearch.rank(-a[i], a) > i)
				cnt++;
		return cnt;
	}

	public static int p1418(int[] a)
	{
		if (a.length < 3)
		{
			return -1;
		}
		int n = a.length / 2;
		if (a[n] < a[n-1] && a[n] < a[n+1]) 
		{
			StdOut.printf("%d %d %d\n", a[n-1], a[n], a[n+1]);
			return n;
		}
		int i = p1418(Arrays.copyOfRange(a, 0, n));
		if (i > 0) return i;
		i = p1418(Arrays.copyOfRange(a, n+1, a.length));
		return i;
	}

	public static int binarySearch1(int[] a, int lo, int hi, int b)
	{
		if (lo > hi) return -1;
		int mid = (lo + hi) / 2;
		if (a[mid] == b) return mid;
		else if (a[mid] > b) return binarySearch1(a, lo, mid-1, b);
		else return binarySearch1(a, mid+1, hi, b);
	}

	public static int binarySearch2(int[] a, int lo, int hi, int b)
	{
		if (lo > hi) return -1;
		int mid = (lo + hi) / 2;
		if (a[mid] == b) return mid;
		else if (a[mid] < b) return binarySearch2(a, lo, mid-1, b);
		else return binarySearch2(a, mid+1, hi, b);
	}

	public static class BitonicMax {

	    // create a bitonic array of size N
	    public static int[] bitonic(int N) {
	        int mid = StdRandom.uniformInt(N);
	        int[] a = new int[N];
	        for (int i = 1; i < mid; i++) {
	            a[i] = a[i-1] + 1 + StdRandom.uniformInt(9);
	        }

	        if (mid > 0) a[mid] = a[mid-1] + StdRandom.uniformInt(10) - 5;

	        for (int i = mid + 1; i < N; i++) {
	            a[i] = a[i-1] - 1 - StdRandom.uniformInt(9);
	        }

	        for (int i = 0; i < N; i++) {
	            StdOut.println(a[i]);
	        }
	        return a;
	    }

	    // find the index of the maximum in a bitonic subarray a[lo..hi]
	    public static int max(int[] a, int lo, int hi) {
	        if (hi == lo) return hi;
	        int mid = lo + (hi - lo) / 2;
	        if (a[mid] < a[mid + 1]) return max(a, mid+1, hi);
	        if (a[mid] > a[mid + 1]) return max(a, lo, mid);
	        else return mid;
	    }



	    public static void main(String[] args) {

	        int N = Integer.parseInt(args[0]);
	        int[] a = bitonic(N);
	        StdOut.println("max = " + a[max(a, 0, N-1)]);
	    }
	}

	public static int p1420(int[] a, int b)
	{
		int N = a.length;
		int max = BitonicMax.max(a, 0, N-1);
		if (a[max] < b)
			return -1;
		int idx = 0;
		idx = binarySearch1(a, 0, max, b);
		if (idx != -1) return idx;
		idx = binarySearch2(a, max+1, N-1, b);
		return idx;
	}

	public static void main(String[] args)
	{
		// a1();
		// p1(args);
		
		// String s = args[0];
		// String r = mystery(s);
		// StdOut.println(r);

		// p134(args);

		// p139();

		// int[] a = {1,2,3};
		// Node n = build(a);
		// printNode(n);
		// Node d = p1320(n,2);
		// printNode(d);

		// int[] a = In.readInts(args[0]);
		// StdOut.println(fourSum(a));
		// StdOut.println(ThreeSum.count(a));
		// StdOut.println(p1415d2(a));
		// StdOut.println(twoSum(a));
		// StdOut.println(p1415(a));

		// StdOut.println(p1418(a));

		// int[] a = {1, 2, 3, 4, 5};
		// int b = 5;
		// StdOut.println(binarySearch1(a, 0, 4, b));

		int[] a = BitonicMax.bitonic(10);
		StdOut.println("");
		StdOut.println(a[3]);
		StdOut.println(p1420(a, a[3]));
		StdOut.println(4);
		StdOut.println(p1420(a, 4));
	}
}