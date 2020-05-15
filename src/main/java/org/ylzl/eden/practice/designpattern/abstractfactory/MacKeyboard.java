package org.ylzl.eden.practice.designpattern.abstractfactory;

/**
 * Mac 键盘
 *
 * @author gyl
 * @since 2.0.0
 */
public class MacKeyboard implements Keyboard {

  @Override
  public void show() {
    System.out.println("Mac 键盘");
  }
}
