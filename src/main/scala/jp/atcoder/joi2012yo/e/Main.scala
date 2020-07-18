package jp.atcoder.joi2012yo.e

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val W, H = sc.nextInt()
    val F = Array.fill(H, W)(sc.nextInt())

    // 正六角形が敷き詰められている
    // 建物が必ず1つ以上ある
    // イルミネーションで飾り付けを行う壁面の長さの合計を1行で出力せよ
    // 外から見えない部分にイルミネーションを施すのは無駄なので
    // イルミネーションは外から建物の中を通らずに行くことのできる壁面にのみ飾りつけることにした

    // 方針としては, 領域についてbfsして, 建物の外側と、内側との境界線だったらカウントすればいい
    // しかし, 一番外側の判定をする必要がある. 何層にも渡って包まれているような建物は, 結局建物の中を通らないと通れない
    // 一番外というのは, フィールドの一番外枠の建物がない区画から, bfsして到達できる領域のこと
    // 一番外からbfsしたときに建物にぶつかる, かつ, 建物についてbfsして壁に衝突したときに初めてイルミネーションを飾れる領域になる
    // 問題はどうやって領域の間の境界をコードに落とすか？
    // 移動ベクトルは, 現在のマスが奇数番目か偶数番目かでも異なる, なお, 0番目からカウントする
    val evenVector = Array((1, -1), (1, 0), (1, 1), (0, 1), (-1, 0), (0, -1))
    val oddVector = Array((0, -1), (1, 0), (0, 1), (-1, 1), (-1, 0), (-1, -1))

    // 例えば, (2,2), (2,3)の間は? H * W = 10000なので, (H*W)^2分用意すると足りなさそう
    // ベクトルを割り当てているので,
    // 例えば, あるマスについて, (h, w, v) の場所が境界の状態とできる
    // じゃあ対応するマスは? h + xVector(v).dy, w + xVector(v).dx とすればそのマスに移動できて,
    // かつベクトルは, 同じ順番で定義しているので, 対象のベクトルになる, (v + 3) % 6 が対応するベクトル,
    // しかもその対応するベクトルは奇数偶数を考える必要がある
    // if S = 0 then 何も起きていない状態, else if S = 1 then 外側もしくは, 内側のどちらかが衝突したケース
    // else if S = 2 then 外側と, 内側の両方から衝突したケース.
    // 最終的に, S = 2の境界のカウント / 2すればいい
    val S = Array.fill(H, W, 6)(0)
    val visited = Array.fill(H, W)(false)
    def bfs(sx: Int, sy: Int, target: Int): Unit = {
      def recursive(queue: Queue[(Int, Int)]): Unit =
        if (queue.nonEmpty) {
          val (x, y) = queue.head
          val vectors = if (y % 2 == 0) evenVector else oddVector
          val candidate = collect(x, y, target, vectors)
          recursive(queue.tail.enqueue(candidate))
        }
      // ベクトルを見ていき, 壁の衝突と, 次の異動先のチェックを同時に行う
      def collect(x: Int, y: Int, target: Int, vector: Array[(Int, Int)]): List[(Int, Int)] = {
        def recursive(v: Int, candidate: List[(Int, Int)]): List[(Int, Int)] =
          if (v < 6) {
            val (dx, dy) = vector(v)
            val nx = x + dx
            val ny = y + dy
            // 壁の衝突, 必ず1度しかこの角度からの衝突はない,
            if (!(nx >= 0 && nx < W && ny >= 0 && ny < H)) {
              if (target == 1) {
                S(y)(x)(v) += 4
              }
              recursive(v + 1, candidate)
            } else if (F(ny)(nx) != target) {
              S(y)(x)(v) += 1
              S(ny)(nx)((v + 3) % 6) += 1
              recursive(v + 1, candidate)
            } else if (!visited(ny)(nx)) {
              visited(ny)(nx) = true
              recursive(v + 1, (nx, ny) :: candidate)
            } else recursive(v + 1, candidate)
          } else candidate
        recursive(0, Nil)
      }
      visited(sy)(sx) = true
      recursive(Queue((sx, sy)))
    }
    // 外側チェック
    val outer = (for {
      y <- (0 until H)
      x <- List(0, W - 1)
    } yield (x, y)) ++ (
      for {
        x <- (0 until W)
        y <- List(0, H - 1)
      } yield (x, y)
    )
    for {
      (x, y) <- outer
    } if (!visited(y)(x) && F(y)(x) == 0) bfs(x, y, 0)

    // 内側チェック
    for {
      y <- 0 until H
      x <- 0 until W
    } if (!visited(y)(x) && F(y)(x) == 1) bfs(x, y, 1)

    val r = S.foldLeft(0)((s, arr) => arr.foldLeft(s)((s, vectors) => vectors.foldLeft(s)((s, c) => if (c % 2 == 0) s + c / 2 else s))) / 2
    println(r)
  }
}
