import java.util.Arrays;
import java.util.PriorityQueue;

public class DeterministicSelect {
	
	
	
	public static <E extends Comparable<E>> Integer deterministicSelect(Integer[] A, int i, int j, int k) {
		
		int length = A.length;
		Integer[] D = new Integer[length];
		for(int q = 0; q < length; q++) {
			D[q] = (int) A[q];
		}
		Integer[] allMedians = new Integer[D.length/5];
		int count = 0;
		for(int h = 0; h < D.length/5; h++) {
			Integer[] B = new Integer[5];
			B = Arrays.copyOfRange(D, 0, 4);
			heap(B);
			allMedians[0 + count] = B[2];
			count ++;
		}
		
		heap(allMedians);
		int median = 0;
		int n = allMedians.length;
		if(n % 2 == 1) {
			median = allMedians[(n + 1)/2-1];
		}
		else {
			median = (allMedians[n/2-1] + allMedians[n/2])/2;
		}
		
		
		
		int pivotindex = median; // Pick a pivot
		swap(A, pivotindex, j); // Stick pivot at end

		// k will be the first position in the right sub-array
		int q = partition(A, i - 1, j, A[j]);
		swap(A, k, j); // Put pivot in place

		int sz = k - i;
		if (q == sz + 1)
			return  A[q];
		else if (k <= sz)
			return deterministicSelect(A, i, q - 1, k);
		else
			return deterministicSelect(A, q + 1, j, k - sz - 1);

		
	}

	private static <E extends Comparable<E>> void heap(Integer[] A) {
		    // The heap constructor invokes the buildheap method
		    
		    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(A.length);
		    for (int i = 0; i < A.length; i++)
		      pq.add(A[i]);
		    for (int i = 0; i < A.length; i++)
		      A[A.length - i - 1] = (int) pq.poll(); // Removemax places max at end
		    // of heap
		  }
			

	private static <E extends Comparable<E>> int partition(E[] A, int l, int r, E pivot) {
		do { // Move bounds inward until they meet
			while (A[++l].compareTo(pivot)<0)
				;
			while ((r != 0) && (A[--r].compareTo(pivot)>0))
				;
			swap(A, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		swap(A, l, r); // Reverse last, wasted swap
		return l; // Return first position in right partition
	}

	private static <E extends Comparable<E>> void swap(E[] A, int i, int j) {
		E temp = A[j];
		A[j] = A[i];
		A[i] = temp;
	}
	
}
