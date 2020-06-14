/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
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

package org.ylzl.eden.practice.collections.list;

import java.io.Serializable;

/**
 * 链表 List
 *
 * @author gyl
 * @since 2.0.0
 */
public class AbstractLinkedList<E> extends SequentialListAbstract<E> implements List<E>, Cloneable, Serializable {

	transient int size = 0;

	transient Node<E> first; // 保存首部节点，可知获取第一个元素的复杂度为 O(1)

	transient Node<E> last; // 保存尾部节点，可知获取最后一个元素的复杂度为 O(1)

	protected transient int modCount = 0;

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	Node<E> node(int index) {
		if (index < (size >> 1)) { // 二分法（但没有递归），复杂度为 O(n/2)，为什么不优化为 O(logn)？问题待定
			Node<E> x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		} else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public void clear() {

	}

	private E unlinkFirst(Node<E> f) {
		final E element = f.item;
		final Node<E> next = f.next;
		f.item = null;
		f.next = null; // 为了尽快 GC 回收
		first = next;
		if (next == null) {
			last = null;
		} else {
			next.prev = null;
		}
		size--;
		modCount++;
		return element;
	}
}
