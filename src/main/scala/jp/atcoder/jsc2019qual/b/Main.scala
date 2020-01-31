package jp.atcoder.jsc2019qual.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val K = sc.nextLong()
    val A = Array.fill(N)(sc.nextInt())

    // 左の数の方が大きい数になっている個数
    // 全部見ていくと間に合わない
    // 一旦無視すると,
    // ある数 aiについて
    // aiより大きい数の数を AiC とする
    // aiより左にある大きい数の数を AiCL とする
    // aiより右にある大きい数の数を ACiR とする, ACiR = AiC - AiCL
    // ここで K = 1なら, AiCLであり
    // K = 2なら, AiCL + AiCL + AiCR = AiC + AiCL であり,
    // K >= 3なら, (AiC * (AiC + 1) / 2) - AiC
    // つまり AiCR は必要ない

    val Inf = 1000000000 + 7

    val AiC = new Array[Int](N)
    val AiCL = new Array[Int](N)

    var i = 1
    while (i < N) {
      var j = 0
      while (j < i) {
        if (A(j) > A(i))
          AiCL(i) += 1
        j += 1
      }
      i += 1
    }

    i = 0
    while (i < N) {
      var j = 0
      while (j < N) {
        if (i != j && A(j) > A(i))
          AiC(i) += 1
        j += 1
      }
      i += 1
    }

    var r = BigInt(0)
    i = 0
    while (i < N) {
      r += BigInt(K * (K - 1L) / 2L) * AiC(i).toLong + AiCL(i).toLong * K
      i += 1
    }

    println(r % Inf.toLong)
  }

}
