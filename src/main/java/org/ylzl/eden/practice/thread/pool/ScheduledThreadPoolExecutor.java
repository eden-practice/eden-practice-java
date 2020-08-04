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

package org.ylzl.eden.practice.thread.pool;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class ScheduledThreadPoolExecutor extends ThreadPoolExecutor implements ScheduledExecutorService {

	public ScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue());
	}

	static class DelayedWorkQueue extends AbstractQueue<Runnable> implements BlockingQueue<Runnable> {

		@Override
		public Iterator<Runnable> iterator() {
			return null;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public void put(Runnable runnable) throws InterruptedException {

		}

		@Override
		public boolean offer(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
			return false;
		}

		@Override
		public Runnable take() throws InterruptedException {
			return null;
		}

		@Override
		public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
			return null;
		}

		@Override
		public int remainingCapacity() {
			return 0;
		}

		@Override
		public int drainTo(Collection<? super Runnable> c) {
			return 0;
		}

		@Override
		public int drainTo(Collection<? super Runnable> c, int maxElements) {
			return 0;
		}

		@Override
		public boolean offer(Runnable runnable) {
			return false;
		}

		@Override
		public Runnable poll() {
			return null;
		}

		@Override
		public Runnable peek() {
			return null;
		}
	}

	@Override
	public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
		return null;
	}

	@Override
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
		return null;
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
		return null;
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
		return null;
	}
}
