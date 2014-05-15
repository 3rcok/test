package leetcode;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

http://n00tc0d3r.blogspot.com/2013/06/substring-with-concatenation-of-all.html

 */

public class SubStringIndexes {
	//m is number of strings in List, k is the length of each string, n is the length of the whole String
/*
	The time of checking a concatenation is O(k*m) = O(m) and we check it (n - k*m) times, so the total running time is O((n - k*m)*m). The space complexity is the size of list L, 
	which is O(k*m) = O(m),  since we create a hash table of list L.*/
			 public ArrayList<Integer> findSubstringM2(String S, String[] L) {  
			   ArrayList<Integer> indices = new ArrayList<Integer>();  
			   if (L.length == 0) return indices;  
			   
			   int total = L.length, wordLen = L[0].length();  
			   
			   // store the words and frequencies in a hash table  
			   HashMap<String, Integer> words = new HashMap<String, Integer>();  
			   for (String s : L) {  
			     if (words.containsKey(s)) {  
			       words.put(s, words.get(s)+1);  
			     } else {  
			       words.put(s, 1);  
			     }  
			   }  
			   
			   // find concatenations  
			   for (int i=0; i <= S.length() - total*wordLen; ++i) {  
			     // check if it is a concatenation   
			     HashMap<String, Integer> target = new HashMap<String, Integer>(words);  
			     for (int j = i; j <= S.length() - wordLen && !target.isEmpty(); j+=wordLen) {  
			       String sub = S.substring(j, j+wordLen);  
			       if (!target.containsKey(sub)) break;  
			       if (target.get(sub) > 1) {  // reduce the frequency
			         target.put(sub, target.get(sub)-1);  
			       } else {  // remove the word if only one left
			         target.remove(sub);  
			       }
			     }  
			     if (target.isEmpty()) {  
			       indices.add(i);  
			     }  
			   }  
			   
			   return indices;  
			 }  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * We hit each substring at most twice, one to add into the collection and one to remove from the collection. There are O(n) substrings in total, where n is the total number of characters in string S, and for each substring it takes O(k) = O(1) to check whether it is a valid word or not.
So, in total, this algorithm runs in time O(n*k) = O(n).
	 */
	private void addWord(String w, HashMap<String, Integer> words) {  
		   if (words.containsKey(w)) {  
		     words.put(w, words.get(w)+1);  
		   } else {  
		     words.put(w, 1);  
		   }  
		 }  
		   
		 private void removeWord(String w, HashMap<String, Integer> words) {  
		   if (!words.containsKey(w)) return;  
		   if (words.get(w) > 1) {  
		     words.put(w, words.get(w)-1);  
		   } else {  
		     words.remove(w);  
		   }  
		 }  
		   
		 private int slideWindow(String S, int begin, int wordLen, HashMap<String, Integer> words) {  
		   String old = S.substring(begin, begin+wordLen);  
		   addWord(old, words);  
		   return begin+wordLen;  
		 }  
		   
		 public ArrayList<Integer> findSubstring(String S, String[] L) {  
		   ArrayList<Integer> indices = new ArrayList<Integer>();  
		   if (L.length == 0) return indices;  
		   
		   int total = L.length, wordLen = L[0].length();  
		   
		   // store the words and frequencies in a hash table  
		   HashMap<String, Integer> expectWords = new HashMap<String, Integer>();  
		   for (String w : L) {  
		     addWord(w, expectWords);  
		   }  
		   
		   // find concatenations  
		   for (int i=0; i < wordLen; ++i) {  
		     // check if there are any concatenations  
		     int count = 0;  
		     HashMap<String, Integer> collectWords = new HashMap<String, Integer>(expectWords);  
		     for (int j = i, begin = i; j <= S.length() - (total-count)*wordLen && begin <= S.length() - total*wordLen;) {  
		       String sub = S.substring(j, j+wordLen);  
		       if (!expectWords.containsKey(sub)) { // if not an expect word, reset  
		         begin = j + wordLen;  
		         j = begin;  
		         count = 0;  
		         collectWords.putAll(expectWords);  
		       } else if (!collectWords.containsKey(sub)) { // if duplicate, forward begin by 1  
		         begin = slideWindow(S, begin, wordLen, collectWords);  
		       } else {  
		         removeWord(sub, collectWords);  
		         j += wordLen;  
		         ++count;  
		         if (collectWords.isEmpty()) {  
		           indices.add(begin);  
		           begin = slideWindow(S, begin, wordLen, collectWords);  
		           --count;  
		         }  
		       }  
		     }  
		   }  
		   
		   return indices;  
		 }  
}
