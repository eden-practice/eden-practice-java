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

package org.ylzl.eden.practice.nowcoder;

import java.util.Arrays;

/**
 * TODO 重建二叉树
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @see Arrays#copyOfRange
 * @since 1.0.0
 */
public class ReConstructBinaryTree {

  public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
    return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
  }

  // 前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
  private TreeNode reConstructBinaryTree(
      int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
    if (startPre > endPre || startIn > endIn) {
      return null;
    }
    TreeNode root = new TreeNode(pre[startPre]);
    for (int i = startIn; i <= endIn; i++) {
      if (in[i] == pre[startPre]) {
        root.left =
            reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
        root.right =
            reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
        break;
      }
    }
    return root;
  }

  /** 树节点 */
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
