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

import org.ylzl.eden.practice.thread.Callable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public interface ExecutorService extends Executor {

	void shutdown();

	List<Runnable> shutdownNow();

	boolean isShutdown();

	boolean isTerminated();

	boolean awaitTermination(long var1, TimeUnit var3) throws InterruptedException;

	<T> Future<T> submit(Callable<T> var1);

	<T> Future<T> submit(Runnable var1, T var2);

	Future<?> submit(Runnable var1);

	<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> var1) throws InterruptedException;

	<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> var1, long var2, TimeUnit var4) throws InterruptedException;

	<T> T invokeAny(Collection<? extends Callable<T>> var1) throws InterruptedException, ExecutionException;

	<T> T invokeAny(Collection<? extends Callable<T>> var1, long var2, TimeUnit var4) throws InterruptedException, ExecutionException, TimeoutException;
}
