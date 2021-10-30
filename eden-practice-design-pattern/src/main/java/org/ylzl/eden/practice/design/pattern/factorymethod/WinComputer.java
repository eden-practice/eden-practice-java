package org.ylzl.eden.practice.design.pattern.factorymethod;

/**
 * Win 电脑
 *
 * @author gyl
 * @since 2.0.0
 */
public class WinComputer implements Computer {

  @Override
  public void show() {
    System.out.println("Win 电脑");
  }
}
