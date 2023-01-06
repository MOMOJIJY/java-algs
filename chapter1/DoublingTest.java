import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;


public class DoublingTest
{
	public static double timeTrial(int N)
	{
		int MAX = 1000000;
		int[] a = new int[N];
		for (int i = 0; i < N; i++)
		{
			a[i] = StdRandom.uniform(-MAX, MAX);
		}
		Stopwatch timer = new Stopwatch();
		int cnt = ThreeSum.count(a);
		double t = timer.elapsedTime();
		StdDraw.point(Double.valueOf(cnt), t);
		return t;
	}

	public static void main(String[] args)
	{
		StdDraw.setXscale(-10.0, 8000.0);
		StdDraw.setYscale(-1.0, 50.0);
		StdDraw.setPenRadius(0.005);
		for (int N = 250; true; N += N)
		{
			double elapsed = timeTrial(N);
			StdOut.printf("%7d %5.1f\n", N, elapsed);
		}
	}
}