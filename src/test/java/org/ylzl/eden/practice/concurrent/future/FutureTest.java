package org.ylzl.eden.practice.concurrent.future;

import org.junit.jupiter.api.Test;
import org.ylzl.eden.practice.concurrent.Future;

/**
 * 自定义 Future 测试
 *
 * @author gyl
 * @since 2.0.0
 */
public class FutureTest {

  @Test
  public void assertThatAsyncResult() throws InterruptedException {
    final Future asyncData = new AsyncInvoker();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                final SyncInvoker syncInvoker = new SyncInvoker();
                asyncData.set(syncInvoker.remoteInvokeSlowly());
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
