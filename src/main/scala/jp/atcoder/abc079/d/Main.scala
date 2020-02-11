package jp.atcoder.abc079.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W = sc.nextInt()
    val C = Array.fill(10, 10)(sc.nextInt())
    val A = Array.fill(H, W)(sc.nextInt())

    // つまり, どの数字をどの順番で変えてもいいし, 同じ数字に変えてもいい
    // ある数字は, 必ず同じ順番で数字を変化させ, 最終的に数字を1にする
    // ある数字を1に変える最小魔力は ある数字u からある数字vへの変換の最小コストを求める
    // 全点対最短経路問題に帰着でき, Cはそのまま数字から数字への接続を表す無向グラフを表現している
    // V = 10程度なので, O(V^3)のワーシャルフロイドで解くことができる
    val d = floydWarshall(10, C)
    val minCost = (for {
      i <- 0 until H
      j <- 0 until W
      if A(i)(j) != -1
    } yield d(A(i)(j))(1)).sum

    println(minCost)
  }

  @annotation.Graph.WarshallFloyd
  def floydWarshall(V: Int, G: Array[Array[Int]]): Array[Array[Int]] = {
    for {
      k <- 0 until V
      u <- 0 until V
      v <- 0 until V
    } G(u)(v) = G(u)(v) min (G(u)(k) + G(k)(v))
    G
  }

}
