package jp.atcoder.abc146.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val G = Array.fill(N + 1)(List[(Int, Int)]())
    (0 until N - 1) foreach { i =>
      val a, b = sc.nextInt()
      G(a) ::= (b, i)
      G(b) ::= (a, i)
    }

    // Gの'辺'を何色かで塗り分ける
    // 各頂点について, その頂点を端点に持つ辺の色が全て相異なるようにしたい
    // つまり, ある頂点を見たときに, その頂点から生えている辺は全て違う色である必要がある
    // 木なので, そのさらに次の頂点では, 既に使っている1色以外を再利用してふり直せばいい
    // 通ってきた辺の色を受け取っておけば良い. スタート時には, 0(undefined) からきたことにする
    // 来たところから戻らないようにする
    // 知見, 木の場合だと, 来たところに戻らないようにするだけでいい. そうでない場合だとその前提が崩れるので
    // 「訪れた」時のみ訪れたことにし、既に訪れていたときにはパスすることになる（いうて定数倍削減だけど）
    def solve(N: Int, G: Array[List[(Int, Int)]]): (Int, Array[Int]) = {
      val color = new Array[Int](N - 1)
      val visited = new Array[Boolean](N + 1)
      def dfs(stack: List[(Int, Int)], K: Int): Int =
        if (stack.nonEmpty) {
          val (u, c) = stack.head
          visited(u) = true
          val (next, colors) =
            G(u).foldLeft((stack.tail, 1))((state, v) =>
              if (!visited(v._1)) {
                if (state._2 == c) {
                  color(v._2) = state._2 + 1
                  ((v._1, state._2 + 1) :: state._1, state._2 + 2)
                } else {
                  color(v._2) = state._2
                  ((v._1, state._2) :: state._1, state._2 + 1)
                }
              } else state)
          dfs(next, K max (colors - 1))
        } else K
      (dfs((1, 0) :: Nil, 0), color)
    }

    val (k, c) = solve(N, G)
    println(k)
    println(c.mkString("\n"))
  }

}
