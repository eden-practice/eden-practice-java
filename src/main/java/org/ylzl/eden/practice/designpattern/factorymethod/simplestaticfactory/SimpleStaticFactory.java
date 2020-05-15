package org.ylzl.eden.practice.designpattern.factorymethod.simplestaticfactory;


import org.ylzl.eden.practice.designpattern.factorymethod.Computer;
import org.ylzl.eden.practice.designpattern.factorymethod.MacComputer;
import org.ylzl.eden.practice.designpattern.factorymethod.WinComputer;

/**
 * 简单静态工厂
 *
 * @author gyl
 * @since 2.0.0
 */
public class SimpleStaticFactory {

	public static Computer getInstance(char c) { // 对一类产品维护，违反开闭原则
		switch (c) {
			case 'M':
				return new MacComputer();
			case 'W':
				return new WinComputer();
		}
		return null;
	}
}
