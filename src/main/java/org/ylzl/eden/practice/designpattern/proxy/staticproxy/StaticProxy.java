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

package org.ylzl.eden.practice.designpattern.proxy.staticproxy;

import org.ylzl.eden.practice.designpattern.proxy.Bean;
import org.ylzl.eden.practice.designpattern.proxy.BeanImpl;

/**
 * 静态代理
 *
 * @author gyl
 * @since 2.0.0
 */
public class StaticProxy implements Bean {

	private Bean bean;

	public StaticProxy() {
		this.bean = new BeanImpl();
	}

	@Override
	public boolean invoke() {
		boolean flag = false;
		this.before();
		flag = bean.invoke();
		this.after();
		return flag;
	}

	private void before() {
    System.out.println("静态代理预处理");
	}

	private void after() {
		System.out.println("静态代理后处理");
	}
}
