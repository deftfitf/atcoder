package jp.atcoder.educationdp.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, W = sc.nextInt()
    val w, v = new Array[Int](N)
    (0 until N) foreach { i =>
      val _w, _v = sc.nextInt()
      w(i) = _w
      v(i) = _v
    }

    val dp = Array.fill(N+1, W+1)(0L)
    var i = 0
    while (i < N) {
      var j = 0
      while (j < W) {
        if (j+1-w(i) >= 0)
          dp(i+1)(j+1) =
            (dp(i)(j+1-w(i)) + v(i)) max dp(i)(j+1)
        else
          dp(i+1)(j+1) = dp(i)(j+1)
        j += 1
      }
      i += 1
    }

    println(dp(N)(W))
  }

}
