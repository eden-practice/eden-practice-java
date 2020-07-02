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

import sun.misc.Unsafe;

import java.io.Serializable;

/**
 * AQS 抽象队列同步器
 *
 * @author gyl
 * @since 2.0.0
 */
public class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer
    implements Serializable {

  private static final long serialVersionUID = 6483697211995780883L;

  static final class Node { // 就是一个链表

    static final Node SHARED = new Node(); // 共享模式
    static final Node EXCLUSIVE = null; // 排他模式

    static final int CANCELLED = 1;
    static final int SIGNAL = -1;
    static final int CONDITION = -2;
    static final int PROPAGATE = -3;

    volatile int waitStatus;

    volatile Node prev;

    volatile Node next;

    volatile Thread thread;

    Node nextWaiter; // 队列下一个等待节点

    final boolean isShared() {
      return nextWaiter == SHARED;
    }

    final Node predecessor() throws NullPointerException {
      Node p = prev;
      if (p == null) {
        throw new NullPointerException();
      }
      return p;
    }

    Node() {}

    Node(Thread thread, AbstractQueuedSynchronizer.Node mode) {
      this.nextWaiter = mode;
      this.thread = thread;
    }

    Node(Thread thread, int waitStatus) {
      this.waitStatus = waitStatus;
      this.thread = thread;
    }
  }

  /* 等待队列头部 */
  private transient volatile Node head;

  /* 等待队列尾部 */
  private transient volatile Node tail;

  /* 同步状态值 */
  private volatile int state;

  private static final Unsafe unsafe = Unsafe.getUnsafe();

  private static final long stateOffset;

  private static final long headOffset;

  private static final long tailOffset;

  private static final long waitStatusOffset;

  private static final long nextOffset;

  static {
    try {
      stateOffset =
          unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
      headOffset =
          unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("head"));
      tailOffset =
          unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
      waitStatusOffset =
          unsafe.objectFieldOffset(
              AbstractQueuedSynchronizer.Node.class.getDeclaredField("waitStatus"));
      nextOffset =
          unsafe.objectFieldOffset(AbstractQueuedSynchronizer.Node.class.getDeclaredField("next"));
    } catch (Exception ex) {
      throw new Error(ex);
    }
  }

  /* 由子类取实现获取锁、释放锁 */
  protected boolean tryAcquire(int arg) {
    throw new UnsupportedOperationException();
  }

  protected boolean tryRelease(int arg) {
    throw new UnsupportedOperationException();
  }

  protected int tryAcquireShared(int arg) {
    throw new UnsupportedOperationException();
  }

  protected boolean tryReleaseShared(int arg) {
    throw new UnsupportedOperationException();
  }

  public final void acquire(int arg) {
    if (!tryAcquire(arg)
        && acquireQueued(addWaiter(AbstractQueuedSynchronizer.Node.EXCLUSIVE), arg))
      selfInterrupt();
  }

  private AbstractQueuedSynchronizer.Node enq(
      final AbstractQueuedSynchronizer.Node node) { // 入队，并检测是否初始化队列头部和尾部
    for (; ; ) {
      AbstractQueuedSynchronizer.Node t = tail;
      if (t == null) { // Must initialize
        if (compareAndSetHead(new AbstractQueuedSynchronizer.Node())) {
          tail = head;
        }
      } else {
        node.prev = t;
        if (compareAndSetTail(t, node)) {
          t.next = node;
          return t;
        }
      }
    }
  }

  private AbstractQueuedSynchronizer.Node addWaiter(
      AbstractQueuedSynchronizer.Node mode) { // 添加到等待队列
    AbstractQueuedSynchronizer.Node node =
        new AbstractQueuedSynchronizer.Node(Thread.currentThread(), mode);
    AbstractQueuedSynchronizer.Node pred = tail;
    if (pred != null) {
      node.prev = pred;
      if (compareAndSetTail(pred, node)) {
        pred.next = node;
        return node;
      }
    }
    enq(node);
    return node;
  }

  final boolean acquireQueued(final AbstractQueuedSynchronizer.Node node, int arg) {
    boolean failed = true;
    try {
      boolean interrupted = false;
      for (; ; ) {
        final AbstractQueuedSynchronizer.Node p = node.predecessor();
        if (p == head && tryAcquire(arg)) {
          setHead(node);
          p.next = null; // help GC
          failed = false;
          return interrupted;
        }
        if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) interrupted = true;
      }
    } finally {
      if (failed) {
        //        cancelAcquire(node);
      }
    }
  }

  static void selfInterrupt() {
    Thread.currentThread().interrupt();
  }

  private void setHead(AbstractQueuedSynchronizer.Node node) {
    head = node;
    node.thread = null;
    node.prev = null;
  }

  private static boolean shouldParkAfterFailedAcquire(
      AbstractQueuedSynchronizer.Node pred, AbstractQueuedSynchronizer.Node node) {
    int ws = pred.waitStatus;
    if (ws == AbstractQueuedSynchronizer.Node.SIGNAL) return true;
    if (ws > 0) {
      do {
        node.prev = pred = pred.prev;
      } while (pred.waitStatus > 0);
      pred.next = node;
    } else {
      compareAndSetWaitStatus(pred, ws, AbstractQueuedSynchronizer.Node.SIGNAL);
    }
    return false;
  }

  private final boolean parkAndCheckInterrupt() {
    //		LockSupport.park(this);
    return Thread.interrupted();
  }

  public final void acquireInterruptibly(int arg) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    if (!tryAcquire(arg)) doAcquireInterruptibly(arg);
  }

  private void doAcquireInterruptibly(int arg) throws InterruptedException {
    final AbstractQueuedSynchronizer.Node node =
        addWaiter(AbstractQueuedSynchronizer.Node.EXCLUSIVE);
    boolean failed = true;
    try {
      for (; ; ) {
        final AbstractQueuedSynchronizer.Node p = node.predecessor();
        if (p == head && tryAcquire(arg)) {
          setHead(node);
          p.next = null; // help GC
          failed = false;
          return;
        }
        if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt())
          throw new InterruptedException();
      }
    } finally {
      if (failed) {
        // cancelAcquire(node);
      }
    }
  }

  public final boolean hasQueuedPredecessors() {
    AbstractQueuedSynchronizer.Node t = tail; // Read fields in reverse initialization order
    AbstractQueuedSynchronizer.Node h = head;
    AbstractQueuedSynchronizer.Node s;
    return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
  }

  protected final int getState() {
    return state;
  }

  protected final void setState(int newState) {
    state = newState;
  }

  /* CAS 比较并交换 */
  protected final boolean compareAndSetState(int expect, int update) { // 状态
    return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
  }

  private final boolean compareAndSetHead(AbstractQueuedSynchronizer.Node update) { // 出队
    return unsafe.compareAndSwapObject(this, headOffset, null, update);
  }

  private final boolean compareAndSetTail(
      AbstractQueuedSynchronizer.Node expect, AbstractQueuedSynchronizer.Node update) { // 入队
    return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
  }

  private static final boolean compareAndSetWaitStatus(
      AbstractQueuedSynchronizer.Node node, int expect, int update) {
    return unsafe.compareAndSwapInt(node, waitStatusOffset, expect, update);
  }

  private static final boolean compareAndSetNext(
      AbstractQueuedSynchronizer.Node node,
      AbstractQueuedSynchronizer.Node expect,
      AbstractQueuedSynchronizer.Node update) {
    return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
  }
}
