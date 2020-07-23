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

package org.ylzl.eden.practice.springframework.beans;

import org.ylzl.eden.practice.springframework.beans.factory.BeanFactory;
import org.ylzl.eden.practice.springframework.beans.factory.config.BeanDefinition;
import org.ylzl.eden.practice.springframework.beans.factory.config.BeanDefinitionReader;
import org.ylzl.eden.practice.springframework.beans.factory.config.BeanDefinitionRegistry;
import org.ylzl.eden.practice.springframework.beans.factory.support.DefaultBeanDefinitionReader;
import org.ylzl.eden.practice.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class ApplicationContext implements BeanDefinitionRegistry, BeanFactory {

	private BeanFactory beanFactory;

  public ApplicationContext() {
    refresh();
  }

  public void refresh() { // AbstractApplicationContext.refresh()
		beanFactory = new DefaultListableBeanFactory();
    this.scanAndLoadBeanDefinitions(beanFactory);
    this.prepareBeanFactory(beanFactory);
    this.postProcessBeanFactory(beanFactory);
    this.invokeBeanFactoryPostProcessors(beanFactory);
    // ...
    this.finishBeanFactoryInitialization(beanFactory);
  }

  protected void scanAndLoadBeanDefinitions(BeanFactory beanFactory) {
		if (beanFactory instanceof DefaultListableBeanFactory) {
			BeanDefinitionReader reader = new DefaultBeanDefinitionReader();
			reader.loadBeanDefinitions((DefaultListableBeanFactory) beanFactory);
		}

  }

  protected void prepareBeanFactory(BeanFactory beanFactory) {}

  protected void postProcessBeanFactory(BeanFactory beanFactory) {}

  protected void invokeBeanFactoryPostProcessors(BeanFactory beanFactory) {}

  protected void finishBeanFactoryInitialization(BeanFactory beanFactory) {
    if (beanFactory instanceof DefaultListableBeanFactory) {
      ((DefaultListableBeanFactory) beanFactory).preInstantiateSingletons();
    }
  }

  @Override
  public Object getBean(String name) throws Exception {
    return beanFactory.getBean(name);
  }

  @Override
  public <T> T getBean(Class<T> requiredType) throws Exception {
    return beanFactory.getBean(requiredType);
  }

  @Override
  public boolean containsBean(String name) {
    return beanFactory.containsBean(name);
  }

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {}

  @Override
  public void removeBeanDefinition(String beanName) {}
}
