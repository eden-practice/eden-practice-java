package org.ylzl.eden.practice.jdk6;

/**
 * 内部类
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningInnerClass {

	/* 成员内部类 */
	class FieldClass {}
	private class PrivateFieldClass {}
	protected class ProtectedFieldClass {}
	public class PublicFieldClass {}
	final class FinalFieldClass {}
	abstract class AbstractFieldClass {}
	static abstract class StaticAbstractFieldClass {}

	/* 静态成员内部类 */
	private static String OUT_FIELD_STR = "外部类静态成员变量";

	static class StaticFieldClass {

		void innerMethod() {
			System.out.println(OUT_FIELD_STR);
		}
	}

	/* 局部内部类 */
	public void method() {
		  class NormalClassInMethod {}
		abstract class AbstractClassInMethod {}
//		interface InterfaceInMethod {} // 报错
//		final class PublicClassInMethod {} // 报错
//		private class PrivateClassInMethod {} // 报错
//		protected class ProtecedClassInMethod {} // 报错
//		public class PublicClassInMethod {} // 报错
	}

	/* 匿名内部类 */
	interface AnonymousInterface {
		void exec();
	}

	public void methodForAnonymous() {
		new AnonymousInterface() {
			@Override
			public void exec() {

			}
		}.exec();
	}
}
