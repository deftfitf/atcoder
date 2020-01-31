package jp.atcoder.abc132.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()

    // K個の青いボールを回収するために高橋君がちょうどi回操作をする必要がある時
    // 青いボールの置き方と赤いボールの置き方は独立して考えられる
    // N - K個の赤いボールを一列に並べて, N - K + 1個の隙間のどこに青いボールをK個置くかを考える
    // すると, N - K + 1個の隙間から i 個選ぶ選び方は {N-K+1}_C_i 通り
    // その隙間を選んだ後に,　各隙間に何個ボールを入れるかを考えるので
    // K 個のボールの隙間 K - 1 個の中から i - 1 個選ぶのは
    // {K-1}_C_{i-1} 通りあるので, それぞれの掛け合わせで
    // {N-K+1}_C_i * {K-1}_C_{i-1} となる

    // TODO: なぜか後半TLEになる.. 手元では1秒かからないが Java版は通過する.
    def solve(N: Int, K: Int): IndexedSeq[BigInt] = {
      val MAX: Long = Math.pow(10, 9).toLong + 7
      val nCr = Array.fill(N+1, K+1)(BigInt(0L))
      for {
        i <- 0 to N
      } {
        nCr(i)(0) = 1
        for {
          j <- 1 to (i min K)
        } nCr(i)(j) = nCr(i-1)(j-1) + nCr(i-1)(j)
      }
      for {
        i <- 1 to K
      } yield (nCr(N-K+1)(i) * nCr(K-1)(i-1)) % MAX
    }

    println(solve(N, K).mkString("\n"))
  }

}
