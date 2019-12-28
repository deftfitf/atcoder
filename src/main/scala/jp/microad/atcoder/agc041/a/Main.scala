package jp.microad.atcoder.agc041.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, A, B = sc.nextLong()

    // 負けが右, 勝ちが左へ
    // 一番左で勝っても動かず、一番右で負けても動かない
    // 同時に動く場合, 一度に2ずつ近づける
    // * L = 2N + 1 台間にある場合には N ラウンドでOK
    // * L = 2N 台間にある場合には そのまま近づいていってもダメなので
    //   (A max B) min ((N - A) max (N - B)) で良い？
    def solve(N: Long, A: Long, B: Long): Long = {
      val L = B - A
      if ((L - 1L) % 2L == 1L) L / 2L
      else (((A - 1) min (N - B)) + 1 + (B - A - 1) / 2)
    }

    println(solve(N, A, B))
  }

}
