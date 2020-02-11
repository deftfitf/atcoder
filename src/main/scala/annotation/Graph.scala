package annotation

import scala.annotation.Annotation

object Graph {

  class Kruskal extends Annotation
  class Prim extends Annotation

  class DFS extends Annotation

  /**
    * 愚直だとO(VE). トポロジカルソート版ならO(V+E).
    * 入力グラフGに閉路が含まれる場合,終了後に再度全辺を緩和することで閉路検出可能.
    * トポロジカルソート版の場合, 有向非巡回グラフでないと正しく答えが出せない.
    */
  class BellmanFord extends Annotation

  /**
    * 単一始点最短経路
    * Scalaの優先度付きキューを使った場合, O((V+E)log(V+E))ぐらい.
    * 全ての辺の重みが非負である必要がある.
    */
  class Dijkstra extends Annotation

  /**
    * 全点対最短経路
    * 実行後, D(v)(v) < 0の頂点vがある場合, 負閉路があることが検出できる.
    * O(V^3)
    **/
  class WarshallFloyd extends Annotation

}
