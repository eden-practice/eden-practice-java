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

package org.ylzl.eden.practice.spring.framework.mvc;

import org.ylzl.eden.practice.spring.framework.mvc.handler.HandlerAdapter;
import org.ylzl.eden.practice.spring.framework.mvc.handler.HandlerExecutionChain;
import org.ylzl.eden.practice.spring.framework.mvc.handler.HandlerMapping;
import org.ylzl.eden.practice.spring.framework.mvc.http.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class DispatcherServlet {

	private List<HandlerMapping> handlerMappings;

	private List<HandlerAdapter> handlerAdapters;

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.doDispatch(request, response);
	}

	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HandlerExecutionChain mappedHandler = this.getHandler(request);

		if (!mappedHandler.applyPreHandle(request, response)) {
			return;
		}

		HandlerAdapter handlerAdapter = this.getHandlerAdapter(mappedHandler.getHandler());
		ModelAndView modelAndView = handlerAdapter.handle(request, response, mappedHandler.getHandler());
		mappedHandler.applyPostHandle(request, response, modelAndView);
	}

	private HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		Iterator<HandlerMapping> iterator = handlerMappings.iterator();
		while (iterator.hasNext()) {
			HandlerExecutionChain handler = iterator.next().getHandler(request);
			if (handler != null) {
				return handler;
			}
		}
		return null;
	}

	private HandlerAdapter getHandlerAdapter(Object handler) {
		return null;
	}
}
