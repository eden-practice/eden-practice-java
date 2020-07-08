package org.ylzl.eden.practice.nowcoder;

/**
 * 生成二叉树的镜像（左右调换）
 *
 * @author gyl
 * @since 2.0.0
 * @see <a
 *     href="https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking">剑指
 *     Offer 原题</a>
 */
public class BinaryTreeMirror {

	public void mirror(TreeNode root) {
		if (root == null) {
			return;
		}

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		mirror(root.left);
		mirror(root.right);
	}

	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}
}
