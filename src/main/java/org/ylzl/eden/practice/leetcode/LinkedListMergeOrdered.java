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

package org.ylzl.eden.practice.leetcode;

/**
 * 合并两个有序链表
 *
 * @author gyl
 * @see <a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/">leetcode 原题</a>
 * @since 2.0.0
 */
public class LinkedListMergeOrdered {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// l1：1 -> 3 -> 4 -> 5
		// l2：2 -> 6 -> 7 -> 8

		if (l1 == null) {
			return l2;
		}

		if (l2 == null) {
			return l1;
		}

		ListNode merge = new ListNode();
		ListNode cursor = merge;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) { // 1 < 2
				cursor.next = l1; // 1
				l1 = l1.next; // 1 -> 3, l1 = 3
			} else {
				cursor.next = l2;
				l2 = l2.next;
			}
			cursor = cursor.next;
		}
		cursor.next = l1 == null? l2 : l1;
		return merge.next;
	}


	public class ListNode {
		  int val;
      ListNode next;

      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
