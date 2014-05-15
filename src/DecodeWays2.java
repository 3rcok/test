/*
http://stackoverflow.com/questions/20342462/review-an-answer-decode-ways
	*/
/**
 * LeetCode
 * Decode Ways
 * 
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
 
For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 
The number of ways decoding "12" is 2.
 
 * solution by zingshow on June 27, 2013
 * two algorithms to solve this problem
 * 1. graph + depth-first search (DFS) + recursion
 * 2. dynamic programming (DP) (current code). it is very similar to "Climbing 
 * Stairs" question. Find the solution to sub-problem first and grow sub-problem.
 * 
 * algorithm 2:
 * 1) find the number of ways to decode the first char: 1 if the first char 
 * (char 1) is between "1" and "9", 0 if else (and return 0). 
 * we can assume a virtual char (char 0)
 * is before the first char and the number of ways to decode it is 1.
 * 2) decode the string until second char (char 2) 
 * and the rest with four situations: 
 * 
 * a) if char n is ("1" to "9") and char n-1 + char n is ("10" to "26"), 
 * the number of ways to decode 
 * the string until char n is the sum of the number of ways of 
 * decoding the string until char n-1 and decoding the string until char n-2: 
 * for string until char 2, it is number of ways for char 0 
 * plus number of ways for char 1;
 * 
 * b) if char n is ("1" to "9") but char n-1 + char n is not ("10" to "26"), 
 * the number of ways to decode the string until char n
 * is equal to the number of ways to decode the string until char n-1:
 * for string until char 2, it is equal to the number of ways for char 1;
 * 
 * c) if char n is not ("1" to "9") but char n-1 + char n is ("10" to "26"), 
 * the number of ways to decode the string until char n
 * is equal to the number of ways to decode the string until char n-2:
 * for string until char 2, it is equal to number of ways for char 0;
 * 
 * d) if neither char n is ("1" to "9") nor char n-1 + char n is ("10" to "26"), 
 * the number of ways to decode the string until char n is zero and zero can 
 * be returned immediately
 * 
 * comments: DP has less time and space complexity O(1).
 * 
 */
 
 
 
public class DecodeWays2 {
 
    public int numDecodings(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (s == null || s.equals("")) return 0;
        char[] cc = s.toCharArray(); //conver to char array
        int n = s.length(); //number of chars
        int numEn[] = new int[n+1];
        numEn[0] = 1;
        if (cc[0] >= '1' && cc[0] <= '9') numEn[1] = 1;
        else return 0;
         
        for (int i = 1; i < n; i ++) {
            if (cc[i] >= '1' && cc[i] <= '9') {
                if (s.substring(i-1,i+1).compareTo("10") >= 0
                        && s.substring(i-1,i+1).compareTo("26") <= 0) {
                    numEn[i+1] = numEn[i] + numEn[i-1];
                } else {
                    numEn[i+1] = numEn[i];
                }
            } else {
                if (s.substring(i-1,i+1).compareTo("10") >= 0
                        && s.substring(i-1,i+1).compareTo("26") <= 0) {
                    numEn[i+1] = numEn[i-1];
                } else {
                    return 0;
                }
            }
        }
         
        return numEn[n];
    }
    static final int upperLimit  = 26;
static final int maxHeadSize = ("" + upperLimit).length();

public  int numDecodings2(String s) {
    // check base case for the recursion
    if (s.length() == 0) {
        return 1;
    }

    // sum all tails
    int sum = 0;
    for (int headSize = 1; headSize <= maxHeadSize && headSize <= s.length(); headSize++) {
        String head = s.substring(0, headSize);
        String tail = s.substring(headSize);
        if (Integer.parseInt(head) > upperLimit) {
            break;
        }
        sum += numDecodings(tail);
    }

    return sum;
}
 public int numDecodings3(String encodedText) {
    int[] cache = new int[encodedText.length() + 1];

    // base case: the empty string at encodedText.length() is 1:
    cache[encodedText.length()] = 1;

    for (int position = encodedText.length() - 1; position >= 0; position--) {
        // sum directly into the cache
        for (int headSize = 1; headSize <= maxHeadSize && headSize + position <= encodedText.length(); headSize++) {
            String head = encodedText.substring(position, position + headSize);
            if (Integer.parseInt(head) > upperLimit) {
                break;
            }
            cache[position] += cache[position + headSize];
        }
    }

    return cache[0];
}     
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DecodeWays2 x = new DecodeWays2();
        System.out.println(x.numDecodings3("123"));
//        System.out.println(x.numDecodings2("226"));
//        System.out.println(x.numDecodings2("1203040"));
//        System.out.println(x.numDecodings2("1787897759966261825913315262377298132516969578441236833255596967132573482281598412163216914566534565"));
//        
//        
//        System.out.println(x.numDecodings("30"));
//        System.out.println(x.numDecodings("226"));
//        System.out.println(x.numDecodings("1203040"));
//        System.out.println(x.numDecodings("1787897759966261825913315262377298132516969578441236833255596967132573482281598412163216914566534565"));
         
    }
 
}