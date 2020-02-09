package jp.atcoder.abc154.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    if (Array.fill(N)(sc.nextInt()).toSet.size == N) println("YES")
    else println("NO")
  }

}
