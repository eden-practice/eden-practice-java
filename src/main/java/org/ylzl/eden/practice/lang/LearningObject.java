package org.ylzl.eden.practice.lang;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Object 内置方法
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningObject {

  private static final String DATE_PATTERN = "HH:mm:ss";

  private static Object lock = new Object();

  private static boolean flag = true;

  public static void main(String[] args) throws InterruptedException {
    new Thread(new WaitRunnable(), "wait").start();
    TimeUnit.SECONDS.sleep(1);
    new Thread(new NotifyRunnable(), "notify").start();
  }

  public static class WaitRunnable implements Runnable {

    @SneakyThrows
    public void run() {
      synchronized (lock) {
        System.out.println(
            Thread.currentThread()
                + " 获取到 lock 静态实例，时间为："
                + new SimpleDateFormat(DATE_PATTERN).format(new Date()));
        while (flag) {
          System.out.println(
              Thread.currentThread()
                  + " 调用 wait() 进入阻塞态，时间为："
                  + new SimpleDateFormat(DATE_PATTERN).format(new Date()));
          lock.wait();
        }

        System.out.println(
            Thread.currentThread()
                + " 被唤醒，从就绪态进入运行态，时间为："
                + new SimpleDateFormat(DATE_PATTERN).format(new Date()));
      }
    }
  }

  public static class NotifyRunnable implements Runnable {

    @SneakyThrows
    public void run() {
      synchronized (lock) {
        System.out.println(
            Thread.currentThread()
                + " 获取到 lock 静态实例，通过 notify() 唤醒 lock 相关的线程，时间为："
                + new SimpleDateFormat(DATE_PATTERN).format(new Date()));
        lock.notify();
        flag = false;
      }

      synchronized (lock) {
        System.out.println(
            Thread.currentThread()
                + " 再次获取到 lock 静态实例，时间为："
                + new SimpleDateFormat(DATE_PATTERN).format(new Date()));
      }
    }
  }
}
