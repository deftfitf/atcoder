package jp.atcoder.abc148.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B = sc.nextInt()

    def gcd(a: Long, b: Long): Long =
      if (a % b == 0) b
      else gcd(b, a % b)

    def lcm(a: Long, b: Long): Long =
      a * (b / gcd(a, b))

    println(lcm(A, B))
  }

}
