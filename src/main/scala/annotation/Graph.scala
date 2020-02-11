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
    * O(V^3), 隣接行列を使う必要があり, 空間計算量O(V^2)が必要になる.
    **/
  class WarshallFloyd extends Annotation

  /**
    * Ford-Folkerson法,
    * 最大フロー値 |f|の場合, O(|f|E)
    *
    * 最大フロー問題, 超入口, 超出口を導入することで, 二部グラフの最大マッチング問題が可能
    */
  class FordFolkerson extends Annotation

}
