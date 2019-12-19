package jp.microad.atcoder.abc070.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val T = Array.fill(N)(sc.nextLong())

    // 各時計は Ti の倍数の時に 上を向く. n * Ti 秒で上になる
    // つまり, 全ての Ti の最小公倍数を求めればそれが 全て上を向く時になる

    def gcd(a: Long, b: Long): Long =
      if (a % b == 0) b
      else gcd(b, a % b)

    def lcm(a: Long, b: Long): Long =
      a * (b / gcd(a, b))

    println(T.reduce((a, b) => lcm(a, b)))
  }

}
