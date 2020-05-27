package org.ylzl.eden.practice.concurrent.future;

import org.junit.jupiter.api.Test;
import org.ylzl.eden.practice.concurrent.CustomFuture;

/**
 * 自定义 Future 测试
 *
 * @author gyl
 * @since 2.0.0
 */
public class CustomFutureTest {

  @Test
  public void assertThatAsyncResult() throws InterruptedException {
    final CustomFuture asyncData = new AsyncData();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                final SyncData syncData = new SyncData();
                asyncData.set(syncData.remoteInvokeSlowly());
              }
            },
            "test")
        .start();

    System.out.println("a");
    System.out.println("b");
    System.out.println("c");
    System.out.println(asyncData.get());
  }
}
