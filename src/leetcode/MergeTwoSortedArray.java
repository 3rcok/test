package leetcode;
/*
 * http://yucoding.blogspot.com/2013/01/leetcode-question-53-merge-sorted-array.html
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B.
 The number of elements initialized in A and B are m and n respectively.
 */
		
public class MergeTwoSortedArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    void merge(int A[], int m, int B[], int n) {
        // Start typing your C/C++ solution below
        // DO NOT write int main() function
        int count=m+n-1;
        m--; n--;
        while (m>=0 && n>=0){
            A[count--] = A[m]>B[n]? A[m--]:B[n--];
        }
        while (n>=0){ A[count--] = B[n--];}
    }
    

}
