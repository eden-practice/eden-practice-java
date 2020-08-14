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

package org.ylzl.eden.practice.collections.queue;

import org.ylzl.eden.practice.collections.iterator.Iterator;

/**
 * 反队列（栈）
 *
 * @author gyl
 * @since 2.0.0
 */
public interface Deque<E> extends Queue<E> {

	void addFirst(E e);

	void addLast(E e);

	boolean offerFirst(E e);

	boolean offerLast(E e);

	E removeFirst();

	E removeLast();

	E pollFirst();

	E pollLast();

	E getFirst();

	E getLast();

	E peekFirst();

	E peekLast();

	boolean removeFirstOccurrence(Object o);

	boolean removeLastOccurrence(Object o);

	// 面向 Queue 的接口方法

	boolean add(E e);

	boolean offer(E e);

	E remove();

	E poll();

	E element();

	E peek();

	// 面向 Stack 的接口方法

	void push(E e);

	E pop();

	// 面向 Collection 的接口方法

	boolean remove(Object o);

	boolean contains(Object o);

	public int size();

	Iterator<E> iterator();

	Iterator<E> descendingIterator();
}
