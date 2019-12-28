package jp.microad.atcoder.abc109.d

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W = sc.nextInt()
    val A = Array.fill(H,W)(sc.nextInt())

    // １マスにつき一回、１枚だけコインを上下左右のどこかのマスに移動させれる
    // 偶数枚のコインが置かれたマスの数を最大化
    // 既に偶数枚置かれているマスは, 動かしても意味がない
    // 奇数枚置かれているマスから奇数枚置かれているマスに移動させるのがベスト
    // ただし, 奇数枚置かれているマスから偶数枚動かすパターンでもあとあと全部偶数にできるケースがある
    // 奇数枚のマスと奇数枚のマスが離れていて, その間の偶数枚のマスの上をコインをスライドさせれば
    // その経路は使用不可能になるが全て偶数枚のマスにすることができる
    // 奇数枚のマスから奇数枚のマスへのBFSを行えば良さそう
    // すると, 奇数枚のマスを発見した場合, そこから最短で見つかる奇数枚のマスを見つける
    // 経路を覚えておき, 使った経路のみを次回の探索に使えないようにし, かつ操作として加える,
    type Path = (Int, Int, Int, Int)
//    def solve(H: Int, W: Int, A: Array[Array[Int]]): List[Path] = {
//      val dx = Array(-1, 0, 1, 0)
//      val dy = Array(0, -1, 0, 1)
//      val used = Array.fill(H, W)(false)
//      val visited = Array.fill(H, W)(false)
//      def clear(): Unit =
//        for {
//          i <- 0 until H
//          j <- 0 until W
//        } visited(i)(j) = false
//
//      def find(sx: Int, sy: Int): Option[List[Path]] = {
//        def bfs(queue: Queue[((Int, Int), List[Path])]): Option[List[Path]] =
//          if (queue.nonEmpty) {
//            val (((x, y), _path), next) = queue.dequeue
//            if (!(x == sx && y == sy) && A(y)(x) % 2 == 1) {
//              _path foreach { case (x, y, gx, gy) =>
//                used(y)(x)   = true
//                used(gy)(gx) = true
//              }
//              Some(_path)
//            } else {
//              bfs((0 until 3).foldLeft(next)((queue, i) => {
//                val nx = x + dx(i)
//                val ny = y + dy(i)
//                if (nx >= 0 && nx < W && ny >= 0 && ny < H && !used(ny)(nx) && !visited(ny)(nx)) {
//                  visited(ny)(nx) = true
//                  queue.enqueue(((nx, ny), (x, y, nx, ny) :: _path))
//                } else queue
//              }))
//            }
//          } else None
//        bfs(Queue(((sx, sy), List[Path]())))
//      }
//
//      var paths = List[Path]()
//      for {
//        i <- 0 until H
//        j <- 0 until W
//      } yield {
//        if (!used(i)(j) && A(i)(j) % 2 == 1) {
//          find(j, i) foreach { p =>
//            paths = p ++ paths
//          }
//          clear()
//        }
//      }
//      paths.reverse
//    }

    // 実際には、一筆書きをすればよかった
    // 奇数から奇数にスライドすれば偶数になるので,
    // 奇数から始まり奇数に終わる全てのマスについて, 一筆書きを行うと
    // 奇数マスが偶数個あれば, 全てのマスについて偶数にできるが
    // 奇数個の場合には一つだけ残る
    // また, 無駄な操作があっても良い
    def solve(H: Int, W: Int, A: Array[Array[Int]]): List[Path] = {
      def moveRight(x: Int, y: Int, path: List[Path]): List[Path] = {
        if (x < W - 1) {
          if (A(y)(x) % 2 == 1) {
            A(y)(x) -= 1
            A(y)(x+1) += 1
            moveRight(x + 1, y, (x, y, x+1, y) :: path)
          } else moveRight(x + 1, y, path)
        } else {
          if (A(y)(x) % 2 == 1) {
            if (y < H - 1) {
              A(y)(x) -= 1
              A(y+1)(x) += 1
              moveLeft(x, y + 1, (x, y, x, y + 1) :: path)
            } else path
          } else {
            if (y < H - 1) {
              moveLeft(x, y + 1, path)
            } else path
          }
        }
      }
      def moveLeft(x: Int, y: Int, path: List[Path]): List[Path] = {
        if (x > 0) {
          if (A(y)(x) % 2 == 1) {
            A(y)(x) -= 1
            A(y)(x-1) += 1
            moveLeft(x - 1, y, (x, y, x-1, y) :: path)
          } else moveLeft(x - 1, y, path)
        } else {
          if (A(y)(x) % 2 == 1) {
            if (y < H - 1) {
              A(y)(x) -= 1
              A(y + 1)(x) += 1
              moveRight(x, y + 1, (x, y, x, y + 1) :: path)
            } else path
          } else {
            if (y < H - 1) {
              moveRight(x, y + 1, path)
            } else path
          }
        }
      }
      moveRight(0, 0, Nil).reverse
    }

    val paths = solve(H, W, A)
    println(paths.size)
    println(paths.map { case (x, y, gx, gy) =>
      s"${y+1} ${x+1} ${gy+1} ${gx+1}"
    }. mkString("\n"))
  }

}
