package jp.microad.atcoder.abc133.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())
    val M = new Array[Int](N)

    var f = false
    var i = 0
    var sr = 0L
    while (i < N) {
      if (f)
        sr += A(i)
      else
        sr -= A(i)
      f = !f
      i += 1
    }
    val X1 = Math.abs(sr)

    // x1 は山1に降った雨の量
    // x2 = A(1) - x1
    // ...
    // xN = A(N-2) - xN-2
    // xN + x1 == A(N-1)かどうか？
    // solveは, これを達成する時のx1の値を返す.
    def ans(x1: Long): Array[Long] = {
      val arr = new Array[Long](N)
      var nx = x1 / 2
      var i = 0
      while (i < N) {
        arr(i) = nx * 2
        nx = A(i) - nx
        i += 1
      }
      arr
    }

    println(ans(X1).mkString(" "))
  }

}
