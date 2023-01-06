import edu.princeton.cs.algs4.StdDraw;

public class chapter2Test
{
	public static void drawColumnChart()
	{
		StdDraw.setXscale(0, 50);
		StdDraw.setYscale(0, 50);
		StdDraw.setScale(0, 100);
		StdDraw.filledRectangle(1, 2, 1, 2);
	}

	public static void main(String[] args)
	{
		drawColumnChart();
	}
}