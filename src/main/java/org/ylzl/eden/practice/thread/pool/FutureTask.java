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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class FutureTask<V> implements RunnableFuture<V> {

	/**
	 * Possible state transitions:
	 * NEW -> COMPLETING -> NORMAL
	 * NEW -> COMPLETING -> EXCEPTIONAL
	 * NEW -> CANCELLED
	 * NEW -> INTERRUPTING -> INTERRUPTED
	 */
	private volatile int state;
	private static final int NEW          = 0;
	private static final int COMPLETING   = 1;
	private static final int NORMAL       = 2;
	private static final int EXCEPTIONAL  = 3;
	private static final int CANCELLED    = 4;
	private static final int INTERRUPTING = 5;
	private static final int INTERRUPTED  = 6;

	public FutureTask(Runnable runnable, V result) {

	}

	@Override
	public void run() {

	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return null;
	}

	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}
}
