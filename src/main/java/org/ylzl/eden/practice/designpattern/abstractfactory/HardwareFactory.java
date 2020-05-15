package org.ylzl.eden.practice.designpattern.abstractfactory;

/**
 * PC 工厂（抽象工厂）
 *
 * <p>由于工厂分为经典款汽车工厂和运动款汽车工厂，每种款式有不同的汽车品牌，工厂方法模式只能对同一类品牌进行维护，所以需要吧工厂抽象出来，由具体的工厂类实现</p>
 *
 * @author gyl
 * @since 2.0.0
 */
public interface HardwareFactory {

	Keyboard produceKeyboard();

	Mouse produceMouse();
}
