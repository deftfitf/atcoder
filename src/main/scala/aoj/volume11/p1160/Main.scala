package aoj.volume11.p1160

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    while (true) {
      val W, H = sc.nextInt()
      if (W == 0 && H == 0) {
        return
      }
      val C = Array.fill(H, W)(sc.nextInt())
      println(solve(W, H, C))
    }

    def solve(W: Int, H: Int, C: Array[Array[Int]]): Int = {
      val vector = List((0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1))
      def dfs(stack: List[(Int, Int)]): Unit =
        if (stack.nonEmpty) {
          val (w, h) = stack.head
          if (h >= 0 && h < H && w >= 0 && w < W && C(h)(w) == 1) {
            C(h)(w) = 0
            dfs(vector.map { case (dw, dh) => (w + dw, h + dh) } ::: stack)
          } else dfs(stack.tail)
        }

      (for {
        w <- 0 until W
        h <- 0 until H
      } yield {
        if (C(h)(w) == 1) { dfs((w, h) :: Nil); 1 } else 0
      }).sum
    }
  }

}
