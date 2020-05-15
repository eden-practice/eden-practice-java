package org.ylzl.eden.practice.designpattern.factorymethod;

/**
 * Mac 电脑
 *
 * @author gyl
 * @since 2.0.0
 */
public class MacComputer implements Computer {

  @Override
  public void show() {
    System.out.println("Mac 电脑");
  }
}
