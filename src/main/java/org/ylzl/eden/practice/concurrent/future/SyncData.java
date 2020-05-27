package org.ylzl.eden.practice.concurrent.future;

import lombok.SneakyThrows;
import org.ylzl.eden.practice.concurrent.CustomFuture;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class SyncData {

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
