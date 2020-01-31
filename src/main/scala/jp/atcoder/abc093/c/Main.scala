package jp.atcoder.abc093.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = new Array[Int](N+2)
    (1 to N) foreach { i =>
      A(i) = sc.nextInt()
    }

    val MAX = A.sliding(2).foldLeft(0)((b, slide) => b + slide.reduce((a, b) => (b - a).abs))
    val r = (1 to N) map { i =>
      MAX - ((A(i) - A(i - 1)).abs + (A(i + 1) - A(i)).abs) + (A(i + 1) - A(i - 1)).abs
    }

    println(r.mkString("\n"))
  }

}
