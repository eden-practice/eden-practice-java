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
 * 反转链表
 *
 * @author gyl
 * @see <a href="https://leetcode-cn.com/problems/reverse-linked-list">leetcode 原题</a>
 * @since 2.0.0
 */
public class LinkedListReverse {

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode reverse = null;
		ListNode cursor = null;
		while (head != null) {
			cursor = head.next; // current = 2，保留 head.next = 2 # current = 3
			head.next = reverse; // head = 1， 1 -> null # head = 1， 3 ->
			reverse = head; // reverse = 1 #
			head = cursor; // head = 2 #
		}
		return reverse;
	}

	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}
}
