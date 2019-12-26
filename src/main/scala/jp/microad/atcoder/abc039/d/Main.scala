package jp.microad.atcoder.abc039.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W = sc.nextInt(); sc.nextLine()
    val S = Array.fill(H)(sc.nextLine().toArray)

    def solve(H: Int, W: Int, S: Array[Array[Char]]): Option[Array[Array[Char]]] = {
      val recovered = Array.fill(H, W)('.')
      val dx = Array(-1, 0, 1, -1, 1, -1, 0, 1)
      val dy = Array(-1, -1, -1, 0, 0, 1, 1, 1)
      val visited = Array.fill(H, W)(true)
      def init(): Unit = {
        for {
          y <- 0 until H
          x <- 0 until W
        } if (S(y)(x) == '#')
          visited(y)(x) = false
      }
      init()

      for {
        y <- 0 until H
        x <- 0 until W
        if S(y)(x) == '#'
      } {
        if ((for {
          k <- 0 until 8
          nx = x + dx(k)
          ny = y + dy(k)
          if ny >= 0 && ny < H && nx >= 0 && nx < W
        } yield S(ny)(nx) == '#').forall(identity)) {
          visited(y)(x) = true
          for {
            k <- 0 until 8
            nx = x + dx(k)
            ny = y + dy(k)
            if ny >= 0 && ny < H && nx >= 0 && nx < W
          } visited(ny)(nx) = true
          recovered(y)(x) = '#'
        }
      }

      if (visited.forall(_.forall(identity))) Some(recovered)
      else None
    }

    solve(H, W, S) match {
      case Some(arr) => println("possible")
        println(arr.map(_.mkString).mkString("\n"))
      case None => println("impossible")
    }
  }

}
