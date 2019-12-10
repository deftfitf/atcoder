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

  /**
    * (left, right)の範囲の整数について二分探索する
    * この時, fがtrueになる最小の整数を返す
    * 見つからない場合, rightを返す
    * @param left
    * @param right
    * @param f
    * @return
    */
  def lowerBound(left: Int, right: Int, f: Int => Boolean) = {
    def recursive(left: Int, right: Int): Int =
      if (right - left > 1) {
        val mid = left + (right - left) / 2
        if (f(mid)) recursive(left, mid)
        else recursive(mid, right)
      } else right
    recursive(left, right)
  }

  /**
    * (left, right)の範囲の整数について二分探索し
    * この時, fがfalseになる最大の整数を返す
    * 見つからない場合, leftを返す
    * @param left
    * @param right
    * @param f
    * @return
    */
  def upperBound(left: Int, right: Int, f: Int => Boolean) = {
    def recursive(left: Int, right: Int): Int =
      if (right - left > 1) {
        val mid = left + (right - left) / 2
        if (f(mid)) recursive(left, mid)
        else recursive(mid, right)
      } else left
    recursive(left, right)
  }

  /**
    * 小数点2桁精度まで調べる
    * @param left
    * @param right
    * @param f
    * @return
    */
  def upperBound(left: Float, right: Float, f: Float => Boolean): Float = {
    def recursive(left: Float, right: Float): Float =
      if (right - left > 0.01) {
        val mid = left + (right - left) / 2.0F
        if (f(mid)) recursive(mid, right)
        else recursive(left, mid)
      } else left
    recursive(left, right)
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
