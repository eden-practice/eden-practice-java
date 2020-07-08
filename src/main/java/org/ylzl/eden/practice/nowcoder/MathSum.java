package org.ylzl.eden.practice.nowcoder;

/**
 * 短路求和
 *
 * <br/> 要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句 A ? B : C。
 *
 * @author gyl
 * @since 2.0.0
 * @see <a
 *     href="https://www.nowcoder.com/practice/7a0da8fc483247ff8800059e12d7caf1?tpId=13&tqId=11200&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 */
public class MathSum {

  int[] mask = {0x00000000, 0xFFFFFFFF};

  public int sum(int n) {
//    int result = n;
//    boolean isStop = (n > 0) && (result += sum(n - 1)) > 0;
//    return result;
		return gaussSum(n, n + 1) >> 1;
  }

  int gaussSum(int m, int n) {
    int result = 0;
    boolean isStop = (m != 0) && (result = (n & mask[m & 1]) + gaussSum(m >> 1, n << 1)) != 0;
    return result;
  }
}
