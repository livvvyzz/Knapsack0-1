package BruteForce;

import java.util.ArrayList;

public class KnapsackBruteForce {

	private int[] value;
	private int[] weights;
	private int[] names;
	private int W;
	private int n;

	private int solution;

	public KnapsackBruteForce(int[] value, int[] weights, int[] count, int wt, int n) {
		System.out.println("bruteforce");
		this.value = value;
		this.weights = weights;
		this.W = wt;
		this.n = n;

		// find the total number of items
		int tot = 0;
		for (int i = 0; i < count.length; i++) {
			tot += count[i];
		}
		// convert O-N to O-1
		int[] newVals = new int[tot + 1];
		int[] newWeights = new int[tot + 1];
		names = new int[tot + 1];

		names[0] = 0;
		newVals[0] = 0;
		newWeights[0] = 0;

		int x = 1;
		for (int i = 1; i < value.length; i++) {
			for (int j = 0; j < count[i]; j++) {
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

		ArrayList<Integer> set = new ArrayList<Integer>();
		for (int i = 1; i < n+1; i++) {
			set.add(i);
		}

		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
		int max = (int) Math.pow(2, set.size());
		for (int i = 0; i < max; i++) {
			ArrayList<Integer> subset = new ArrayList<Integer>();
			for (int j = 0; j < set.size(); j++) {
				if (((i >> j) & 1) == 1) {
					subset.add(set.get(j));
				}
			}
			allsubsets.add(subset);
		}
		
		
		ArrayList<Integer> bestSet = new ArrayList<Integer>();
		bestSet = allsubsets.get(0);
		for(ArrayList<Integer> s : allsubsets) {
			if(getValue(s) > getValue(bestSet) && getWeight(s) <= W) {
				bestSet = s;
			}
		}
		
		System.out.println("Solution: " + getValue(bestSet));
		
		for(int i : bestSet) {
			System.out.println("Item " + names[i] + "   Weight: " + weights[i]);
		}
		
		System.out.println("-");
		for(ArrayList<Integer> s : allsubsets) {
			for(int i : s) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}
		
		System.out.println(allsubsets.size());
		
	}
	
	public int getValue(ArrayList<Integer> s) {
		
		int val = 0;
		for(int i : s) {
			val += value[i];
		}
		
		return val;
	}
	
	public int getWeight(ArrayList<Integer> s) {
		
		int wgt = 0;
		for(int i : s) {
			wgt += weights[i];
		}
		
		return wgt;
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
