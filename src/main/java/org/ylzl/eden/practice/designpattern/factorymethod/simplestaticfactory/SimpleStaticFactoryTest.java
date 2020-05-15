package org.ylzl.eden.practice.designpattern.factorymethod.simplestaticfactory;

import org.ylzl.eden.practice.designpattern.factorymethod.BenzCarProduct;

/**
 * 简单静态工厂测试
 *
 * @author gyl
 * @since 2.0.0
 */
public class SimpleStaticFactoryTest {

  public static void main(String[] args) {
	  BenzCarProduct product = SimpleStaticFactory.getInstance('S');
	  product.show();
  }
}
