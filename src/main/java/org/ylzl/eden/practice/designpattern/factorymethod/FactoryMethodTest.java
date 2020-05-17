package org.ylzl.eden.practice.designpattern.factorymethod;

/**
 * 工厂方法
 *
 * @author gyl
 * @since 2.0.0
 */
public class FactoryMethodTest {

  public static void main(String[] args) {
    ComputerFactory factory = new WinFactory();
    factory.produceComputer().show();
  }
}
