package org.ylzl.eden.practice.designpattern.singleton;

/**
 * 饿汉单例模式
 *
 * @author gyl
 * @since 2.0.0
 */
public class HungrySingleton {

	private static HungrySingleton INSTANCE = new HungrySingleton();

	private HungrySingleton() {}

	public static HungrySingleton getInstance() {
		return INSTANCE;
	}

	private Object readResolve() {
		return INSTANCE;
	}
}
