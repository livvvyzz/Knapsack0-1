package DP01;

public class Knapsack {

	private int[] value;
	private int[] weights;
	private int W;
	private int n;
	private int solution;
	
	private int bm = 0;

	public Knapsack(int[] value, int[] weights, int wt, int n) {
		System.out.println("--------------------------------");
		System.out.println("Knapsack O-1 Dynamic Programming");
		this.value = value;
		this.weights = weights;
		this.W = wt;
		this.n = n;
	
		knapsackAlgorithm();
	}

	public void knapsackAlgorithm() {

		int[][] m = new int[n + 1][W + 1];

		for (int j = 0; j <= W; j++) {
			m[0][j] = 0;
			bm++;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= W; j++) {
				if (weights[i] > j) {
					m[i][j] = m[i - 1][j];
				} else {
					m[i][j] = max(m[i - 1][j], m[i - 1][j - weights[i]] + value[i]);
				}
				bm++;
			}
		}

		int sol = m[n][W];
		System.out.println("Solution: " + sol);
		System.out.println("Items included are...");
		int w = W;
		for (int i = n; i > 0 && sol > 0; i--) {
			if (sol != m[i - 1][w]) {
				System.out.println("Item " + i + " with weight " + weights[i] + " and value " + value[i]);

				sol = sol - value[i];
				w = w - weights[i];
			}
			bm++;
		}

	}

	
	public int max(int a, int b) {
		if (a >= b)
			return a;
		else
			return b;
	}

	public int getSol() {
		return solution;

	}

	public int getBM() {
		return bm;
	}
}
