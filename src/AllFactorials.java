//For example, if you need a factorial function that returns all its intermediate results (factorials less
//		than n), as well as the final result (n!), you most naturally return these results as an integer array,
//				which means the function needs to allocate an array. You also need to know where in the array each
//				result should be written. These tasks are easily accomplished using a wrapper function, as follows:

public class AllFactorials {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
			int[] allFactorials( int n ){ /* Wrapper function */
			int[] results = new int[ n == 0 ? 1 : n ];
			doAllFactorials( n, results, 0 );
			return results;
			}
			int doAllFactorials( int n, int[] results, int level ){
			if( n > 1 ){ /* Recursive case */
			results[level] = n * doAllFactorials( n - 1, results, level + 1 );
			return results[level];
			} else { /* Base case */
			results[level] = 1;
			return 1;
			}
			}
}
