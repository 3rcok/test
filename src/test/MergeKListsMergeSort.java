package test;

import java.util.ArrayList;

public class MergeKListsMergeSort {

	public ListNode mergeKLists(ArrayList<ListNode> lists) {  
		   int last = lists.size() - 1;  
		   if (last < 0) return null;  
		     
		   while (last > 0) {  
		     int cur = 0;  
		     while (cur < last) {  
		       lists.set(cur, mergeTwoLists(lists.get(cur++), lists.get(last--)));  
		     }  
		   }  
		     
		   return lists.get(0);  
		 } 

/*	 
Merge Sorted Lists/Arrays
Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.
Solution

Merging two linked list is not difficult. Basically, we need two extra pointers:
root of the resulting list that will never be moved
current pointer that move along the two list and link nodes to its end
Here is how they work:
 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {  
   if (l1 == null) return l2;  
   if (l2 == null) return l1;  

   ListNode root;  
   if (l1.val <= l2.val) {  
     root = l1;  
     l1 = l1.next;  
   } else {  
     root = l2;  
     l2 = l2.next;  
   }  

   ListNode cur = root;  
   while (l1 != null || l2 != null) {  
     if (l2 == null || (l1 != null && l1.val <= l2.val)) {  
       cur.next = l1;  
       l1 = l1.next;  
     } else {  
       cur.next = l2;  
       l2 = l2.next;  
     }  
     cur = cur.next;  
   }  

   return root;  
 }  
 
 */
//Since this is an in-place merging, it takes O(m+n) time and O(1) space.

//Adding a dummy head node can make the code look simpler.
 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {  
   ListNode head = new ListNode(0);  
   ListNode cur = head;  

   while (l1 != null || l2 != null) {  
     if (l2 == null || (l1 != null && l1.val <= l2.val)) {  
       cur.next = l1;  
       l1 = l1.next;  
     } else {  
       cur.next = l2;  
       l2 = l2.next;  
     }  
     cur = cur.next;  
   }  

   return head.next;  
 }  	
	
//	To get it done in O(n) time, as we already know how many numbers there, we iterate from the ends and  put each number to its final spot directly.
//A[] has m elements, B[] has n elements, A[] has extra space for both A and B
	/*
	public void merge(int A[], int m, int B[], int n) {  
	   int tail = m + n - 1;  
	   --n; --m;  
	   while (n >= 0) {  
	     if (m <0 || A[m] <= B[n]) {  
	       A[tail--] = B[n--];  
	     } else {  
	       A[tail--] = A[m--];  
	     }  
	   }  
	 }	
	 */
}
