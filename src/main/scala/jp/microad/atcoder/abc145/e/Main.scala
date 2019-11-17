package jp.microad.atcoder.abc145.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, T = sc.nextInt()
    val A, B = new Array[Int](N)
    (0 until N) foreach { i =>
      A(i) = sc.nextInt()
      B(i) = sc.nextInt()
    }
    val idx = (0 until N).sortBy(i => A(i)).toArray

    val dp = Array.fill(N+1, T+1)(0)

    var _i = 0
    while (_i < N) {
      val i = idx(_i)
      var t = 0
      while (t < T) {
        val nextT = (t + A(i)) min T
        dp(_i+1)(nextT) = dp(_i+1)(nextT) max (dp(_i)(t) + B(i))
        dp(_i+1)(t) = dp(_i+1)(t) max dp(_i)(t)
        dp(_i+1)(t+1) = dp(_i+1)(t+1) max dp(_i)(t)
        t += 1
      }
      _i += 1
    }

    println(dp.map(_.max).max)
  }

}
