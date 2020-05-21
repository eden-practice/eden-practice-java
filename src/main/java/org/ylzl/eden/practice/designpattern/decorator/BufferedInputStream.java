package org.ylzl.eden.practice.designpattern.decorator;

import java.io.IOException;

/**
 * 缓冲输入流
 *
 * @author gyl
 * @since 2.0.0
 */
public class BufferedInputStream implements InputStream {

  private volatile InputStream in;

  public BufferedInputStream(InputStream in) {
    this.in = in;
  }

  @Override
  public int read() {
  	int offset = in.read();
    System.out.println("缓冲输入流装饰 InputStream.read()，缓冲字节，按块读取");
//    byte[] buffer = new byte[1 << 10];
    return offset + 1;
  }
}
