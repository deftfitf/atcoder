package jp.microad.atcoder.educationdp.g

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N, M = sc.nextInt()
    val G = Array.fill[List[Int]](N+1)(Nil)
    (0 until M) foreach { _ =>
      val u, v = sc.nextInt()
      G(u) = v :: G(u)
    }

    val dp = Array.fill(N+1)(-1)
    val stack = new scala.collection.mutable.Stack[Int]
    (1 to N) foreach { u =>
      if (dp(u) == -1) {
        stack.push(u)
        while (stack.nonEmpty) {
          val u = stack.pop()
          if (u < 0) {
            dp(-u) = 0
            G(-u).foreach { v =>
              dp(-u) = dp(-u) max (dp(v) + 1)
            }
          } else {
            stack.push(-u)
            G(u).foreach { v =>
              if (dp(v) == -1)
                stack.push(v)
            }
          }
        }
      }
    }
    println(dp.max)
  }

}
