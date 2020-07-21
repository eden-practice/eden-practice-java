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

import org.springframework.util.Assert;
import org.ylzl.eden.practice.springframework.beans.factory.ObjectFactory;
import org.ylzl.eden.practice.springframework.beans.factory.singleton.SingletonBeanRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

  private static final Object NULL_OBJECT = new Object();

  private final Map<String, Object> singletonObjects = new ConcurrentHashMap(256);

  private final Map<String, Object> earlySingletonObjects = new HashMap(16);

  private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap(16);

  private final Set<String> registeredSingletons = new LinkedHashSet(256);

  private final Set<String> singletonsCurrentlyInCreation =
      Collections.newSetFromMap(new ConcurrentHashMap(16));

  @Override
  public void registerSingleton(String beanName, Object singletonObject)
      throws IllegalStateException {
    Assert.notNull(beanName, "Bean name must not be null");
    Assert.notNull(singletonObject, "Singleton object must not be null");
    synchronized (this.singletonObjects) {
      Object oldObject = this.singletonObjects.get(beanName);
      if (oldObject != null) {
        throw new IllegalStateException(
            "Could not register object ["
                + singletonObject
                + "] under bean name '"
                + beanName
                + "': there is already object ["
                + oldObject
                + "] bound");
      } else {
        this.addSingleton(beanName, singletonObject);
      }
    }
  }

  @Override
  public Object getSingleton(String beanName) {
    return this.getSingleton(beanName, true);
  }

  protected Object getSingleton(String beanName, boolean allowEarlyReference) {
    Object singletonObject = this.singletonObjects.get(beanName);
    if (singletonObject == null && this.isSingletonCurrentlyInCreation(beanName)) {
      synchronized (this.singletonObjects) {
        singletonObject = this.earlySingletonObjects.get(beanName);
        if (singletonObject == null && allowEarlyReference) {
          ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
          if (singletonFactory != null) {
            singletonObject = singletonFactory.getObject();
            this.earlySingletonObjects.put(beanName, singletonObject);
            this.singletonFactories.remove(beanName);
          }
        }
      }
    }

    return singletonObject;
  }

  @Override
  public boolean containsSingleton(String beanName) {
    return this.singletonObjects.containsKey(beanName);
  }

  protected Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
    synchronized (this.singletonObjects) {
      Object singletonObject = this.singletonObjects.get(beanName);
      if (singletonObject == null) {
        this.singletonsCurrentlyInCreation.add(beanName);
        singletonObject = singletonFactory.getObject();
        this.singletonsCurrentlyInCreation.remove(beanName);
        addSingleton(beanName, singletonObject);
      }
      return (singletonObject != NULL_OBJECT ? singletonObject : null);
    }
  }

  protected void addSingleton(String beanName, Object singletonObject) {
    synchronized (this.singletonObjects) {
      this.singletonObjects.put(beanName, singletonObject);
      this.singletonFactories.remove(beanName);
      this.earlySingletonObjects.remove(beanName);
      this.registeredSingletons.add(beanName);
    }
  }

  protected boolean isSingletonCurrentlyInCreation(String beanName) {
    //		return this.singletonsCurrentlyInCreation.contains(beanName);
    return true;
  }

  protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
    synchronized (this.singletonObjects) {
      if (!this.singletonObjects.containsKey(beanName)) {
        this.singletonFactories.put(beanName, singletonFactory);
        this.earlySingletonObjects.remove(beanName);
      }
    }
  }

  protected void removeSingleton(String beanName) {
    synchronized (this.singletonObjects) {
      this.singletonObjects.remove(beanName);
      this.singletonFactories.remove(beanName);
      this.earlySingletonObjects.remove(beanName);
    }
  }
}
