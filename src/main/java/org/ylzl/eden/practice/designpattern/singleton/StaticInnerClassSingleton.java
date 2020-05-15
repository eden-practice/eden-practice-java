package org.ylzl.eden.practice.designpattern.singleton;

/**
 * 静态内部类单例模式
 *
 * @author gyl
 * @since 2.0.0
 */
public class StaticInnerClassSingleton {

  private static class SingletonHolder {
    private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
  }

  private StaticInnerClassSingleton() {}

  public static final StaticInnerClassSingleton getInstance() { // 只有对内部类主动使用时才会初始化
    return SingletonHolder.INSTANCE;
  }

  private Object readResolve() {
    return SingletonHolder.INSTANCE;
  }
}
