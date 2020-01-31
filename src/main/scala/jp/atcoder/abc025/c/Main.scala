package jp.atcoder.abc025.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val B = Array.fill(N-1)(sc.nextInt())
    val BMax, BMin = Array.fill(N+1)(0L)
    val Salary = Array.fill(N+1)(0L)

    var i = N - 2
    while (i >= 0) {
      val current = i + 2
      val parent = B(i)
      Salary(current) = BMax(current) + BMin(current) + 1
      BMax(parent) = BMax(parent) max Salary(current)
      if (BMin(parent) >= 1)
        BMin(parent) = BMin(parent) min Salary(current)
      else
        BMin(parent) = Salary(current)
      i -= 1
    }

    println(BMax(1) + BMin(1) + 1)
  }

}
