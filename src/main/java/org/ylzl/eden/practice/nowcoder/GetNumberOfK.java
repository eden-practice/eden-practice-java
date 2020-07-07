package org.ylzl.eden.practice.nowcoder;

/**
 * 统计一个数字在排序数组中出现的次数
 *
 * @author gyl
 * @since 2.0.0
 * @see <a
 *     href="https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2?tpId=13&tqId=11191&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking">剑指
 *     Offer 原题</a>
 */
public class GetNumberOfK {

  public int total(int[] array, int k) {
    if (array == null) {
      return 0;
    }

    int first = binarySearch(array, k);
    int last = binarySearch(array, k + 1);
    return (first == array.length || array[first] != k) ? 0 : last - first;
  }

  private int binarySearch(int[] array, int k) {
    int index = 0;
    int len = array.length;
    while (index < len) {
      int m = (index + len - 1) >> 1;
      if (array[m] >= k) {
        len = m;
      } else {
        index = m + 1;
      }
    }
    return index;
  }
}
