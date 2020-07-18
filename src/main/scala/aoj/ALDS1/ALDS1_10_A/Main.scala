package aoj.ALDS1.ALDS1_10_A

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val F = new Array[Int](N+1)
    F(0) = 1
    F(1) = 1
    for {
      n <- 2 to N
    } {
      F(n) = F(n - 1) + F(n - 2)
    }
    println(F(N))
  }

}
