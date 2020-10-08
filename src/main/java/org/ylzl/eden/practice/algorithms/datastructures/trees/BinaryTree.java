/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.practice.algorithms.datastructures.trees;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

/**
 * 普通二叉树
 *
 * @author gyl
 * @since 2.0.0
 */
@Data
public class BinaryTree implements Serializable {

	private static final long serialVersionUID = -8227444090020997250L;

	private TreeNode root;

	private int level;

	public boolean insert(int val) {
		if (root == null) {
			root = new TreeNode(val);
			level++;
			return true;
		}

		return insert(val, root, 1);
	}

	private boolean insert(int val, TreeNode parent, int curLevel) {
		if (val == parent.val) {
			return false;
		}

		if (val > parent.val) {
			if (parent.right == null) {
				parent.right = new TreeNode(val);
				if (curLevel > level) {
					level = curLevel;
				}
				return true;
			}
			return insert(val, parent.right, curLevel + 1);
		} else {
			if (parent.left == null) {
				parent.left = new TreeNode(val);
				if (curLevel > level) {
					level = curLevel;
				}
				return true;
			}
			return insert(val, parent.left, curLevel + 1);
		}
	}

	public TreeNode search(int val) {
		return search(val, root);
	}

	private TreeNode search(int val, TreeNode parent) {
		if (parent == null) {
			return null;
		}

		if (val == parent.val) {
			return parent;
		}

		if (val < parent.val) {
			return search(val, parent.left);
		} else {
			return search(val, parent.right);
		}
	}

	public int level() {
		return depth(root) + 1;
	}

	public int height() {
		return depth(root);
	}

	public int depth() {
		return depth(root);
	}

	public int depth(TreeNode node) {
		if (node == null || (node.left == null && node.right == null)) {
			return 0;
		}

		return Math.max(depth(node.left), depth(node.right)) + 1;
	}

	public enum IteratorEnum {
		PRE,
		IN,
		POST;
	}

	public List<Integer> iterator(TreeNode node, IteratorEnum iteratorEnum) {
		if (root == null) {
			return null;
		}

		List<Integer> nodes = Lists.newArrayList();
		switch (iteratorEnum) {
			case PRE:
				preIterator(node, nodes);
				break;
			case IN:
				inIterator(node, nodes);
				break;
			case POST:
				postIterator(node, nodes);
				break;
		}
		return nodes;
	}

	public void preIterator(TreeNode node, List<Integer> nodes) {
		if (node == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<>();
		TreeNode curNode = node;
		while (curNode != null || !stack.isEmpty()) {
			while (curNode != null) {
				nodes.add(curNode.val);
				stack.push(curNode);
				curNode = curNode.left;
			}
			if (!stack.isEmpty()) {
				curNode = stack.pop();
				curNode = curNode.right;
			}
		}

    /*nodes.add(node.val);
    if (node.left != null) {
      preIterator(node.left, nodes);
    }
    if (node.right != null) {
      preIterator(node.right, nodes);
    }*/
	}

	public void inIterator(TreeNode node, List<Integer> nodes) {
		if (node == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<>();
		TreeNode curNode = node;
		while (curNode != null || !stack.isEmpty()) {
			while (curNode != null) {
				stack.push(curNode);
				curNode = curNode.left;
			}
			if (!stack.isEmpty()) {
				curNode = stack.pop();
				nodes.add(curNode.val);
				curNode = curNode.right;
			}
		}

    /*if (node.left != null) {
      inIterator(node.left, nodes);
    }
    nodes.add(node.val);
    if (node.right != null) {
      inIterator(node.right, nodes);
    }*/
	}

	public void postIterator(TreeNode node, List<Integer> nodes) {
		if (node == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<>();
		TreeNode curNode = node;
		TreeNode visitedNode = node;
		while (curNode != null || !stack.isEmpty()) {
			while (curNode != null) {
				stack.push(curNode);
				curNode = curNode.left;
			}
			curNode = stack.peek();
			if (curNode.right == null || curNode.right == visitedNode) {
				stack.pop();
				nodes.add(curNode.val);
				visitedNode = curNode;
				curNode = null;
			} else {
				curNode = curNode.right;
			}
		}

		/* if (node.left != null) {
      postIterator(node.left, nodes);
    }
    if (node.right != null) {
      postIterator(node.right, nodes);
    }
    nodes.add(node.val);*/
	}

	public TreeNode reConstruct(int[] pre, int[] in) {
		return reConstruct(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	private TreeNode reConstruct(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
		if (preStart > preEnd | inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preStart]); // 根节点
		for (int i = inStart; i <= inEnd; i++) { // 寻找根节点在中序序列的位置
			if (in[i] == pre[preStart]) {
				// 中序序列的左子树为 inStart 到 i-1，右子树为 i+1 到 inEnd
				// 前序序列的左子树为 preStart+1 到 preStart+i-inStart，右子树为 preStart+i-inStart+1 到 preEnd
				root.left = reConstruct(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1);
				root.right = reConstruct(pre, preStart + i - inStart + 1, preEnd, in, i + 1, inEnd);
			}
		}
		return root;
	}

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		binaryTree.insert(10);
		binaryTree.insert(4);
		binaryTree.insert(1);
		binaryTree.insert(3);
		binaryTree.insert(2);
		binaryTree.insert(5);
		binaryTree.insert(11);
		binaryTree.insert(13);
		binaryTree.insert(14);
		binaryTree.insert(17);
		binaryTree.insert(15);
		binaryTree.insert(18);
		System.out.println("查找 5 是否存在：" + binaryTree.search(5));
		System.out.println("查找 6 是否存在：" + binaryTree.search(6));
		System.out.println("树的深度为：" + binaryTree.depth());
		System.out.println("树根的右节点 " + binaryTree.root.right + " 的深度为：" + binaryTree.depth(binaryTree.root.right));
		System.out.println("树的高度为：" + binaryTree.height());
		System.out.println("树的层数为：" + binaryTree.level());
		System.out.println("前序遍历：" + binaryTree.iterator(binaryTree.root, IteratorEnum.PRE));
		System.out.println("中序遍历：" + binaryTree.iterator(binaryTree.root, IteratorEnum.IN));
		System.out.println("后序遍历：" + binaryTree.iterator(binaryTree.root, IteratorEnum.POST));
		binaryTree.root.print();
	}
}
