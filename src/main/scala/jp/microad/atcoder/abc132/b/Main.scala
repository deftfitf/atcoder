package jp.microad.atcoder.abc132.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val n = sc.nextInt()
    val p = Array.fill(n)(sc.nextInt())
    val count = p.sliding(3, 1).count { case Array(a, b, c) =>
      if ((b >= a && b < c) || (b >= c && b < a)) true
      else false
    }
    println(count)
  }

}
