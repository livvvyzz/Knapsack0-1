package BruteForce;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BruteForce {

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
	public static KnapsackBruteForce main(String[] args) {
		// TODO Auto-generated method stub

		
		int W = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		
		int[] values = new int[n+1];
		int[] weights = new int[n+1];
		int[] count = new int[n+1]; //only for ON
		values[0] = 0; weights[0] = 0; count[0] = 0;
		
		
		for(int i = 2; i < n +2; i++) {
			values[i-1] = Integer.parseInt(args[i]);
		}
		for(int i = n+2; i < 2*n + 2; i++) {
			weights[i - (n+1)] = Integer.parseInt(args[i]);
		}
		//only for ON
		for(int i = 2*n+2; i < 3*n + 2; i++) {
			count[i - (2*n+1)] = Integer.parseInt(args[i]);
		}
		
		
		
		if(weights.length != values.length) {
			System.out.println("number of weights/values dont match");
			System.exit(n);
		}
		
		
		KnapsackBruteForce k = new KnapsackBruteForce(values,weights, count, W, n);
		
		//Knapsack k = new Knapsack(values, weights, W, n);
		
		/**
		for (int t = 0; t < 50; t++) {
			int c = 0;
			for (int i = 10; i <= 1011; i += 100) {
				long time = tests(i, W);
				times[c]+=time;
				c++;
			}
		}
		
		for(int i = 0; i < times.length; i++) {
			times[i] = times[i]/50;
		}

		for (int i = 0; i < numberOfNTested; i++) {
			System.out.println(times[i]);
		}
		*/
		
		return k;
		
	}

	public static long tests(int n, int W) {
		Random r = new Random();
		long time = 0;
		for (int i = 0; i < numTestsPerN; i++) {

			// generate values
			int[] values = new int[n + 1];
			values[0] = 0;
			for (int a = 1; a <= n; a++) {
				values[a] = r.nextInt(1000);
			}

			// generate weights
			int[] weights = new int[n + 1];
			weights[0] = 0;
			for (int a = 1; a <= n; a++) {
				weights[a] = r.nextInt(W);
			}
			
			//generate counts for each item
			int[] counts = new int[n+1];
			counts[0] = 0;
			for(int a = 1; a <= n; a++	) {
				counts[a] = r.nextInt(5);
			}
			
			long startTime = System.nanoTime();
			int solution = new KnapsackBruteForce(values, weights, counts, W, n).getSol();
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;

			time += totalTime;
		}

		long avgTime = time / numTestsPerN;
		return avgTime;

	}
}
