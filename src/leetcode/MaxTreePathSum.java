package leetcode;


/* Definition for binary tree*/

 
public class MaxTreePathSum {
	public static void main(String[] args) {
//		TreeNode treeNode = new TreeNode(10);
//		TreeNode treeNode2 = new TreeNode(-1);
//		TreeNode treeNode3 = new TreeNode(7);
//		TreeNode treeNode4 = new TreeNode(9);
//		TreeNode treeNode5 = new TreeNode(-4);
//		treeNode.left = treeNode2;
//		treeNode.right = treeNode3;
//		treeNode2.left = treeNode4;
//		treeNode2.right = treeNode5;
		
		TreeNode treeNode = new TreeNode(-1);
		TreeNode treeNode2 = new TreeNode(-2);
		TreeNode treeNode3 = new TreeNode(-3);

		treeNode.left = treeNode2;
		treeNode.right = treeNode3;		
		MaxTreePathSum maxTreePathSum = new MaxTreePathSum();
		int maxPathSum = maxTreePathSum.maxPathSum(treeNode);
		System.out.println(" max " + maxPathSum);
	}
	//store max value
	int max; 
 
	public int maxPathSum(TreeNode root) {
		max = (root == null) ? 0 : root.val;
		findMax(root);
		return max;
	}
 
	public int findMax(TreeNode node) {
		if (node == null)
			return 0;
 
		// recursively get sum of left and right path
		
		int left = Math.max(findMax(node.left), 0);
		System.out.println("left " + left);
		int right = Math.max(findMax(node.right), 0);
		System.out.println("right " + right);
 
		//update maximum here
		max = Math.max(node.val + left + right, max);
 
		// return sum of largest path of current node
		return node.val + Math.max(left, right);
	}
	
	public int maxPathSumUserArray(TreeNode root) {
		int max[] = new int[1]; 
		max[0] = Integer.MIN_VALUE;
		calculateSum(root, max);
		return max[0];
	}
 
	public int calculateSum(TreeNode root, int[] max) {
		if (root == null)
			return 0;
 
		int left = calculateSum(root.left, max);
		int right = calculateSum(root.right, max);
 
		int current = Math.max(root.val, Math.max(root.val + left, root.val + right));
 
		max[0] = Math.max(max[0], Math.max(current, left + root.val + right));
 
		return current;
	}
}