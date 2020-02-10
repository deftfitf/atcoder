package jp.atcoder.aoj.alds.alds1_12a

object MinimumSpanningTree {

  import scala.collection.mutable
  import scala.collection.mutable.ArrayBuffer

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V = sc.nextInt()
    val G = Array.fill(V, V)(sc.nextInt())

    // 訪問済みの頂点を入れる集合
    // 全ての頂点を訪れるまで, 訪れたかつ, 訪れていない頂点に行くような辺のうち
    // 重み最小のものをe = {u,v}とする
    // Gを張る最小全域木を構築する
    @annotation.Graph.Prim
    def prim(V: Int, G: Array[Array[Int]]): Array[ArrayBuffer[(Int, Int)]] = {
      val MST = Array.fill(V)(new ArrayBuffer[(Int, Int)]())
      val visited = mutable.Set[Int]()

      val priorityQueue =
        new mutable.PriorityQueue[(Int, Int)]()(
          Ordering.by[(Int, Int), Int]{case (u, v) => G(u)(v)}.reverse)

      priorityQueue.enqueue((0, 0))
      while (visited.size < V) {
        val (u, v) = priorityQueue.dequeue()
        if (!visited.contains(v)) {
          for {
            i <- G(v).indices
            if G(v)(i) >= 0
          } priorityQueue.enqueue((v, i))
          MST(u).append((v, G(u)(v)))
          visited.add(v)
        }
      }

      MST
    }

    // 最小全域木を受け取って, 重みの合計を計算する.
    @annotation.Graph.DFS
    def sum(V: Int, G: Array[ArrayBuffer[(Int, Int)]]): Long = {
      val visited = new Array[Boolean](V)
      def dfs(stack: List[(Int, Int)], sum: Long): Long =
        if (stack.nonEmpty) {
          val (u, w) = stack.head
          if (!visited(u)) {
            visited(u) = true
            dfs(G(u).foldLeft(stack.tail)((stack, v) => v :: stack), sum + w)
          } else dfs(stack.tail, sum)
        } else sum
      dfs((0, 0) :: Nil, 0L)
    }

    println(sum(V, prim(V, G)))
  }

}
