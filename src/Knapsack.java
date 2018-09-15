
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
		
		int A[] = new int[W+1];
		int B[] = new int[W+1];
		
		for(int i = 0; i <= W; i++) {
			B[i] = 0;
		}
		
		for(int k = 1; k <= n; k++ ) {
			A = copyArray(A,B);
			
			for(int i = k; i <= W; i++) {
				if(A[i-k] + )
			}
		}
		
		
		
		return 0;
	}
	
	public int[] copyArray(int[] A, int[] B) {
		for(int i = 0; i <= B.length; i++) {
			A[i] = B[i];
		}
		
		return A;
	}
}
