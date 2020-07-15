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

import org.ylzl.eden.practice.collections.Collection;
import org.ylzl.eden.practice.collections.Iterator;

import java.io.Serializable;

/**
 * 链表 List
 *
 * @author gyl
 * @since 2.0.0
 */
public class LinkedList<E> implements List<E>, Cloneable, Serializable {

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
		Node<E> x;
		if (index < (size >> 1)) { // 二分法（但没有递归），复杂度为 O(n/2)，为什么不优化为 O(logn)？问题待定
			x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
		} else {
			x = last;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
		}
		return x;
	}


	@Override
	public boolean add(E element) {
		linkLast(element);
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index == size) {
			add(element);
		} else {
			linkBefore(element, node(index));
		}
	}

	private void linkBefore(E element, Node<E> indexNode) {
		Node<E> prev = indexNode.prev;
		Node<E> newNode = new Node<>(prev, element, indexNode.next);
		if (prev == null) {
			first = newNode;
		} else {
			prev.next = first;
		}
		size++;
		modCount++;
	}

	private void linkLast(E element) {
		Node<E> l = last;
		Node<E> newNode = new Node<>(l, element, null);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			last.next = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public E remove(int index) {
		return null;
	}

	@Override
	public E get(int index) {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public void clear() {

	}

	@Override
	public E set(int index, E element) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	private void rangeCheck(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("索引越界，index：" + index);
		}
	}
}
