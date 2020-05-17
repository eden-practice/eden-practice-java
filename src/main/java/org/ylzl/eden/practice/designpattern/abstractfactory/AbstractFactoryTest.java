package org.ylzl.eden.practice.designpattern.abstractfactory;

/**
 * 抽象工厂模式测试
 *
 * @author gyl
 * @since 2.0.0
 */
public class AbstractFactoryTest {

  public static void main(String[] args) {
    HardwareFactory factory = new MacFactory();
    factory.produceKeyboard().show();
    factory.produceMouse().show();
  }
}
