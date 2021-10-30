package org.ylzl.eden.practice.design.pattern.singleton;

/**
 * 懒汉单例模式
 *
 * @author gyl
 * @since 2.0.0
 */
public class LazySingleton {

  private static volatile LazySingleton instance; // 使用 volatile 防止多线程产生变量不可见问题

  private LazySingleton() {
    boolean initialized = false; // 防止反射破坏单例
    if (instance != null) {
      initialized = true;
    } else {
      synchronized (LazySingleton.class) {
        if (instance != null) {
          initialized = true;
        }
      }
    }
    if (initialized) {
      throw new RuntimeException("单例模式下不可重复初始化");
    }
  }

  public static LazySingleton getInstance() {
    if (instance == null) { // 先判断是否为 null，提高效率
      synchronized (LazySingleton.class) { // 类锁
        if (instance == null) {
          instance = new LazySingleton();
        }
      }
    }
    return instance;
  }

  /**
   * 防止序列化/反序列化破坏单例
   *
   * @see java.io.ObjectInputStream#readObject()
   * @return
   */
  private Object readResolve() {
    return instance;
  }
}
