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

package org.ylzl.eden.practice.spi;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * SPi 测试
 *
 * @author gyl
 * @since 2.0.0
 */
public class ServiceProviderTest {

  @Test
  public void assertThatInvokeSuccess() {
  	// ServiceLoader 默认加载 /META-INF/services/包+类名称，内容为加载的类路径
    ServiceLoader<ServiceProvider> serviceProviders = ServiceLoader.load(ServiceProvider.class);
    for (ServiceProvider serviceProvider : serviceProviders) {
      System.out.println(serviceProvider.getClass());
      assertNotEquals(serviceProvider.invoke(), 0);
    }
  }
}
