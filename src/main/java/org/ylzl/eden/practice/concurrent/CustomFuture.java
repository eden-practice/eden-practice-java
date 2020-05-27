package org.ylzl.eden.practice.concurrent;

/**
 * 自定义 Future
 *
 * @author gyl
 * @since 2.0.0
 */
public interface CustomFuture<V> {

	V get() throws InterruptedException;

	void set(V data);
}
