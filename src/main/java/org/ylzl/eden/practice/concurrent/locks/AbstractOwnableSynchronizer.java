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

import java.io.Serializable;

/**
 * 线程独占锁同步器，用于非公平锁
 *
 * @author gyl
 * @since 2.0.0
 */
public abstract class AbstractOwnableSynchronizer implements Serializable {

	private static final long serialVersionUID = 6013028045340226243L;

	protected AbstractOwnableSynchronizer() { }

	private transient Thread exclusiveOwnerThread;

	protected final void setExclusiveOwnerThread(Thread thread) {
		exclusiveOwnerThread = thread;
	}

	protected final Thread getExclusiveOwnerThread() {
		return exclusiveOwnerThread;
	}
}
