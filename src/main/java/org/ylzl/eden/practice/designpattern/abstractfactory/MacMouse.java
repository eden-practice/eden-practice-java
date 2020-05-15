package org.ylzl.eden.practice.designpattern.abstractfactory;

/**
 * Mac 鼠标
 *
 * @author gyl
 * @since 2.0.0
 */
public class MacMouse implements Mouse {

  @Override
  public void show() {
    System.out.println("Mac 鼠标");
  }
}
