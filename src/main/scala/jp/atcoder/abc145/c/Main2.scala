package jp.atcoder.abc145.c

object Main2 {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val P = Array.fill(N, 2)(sc.nextInt())

    // 街を全て一回ずつ訪れるとき, 街を訪れる経路は全部でN!通りあります
    // 1番目に訪れる街, ,,,
    // N * N - 1 * N - 2 * ... * 1 通り
    // その経路の長さとします。これらのN!通りの経路の長さの平均値を計算してください
    val (sum, n) = (0 until N).permutations.foldLeft((0.0, 0))(
      (sum, permutation) => (permutation.sliding(2).foldLeft(sum._1)(
        (sum, path) => {
          val Seq(p1, p2) = path
          sum + Math.sqrt(Math.pow((P(p1)(0) - P(p2)(0)), 2) + Math.pow(P(p1)(1) - P(p2)(1), 2))
        }), sum._2 + 1))
    println(sum / n.toDouble)
  }

}
