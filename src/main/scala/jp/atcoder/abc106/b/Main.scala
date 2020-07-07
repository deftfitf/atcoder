package jp.atcoder.abc106.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()

    val count = (for {
      n <- 1 to N
      if n % 2 == 1
      count = for {
        a <- 1 to n
        if n % a == 0
      } yield a
      if count.size == 8
    } yield n)

    println(count.size)
  }

}
