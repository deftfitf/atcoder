package jp.microad.atcoder.abc135.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val sortedP = (1 to N).toArray
    val P = Array.fill(N)(sc.nextInt())

    def check(a: Array[Int], b: Array[Int], ii: Int, jj: Int): Boolean = {
      var i = 0
      var r = true
      while (i < N) {
        r &&= {
          if (i == ii) a(jj) == b(i)
          else if (i == jj) a(ii) == b(i)
          else a(i) == b(i)
        }
        i += 1
      }
      r
    }

    val results = for {
      i <- 0 until N
      j <- 0 until N
    } yield check(P, sortedP, i, j)
    if (results.exists(identity) || check(P, sortedP, 0, 0)) {
      println("YES")
    } else println("NO")
  }

}
