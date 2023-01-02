
import java.util.Random;

/*
 * Numerical experiment with deterministic selection algorithm.
 */

public class Grade {

	public static Random rm;

	public static void main(String[] args) {
		rm = new Random();
		RandomizedSelect rsel = new RandomizedSelect();
		DeterministicSelect dsel = new DeterministicSelect();
		int n;
		int score = 0;

		n = 10;

		Integer[] Ainit = new Integer[n];
		Integer[] A = new Integer[n];
		for (int j = 0; j < n; ++j)
			A[j] = Ainit[j] = j;

		shuffleArray(A);

		int k = 1 + rm.nextInt(n);
		Integer kth = Ainit[k - 1]; // true k-th smallest
		//System.out.println("k= " + k + " kth= " + kth);
		// Integer lr = rsel.randomizedSelect(A, 0, n - 1, k);
		Integer ld = dsel.deterministicSelect(A, 0, n - 1, k);
		// System.out.println(k + "th smallest: " + A[lr]);
		if (A[ld] != kth) {
			System.out.println("For input ");
			for (int j = 0; j < n; ++j)
				System.out.print(Ainit[j] + " ");
			System.out.println();
			System.out.println("the " + k + "th smallest is " + kth + ", not " + ld);
			score = 0;
		} else {
			score = 10;
		}

		System.out.println("score = " + score);

	}

	private static <E extends Comparable<E>> void swap(E[] A, int i, int j) {
		E temp = A[j];
		A[j] = A[i];
		A[i] = temp;
	}

	private static <E extends Comparable<E>> void shuffleArray(E[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			E a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

}
