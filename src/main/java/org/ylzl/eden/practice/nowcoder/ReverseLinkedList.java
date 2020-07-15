package org.ylzl.eden.practice.nowcoder;

/**
 * 反转链表
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=13&tqId=11168&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @since 2.0.0
 */
public class ReverseLinkedList {

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = null;
		ListNode current = null;
		while(head != null) {
			current = head.next; // 用于下一轮遍历
			head.next = pre; // 将当前节点指向上一轮的当前节点
			pre = head;
			head = current;
		}
		return pre;
	}

	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}
}
