package org.ylzl.eden.practice.designpattern.decorator;

import java.io.IOException;

/**
 * 文件输入流
 *
 * @author gyl
 * @since 2.0.0
 */
public class FileInputStream implements InputStream {

  @Override
  public int read() {
    System.out.println("文件输入流调用 InputStream.read()，按字节读取");
    return -1;
  }
}
