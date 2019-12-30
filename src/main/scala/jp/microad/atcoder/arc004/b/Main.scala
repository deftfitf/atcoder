package jp.microad.atcoder.arc004.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val D = Array.fill(N)(sc.nextInt()).sorted(Ordering.Int.reverse)

    // 最大の場合は単に伸ばしきった時の長さが最大だが,
    // 最小の場合は？
    // 繋げることができるなら最小は常に0になりそうだが...
    def solve(N: Int, D: Array[Int]): (Int, Int) = {
      val dist = (1 until N).map(D).sum
      (dist + D(0), if (dist >= D(0)) 0 else D(0) - dist)
    }

    val (max, min) = solve(N, D)
    println(s"$max\n$min")
  }

}
