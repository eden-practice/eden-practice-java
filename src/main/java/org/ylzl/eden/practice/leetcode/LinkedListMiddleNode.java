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
 * 求链表的中间结点
 *
 * @author gyl
 * @see <a href="https://leetcode-cn.com/problems/middle-of-the-linked-list/">leetcode 原题</a>
 * @since 2.0.0
 */
public class LinkedListMiddleNode {

	public ListNode middleNode(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		// 1 2 3 4 5 6 7 8 9
		ListNode slow = head; // 1
		ListNode fast = head.next; // 2
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next; // 4，6，8
			slow = slow.next; // 2，3，4
		}
		return slow.next;
	}

	public class ListNode {
      int val;
      ListNode next;

      ListNode(int x) { val = x; }
  }
}
