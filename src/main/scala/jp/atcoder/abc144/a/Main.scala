package jp.atcoder.abc144.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val result = for {
      a <- (1 to 9)
      b <- (1 to 9)
      if a * b == N
    } yield ()

    if (result.nonEmpty) println("Yes")
    else println("No")
  }

}