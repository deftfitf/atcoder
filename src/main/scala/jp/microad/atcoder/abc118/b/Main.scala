package jp.microad.atcoder.abc118.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val A = Array.fill(N)(Set[Int]())

    (0 until N) foreach { i =>
      val k = sc.nextInt()
      (0 until k) foreach { _ =>
        val a = sc.nextInt()
        A(i) = A(i) + a
      }
    }

    val r = (1 to M) count { a =>
      A.forall(_.contains(a))
    }

    println(r)
  }

}
