
public class ConvertIntAndStr {
	public static final int MAX_DIGITS = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public static String intToStr( int num ){
		int i = 0;
		boolean isNeg = false;
		/* Buffer big enough for largest int and - sign */
		char[] temp = new char[ MAX_DIGITS + 1 ];
		/* Check to see if the number is negative */
		if( num < 0 ){
			num = -num;
			isNeg = true;
		}
		/* Fill buffer with digit characters in reverse order */
		while( num != 0 ){
			temp[i++] = (char)((num % 10) + '0');
			num /= 10;
		}

		StringBuilder b = new StringBuilder();
		if( isNeg )
			b.append( '-' );
		while( i > 0 ){
			b.append( temp[--i] );
		}
		return b.toString();
	}

	public static int strToInt( String str ){
		int i = 0, num = 0;
		boolean isNeg = false;
		int len = str.length();
		if( str.charAt(0) == '-' ){
			isNeg = true;
			i = 1;
		}
		while( i < len ){
			num *= 10;
			num += ( str.charAt(i++) - '0' );
		}
		if( isNeg )
			num = -num;
		return num;
	}
}
