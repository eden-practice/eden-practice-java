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

package org.ylzl.eden.practice.spring.framework.mvc.handler;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.ylzl.eden.practice.spring.framework.mvc.http.ModelAndView;
import org.ylzl.eden.practice.spring.framework.mvc.interceptor.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class HandlerExecutionChain {

  private final Object handler;

  private HandlerInterceptor[] interceptors;

  private List<HandlerInterceptor> interceptorList;

  private int interceptorIndex;

  public HandlerExecutionChain(Object handler) {
    this.handler = handler;
    interceptorIndex = -1;
  }

  public Object getHandler() {
    return this.handler;
  }

  public void addInterceptor(HandlerInterceptor interceptor) {
    this.initInterceptorList().add(interceptor);
  }

  public void addInterceptor(int index, HandlerInterceptor interceptor) {
    this.initInterceptorList().add(index, interceptor);
  }

  public void addInterceptors(HandlerInterceptor... interceptors) {
    if (!ObjectUtils.isEmpty(interceptors)) {
      CollectionUtils.mergeArrayIntoCollection(interceptors, this.initInterceptorList());
    }
  }

  private List<HandlerInterceptor> initInterceptorList() {
    if (this.interceptorList == null) {
      this.interceptorList = new ArrayList();
      if (this.interceptors != null) {
        CollectionUtils.mergeArrayIntoCollection(this.interceptors, this.interceptorList);
      }
    }

    this.interceptors = null;
    return this.interceptorList;
  }

  public HandlerInterceptor[] getInterceptors() {
    if (this.interceptors == null && this.interceptorList != null) {
      this.interceptors =
          (HandlerInterceptor[]) this.interceptorList.toArray(new HandlerInterceptor[0]);
    }

    return this.interceptors;
  }

  public boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    HandlerInterceptor[] interceptors = this.getInterceptors();
    if (!ObjectUtils.isEmpty(interceptors)) {
      for (int i = 0; i < interceptors.length; this.interceptorIndex = i++) {
        HandlerInterceptor interceptor = interceptors[i];
        if (!interceptor.preHandle(request, response, this.handler)) {
          this.triggerAfterCompletion(request, response, (Exception) null);
          return false;
        }
      }
    }

    return true;
  }

  public void applyPostHandle(
      HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
    HandlerInterceptor[] interceptors = this.getInterceptors();
    if (!ObjectUtils.isEmpty(interceptors)) {
      for (int i = interceptors.length - 1; i >= 0; --i) {
        HandlerInterceptor interceptor = interceptors[i];
        interceptor.postHandle(request, response, this.handler, mv);
      }
    }
  }

  private void triggerAfterCompletion(
      HttpServletRequest request, HttpServletResponse response, Exception e) {
    HandlerInterceptor[] interceptors = this.getInterceptors();
    if (!ObjectUtils.isEmpty(interceptors)) {
      for (int i = interceptors.length - 1; i >= 0; --i) {
        if (interceptors[i] instanceof AsyncHandlerInterceptor) {
          AsyncHandlerInterceptor asyncInterceptor = (AsyncHandlerInterceptor) interceptors[i];
          asyncInterceptor.afterConcurrentHandlingStarted(request, response, this.handler);
        }
      }
    }
  }
}
