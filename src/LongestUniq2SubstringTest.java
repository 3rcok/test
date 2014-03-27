import java.util.Arrays;
import java.util.HashSet;
import static org.junit.Assert.*;
//import static junit.framework.Assert.*; //this junit3 package

public class LongestUniq2SubstringTest {

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
	        		longestSubString(a[0]));
//	        assertEquals("aabc", "bc", longestUniqueString("aabc"));
	    }

	}
	
	private static String longestSubString(String str)
    {
		if(str.length()<=2) {
			return str;
		}
		// Find the first letter that is not equal to the first one, 
		// or return the entire string if it consists of one type of characters
		int start = 0;
		int i = 1;
		String max="";
		while (i < str.length() && str.charAt(i) == str.charAt(start))
		    i++;
		if (i == str.length())
		    return str;

		// The main algorithm
		char[] chars = new char[2];
		chars[0]=str.charAt(start);
		chars[1] = str.charAt(i);
		int lastGroupStart = 0;
		while (i < str.length()) {
		    if (str.charAt(i) == chars[0] || str.charAt(i) == chars[1]) {
		        if (str.charAt(i) != str.charAt(i-1))
		            lastGroupStart = i;
		    }
		    else {
		        //TODO: str.substring(start, i) is a locally maximal string; 
		        //      compare it to the longest one so far
		    	String current = str.substring(start, i);
		    	if(current.length() > max.trim().length()) {
		    		max  = current;
		    	}
		        start = lastGroupStart;
		        lastGroupStart = i;
		        chars[0] = str.charAt(start);
		        chars[1] = str.charAt(lastGroupStart);
		    }
		    i++;
		}
		//TODO: After the loop, str.substring(start, str.length()) 
//      is also a potential solution.
		String result = str.substring(start);
		System.out.println("result " + result);
		if(result.length()> max.length()) {
			max=result;
		}
		return max;
    }

}
