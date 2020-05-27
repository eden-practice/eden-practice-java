package org.ylzl.eden.practice.concurrent.future;

import org.ylzl.eden.practice.concurrent.CustomFuture;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class AsyncData implements CustomFuture<String> {

  private volatile boolean flag = true;

  private Object lock = new Object();

  private String data;

  @Override
  public String get() throws InterruptedException {
    synchronized (lock) {
      while (flag) {
        lock.wait();
      }
    }
    return data;
  }

  public void set(String data) {
    synchronized (lock) {
      if (!flag) {
        return;
      }
      this.data = data;
      flag = false;
      lock.notify();
    }
  }
}
