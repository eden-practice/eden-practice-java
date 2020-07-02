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

package org.ylzl.eden.practice.concurrent;

import sun.misc.VM;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.security.ProtectionDomain;

/**
 *
 * @see sun.misc.Unsafe
 * @author gyl
 * @since 2.0.0
 */
public class Unsafe {

	private static final Unsafe theUnsafe;

	public static final int INVALID_FIELD_OFFSET = -1;

	// 数组第一个元素的偏移地址
	public static final int ARRAY_BOOLEAN_BASE_OFFSET;
	public static final int ARRAY_BYTE_BASE_OFFSET;
	public static final int ARRAY_SHORT_BASE_OFFSET;
	public static final int ARRAY_CHAR_BASE_OFFSET;
	public static final int ARRAY_INT_BASE_OFFSET;
	public static final int ARRAY_LONG_BASE_OFFSET;
	public static final int ARRAY_FLOAT_BASE_OFFSET;
	public static final int ARRAY_DOUBLE_BASE_OFFSET;
	public static final int ARRAY_OBJECT_BASE_OFFSET;

	// 数组中元素的增量地址
	public static final int ARRAY_BOOLEAN_INDEX_SCALE;
	public static final int ARRAY_BYTE_INDEX_SCALE;
	public static final int ARRAY_SHORT_INDEX_SCALE;
	public static final int ARRAY_CHAR_INDEX_SCALE;
	public static final int ARRAY_INT_INDEX_SCALE;
	public static final int ARRAY_LONG_INDEX_SCALE;
	public static final int ARRAY_FLOAT_INDEX_SCALE;
	public static final int ARRAY_DOUBLE_INDEX_SCALE;
	public static final int ARRAY_OBJECT_INDEX_SCALE;
	public static final int ADDRESS_SIZE;

	static {
		registerNatives();
		Reflection.registerMethodsToFilter(Unsafe.class, new String[]{"getUnsafe"});
		theUnsafe = new Unsafe();
		ARRAY_BOOLEAN_BASE_OFFSET = theUnsafe.arrayBaseOffset(boolean[].class);
		ARRAY_BYTE_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
		ARRAY_SHORT_BASE_OFFSET = theUnsafe.arrayBaseOffset(short[].class);
		ARRAY_CHAR_BASE_OFFSET = theUnsafe.arrayBaseOffset(char[].class);
		ARRAY_INT_BASE_OFFSET = theUnsafe.arrayBaseOffset(int[].class);
		ARRAY_LONG_BASE_OFFSET = theUnsafe.arrayBaseOffset(long[].class);
		ARRAY_FLOAT_BASE_OFFSET = theUnsafe.arrayBaseOffset(float[].class);
		ARRAY_DOUBLE_BASE_OFFSET = theUnsafe.arrayBaseOffset(double[].class);
		ARRAY_OBJECT_BASE_OFFSET = theUnsafe.arrayBaseOffset(Object[].class);
		ARRAY_BOOLEAN_INDEX_SCALE = theUnsafe.arrayIndexScale(boolean[].class);
		ARRAY_BYTE_INDEX_SCALE = theUnsafe.arrayIndexScale(byte[].class);
		ARRAY_SHORT_INDEX_SCALE = theUnsafe.arrayIndexScale(short[].class);
		ARRAY_CHAR_INDEX_SCALE = theUnsafe.arrayIndexScale(char[].class);
		ARRAY_INT_INDEX_SCALE = theUnsafe.arrayIndexScale(int[].class);
		ARRAY_LONG_INDEX_SCALE = theUnsafe.arrayIndexScale(long[].class);
		ARRAY_FLOAT_INDEX_SCALE = theUnsafe.arrayIndexScale(float[].class);
		ARRAY_DOUBLE_INDEX_SCALE = theUnsafe.arrayIndexScale(double[].class);
		ARRAY_OBJECT_INDEX_SCALE = theUnsafe.arrayIndexScale(Object[].class);
		ADDRESS_SIZE = theUnsafe.addressSize();
	}

	private Unsafe() {
	}

	private static native void registerNatives();

	@CallerSensitive
	public static Unsafe getUnsafe() {
		Class var0 = Reflection.getCallerClass();
		// 判断是否启动加载器加载的，如果应用层要侵入，可以使用 `-Xbootclasspath` 设置
		if (!VM.isSystemDomainLoader(var0.getClassLoader())) {
			throw new SecurityException("Unsafe");
		} else {
			return theUnsafe;
		}
	}

	public native int getInt(Object var1, long var2);

	public native void putInt(Object var1, long var2, int var4);

	public native Object getObject(Object var1, long var2);

	public native void putObject(Object var1, long var2, Object var4);

	public native boolean getBoolean(Object var1, long var2);

	public native void putBoolean(Object var1, long var2, boolean var4);

	public native byte getByte(Object var1, long var2);

	public native void putByte(Object var1, long var2, byte var4);

	public native short getShort(Object var1, long var2);

	public native void putShort(Object var1, long var2, short var4);

	public native char getChar(Object var1, long var2);

	public native void putChar(Object var1, long var2, char var4);

	public native long getLong(Object var1, long var2);

	public native void putLong(Object var1, long var2, long var4);

