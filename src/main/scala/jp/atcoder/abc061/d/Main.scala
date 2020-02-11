package jp.atcoder.abc061.d

object Main {

  val INF = Long.MaxValue

  case class Edge(u: Int, v: Int, weight: Int)
  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E = sc.nextInt()

    val edges = (0 until E).foldLeft(Nil: List[Edge])((lst, _) => {
      val u, v, w = sc.nextInt()
      Edge(u, v, -w) :: lst
    })

    // N頂点,M辺の重み付き有向グラフ
    // このグラフと駒を使用する
    // 最初, 駒を頂点1に置いて, プレイヤーのスコアを0とします
    // プレイヤーは次の条件で駒を繰り返し移動させることができます
    // * 頂点a_iに駒がある時, i番目の辺を利用して, 頂点b_iに移動する
    //   移動後にプレイヤーのスコアがc_i加算される
    // 頂点Nに駒がある時のみ, ゲームを終了できます.
    // なお, 与えられる有向グラフの上で、頂点1から頂点Nに移動できることは保証されています
    // プレイヤーがゲーム終了時のスコアをできるだけ大きくするような行動を取った時
    // ゲーム終了時のスコアは幾つになるでしょうか?

    // なんで, 閉路がある場合には, いくらでも回ればいいので, INFになる
    // なるべくスコアを大きくしたいので, この場合, c' = -c となる新しい重みを
    // 導入すると, u -> vに行くときになるべく大きい値を取るようになる(最短路なので)

    // 始点と終点が決まっているので, 単一始点最短経路問題に帰着する.
    // 負辺があるのでダイクストラは使えない.
    // 普通にO(VE)でもV = 1000, E = 2000 なので間に合う感じ
    bellmanFord(V, edges, 1) match {
      case Some(v) => println(v)
      case _ => println("inf")
    }
  }

  @annotation.Graph.BellmanFord
  def bellmanFord(V: Int, edges: List[Edge], s: Int): Option[Long] = {
    val dist = Array.fill(V+1)(INF)

    dist(s) = 0L
    for {
      _ <- 0 until V - 1
      edge <- edges
    } relax(edge.u, edge.v, edge.weight)

    def relax(u: Int, v: Int, w: Int): Unit =
      if (dist(u) != INF && dist(v) > dist(u) + w) {
        dist(v) = dist(u) + w
      }

    val result = dist(V)
    // 問題は, 1からNの道の途中に閉路があるかどうかという点
    // 単にベルマンフォードの閉路検出の場合, それが1からNの道の途中に存在するかどうかは
    // わからない.
    // なので, 緩和した時に, それがVに対して緩和が発生したかどうか？ということを確認する必要がある
    // また, 閉路は最大V本の辺によって構成されるので, V回全ての辺に対して緩和を行わないと
    // 実際にその頂点への道の途中に閉路が存在するかどうかは確認できない
    val negative = new Array[Boolean](V+1)
    def relax2(u: Int, v: Int, w: Int): Unit =
      if (dist(u) != INF && dist(v) > dist(u) + w) {
        dist(v) = dist(u) + w
        negative(v) = true
      }

    for {
      _ <- 0 until V
      edge <- edges
    } relax2(edge.u, edge.v, edge.weight)

    if (!negative(V)) Some(-result)
    else None
  }

}
