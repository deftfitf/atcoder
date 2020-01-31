package jp.atcoder.abc110.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, C = sc.nextInt()
    val nums = List(A, B, C).sorted(Ordering.Int.reverse)
    println(nums.head * 10 + nums.tail.sum)
  }

}

