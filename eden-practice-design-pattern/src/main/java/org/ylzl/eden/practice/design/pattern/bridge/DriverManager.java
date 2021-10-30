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

package org.ylzl.eden.practice.design.pattern.bridge;

import lombok.NonNull;
import lombok.Synchronized;

import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 驱动管理器
 *
 * @author gyl
 * @since 2.0.0
 */
public class DriverManager {

  private static final CopyOnWriteArrayList<Driver> registeredDrivers =
      new CopyOnWriteArrayList<>();

  @Synchronized
  public static void registeredDriver(@NonNull Driver driver) {
    System.out.println("注册驱动：" + driver.getClass().getName());
    registeredDrivers.addIfAbsent(driver);
  }

  public static Connection getConnection(String url, Properties info) {
  	for (Driver driver : registeredDrivers) {
  		Connection conn = driver.connect(url, info);
  		if (conn != null && url.contains(conn.getType().toLowerCase())) {
  			return conn;
		}
	}
  	return null;
  }
}
