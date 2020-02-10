package annotation

import scala.annotation.Annotation

object Graph {

  class Kruskal extends Annotation
  class Prim extends Annotation

  class DFS extends Annotation

  // 愚直だとO(VE). トポロジカルソート版ならO(V+E).
  // 入力グラフGに閉路が含まれる場合,終了後に再度全辺を緩和することで閉路検出可能.
  // トポロジカルソート版の場合, 有向非巡回グラフでないと正しく答えが出せない.
  class BellmanFord extends Annotation

}
