package org.ylzl.eden.practice.nowcoder;

/**
 * 合并链表
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking">剑指
 *     Offer 原题</a>
 * @since 2.0.0
 */
public class ListMerge {

  public ListNode merge(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }

    if (list2 == null) {
      return list1;
    }

    if (list1.val < list2.val) {
      list1.next = merge(list1.next, list2);
      return list1;
    } else {
      list2.next = merge(list1, list2.next);
      return list2;
    }
  }

  public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
      this.val = val;
    }
  }
}
