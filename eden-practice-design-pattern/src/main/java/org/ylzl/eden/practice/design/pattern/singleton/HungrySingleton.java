package org.ylzl.eden.practice.design.pattern.singleton;

/**
 * 饿汉单例模式
 *
 * @author gyl
 * @since 2.0.0
 */
public class HungrySingleton {

  private static HungrySingleton INSTANCE = new HungrySingleton();

  private static boolean initialized = false;

  private HungrySingleton() {
    synchronized (LazySingleton.class) { // 防止反射破坏单例
      if (!initialized) {
        initialized = !initialized;
      } else {
        throw new RuntimeException("单例模式下不可重复初始化");
      }
    }
  }

  public static HungrySingleton getInstance() {
    return INSTANCE;
  }

  /**
   * 防止序列化/反序列化破坏单例
   *
   * @see java.io.ObjectInputStream#readObject()
   * @return
   */
  private Object readResolve() {
    return INSTANCE;
  }
}
