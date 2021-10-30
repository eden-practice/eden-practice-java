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

package org.ylzl.eden.practice.design.pattern.proxy.reflect;

import lombok.SneakyThrows;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 *
 * @author gyl
 * @since 2.0.0
 */
public class JdkDynamicProxy<T> implements InvocationHandler {

  private T target;

  public JdkDynamicProxy(T target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    this.before();
    Object result = method.invoke(target, args);
    this.after();
    return result;
  }

  public static <T> T newProxyInstance(T target) {
    return (T)
        Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new JdkDynamicProxy(target));
  }

  private void before() {
    System.out.println("JDK 动态代理预处理");
  }

  private void after() {
    System.out.println("JDK 动态代理后处理");
  }

  @SneakyThrows
  public static String generateProxyClassFile(Class clazz, String proxyName) {
    byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
    String classPath = clazz.getResource(".").getPath() + proxyName + ".class";
    try (FileOutputStream out = new FileOutputStream(classPath); ) {
      out.write(classFile);
      out.flush();
    }
    return classPath;
  }
}
