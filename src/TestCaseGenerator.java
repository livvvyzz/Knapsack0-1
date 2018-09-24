import java.util.Random;
import java.util.Scanner;

import BruteForce.BruteForce;
import DP01.Core1;
import DPON.Comp1;
import GraphSearch.GraphSearch;

public class TestCaseGenerator {

	private static int numTests = 10;
	private static int numTestsPerN = 50;

	public static void askUser() {
		Scanner s = new Scanner(System.in);
		System.out.println("To generate a test for all algorithms enter a string of numbers in the form: ");
		System.out.println("(Capacity) (number of items) (values) (weights) (counts for each item)");
		System.out.println("Brackets are not needed to seperate. An example input would be:");
		System.out.println("10 3 20 30 40 4 7 3 1 2 1");
		System.out.println("OR to automatically generate random test cases just type '0' :)");

		String line = s.nextLine();
		s.close();
		String[] nums = line.split("\\s+");

		if (nums[0].equals("0")) {
			generateTestCases();
		}

		else {
			if (nums.length > 1) {
				if (Character.isDigit(nums[1].charAt(0))) {
					if (nums.length != (3 * Integer.parseInt(nums[1]) + 2)) {
						System.out.println("An incorrect format was entered, please try again");
						askUser();
					}
				}
			} else if (nums.length == 1)
				askUser();

			// run each algorithm
			int core = Core1.main(nums).getSol();
			int bf = BruteForce.main(nums).getSol();
			int comp = Comp1.main(nums).getSol();
			int gs = GraphSearch.main(nums).getSol();

			System.out.println("-----------------------");
			if (bf == comp && comp == gs) {
				System.out.println("Solution for O-N algorithms are th same");
			}

		}

	}

	/**
	 * This method randomly generates cases to see if the algorithms find the same
	 * solution
	 */
	public static void generateTestCases() {
		System.out.println("***************Randomly Generating Tests***************");
		int correct = 0;
		for (int i = 0; i < numTests; i++) {
			System.out.println("----------------------- Test " + (i + 1) + "---------------------");

			String[] str = createTestString(5, 50, 30, false);

			System.out.println("cap: " + str[0]);
			// run each algorithm
			int core = Core1.main(str).getSol();
			int bf = BruteForce.main(str).getSol();
			int comp = Comp1.main(str).getSol();
			int gs = GraphSearch.main(str).getSol();
			if (comp == gs) {
				correct++;
			}
		}

		if (correct == numTests) {
			System.out.println("All the O-N tests had the same solution");
		}
	}

	public static String[] createTestString(int maxN, int maxC, int maxV, boolean constantN) {
		Random ran = new Random();

		// capacity
		int capacity = ran.nextInt(maxC - 1 + 1) + 1;
		// number of items
		int n;
		if (constantN) {
			n = maxN;
		} else {
			n = ran.nextInt(maxN) + 1;
		}
		// values
		int[] values = new int[n];
		for (int x = 0; x < n; x++) {
			values[x] = ran.nextInt(maxV - 1 + 1) + 1;
		}
		// weights
		int[] weights = new int[n];
		for (int x = 0; x < n; x++) {
			weights[x] = ran.nextInt(capacity) + 1;
		}
		// counts
		int[] counts = new int[n];
		for (int x = 0; x < n; x++) {
			counts[x] = ran.nextInt(5);
		}

		// make into string
		int len = 2 + (n * 3);
		String[] str = new String[len];

		str[0] = Integer.toString(capacity);
		str[1] = Integer.toString(n);

		for (int x = 0; x < n; x++) {
			str[x + 2] = Integer.toString(values[x]);
		}

		for (int x = 0; x < n; x++) {
			str[2 + n + x] = Integer.toString(weights[x]);
		}

		for (int x = 0; x < n; x++) {
			str[2 + (2 * n) + x] = Integer.toString(counts[x]);
		}
		return str;
	}

	public static void main(String[] args) {
		//askUser();
		//testDPO1();
		testBruteForce();
		//testDPON();
		//testGS();
	}

	/**
	 * Test average case (so randomly generated values. Compare barometers
	 */
	public static void testDPO1() {
		int[] bms = new int[10];
		int c = 0;
		int avgBM = 0;
		for (int n = 10; n < 1010; n += 100) {
			for (int t = 0; t < 50; t++) {
				avgBM += Core1.main(createTestString(n, 50, 30, true)).getBM();
			}
			bms[c] = avgBM / 50;
			c++;
		}
		
		for(int i = 0; i < bms.length; i++) {
			System.out.println(bms[i]);
		}

	}
	

	/**
	 * Test average case (so randomly generated values. Compare barometers
	 */
	public static void testBruteForce() {
		int[] bms = new int[10];
		int c = 0;
		int avgBM = 0;
		for (int n = 2; n < 10; n += 1) {
			for (int t = 0; t < 2; t++) {
				avgBM += BruteForce.main(createTestString(n, 20, 10, true)).getBM();
			}
			bms[c] = avgBM / 2;
			c++;
		}
		
		for(int i = 0; i < bms.length; i++) {
			System.out.println(bms[i]);
		}

	}
	

	/**
	 * Test average case (so randomly generated values. Compare barometers
	 */
	public static void testDPON() {
		int[] bms = new int[10];
		int c = 0;
		int avgBM = 0;
		for (int n = 10; n < 1010; n += 100) {
			for (int t = 0; t < 50; t++) {
				avgBM += Comp1.main(createTestString(n, 50, 30, true)).getBM();
			}
			bms[c] = avgBM / 50;
			c++;
		}
		
		for(int i = 0; i < bms.length; i++) {
			System.out.println(bms[i]);
		}

	}
	

	/**
	 * Test average case (so randomly generated values. Compare barometers
	 */
	public static void testGS() {
		int[] bms = new int[10];
		int c = 0;
		int avgBM = 0;
		for (int n = 10; n < 1010; n += 100) {
			for (int t = 0; t < 50; t++) {
				avgBM += GraphSearch.main(createTestString(n, 50, 30, true)).getBM();
			}
			bms[c] = avgBM / 50;
			c++;
		}
		
		for(int i = 0; i < bms.length; i++) {
			System.out.println(bms[i]);
		}

	}

}
