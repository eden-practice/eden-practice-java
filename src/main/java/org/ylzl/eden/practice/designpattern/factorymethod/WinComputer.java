package org.ylzl.eden.practice.designpattern.factorymethod;

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
