import java.util.Arrays;
import java.util.HashSet;
import static org.junit.Assert.*;
//import static junit.framework.Assert.*; //this junit3 package

public class LongestUniqSubstringTest {

	/**
	 * @param args
	 */
	public static void main(String... args) {
	    String input[][] = { { "" }, { "a" }, { "ab" }, { "aab" }, { "abb" },
	            { "aabc" }, { "abbc" }, { "aabbccdefgbc" },
	            { "abcdeafghicabcdefghijklmnop" },
	            { "abcdeafghicabcdefghijklmnopqrabcdx" },
	            { "zxxaabcdeafghicabcdefghijklmnopqrabcdx" },
	            {"aaabcdefgaaa"}};
	    System.out.println("ll  ");
	    for (String[] a : input) {
	        System.out.format("%s  *** GIVES ***  {%s}%n", Arrays.toString(a),
	                longestUniqueString(a[0]));
//	        assertEquals("aabc", "bc", longestUniqueString("aabc"));
	    }
	    
	    for (String[] a : input) {
	        System.out.format("%s  *** GIVES ***  {%s}%n", Arrays.toString(a),
	        		LongestSubString(a[0]));
	    }
	}
	public static String longestUniqueString(String S) {
	    int start = 0, end = 0, length = 0;
	    boolean bits[] = new boolean[256];
	    int x = 0, y = 0;
	    for (; x < S.length() && y < S.length() && length < S.length() - x; x++) {
	        bits[S.charAt(x)] = true;
	        for (y++; y < S.length() && !bits[S.charAt(y)]; y++) {
	            bits[S.charAt(y)] = true;
	        }
	        if (length < y - x) {
	            start = x;
	            end = y;
	            length = y - x;
	        }
	        while(y<S.length() && x<y && S.charAt(x) != S.charAt(y))
	            bits[S.charAt(x++)]=false;
	    }
	    return S.substring(start, end);
	}//
	
	private static String LongestSubString(String word)
    {
		char[] charArray = word.toCharArray();
		HashSet set = new HashSet();
        String longestOverAll = "";
        String longestTillNow = "";
        for (int i = 0; i < charArray.length; i++) {
        	Character c = charArray[i];

            if (set.contains(c)) {
                longestTillNow = "";
                set.clear();
            }
            longestTillNow += c;
            set.add(c);
            if (longestTillNow.length() > longestOverAll.length())
            {
                longestOverAll = longestTillNow;

            }
        }

        return longestOverAll;
    }

}
