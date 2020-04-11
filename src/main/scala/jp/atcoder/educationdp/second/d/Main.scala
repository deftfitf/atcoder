package jp.atcoder.educationdp.second.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, W = sc.nextInt()
    val w, v = new Array[Int](N)
    for (i <- 0 until N) {
      val _w, _v = sc.nextInt()
      w(i) = _w
      v(i) = _v
    }

    val dp = Array.fill(N+1, W+1)(0L)
    for {
      i <- 0 until N
      j <- 0 to W
    } {
      if (j + w(i) <= W)
        dp(i+1)(j+w(i)) = dp(i+1)(j+w(i)) max (dp(i)(j) + v(i))
      dp(i+1)(j) = dp(i+1)(j) max dp(i)(j)
    }

    println(dp(N)(W))
  }

}
