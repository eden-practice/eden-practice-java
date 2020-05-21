package org.ylzl.eden.practice.designpattern.decorator;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * 装饰着模式测试
 *
 * @author gyl
 * @since 2.0.0
 */
class DecoratorTest {

	@Test
	void assertThatInvokeSuccess() {
		InputStream is = new FileInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		assertEquals(bis.read() != -1, true);
	}
}
