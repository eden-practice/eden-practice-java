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

import org.junit.jupiter.api.Test;
import org.ylzl.eden.practice.design.pattern.proxy.Bean;
import org.ylzl.eden.practice.design.pattern.proxy.BeanImpl;
import org.ylzl.eden.practice.design.pattern.proxy.StandaloneBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JDK 动态代理测试
 *
 * <p>JDK 代理需要从对象找到对应上游的接口，因此被代理的对象需要实现接口</p>
 *
 * @author gyl
 * @since 2.0.0
 */
class JdkDynamicProxyTest {

  @Test
  void assertThatInvokeBeanSuccess() {
    Bean bean = JdkDynamicProxy.newProxyInstance(new BeanImpl());
    assertEquals(bean.invoke(), true);
    // 保存生成的代理类到磁盘
    // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
    System.out.println(
        "代理类路径：" + JdkDynamicProxy.generateProxyClassFile(BeanImpl.class, "JdkDynamicProxy$"));
  }

  @Test
  void assertThatInvokeStandaloneBeanFailed() {
    assertThrows(
        ClassCastException.class,
        () -> {
          StandaloneBean standaloneProxy = JdkDynamicProxy.newProxyInstance(new StandaloneBean());
          standaloneProxy.invoke();
        });
  }
}
