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

import java.util.EmptyStackException;

/**
 * 栈
 *
 * <p>Stack 是线程安全类，继承 Vector，主要增加了 `push` 入栈和 `pop` 出栈的同步方法，遵循 LIFO（后进先出）原则。
 * 已被 {@link java.util.LinkedList} 代替入栈出栈。</p>
 *
 * @author gyl
 * @since 1.0.0
 */
@Deprecated
public class Stack<E> extends Vector<E> {

	/*public E push(E item) { // 入栈，添加到元素到末尾，可换为 LinkedList.add(E item)
		addElement(item);
		return item;
	}

	public synchronized E pop() { // 出栈，弹出末尾元素，可换为 LinkedList.removeLast()
		E obj;
		int len = size();
		obj = peek();
		removeElementAt(len - 1);
		return obj;
	}

	public synchronized E peek() { // 获取末尾元素，可换为 LinkedList.getLast()
		int len = size();
		if (len == 0) {
			throw new EmptyStackException();
		}
		return elementAt(len - 1);
	}*/
}
