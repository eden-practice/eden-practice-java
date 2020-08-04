package org.ylzl.eden.practice.concurrent.future;

/**
 * 异步调用
 *
 * @author gyl
 * @since 2.0.0
 */
public class AsyncInvoker implements Future<String> {

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
