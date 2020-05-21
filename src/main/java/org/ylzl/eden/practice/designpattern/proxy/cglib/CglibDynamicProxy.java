/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.practice.designpattern.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.misc.ProxyGenerator;

import java.lang.reflect.Method;

/**
 * Cglib 动态代理
 *
 * <p>Cglib 代理基于 asm 字节码增强技术生成代理类，支持非接口 Bean</p>
 *
 * @author gyl
 * @since 1.0.0
 */
public class CglibDynamicProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		this.before();
		Object result = methodProxy.invokeSuper(obj, objects);
		this.after();
		return result;
	}

	public static <T> T newProxyInstance(Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(new CglibDynamicProxy());
		return (T) enhancer.create();
	}

	public static String generateProxyClassFile(Class clazz, String proxyName) {
		String classPath = clazz.getResource(".").getPath() + proxyName;
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, classPath);
		return classPath;
	}

	private void before() {
		System.out.println("Cglib 动态代理预处理");
	}

	private void after() {
		System.out.println("Cglib 动态代理后处理");
	}


}
