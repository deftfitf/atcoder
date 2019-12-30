package jp.microad.atcoder.soundhound2018.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M, D = sc.nextLong()

    // 数列の美しさを、隣り合う２項の組であって、差の絶対値がdであるものの個数として定義します
    // 1以上n以下の整数が各要素の長さmの数列は n^m通り存在
    // この全ての数列全ての美しさを求め, それらの平均を出力
    // 美しさが0の数列の方が多そう
    // i番目の要素がa_iの時に, の場合に美しさが1増える
    // a_{i+1} = a_i + d (a_i <= n - d)
    //         = a_i - d (a_i >= d)
    // 最初の要素を1つ決めた時
    // 残りの(m - 1)の要素について, 2 * ((n - d) - d) = 2(n - 2d)回美しさが増える
    // d = 0の時, 美しさ1となるペアの確率は, (0, 0), (1, 1),..., (n, n), n / n * n = 1/n
    // これが (m - 1) 個である, これを足し上げて, n^m 通りで割る
    // (m - 1) / n / n^m
    // d != 0の時, (1, 1 + d), (2, 2 + d), ..., (n - d, n), (n, n - d), ..., (2 + d, 2), (1 + d, 1)
    // で 2*(n - d)通り, 2(n - d)/n^2
    def solve(N: Long, M: Long, D: Long): Double = {
      (if (D == 0) (1 / N.toDouble)
      else 2 * (N - D) / (N * N).toDouble) * (M - 1)
    }

    println(solve(N, M, D))
  }

}
