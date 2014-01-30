
public class PrintArrayRec {

	static void printArray(int[] a, int index) {
		if (index < a.length) {
			System.out.print(" " + a[index]);
			printArray(a, index+1);
		}

	}
	
	static int sumArray(int[] a, int n) {
		if(n==0) {
			return a[0];
		}else {
			return sumArray(a, n-1) + a[n];
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2, 3,4};
		printArray(a,0);
		System.out.println(sumArray(a,3));
	}

}
