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

package org.ylzl.eden.practice.springframework.bean;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.util.StringUtils;
import org.ylzl.eden.practice.springframework.bean.config.BeanDefinition;
import org.ylzl.eden.practice.springframework.bean.config.ConstructorArg;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author gyl
 * @since 1.0.0
 */
public class BeanFactoryImpl implements BeanFactory {

  private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

  private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap =
      new ConcurrentHashMap<>();

  private static final Set<String> beanNames = Collections.synchronizedSet(new HashSet<>());

  private final Map<String, Object> earlySingletonObjects =
      new HashMap<String, Object>(1 << 4); // 解决循环依赖

  @Override
  public Object getInstance(String name) throws Exception {
    Object bean = beanMap.get(name);
    if (bean != null) {
      return bean;
    }

    bean = createInstance(beanDefinitionMap.get(name));
    if (bean != null) {
      earlySingletonObjects.put(name, bean);
      populate(bean);
      beanMap.put(name, bean);
      earlySingletonObjects.remove(name);
    }
    return bean;
  }

  protected void register(String name, BeanDefinition bd) {
    beanDefinitionMap.put(name, bd);
    beanNames.add(name);
  }

  private Object createInstance(BeanDefinition beanDefinition) throws Exception {
    String beanName = beanDefinition.getClassName();
    Class clazz = loadClass(beanName);
    List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
    if (constructorArgs != null && !constructorArgs.isEmpty()) {
      List<Object> objects = new ArrayList<>();
      for (ConstructorArg constructorArg : constructorArgs) {
        objects.add(getInstance(constructorArg.getRef()));
      }
      return newInstance(clazz, clazz.getConstructor(), objects.toArray());
    }
    return newInstance(clazz, null, null);
  }

  private void populate(Object bean) throws Exception {
    Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
    if (fields == null || fields.length == 0) {
      return;
    }
    for (Field field : fields) {
      if (beanNames.contains(field.getName())) {
        Object fieldBean = getInstance(resolveFieldName(field));
        if (fieldBean != null) {
          field.setAccessible(true);
          field.set(bean, fieldBean);
        }
      }
    }
  }

  private static <T> T newInstance(Class<T> clazz, Constructor constructor, Object[] args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(clazz);
    enhancer.setCallback(NoOp.INSTANCE);
    if (constructor == null) {
      return (T) enhancer.create();
    } else {
      return (T) enhancer.create(constructor.getParameterTypes(), args);
    }
  }

  private static Class loadClass(String className) throws ClassNotFoundException {
    return Thread.currentThread().getContextClassLoader().loadClass(className);
  }

  private String resolveFieldName(Field field) {
    return StringUtils.uncapitalize(field.getName());
  }
}
