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

package org.ylzl.eden.practice.collections.queue;

import org.ylzl.eden.practice.collections.iterator.Collection;
import org.ylzl.eden.practice.collections.iterator.Iterator;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public void put(E e) throws InterruptedException {

	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public E take() throws InterruptedException {
		return null;
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		return null;
	}

	@Override
	public int remainingCapacity() {
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
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
