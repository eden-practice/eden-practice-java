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

package org.ylzl.eden.practice.concurrent.locks;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 锁同步约束
 *
 * @author gyl
 * @since 2.0.0
 */
public interface Condition {

	void await() throws InterruptedException; // 使当前线程等待，直到收到 signal 唤醒

	void awaitUninterruptibly();

	long awaitNanos(long nanosTimeout) throws InterruptedException;

	boolean await(long time, TimeUnit unit) throws InterruptedException;

	boolean awaitUntil(Date deadline) throws InterruptedException;

	void signal(); // 随机唤醒一个等待线程

	void signalAll();
}
