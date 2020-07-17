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
 * 删除链表的倒数第N个节点
 *
 * @author gyl
 * @see <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/">leetcode 原题</a>
 * @since 2.0.0
 */
public class LinkedListRemoveIndex {

  public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre = new ListNode(0);
		pre.next = head;
		ListNode start = pre;
		ListNode end = pre;
		while(n != 0) {
			start = start.next;
			n--;
		}
		while(start.next != null) {
			start = start.next;
			end = end.next;
		}
		end.next = end.next.next;
		return pre.next;
  }

  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}
