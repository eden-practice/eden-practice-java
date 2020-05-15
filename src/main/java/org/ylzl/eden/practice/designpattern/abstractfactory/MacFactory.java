package org.ylzl.eden.practice.designpattern.abstractfactory;

/**
 * Mac 键盘工厂（具体工厂）
 *
 * @author gyl
 * @since 2.0.0
 */
public class MacFactory implements HardwareFactory {

	@Override
	public Keyboard produceKeyboard() {
		return new MacKeyboard();
	}

	@Override
	public Mouse produceMouse() {
		return new MacMouse();
	}
}
