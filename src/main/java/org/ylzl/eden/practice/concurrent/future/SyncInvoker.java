package org.ylzl.eden.practice.concurrent.future;

import lombok.SneakyThrows;

/**
 * 同步调用
 *
 * @author gyl
 * @since 2.0.0
 */
public class SyncInvoker {

  @SneakyThrows
  public String remoteInvokeSlowly() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      sb.append(i);
      Thread.sleep(1000);
    }
    return sb.toString();
  }
}
