package aoj.ALDS1.ALDS1_5_A

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val n = sc.nextInt()
    val A = Array.fill(n)(sc.nextInt())
    val q = sc.nextInt()
    val M = Array.fill(q)(sc.nextInt())


    val C = new Array[Boolean](2001)
    for {
      s <- 0 until Math.pow(2, n).toInt
      l = (for {
        i <- 0 until n
        b = (s & (1 << i)) == (1 << i)
        if b
      } yield A(i)).sum
    } if (l <= 2000) C(l) = true

    println(M.map(m => C(m)).map(r => if (r) "yes" else "no").mkString("\n"))
  }

}
