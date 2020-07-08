package org.ylzl.eden.practice.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 字符串的排列
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=13&&tqId=11180&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @since 2.0.0
 */
public class StringOrderBy {

	private ArrayList<String> ret = new ArrayList<>();

	public ArrayList<String> permutation(String str) {
		if (str.length() == 0) {
			return ret;
		}
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		backtracking(chars, new boolean[chars.length], new StringBuilder());
		return ret;
	}

	private void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
		if (s.length() == chars.length) {
			ret.add(s.toString());
			return;
		}

		for (int i = 0; i < chars.length; i++) {
      if (hasUsed[i] || (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1])) {
				continue;
      }

			hasUsed[i] = true;
			s.append(chars[i]);
			backtracking(chars, hasUsed, s);
			s.deleteCharAt(s.length() - 1);
			hasUsed[i] = false;
		}
	}
}
