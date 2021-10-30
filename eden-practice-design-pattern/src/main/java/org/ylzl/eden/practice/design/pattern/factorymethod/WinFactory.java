package org.ylzl.eden.practice.design.pattern.factorymethod;

/**
 * Windows 电脑工厂
 *
 * @author gyl
 * @since 2.0.0
 */
public class WinFactory implements ComputerFactory {

  @Override
  public Computer produceComputer() {
    return new WinComputer();
  }
}
