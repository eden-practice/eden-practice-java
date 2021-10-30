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

package org.ylzl.eden.practice.spring.framework.beans.factory.support;

import org.reflections.Reflections;
import org.ylzl.eden.practice.spring.framework.beans.annotation.Component;
import org.ylzl.eden.practice.spring.framework.beans.factory.config.BeanDefinition;
import org.ylzl.eden.practice.spring.framework.beans.factory.config.BeanDefinitionReader;
import org.ylzl.eden.practice.spring.framework.beans.factory.config.BeanDefinitionRegistry;
import org.ylzl.eden.practice.spring.framework.beans.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

  private static final Reflections reflections = new Reflections("");

  @Override
  public int loadBeanDefinitions(BeanDefinitionRegistry registry) {
    Set<Class<?>> componentClasses = getAllClass(Component.class);
    for (Class<?> componentClass : componentClasses) {
			String beanName = null;
			if (componentClass.isAnnotationPresent(Component.class)) {
				Component component = componentClass.getAnnotation(Component.class);
				beanName = component.value();
			}

      if (StringUtils.isBlank(beanName)) {
				String simpleName = componentClass.getSimpleName();
        beanName = StringUtils.lowerCase(componentClass.getSimpleName());
      }
			BeanDefinition beanDefinition = new GenericBeanDefinition();
      beanDefinition.setBeanClass(componentClass);
      registry.registerBeanDefinition(beanName, beanDefinition);
    }
    return componentClasses.size();
  }

  public static Set<Class<?>> getAllClass(Class<? extends Annotation> clazz) {
    return reflections.getTypesAnnotatedWith(clazz);
  }
}
