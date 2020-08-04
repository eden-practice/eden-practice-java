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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.ylzl.eden.practice.data.json.LearningJackson;

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
@ToString
@EqualsAndHashCode
public class BinaryTree implements Serializable {

  private static final long serialVersionUID = -8227444090020997250L;

  private TreeNode root;

  @Data
  @ToString
  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }

  public boolean add(int val) {
    if (root == null) {
      root = new TreeNode(val);
      return true;
    }

    return add(val, root);
  }

  public boolean add(int val, TreeNode node) {
    if (val == node.val) {
      return false;
    }

    if (val > node.val) {
      if (node.right != null) {
        add(val, node.right);
      } else {
        node.right = new TreeNode(val);
      }
    }

    if (val < node.val) {
      if (node.left != null) {
        add(val, node.left);
      } else {
        node.left = new TreeNode(val);
      }
    }

    return true;
  }

  public boolean search(int val) {
    return search(val, root);
  }

  public boolean search(int val, TreeNode node) {
    if (node == null) {
      return false;
    }

    if (val == node.val) {
      return true;
    }

    if (val < node.val) {
      return search(val, node.left);
    }

    if (val > node.val) {
      return search(val, node.right);
    }

    return false;
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
		while (curNode!= null || !stack.isEmpty()) {
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
		while (curNode!= null || !stack.isEmpty()) {
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
		while (curNode!= null || !stack.isEmpty()) {
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

/*    if (node.left != null) {
      postIterator(node.left, nodes);
    }
    if (node.right != null) {
      postIterator(node.right, nodes);
    }
    nodes.add(node.val);*/
  }

  public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
    return rebuildTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
  }

  private TreeNode rebuildTree(
      int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
    if (preStart > preEnd | inStart > inEnd) {
    	return null;
		}
    TreeNode root = new TreeNode(pre[preStart]); // 根节点
    for (int i = inStart; i <= inEnd; i++) {  // 寻找根节点在中序序列的位置
      if (in[i] == pre[preStart]) {
        // 可以计算出中序序列的左右子树序列为:左：inStart~i -1，右：i+1~inEnd。
        // 前序序列的左右子树：左：preStart+1~preStart+i-inStart，右：preStart+i-inStart+1~preEnd
        root.left = rebuildTree(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1);
        root.right = rebuildTree(pre, preStart + i - inStart + 1, preEnd, in, i + 1, inEnd);
      }
    }
    return root;
  }

  public static void main(String[] args) throws JsonProcessingException {
    BinaryTree binaryTree = new BinaryTree();
    binaryTree.add(10);
    binaryTree.add(4);
    binaryTree.add(1);
    binaryTree.add(3);
    binaryTree.add(2);
    binaryTree.add(5);
    binaryTree.add(11);
    binaryTree.add(18);
    binaryTree.add(12);
    binaryTree.add(13);
    binaryTree.add(14);
    System.out.println(LearningJackson.toJSONString(binaryTree));
    System.out.println("查找 5 是否存在：" + binaryTree.search(5));
    System.out.println("查找 6 是否存在：" + binaryTree.search(6));
    System.out.println("树的深度为：" + binaryTree.depth());
    System.out.println("树根的右节点的深度为：" + binaryTree.depth(binaryTree.root.right));
    System.out.println("树的高度为：" + binaryTree.height());
    System.out.println("树的层数为：" + binaryTree.level());
    System.out.println("前序遍历：" + binaryTree.iterator(binaryTree.root, IteratorEnum.PRE));
    System.out.println("中序遍历：" + binaryTree.iterator(binaryTree.root, IteratorEnum.IN));
    System.out.println("后序遍历：" + binaryTree.iterator(binaryTree.root, IteratorEnum.POST));
  }
}
