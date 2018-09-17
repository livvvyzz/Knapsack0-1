
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int W = 2000;
		int n = 6;
		int[] weights = {0, 580, 1616, 1906, 1942, 50, 294};
		int[] values = {0, 874, 620, 345, 369, 360, 470};
		Knapsack ks = new Knapsack(values, weights ,W, n);
	}

}
