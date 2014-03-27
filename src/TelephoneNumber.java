import java.util.HashMap;

public class TelephoneNumber {
	private static final int PHONE_NUMBER_LENGTH = 7;
	private final int [] phoneNum;
	private char[] result = new char[ PHONE_NUMBER_LENGTH ];
	public TelephoneNumber ( int[] n ) { phoneNum = n; }
	public void printWords(){ printWords( 0 ); }
	private void printWords(int curDigit ) {
		if( curDigit == PHONE_NUMBER_LENGTH ) {
			System.out.println( new String( result ) );
			return;
		}
		for( int i = 1; i <= 3; ++i ) {
			result[ curDigit ] = getCharKey( phoneNum[curDigit], i );
			printWords( curDigit + 1 );
			if( phoneNum[curDigit] == 0 ||
					phoneNum[curDigit] == 1) return;
		}
	}
    private char getCharKey(int i, int i2) {
		// TODO Auto-generated method stub
    	
    	if( i== 0 ||
    			i == 1) return (char) ('0'+i);
    	return map.get(i).charAt(i2-1);
	}
    public static void main(String[] args) {
		int[] phone = {4, 9, 7, 1, 9, 3, 7};
		TelephoneNumber number = new TelephoneNumber(phone);
//		number.printWords();
		number.printWordsIt();

	}
//    
//    private static final int PHONE_NUMBER_LENGTH = 7;
//    private final int [] phoneNum;
//    private char[] result = new char[ PHONE_NUMBER_LENGTH ];
//    public TelephoneNumber ( int[] n ) { phoneNum = n; }
    public void printWordsIt() {
    	// Initialize result with first telephone word
    	for( int i = 0; i < PHONE_NUMBER_LENGTH; ++i )
    		result[i] = getCharKey( phoneNum[i], 1 );
    	for( ; ; ) { // Infinite loop
    		for( int i = 0; i < PHONE_NUMBER_LENGTH; ++i ) {
    			System.out.print( result[i] );
    		}
    		System.out.print( '\n' );
    		/* Start at the end and try to increment from right
    		 * to left.
    		 */
    		for( int i = PHONE_NUMBER_LENGTH - 1; i >= -1; --i ) {
    			if( i == -1 ) // if attempted to carry past leftmost digit,
    				return; // we're done, so return
    			/* Start with high value, carry case so 0 and 1
    			 * special cases are dealt with right away
    			 */
    			if( getCharKey( phoneNum[i], 3 ) == result[i] ||
    					phoneNum[i] == 0 || phoneNum[i] == 1 ){
    				System.out.println(" i " + i);
    				System.out.println("getCharKey( phoneNum[i], 1 ) " + getCharKey( phoneNum[i], 1 ));
    				result[i] = getCharKey( phoneNum[i], 1 );
    				// No break, so loop continues to next digit
    			} else if ( getCharKey( phoneNum[i], 1 ) == result[i] ) {
    				result[i] = getCharKey( phoneNum[i], 2 );
    				break;
    			} else if ( getCharKey( phoneNum[i], 2 ) == result[i] ) {
    				result[i] = getCharKey( phoneNum[i], 3 );
    				break;
    			}
    		}
    	}
    }
	static final HashMap<Integer,String> map = new HashMap<Integer,String>(){{
		put(1,"");
        put(2,"abc");
        put(3,"def");
        put(4,"ghi");
        put(5,"jkl");
        put(6,"mno");
        put(7,"pqrs");
        put(8,"tuv");
        put(9,"wxyz");
        put(0,"");
    }} ;
}