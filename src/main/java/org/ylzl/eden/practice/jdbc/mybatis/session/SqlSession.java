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

package org.ylzl.eden.practice.jdbc.mybatis.session;

import org.ylzl.eden.practice.jdbc.mybatis.cursor.Cursor;
import org.ylzl.eden.practice.jdbc.mybatis.executor.BatchResult;

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Mybatis Session
 *
 * @author gyl
 * @since 2.0.0
 */
public interface SqlSession extends Closeable {

	<T> T selectOne(String statement);

	<T> T selectOne(String statement, Object parameter);

	<E> List<E> selectList(String statement);

	<E> List<E> selectList(String statement, Object parameter);

	<E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds);

	<K, V> Map<K, V> selectMap(String statement, String mapKey);

	<K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);

	<K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds);

	<T> Cursor<T> selectCursor(String statement);

	<T> Cursor<T> selectCursor(String statement, Object parameter);

	<T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds);

	void select(String statement, Object parameter, ResultHandler handler);

	void select(String statement, ResultHandler handler);

	void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler);

	int insert(String statement);

	int insert(String statement, Object parameter);

	int update(String statement);

	int update(String statement, Object parameter);

	int delete(String statement);

	int delete(String statement, Object parameter);

	void commit();

	void commit(boolean force);

	void rollback();

	void rollback(boolean force);

	List<BatchResult> flushStatements();

	void close();

	void clearCache();

	Configuration getConfiguration();

	<T> T getMapper(Class<T> type);

	Connection getConnection();
}
