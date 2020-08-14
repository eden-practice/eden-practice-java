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

package org.ylzl.eden.practice.thread.core;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class Thread implements Runnable {

	ThreadLocal.ThreadLocalMap threadLocals = null;

	public enum State { // 线程状态
		NEW,
		RUNNABLE,
		BLOCKED,
		WAITING,
		TIMED_WAITING,
		TERMINATED;
	}

	public static native Thread currentThread();

	public static native void yield();

	public static native void sleep(long millis) throws InterruptedException;

	@FunctionalInterface
	public interface UncaughtExceptionHandler { // 由 ThreadGroup 实现

		void uncaughtException(Thread t, Throwable e);
	}

	@Override
	public void run() {

	}
}
