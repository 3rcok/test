package test;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
 //nlogk, k size of list n number of total elements
//  Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;
 
	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class MergeKListPriorityQueue {
	public static void main(String[] args) {
		ListNode list13 = new ListNode(13);
		ListNode list12 = new ListNode(12);		

		ListNode list10 = new ListNode(10);		
		list10.next=list12;
		list12.next=list13;

		ListNode list23 = new ListNode(23);
		ListNode list22 = new ListNode(22);		

		ListNode list20 = new ListNode(20);		
		list20.next=list22;
		list22.next=list23;		
		ArrayList lists = new ArrayList();
		lists.add(list10);
		lists.add(list20);
		ListNode result = mergeKLists(lists);
		while(result.next != null) {
			System.out.println(" " + result.next.val);
			result=result.next ;
		}
	}
	public static ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists.size() == 0)
			return null;
 
		//PriorityQueue is a sorted queue
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(),
				new Comparator<ListNode>() {
					public int compare(ListNode a, ListNode b) {
						if (a.val > b.val)
							return 1;
						else if(a.val == b.val)
							return 0;
						else 
							return -1;
					}
				});
 
		//add first node of each list to the queue
		for (ListNode list : lists) {
			if (list != null)
				q.add(list);
		}
 
		ListNode head = new ListNode(0);
		ListNode prev = head;
 
		while (q.size() > 0) {
			ListNode temp = q.poll();
			prev.next = temp;
 
			//keep adding next element of each list
			if (temp.next != null)
				q.add(temp.next);
 
			prev = prev.next;
		}
 
		return head.next;
	}
}
