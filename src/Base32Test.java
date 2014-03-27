import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base32Test {
    public static String base32Decode(String value)
    {
        if (value == null || value.length() == 0)
            return null;

        byte[] b = null;
        try {
            b = Base32.decode(value);
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        // restore the seed, it's at the end of the string
        int rot = b[b.length - 1];

        StringBuffer buf = new StringBuffer();
        // make sure we exclude the last byte
        for (int i = 0; i < b.length - 1; i++)
        {
            int k = (int)b[i] - rot;
            if (k < 0)
                k += 256;
            buf.append((char)k);
            rot = b[i];
        }

        return buf.reverse().toString();
    }
    
    //*****************************start Base32 class********************************************************/
    
    /**
     * Base32 encoding/decoding class.
     * 
     */
    public final static class Base32 {
      /* lookup table used to encode() groups of 5 bits of data */
      private static final String base32Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
      /* lookup table used to decode() characters in Base32 strings */
      private static final byte[] base32Lookup = { 26, 27, 28, 29, 30, 31, -1,
          -1, -1, -1, -1, -1, -1, -1, // 23456789:;<=>?
          -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, // @ABCDEFGHIJKLMNO
          15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, // PQRSTUVWXYZ[\]^_
          -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, // `abcdefghijklmno
          15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 // pqrstuvwxyz
      };
      /* Messsages for Illegal Parameter Exceptions in decode() */
      private static final String errorCanonicalLength = "non canonical Base32 string length";
      private static final String errorCanonicalEnd = "non canonical bits at end of Base32 string";
      private static final String errorInvalidChar = "invalid character in Base32 string";

      /**
       * Decode a Base32 string into an array of binary bytes. May fail if the
       * parameter is a non canonical Base32 string (the only other possible
       * exception is that the returned array cannot be allocated in memory)
       */
      static public byte[] decode(final String base32)
          throws IllegalArgumentException {
        // Note that the code below detects could detect non canonical
        // Base32 length within the loop. However canonical Base32 length
        // can be tested before entering the loop.
        // A canonical Base32 length modulo 8 cannot be:
        // 1 (aborts discarding 5 bits at STEP n=0 which produces no byte),
        // 3 (aborts discarding 7 bits at STEP n=2 which produces no byte),
        // 6 (aborts discarding 6 bits at STEP n=1 which produces no byte)
        // So these tests could be avoided within the loop.
        switch (base32.length() % 8) { // test the length of last subblock
        case 1: // 5 bits in subblock: 0 useful bits but 5 discarded
        case 3: // 15 bits in subblock: 8 useful bits but 7 discarded
        case 6: // 30 bits in subblock: 24 useful bits but 6 discarded
          throw new IllegalArgumentException(errorCanonicalLength);
        }
        byte[] bytes = new byte[base32.length() * 5 / 8];
        int offset = 0, i = 0, lookup;
        byte nextByte, digit;
        // Also the code below does test that other discarded bits
        // (1 to 4 bits at end) are effectively 0.
        while (i < base32.length()) {
          // Read the 1st char in a 8-chars subblock
          // check that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 0: leave 5 bits
          nextByte = (byte) (digit << 3);
          // Assert(i < base32.length) // tested before loop
          // Read the 2nd char in a 8-chars subblock
          // Check that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 5: insert 3 bits, leave 2 bits
          bytes[offset++] = (byte) (nextByte | (digit >> 2));
          nextByte = (byte) ((digit & 3) << 6);
          if (i >= base32.length()) {
            if (nextByte != (byte) 0) {
              throw new IllegalArgumentException(errorCanonicalEnd);
            }
            break; // discard the remaining 2 bits
          }
          // Read the 3rd char in a 8-chars subblock
          // Check that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 2: leave 7 bits
          nextByte |= (byte) (digit << 1);
          // Assert(i < base32.length) // tested before loop
          // Read the 4th char in a 8-chars subblock
          // Check that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 7: insert 1 bit, leave 4 bits
          bytes[offset++] = (byte) (nextByte | (digit >> 4));
          nextByte = (byte) ((digit & 15) << 4);
          if (i >= base32.length()) {
            if (nextByte != (byte) 0) {
              throw new IllegalArgumentException(errorCanonicalEnd);
            }
            break; // discard the remaining 4 bits
          }
          // Read the 5th char in a 8-chars subblock
          // Assert that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 4: insert 4 bits, leave 1 bit
          bytes[offset++] = (byte) (nextByte | (digit >> 1));
          nextByte = (byte) ((digit & 1) << 7);
          if (i >= base32.length()) {
            if (nextByte != (byte) 0) {
              throw new IllegalArgumentException(errorCanonicalEnd);
            }
            break; // discard the remaining 1 bit
          }
          // Read the 6th char in a 8-chars subblock
          // Check that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 1: leave 6 bits
          nextByte |= (byte) (digit << 2);
          // Assert(i < base32.length) // tested before loop
          // Read the 7th char in a 8-chars subblock
          // Check that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 6: insert 2 bits, leave 3 bits
          bytes[offset++] = (byte) (nextByte | (digit >> 3));
          nextByte = (byte) ((digit & 7) << 5);
          if (i >= base32.length()) {
            if (nextByte != (byte) 0) {
              throw new IllegalArgumentException(errorCanonicalEnd);
            }
            break; // discard the remaining 3 bits
          }
          // Read the 8th char in a 8-chars subblock
          // Check that chars are not outside the lookup table and valid
          lookup = base32.charAt(i++) - '2';
          if (lookup < 0 || lookup >= base32Lookup.length) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          digit = base32Lookup[lookup];
          if (digit == -1) {
            throw new IllegalArgumentException(errorInvalidChar);
          }
          // // STEP n = 3: insert 5 bits, leave 0 bit
          bytes[offset++] = (byte) (nextByte | digit);
          // // possible end of string here with no trailing bits
        }
        // On loop exit, discard trialing n bits.
        return bytes;
      }
    }
       
//	messageId example: "20130304123844449-+-2980-DSF7IZ6VHKXCM24LX3XB4T5YDVU24_I";
	public static String getCallerID(String messageID){

		Pattern PATTERN_CALLER = Pattern.compile("(?:\\d*)-(?:[+]?\\w*)-(?:[+]?\\d*)-(\\w*)_(?:\\w)");
		Matcher matcher_caller = PATTERN_CALLER.matcher(messageID);
		String callerID = "";
		if (matcher_caller.matches()){
			callerID = matcher_caller.group(1);
			System.out.println("Lei: callerID " + callerID);
			return base32Decode(callerID);
		}
		return callerID;
	}
	public static String getTime(String messageID){
		
		Pattern PATTERN_CALLER = Pattern.compile("(\\d*)-(?:[+]?\\w*)-(?:[+]?\\d*)-(?:\\w*)_(?:\\w)");
		Matcher matcher_caller = PATTERN_CALLER.matcher(messageID);
		String callerID = "";
		if (matcher_caller.matches()){
			callerID = matcher_caller.group(1);
			System.out.println("Lei: callerID " + callerID);
			return callerID;
		}
		return callerID;
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String b = getCallerID("20131016005147717-590-1001-null_I");
		
//		String b = getCallerID("20131017213505185-14084775221-1028-UNKNOWN_E");

		String b = getCallerID("20131017213505185-14084775221-1028-3QZYFUA3NG7I4_E");
		String time = getTime("20131017213505185-14084775221-1028-3QZYFUA3NG7I4_E");
		if(b == null) {
		System.out.println("b " + b);
		}
		else {
			System.out.println("b " + b);
			System.out.println("time " + time);
			
		}
		

	}

}
