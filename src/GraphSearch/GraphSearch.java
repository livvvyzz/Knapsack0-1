package GraphSearch;

public class GraphSearch {

	public static KnapsackGS main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*
		int capacity = 20;
		int[] count = new int[] {2,1,3,5,2};
		int[] weights = new int[] { 2,9,8,6,4};
		int[] values = new int[] { 4,7,4,2,9};
		*/
		
		int W = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		
		int[] values = new int[n];
		int[] weights = new int[n];
		int[] count = new int[n];
		
		for(int i = 2; i < n +2; i++) {
			values[i-2] = Integer.parseInt(args[i]);
		}
		for(int i = n+2; i < 2*n + 2; i++) {
			weights[i - (n+2)] = Integer.parseInt(args[i]);
		}
		
		//only for ON
		for(int i = 2*n+2; i < 3*n + 2; i++) {
			count[i - (2*n+2)] = Integer.parseInt(args[i]);
		}
		
	
	
		int[] names;
		//convert to 0-1
		
		//find the total number of items
		int tot = 0;
		for(int i = 0; i < count.length; i ++) {
			tot += count[i];
		}
		
		int[] newVals = new int[tot];
		int[] newWeights = new int[tot];
		names = new int[tot];
		
		
		int x = 0;
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < count[i]; j++) {
				newVals[x] = values[i];
				newWeights[x] = weights[i];
				names[x] = i;
				x++;
			}
		}
		
		values = newVals;
		weights = newWeights;
		
		
		KnapsackGS ks = new KnapsackGS(values, weights, W, tot, names);
		return ks;

	}

}
