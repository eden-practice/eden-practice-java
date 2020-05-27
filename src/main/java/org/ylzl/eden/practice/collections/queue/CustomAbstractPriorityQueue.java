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

import org.ylzl.eden.practice.collections.CustomIterator;

import java.io.Serializable;

/**
 * 优先队列
 *
 * <p>使用完全二叉树表示，保证每次取出的元素都是队列中权值最小的</p>
 *
 * @author gyl
 * @since 2.0.0
 */
public class CustomAbstractPriorityQueue<E> extends CustomQueueAbstract<E> implements Serializable {

	@Override
	public CustomIterator<E> iterator() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean offer(E e) {
		return false;
	}

	@Override
	public E poll() {
		return null;
	}

	@Override
	public E peek() {
		return null;
	}
}
