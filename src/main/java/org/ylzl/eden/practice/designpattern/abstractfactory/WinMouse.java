package org.ylzl.eden.practice.designpattern.abstractfactory;

/**
 * 经典款奔驰汽车
 *
 * @author gyl
 * @since 2.0.0
 */
public class WinMouse implements Mouse {

  @Override
  public void show() {
    System.out.println("Windows 鼠标");
  }
}
