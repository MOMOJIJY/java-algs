import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;

public class DoublingRatio
{
	public static double stackIntsTest(long n)
	{
		Stopwatch timer = new Stopwatch();
		FixedCapacityStackOfInts list = new FixedCapacityStackOfInts(1);
		for (long i = 0; i < n; i++)
		{ list.push(1); list.pop(); }
		return timer.elapsedTime();
	}

	public static double stackTest(long n)
	{
		Stopwatch timer = new Stopwatch();
		FixedCapacityStack<Integer> list = new FixedCapacityStack<Integer>(1);
		for (long i = 0; i < n; i++)
		{ list.push(1); list.pop(); }
		return timer.elapsedTime();
	}

	public static void main(String[] args)
	{
		long N = 10000000;
		while (true)
		{
			double t1 = stackIntsTest(N);
			double t2 = stackTest(N);
			StdOut.printf("%7d %7.1f %7.1f %5.1f\n", N, t1, t2, t1/t2);
			N += N;
		}
	}
}