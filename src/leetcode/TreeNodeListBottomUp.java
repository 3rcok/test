package leetcode;


/*
 * Binary Tree Level Order Traversal II Total Accepted: 9424 Total Submissions: 30577 My Submissions
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7]
  [9,20],
  [3],
]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;



// * Definition for binary tree



public class TreeNodeListBottomUp {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNodeListBottomUp bottomUp = new TreeNodeListBottomUp();
		TreeNode root = new TreeNode(5);
		bottomUp.levelOrderBottom(root);
		
	}
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null) return result;
       ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
       nodeList.add(root);
       getChildren(nodeList);
       Collections.reverse(result);

       return result;

   }
   private void getChildren(ArrayList<TreeNode> nodeList) {
       ArrayList<Integer> arrayList = new ArrayList<Integer>();
       ArrayList<TreeNode> childrenList = new ArrayList<TreeNode>();
       for(TreeNode node:nodeList) {
           if(node==null)
               continue;
       	arrayList.add(node.val);
       	if(node.left!=null) {
       		childrenList.add(node.left);
       	}
       	if(node.right!=null) {
       		childrenList.add(node.right);
       	}
       	
       }
       result.add(arrayList);
       if(!childrenList.isEmpty()) {
       	getChildren(childrenList);
       }


		
	}
	public static ArrayList<ArrayList<Integer>> levelOrderBottoma1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Stack<TreeNode> stack1 = new Stack();
        Stack<TreeNode> stack2 = new Stack();
        stack1.push(root);
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            ArrayList<Integer> list1 = new ArrayList<Integer>();

            while(!stack1.isEmpty()){
                
                TreeNode tmp = stack1.pop();
                list1.add(tmp.val);
                stack2.push(tmp.left);
                stack2.push(tmp.right);
                
            }
                        ArrayList<Integer> list2 = new ArrayList<Integer>();

            while(!stack1.isEmpty()){
                
                TreeNode tmp = stack2.pop();
                
                
            }
        
        }
        return null;
    }
}