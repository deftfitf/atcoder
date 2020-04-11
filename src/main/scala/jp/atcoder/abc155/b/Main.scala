package jp.atcoder.abc155.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    println(if (A.forall(a => {
      if (a % 2 == 0) a % 3 == 0 || a % 5 == 0
      else true
    })) "APPROVED" else "DENIED")
  }

}
