package jp.atcoder.abc088.d

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W = sc.nextInt(); sc.nextLine()
    val S: Array[Array[Char]] = Array.fill(H)(sc.nextLine().toCharArray)

    // 開始時点ではマス(1,1)にキャラクターがいる
    // プレイヤーは白いマスだけを通って, H, Wにたどり着けばゲームクリア
    // ゲームを開始する前に, いくつかの白いマスを黒に変えることができる
    // ただし, マス1,1とH,Wの色を変えることはできず、ゲーム開始前に全ての色の更新を
    // 行わなければならない
    // ゲームをクリアしたとき, ゲームの開始前にマスの色を変えた回数がスコアになる
    // その時, 取る可能性のある最大スコア
    // ただし, どのようにマス目の色を変えても, H,Wにたどり着くことができない時 -1になる
    // つまり, 最短距離が, ゴールする際の最小マスなので,
    // 通るときに使うマス以外は黒くすることができる.
    // つまり, 初期状態の白いマスの数 - 最短距離のマスの数 で, たどり着くことができない場合は -1となる

    def solve(H: Int, W: Int, S: Array[Array[Char]]): Int = {
      val vector = List((0, 1), (1, 0), (0, -1), (-1, 0))
      val dist = Array.fill(H, W)(-1)
      def bfs(queue: Queue[(Int, Int)]): Unit =
        if (queue.nonEmpty) {
          val (x, y) = queue.head
          val candidates = vector
            .map { case (dx, dy) => (x + dx, y + dy) }
            .filter { case (x, y) =>
              x >= 0 && x < W && y >= 0 && y < H &&
              dist(y)(x) < 0 && S(y)(x) == '.'
            }
          candidates foreach { case (nx, ny) => dist(ny)(nx) = dist(y)(x) + 1 }
          bfs(queue.tail.enqueue(candidates))
        }
      dist(0)(0) = 1
      bfs(Queue((0, 0)))
      val d = dist(H - 1)(W - 1)
      if (d == -1) d
      else {
        val white = S.foldLeft(0)((c, arr) => arr.foldLeft(c)((c, m) => if (m == '.') c + 1 else c))
        white - d
      }
    }

    val r = solve(H, W, S)
    println(r)
  }

}
