package DP01;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Core1 {

	private static int numberOfNTested = 11;
	private static long[] times = new long[numberOfNTested];
	private static int W = 2000;

	private static int numTestsPerN = 5;

	/**
	 * args[0] = capacity
	 * args[1] = number of items
	 * values
	 * weights
	 * @param args
	 */
	public static Knapsack main(String[] args) {
		// TODO Auto-generated method stub

		int W = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		
		int[] values = new int[n+1];
		int[] weights = new int[n+1];
		values[0] = 0; weights[0] = 0;
		
		
		for(int i = 2; i < n +2; i++) {
			values[i-1] = Integer.parseInt(args[i]);
		}
		for(int i = n+2; i < 2*n + 2; i++) {
			weights[i - (n+1)] = Integer.parseInt(args[i]);
		}
	
		
		
		
		if(weights.length != values.length) {
			System.out.println("number of weights/values dont match");
			System.exit(n);
		}
		
		

		Knapsack k = new Knapsack(values, weights, W, n);
		
		
		/*for (int t = 0; t < 50; t++) {
			int count = 0;
			for (int i = 10; i <= 1011; i += 100) {
				int bm = tests(i, W);
				times[count]+=bm;
				count++;
			}
		}
		
		for(int i = 0; i < times.length; i++) {
			times[i] = times[i]/50;
		}

		for (int i = 0; i < numberOfNTested; i++) {
			System.out.println(times[i]);
		}*/
		
		return k;
	}

	public static int tests(int n, int W) {
		System.out.println("gggggggggggggggg" + n);
		Random r = new Random();
		int avgBM = 0;
		for (int i = 0; i < numTestsPerN; i++) {

			// generate values
			int[] values = new int[n + 1];
			values[0] = 0;
			for (int a = 1; i <= n; i++) {
				values[a] = r.nextInt(1000)+1;
			}

			// generate weights
			int[] weights = new int[n + 1];
			weights[0] = 0;
			for (int a = 1; i <= n; i++) {
				weights[a] = r.nextInt(W)+1;
			}
			Knapsack ks = new Knapsack(values, weights, W, n);
			avgBM += ks.getBM();
		}

		return avgBM/numTestsPerN;

	}
}
