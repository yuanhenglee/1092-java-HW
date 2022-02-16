package assign1;

import java.util.*;

/**
 * 
 * The Armstrong class is used to find the largest Armstrong number below a
 * limit and digest the found number by finding its prime factors and display
 * its bit pattern.
 * @author li
 *
 */
public class Armstrong {

	/**
	 * This is the main method for running assignment #1.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: Armstrong num");
			return;
		}

		// TODO: Put your code here
		int num = Integer.parseInt(args[0]);
		int ans = armstrong(num);

		System.out.println("The largest Armstrong number below " + num + " is " + ans + ".");
		findPrimeFactor(ans);
		printBitPattern(ans);

		System.out.println("ID: s107703004");
		return;
	}

	/**
	 * The method to return the largest Armstrong number below a given limit.
	 * 
	 * @param limit the upper number for the largest Armstrong number
	 * @return the found Armstrong number or 0 if not found
	 */
	public static int armstrong(int limit) {
		// TODO: Put your code here
		while (limit > 0) {
			--limit;
			if (isArmstrong(limit))
				return limit;
		}
		return -1;
	}

	/**
	 * The method is to determine if a number n is an Armstrong number of three
	 * digits
	 * 
	 * @param n a given number to be checked
	 * @return the result that the given number is a three-digit Armstrong number
	 */
	public static boolean isArmstrong(int n) {

		// TODO: Put your code here
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int sum = 0;
		for (int i = n; i > 0; i /= 10)
			digits.add(i % 10);
		for (int i : digits)
			sum += Math.pow(i, digits.size());
		return sum == n;

	}

	/**
	 * The method is used to find and print all the factors of the given number. The
	 * factors are printed to the standard output with the following format:
	 * n1Xn1Xn2Xn3Xn3X....
	 * 
	 * @param in_num A given number to be factored
	 */
	public static void findPrimeFactor(int in_num) {

		// TODO: Put your code here
		System.out.print(in_num);
		ArrayList<Integer> factors = new ArrayList<Integer>();

		for (int i = 2; i < in_num; ++i)
			while (in_num % i == 0) {
				factors.add(i);
				in_num /= i;
			}
		if( in_num != 1 )factors.add(in_num);	
		System.out.print(" can be factored into: ");
		System.out.print(factors.get(0));
		for (int i = 1; i < factors.size(); ++i)
			System.out.print("X" + factors.get(i));
		System.out.println(".");
		return;
	}

	/**
	 * The method is to used to print the bit pattern of a given number. The bits
	 * are grouped with a space between 4 bits.
	 * 
	 * @param num The given number to be converted into bit pattern.
	 */
	public static void printBitPattern(int num) {

		// TODO: Put your code here

		System.out.print(num + " can be converted into binary pattern: ");
		for (int i = 0, j = (1 << 31); i < 32; i++, j >>>= 1) {
			// System.out.print(j);
			System.out.print(((num & j) != 0) ? "1" : "0");
			if( i == 31 )
				System.out.println();
			else if (i % 4 == 3 )
				System.out.print(" ");
		}
		return;
	}

}
