package lib.search

object BinarySearch {

  /**
    * 二分探索
    * @param x 探索対象
    * @param arr 探索配列
    * @return 探索配列から探索対象が発見できるかどうか
    */
  def binarySearch(x: Int, arr: IndexedSeq[Int]): Boolean = {
    def dfs(l: Int, r: Int): Boolean =
      if (r - l >= 1) {
        val center = (l + r) / 2
        if (arr(center) == x) true
        else if (arr(center) < x) dfs(center + 1, r)
        else dfs(l, center)
      } else false
    dfs(0, arr.length)
  }

  /**
    * lower_bound. ソートずみのarrの中からxよりも大きいarr(x)をidxを返却する
    * @param arr
    * @param x
    * @return
    */
  def lowerBound(arr: IndexedSeq[Int], x: Long): Int = {
    def dfs(l: Int, r: Int): Int =
      if (r - l > 1) {
        val center = l + (r - l) / 2
        if (arr(center) <= x) dfs(center, r)
        else dfs(l, center)
      } else r
    dfs(-1, arr.length)
  }

  def upperBound(arr: IndexedSeq[Int], x: Long): Int = {
    def dfs(l: Int, r: Int): Int =
      if (r - l > 1) {
        val center = l + (r - l) / 2
        if (arr(center) < x) dfs(center, r)
        else dfs(l, center)
      } else r
    dfs(-1, arr.length)
  }

  def main(args: Array[String]): Unit = {
    upperBound(Array(1, 4, 4, 7, 7, 8, 8, 11, 13, 19), 8L)
  }

}