	public native float getFloat(Object var1, long var2);

	public native void putFloat(Object var1, long var2, float var4);

	public native double getDouble(Object var1, long var2);

	public native void putDouble(Object var1, long var2, double var4);

	public native byte getByte(long var1);

	public native void putByte(long var1, byte var3);

	public native short getShort(long var1);

	public native void putShort(long var1, short var3);

	public native char getChar(long var1);

	public native void putChar(long var1, char var3);

	public native int getInt(long var1);

	public native void putInt(long var1, int var3);

	public native long getLong(long var1);

	public native void putLong(long var1, long var3);

	public native float getFloat(long var1);

	public native void putFloat(long var1, float var3);

	public native double getDouble(long var1);

	public native void putDouble(long var1, double var3);

	// 设置内存地址

	public native long getAddress(long var1);

	public native void putAddress(long var1, long var3);

	// 分配内存
	public native long allocateMemory(long var1);

	// 扩充内存
	public native long reallocateMemory(long var1, long var3);

	// 设置内存数据
	public native void setMemory(Object var1, long var2, long var4, byte var6);

	public void setMemory(long var1, long var3, byte var5) {
		this.setMemory((Object)null, var1, var3, var5);
	}

	// 复制内存数据
	public native void copyMemory(Object var1, long var2, Object var4, long var5, long var7);

	public void copyMemory(long var1, long var3, long var5) {
		this.copyMemory((Object)null, var1, (Object)null, var3, var5);
	}

	// 释放内存
	public native void freeMemory(long var1);

	public native long staticFieldOffset(Field var1);

	public native long objectFieldOffset(Field var1);

	public native Object staticFieldBase(Field var1);

	public native boolean shouldBeInitialized(Class<?> var1);

	public native void ensureClassInitialized(Class<?> var1);

	public native int arrayBaseOffset(Class<?> var1);

	public native int arrayIndexScale(Class<?> var1);

	public native int addressSize();

	public native int pageSize();

	public native Class<?> defineClass(String var1, byte[] var2, int var3, int var4, ClassLoader var5, ProtectionDomain var6);

	public native Class<?> defineAnonymousClass(Class<?> var1, byte[] var2, Object[] var3);

	public native Object allocateInstance(Class<?> var1) throws InstantiationException;

	public native void throwException(Throwable var1);

	public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);

	public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);

	public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);

	public native Object getObjectVolatile(Object var1, long var2);

	public native void putObjectVolatile(Object var1, long var2, Object var4);

	public native int getIntVolatile(Object var1, long var2);

	public native void putIntVolatile(Object var1, long var2, int var4);

	public native boolean getBooleanVolatile(Object var1, long var2);

	public native void putBooleanVolatile(Object var1, long var2, boolean var4);

	public native byte getByteVolatile(Object var1, long var2);

	public native void putByteVolatile(Object var1, long var2, byte var4);

	public native short getShortVolatile(Object var1, long var2);

	public native void putShortVolatile(Object var1, long var2, short var4);

	public native char getCharVolatile(Object var1, long var2);

	public native void putCharVolatile(Object var1, long var2, char var4);

	public native long getLongVolatile(Object var1, long var2);

	public native void putLongVolatile(Object var1, long var2, long var4);

	public native float getFloatVolatile(Object var1, long var2);

	public native void putFloatVolatile(Object var1, long var2, float var4);

	public native double getDoubleVolatile(Object var1, long var2);

	public native void putDoubleVolatile(Object var1, long var2, double var4);

	public native void putOrderedObject(Object var1, long var2, Object var4);

	public native void putOrderedInt(Object var1, long var2, int var4);

	public native void putOrderedLong(Object var1, long var2, long var4);

	public native void unpark(Object var1);

	public native void park(boolean var1, long var2);

	public native int getLoadAverage(double[] var1, int var2);

	public final int getAndAddInt(Object var1, long var2, int var4) {
		int var5;
		do {
			var5 = this.getIntVolatile(var1, var2);
		} while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

		return var5;
	}

	public final long getAndAddLong(Object var1, long var2, long var4) {
		long var6;
		do {
			var6 = this.getLongVolatile(var1, var2);
		} while(!this.compareAndSwapLong(var1, var2, var6, var6 + var4));

		return var6;
	}

	public final int getAndSetInt(Object var1, long var2, int var4) {
		int var5;
		do {
			var5 = this.getIntVolatile(var1, var2);
		} while(!this.compareAndSwapInt(var1, var2, var5, var4));

		return var5;
	}

	public final long getAndSetLong(Object var1, long var2, long var4) {
		long var6;
		do {
			var6 = this.getLongVolatile(var1, var2);
		} while(!this.compareAndSwapLong(var1, var2, var6, var4));

		return var6;
	}

	public final Object getAndSetObject(Object var1, long var2, Object var4) {
		Object var5;
		do {
			var5 = this.getObjectVolatile(var1, var2);
		} while(!this.compareAndSwapObject(var1, var2, var5, var4));

		return var5;
	}

	public native void loadFence();

	public native void storeFence();

	public native void fullFence();

	private static void throwIllegalAccessError() {
		throw new IllegalAccessError();
	}
}
