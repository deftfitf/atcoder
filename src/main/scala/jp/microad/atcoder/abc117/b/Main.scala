package jp.microad.atcoder.abc117.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val arr = Array.fill(N)(sc.nextInt()).sorted(Ordering.Int.reverse)
    println(if (arr.head < arr.tail.sum) "Yes" else "No")
  }

}
