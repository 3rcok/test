
public class NumberPalinDrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=3456;
		System.out.println(" 3456 is palindrome ? " + isPalindromeNumber(n));
		System.out.println(" 3456 reverse is " + ReverseNumber(n, 0));
	}
	public static boolean isPalindromeNumber(int num) {
	int n = num;
	int rev = 0;
	 while (num > 0)
	 {
	      int dig = num % 10;
	      rev = rev * 10 + dig;
	      num = num / 10;
	 }
	 if(n==rev) {
		 return true;
	 }else {
		 return false;
	 }
	}
	
	public static int ReverseNumber(int n, int partial) {
	    if (n == 0) {
	    	System.out.println("partial " + partial);
	        return partial;
	    }
	    return ReverseNumber(n / 10, partial * 10 + n % 10);
	}

}
