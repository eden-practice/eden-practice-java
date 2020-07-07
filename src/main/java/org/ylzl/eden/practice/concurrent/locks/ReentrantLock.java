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
import java.util.concurrent.TimeUnit;

/**
 * 重入锁
 *
 * @author gyl
 * @since 2.0.0
 */
public class ReentrantLock implements Lock, Serializable {

  private final Sync sync;

	public ReentrantLock() {
		sync = new NonfairSync();
	}

	public ReentrantLock(boolean fair) {
		sync = fair ? new FairSync() : new NonfairSync();
	}

  abstract static class Sync extends AbstractQueuedSynchronizer {

    abstract void lock();

		final boolean nonfairTryAcquire(int acquires) {
			final Thread current = Thread.currentThread();
			int c = getState();
			if (c == 0) {
				if (compareAndSetState(0, acquires)) {
					setExclusiveOwnerThread(current);
					return true;
				}
			}
			else if (current == getExclusiveOwnerThread()) {
				int nextc = c + acquires;
				if (nextc < 0) // overflow
					throw new Error("Maximum lock count exceeded");
				setState(nextc);
				return true;
			}
			return false;
		}

		protected final boolean tryRelease(int releases) {
			int c = getState() - releases;
			if (Thread.currentThread() != getExclusiveOwnerThread())
				throw new IllegalMonitorStateException();
			boolean free = false;
			if (c == 0) {
				free = true;
				setExclusiveOwnerThread(null);
			}
			setState(c);
			return free;
		}
  }

	static final class NonfairSync extends Sync {
		private static final long serialVersionUID = 7316153563782823691L;

		final void lock() {
			if (compareAndSetState(0, 1))
				setExclusiveOwnerThread(Thread.currentThread());
			else
				acquire(1);
		}

		protected final boolean tryAcquire(int acquires) {
			return nonfairTryAcquire(acquires);
		}
	}

	static final class FairSync extends Sync {
		private static final long serialVersionUID = -3000897897090466540L;

		final void lock() {
			acquire(1);
		}

		protected final boolean tryAcquire(int acquires) {
			final Thread current = Thread.currentThread();
			int c = getState();
			if (c == 0) {
				if (!hasQueuedPredecessors() &&
					compareAndSetState(0, acquires)) {
					setExclusiveOwnerThread(current);
					return true;
				}
			}
			else if (current == getExclusiveOwnerThread()) {
				int nextc = c + acquires;
				if (nextc < 0)
					throw new Error("Maximum lock count exceeded");
				setState(nextc);
				return true;
			}
			return false;
		}
	}

	@Override
	public void lock() {
		sync.lock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.nonfairTryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
//		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
		return false;
	}

	@Override
	public void unlock() {
//		sync.release(1);
	}
}
