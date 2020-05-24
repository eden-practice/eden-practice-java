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

import java.util.ArrayList;

/**
 * 从尾到头打印链表
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @since 2.0.0
 */
public class PrintListFromTailToHead {

  public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    ArrayList<Integer> arrayList = new ArrayList<>();
    if (listNode != null) {
      arrayList.addAll(printListFromTailToHead(listNode)); // 从尾到头
      arrayList.add(listNode.val);
    }
    return arrayList;
  }

  public class ListNode {

    int val;

    ListNode next = null;

    ListNode(int val) {
      this.val = val;
    }
  }
}
