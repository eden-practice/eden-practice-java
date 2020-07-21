/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
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

package org.ylzl.eden.practice.springframework.beans.factory.support;

import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.ylzl.eden.practice.springframework.beans.factory.BeanFactory;
import org.ylzl.eden.practice.springframework.beans.factory.config.BeanDefinition;
import org.ylzl.eden.practice.springframework.beans.factory.config.BeanDefinitionRegistry;
import org.ylzl.eden.practice.springframework.beans.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class DefaultListableBeanFactory extends DefaultSingletonBeanRegistry
    implements BeanDefinitionRegistry, BeanFactory {

  private final Map<String, BeanDefinition> beanDefinitionMap = Maps.newConcurrentMap();

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    this.beanDefinitionMap.put(beanName, beanDefinition);
  }

  @Override
  public void removeBeanDefinition(String beanName) {}

  @Override
  public Object getBean(String name) {
    return doGetBean(name);
  }

  @Override
  public <T> T getBean(Class<T> requiredType) {
    return (T) getBean(StringUtils.lowerCase(requiredType.getSimpleName()));
  }

  @Override
  public boolean containsBean(String name) {
    return this.containsSingleton(name) || beanDefinitionMap.containsKey(name);
  }

  private <T> T doGetBean(String name) {
    Object sharedInstance = getSingleton(name, true);
    if (sharedInstance != null) {
      return (T) sharedInstance;
    }
    BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
    if (beanDefinition == null) {
      throw new RuntimeException("can not find the definition of bean '" + name + "'");
    }
    return (T)
        getSingleton(
            name,
            () -> {
              try {
                return doCreateBean(name, beanDefinition);
              } catch (Exception ex) {
                removeSingleton(name);
                throw ex;
              }
            });
  }

  private Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
    Object bean = createBeanInstance(beanName, beanDefinition);
    boolean earlySingletonExposure = isSingletonCurrentlyInCreation(beanName);
    if (earlySingletonExposure) {
      addSingletonFactory(beanName, () -> bean);
    }
    Object exposedObject = bean;
    populateBean(beanName, beanDefinition, bean);
    if (earlySingletonExposure) {
      Object earlySingletonReference = getSingleton(beanName, false);
      if (earlySingletonReference != null) {
        exposedObject = earlySingletonReference;
      }
    }
    return exposedObject;
  }

  private Object createBeanInstance(String beanName, BeanDefinition beanDefinition) {
    Class<?> beanClass = beanDefinition.getBeanClass();
    Constructor<?> constructorToUse;
    if (beanClass.isInterface()) {
      throw new RuntimeException("Specified class '" + beanName + "' is an interface");
    }
    try {
      constructorToUse = beanClass.getDeclaredConstructor((Class<?>[]) null);
      return BeanUtils.instantiateClass(constructorToUse);
    } catch (Exception e) {
      throw new RuntimeException("'" + beanName + "', No default constructor found", e);
    }
  }

  private void populateBean(String beanName, BeanDefinition beanDefinition, Object beanInstance) {
    Field[] beanFields = beanDefinition.getBeanClass().getDeclaredFields();
    try {
      for (Field field : beanFields) {
        if (field.getAnnotation(Resource.class) == null) {
          continue;
        }
        if (!containsBean(field.getName())) {
          throw new RuntimeException(
              "'@Resource' for field '" + field.getClass().getName() + "' can not find");
        }
        field.setAccessible(true);
        field.set(beanInstance, getBean(field.getName()));
      }
    } catch (Exception e) {
      throw new RuntimeException("populateBean '" + beanName + "' error", e);
    }
  }

  public void preInstantiateSingletons() {
    this.beanDefinitionMap.forEach(
        (beanName, beanDef) -> {
          getBean(beanName);
        });
  }
}
