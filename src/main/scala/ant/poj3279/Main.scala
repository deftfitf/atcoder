package ant.poj3279

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val M, N = sc.nextInt()
    val A = Array.fill(M, N)(sc.nextInt())

    def solve(M: Int, N: Int, A: Array[Array[Int]]): Option[Array[Array[Int]]] = {
      def enumerations(stack: List[(Int, List[Int])], minArr: Option[Array[Array[Int]]], min: Int): Option[Array[Array[Int]]] =
        if (stack.nonEmpty) {
          val (i, lst) = stack.head
          if (i < N) enumerations((i+1, lst) :: (i+1, i :: lst) :: stack.tail, minArr, min)
          else {
            flip(lst) match {
              case Some((arr, count)) =>
                if (count < min) enumerations(stack.tail, Some(arr), count)
                else enumerations(stack.tail, minArr, min)
              case None =>
                enumerations(stack.tail, minArr, min)
            }
          }
        } else minArr

      def flip(flipped: List[Int]): Option[(Array[Array[Int]], Int)] = {
        var count = 0
        val I = Array.fill(M, N)(0)
        (0 until N) foreach (j => I(0)(j) = 0)
        flipped foreach { j =>
          count += 1
          I(0)(j) = 1
        }

        for {
          i <- 1 until M
          j <- 0 until N
        } {
          if ((A(i-1)(j) + I(i-1)(j) +
            (if (i >= 2) I(i-2)(j) else 0) +
            (if (j >= 1) I(i-1)(j-1) else 0) +
            (if (j < N - 1) I(i-1)(j+1) else 0)) % 2 == 1) {
            count += 1
            I(i)(j) = 1
          }
        }

        if ((0 until N).forall { j =>
          (A(M-1)(j) + I(M-1)(j) + I(M-2)(j) +
            (if (j >= 1) I(M-1)(j-1) else 0) +
            (if (j < N - 1) I(M-1)(j+1) else 0)) % 2 == 0
        }) Some((I, count))
        else None
      }

      enumerations((0, Nil) :: Nil, None, M * N + 1)
    }

    solve(M, N, A) match {
      case Some(r) => println(r.map(_.mkString(" ")).mkString("\n"))
      case None => println("IMPOSSIBLE")
    }
  }

}
