package org.ylzl.eden.practice.designpattern.factorymethod;

/**
 * Mac 电脑工厂
 *
 * @author gyl
 * @since 2.0.0
 */
public class MacFactory implements ComputerFactory {

	@Override
	public Computer produceComputer() {
		return new MacComputer();
	}
}
