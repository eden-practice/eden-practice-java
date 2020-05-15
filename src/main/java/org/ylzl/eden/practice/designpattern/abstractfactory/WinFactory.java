package org.ylzl.eden.practice.designpattern.abstractfactory;

/**
 * Windows 工厂（具体工厂）
 *
 * @author gyl
 * @since 2.0.0
 */
public class WinFactory implements HardwareFactory {

	@Override
	public Keyboard produceKeyboard() {
		return new WinKeyboard();
	}

	@Override
	public Mouse produceMouse() {
		return new WinMouse();
	}
}
