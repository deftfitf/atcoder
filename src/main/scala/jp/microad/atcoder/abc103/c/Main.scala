package jp.microad.atcoder.abc103.c

object Main {

//  def main(args: Array[String]): Unit = {
//    val sc = new java.util.Scanner(System.in)
//
//    val N = sc.nextInt()
//    val A = Array.fill(N)(BigInt(sc.nextLong()))
//
//    def gcd(a: BigInt, b: BigInt): BigInt =
//      if (a % b == 0L) b
//      else gcd(b, a % b)
//
//    def lcm(a: BigInt, b: BigInt): BigInt =
//      a * (b / gcd(a, b))
//
//    val LCM = A.reduce((a, b) => lcm(a, b)) - 1
//    val r = A.foldLeft(BigInt(0))((b, n) => b + LCM % n)
//    println(r)
//  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 実際にLCMを計算する必要はなく, 全ての a_i について,
    // n * a_i - 1 であるような m を設定できるので
    // それぞれ a_i - 1 を加算していけば良い

    val r = A.foldLeft(0L)((b, a) => b + a - 1)
    println(r)
  }

}
