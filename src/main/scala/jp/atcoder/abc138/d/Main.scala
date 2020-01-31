package jp.atcoder.abc138.d

object Main {

  import scala.collection.mutable.ArrayBuffer

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, Q = sc.nextInt()
    val G = Array.fill(N+1)(ArrayBuffer[Int]())
    (0 until N - 1) foreach { _ =>
      val a, b = sc.nextInt()
      G(a).append(b)
      G(b).append(a)
    }
    val PtoX = new Array[Long](N+1)
    (0 until Q). foreach { _ =>
      val p, x = sc.nextInt()
      PtoX(p) += x
    }

    val Counter = new Array[Long](N+1)
    val visited = new Array[Boolean](N+1)

    val stack = new scala.collection.mutable.Stack[Int]
    stack.push(1)
    while (stack.nonEmpty) {
      val u = stack.pop()
      visited(u) = true
      val thisCount = PtoX(u)
      Counter(u) += thisCount
      G(u).foreach { v =>
        if (!visited(v)) {
          stack.push(v)
          Counter(v) += Counter(u)
        }
      }
    }

    println(Counter.tail.mkString(" "))
  }

}
