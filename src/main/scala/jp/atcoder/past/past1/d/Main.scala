package jp.atcoder.past.past1.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    def solve(N: Int, A: Array[Int]): Unit = {
      val nums = new Array[Boolean](N+1)
      for {
        i <- 0 until N
      } {
        if (nums(A(i))) {
          println(s"${A(i)} ${((1 to N).toSet diff A.toSet).head}")
          return
        } else nums(A(i)) = true
      }
      println("Correct")
    }

    solve(N, A)
  }

}
