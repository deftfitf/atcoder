package aoj.ITP1.ITP1_7_B

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    def solve(n: Int, x: Int): Seq[(Int, Int, Int)] =
      for {
        a <- 1 to n
        b <- (a + 1) to n
        c <- (b + 1) to n
        if a + b + c == x
      } yield (a, b, c)

    val res = Stream.continually(sc.nextLine().split(" ").map(_.toInt))
      .map(arr => (arr(0), arr(1)))
      .takeWhile { case (n, x) => !(n == 0 && x == 0) }
      .map(input => solve(input._1, input._2))
      .map(_.size)

    println(res.mkString("\n"))
  }

}
