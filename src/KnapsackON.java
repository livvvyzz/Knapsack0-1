
public class KnapsackON {

	private int[] value;
	private int[] weights;
	private int[] names;
	private int W;
	private int n;

	private int solution;

	public KnapsackON(int[] value, int[] weights, int[] count, int wt, int n) {
		System.out.println("Knapsack 0n");
		this.value = value;
		this.weights = weights;
		this.W = wt;
		this.n = n;
		
		//find the total number of items
		int tot = 0;
		for(int i = 0; i < count.length; i ++) {
			tot += count[i];
		}
		
		int[] newVals = new int[tot+1];
		int[] newWeights = new int[tot+1];
		names = new int[tot+1];
		
		names[0] = 0; newVals[0] = 0; newWeights[0] = 0;
		
		int x = 1;
		for(int i = 1; i < value.length; i++) {
			for(int j = 0; j < count[i]; j++) {
				newVals[x] = value[i];
				newWeights[x] = weights[i];
				names[x] = i;
				x++;
			}
		}
		
		this.value = newVals;
		this.weights = newWeights;
		
		
		this.n = tot;
		knapsackAlgorithm();
	}

	public void knapsackAlgorithm() {

		int[][] m = new int[n + 1][W + 1];

		for (int j = 0; j <= W; j++) {
			m[0][j] = 0;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= W; j++) {
				if (weights[i] > j) {
					m[i][j] = m[i - 1][j];
				} else {
					m[i][j] = max(m[i - 1][j], m[i - 1][j - weights[i]] + value[i]);
				}
			}
		}

		int sol = m[n][W];
		int w = W;
		System.out.println("----------------------------------");
		System.out.println("Solution " + sol);
		for (int i = n; i > 0 && sol > 0; i--) {
			if (sol != m[i - 1][w]) {
				System.out.println(weights[i] + " item" + names[i]);

				sol = sol - value[i];
				w = w - weights[i];
			}
		}
		System.out.println("----------------------------------");

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

}
