package org.ylzl.eden.practice.design.pattern.factorymethod.simplestaticfactory;

import org.ylzl.eden.practice.design.pattern.factorymethod.Computer;

/**
 * 简单静态工厂测试
 *
 * @author gyl
 * @since 2.0.0
 */
public class SimpleStaticFactoryTest {

  public static void main(String[] args) {
    Computer computer = SimpleStaticFactory.getInstance('S');
    computer.show();
  }
}
