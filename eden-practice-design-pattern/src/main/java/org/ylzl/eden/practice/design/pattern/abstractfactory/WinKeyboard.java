package org.ylzl.eden.practice.design.pattern.abstractfactory;

/**
 * Windows 键盘
 *
 * @author gyl
 * @since 2.0.0
 */
public class WinKeyboard implements Keyboard {

  @Override
  public void show() {
    System.out.println("Windows 键盘");
  }
}
