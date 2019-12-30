package jp.microad.atcoder.arc041.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt(); sc.nextLine()
    val b = Array.fill(N)(sc.nextLine().map(_ - '0').toArray)

    // 初期状態として, 盤面の端にアメーバはいなかった.
    // 大声を出すと, 4匹に分裂し上下左右のマスに移動した
    // ということは, b_{11}, b_{1M}, b_{N1}, b_{NM}は常に0になる.
    // 一行目のアメーバは全て b_{1j}なら b_{2j}からきたものである
    // なので, c_{2j} = b_{1j} とし, 二行目のアメーバを確定する
    // 二行目からは、c_{3j} = b_{2j} - c_{2(j+1)}となる (j < M - 1)の間
    // 三行目以降も同様に確定できる, i < N - 1の間だけやる
    def solve(N: Int, M: Int, b: Array[Array[Int]]): Array[Array[Int]] = {
      val c = Array.fill(N, M)(0)
      for {
        i <- 0 until N - 1
        j <- 0 until M - 1
      } {
        c(i+1)(j) = b(i)(j) - c(i)(j+1)
        if (i >= 1) c(i+1)(j) -= c(i-1)(j)
        if (j >= 1) c(i+1)(j) -= c(i)(j-1)
      }
      c
    }

    println(solve(N, M, b).map(_.mkString).mkString("\n"))
  }

}
