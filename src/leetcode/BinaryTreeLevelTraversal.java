package leetcode;


	import java.util.*;
	/**
	 * Definition for binary tree
	 * */
	
	/*
	 * Is it possible that a solution exists using only one single queue? Yes, you bet. 
	 * The single queue solution requires two extra counting variables which keep tracks of the number of nodes in the current level 
	 * (nodesInCurrentLevel) and the next level (nodesInNextLevel). When we pop a node off the queue, we decrement nodesInCurrentLevel by one. 
	 * When we push its child nodes to the queue, we increment nodesInNextLevel by two. When nodesInCurrentLevel reaches 0, we know that the current l
	 * evel has ended, therefore we print an endline here.
	 */
	//http://leetcode.com/2010/09/printing-binary-tree-in-level-order.html
	
	  class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
	public class BinaryTreeLevelTraversal {
	    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
	        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	        if (root == null)
	            return list;

	        Queue<TreeNode> queue = new LinkedList<TreeNode>();

	        queue.offer(root);
	        while (!queue.isEmpty()) {
	            Queue<TreeNode> nextQueue = new LinkedList<TreeNode>();
	            ArrayList<Integer> newList = new ArrayList<Integer>();
	            while (!queue.isEmpty()) {
	                TreeNode node = queue.poll();
	                newList.add(node.val);

	                if (node.left != null)
	                    nextQueue.offer(node.left);
	                if (node.right != null)
	                    nextQueue.offer(node.right);
	            }
	            list.add(newList);
	            queue.addAll(nextQueue);
	            nextQueue = new LinkedList<TreeNode>();
	        }
	        return list;
	    }
	}
