package jp.atcoder.abc145.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val _ = sc.nextInt(); sc.nextLine()
    val S = sc.nextLine()
    val (l, r) = S.splitAt(S.length / 2)
    println(if (l == r) "Yes" else "No")
  }

}
