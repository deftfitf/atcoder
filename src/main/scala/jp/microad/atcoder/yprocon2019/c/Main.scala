package jp.microad.atcoder.yprocon2019.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val K, A, B = sc.nextLong()
    // 普通に増やすと K 枚まで増やせる
    // B - A > 2 の場合, 普通に叩いて増やすより効率的
    // K max ((K / 2) * (B - A) + K % 2)
    // 注意するのは 最初に A 枚まで増やす必要がある.
    // K - (A + 1)

    println((K + 1) max (((K - A + 1L) / 2L) * (B - A) + (K - A + 1L) % 2L + A))
  }

}
