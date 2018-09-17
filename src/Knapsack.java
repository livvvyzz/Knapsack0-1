
public class Knapsack {

	private int[] value;
	private int[] weights; 
	private int W;
	private int n;
	
	public Knapsack(int[] value, int[] weights, int w, int n) {
		this.value = value;
		this.weights = weights;
		this.W = w;
		this.n= n;
		int solution = knapsackAlgorithm();
	}
	
	public int knapsackAlgorithm() {
		
		int[][] m = new int[n+1][W+1];
		
		for(int j = 0; j <= W; j++) {
			m[0][j] = 0;
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <=W; j++) {
				if(weights[i] > j) {
					m[i][j] = m[i-1][j];
				}
				else {
					m[i][j] = max(m[i-1][j], m[i-1][j-weights[i]] + value[i]);
				}
			}
		}
		
		
		return m[n][W];
	}
	
	public int max(int a, int b) {
		if(a >= b) return a;
		else return b;
	}
	
	public int[] copyArray(int[] A, int[] B) {
		for(int i = 0; i <= B.length; i++) {
			A[i] = B[i];
		}
		
		return A;
	}
}
