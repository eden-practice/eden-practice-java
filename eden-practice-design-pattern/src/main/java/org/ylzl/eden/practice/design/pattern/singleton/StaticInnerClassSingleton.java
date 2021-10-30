package org.ylzl.eden.practice.design.pattern.singleton;

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

  private static boolean initialized = false;

  private StaticInnerClassSingleton() {
    synchronized (StaticInnerClassSingleton.class) { // 防止反射破坏单例
      if (!initialized) {
        initialized = !initialized;
      } else {
        throw new RuntimeException("单例模式下不可重复初始化");
      }
    }
  }

  public static final StaticInnerClassSingleton getInstance() { // 只有对内部类主动使用时才会初始化
    return SingletonHolder.INSTANCE;
  }

  /**
   * 防止序列化/反序列化破坏单例
   *
   * @see java.io.ObjectInputStream#readObject()
   * @return
   */
  private Object readResolve() {
    return SingletonHolder.INSTANCE;
  }
}
