package jp.atcoder.abc150.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val A = Array.fill(N)(sc.nextLong() / 2)

    // aの要素は全て偶数, pが整数であることから,
    // Xは奇数である必要がある. (偶数の場合, pが整数でなくなる.)
    // とにかく, 任意のa_kである数Xを割った数のあまりが a_k / 2 の場合,
    // pが存在すると考えられる.
    // X = (a_k / 2)(2p + 1)
    // なので, 常に 2p + 1は奇数である必要があり,
    // a_k / 2 を2で割れる回数は全て一致している必要がある.
    def div2count(n: Long): Long = {
      def loop(n: Long, count: Long): Long =
        if (n % 2 == 0) loop(n / 2, count + 1)
        else count
      loop(n, 0)
    }

    def gcd(a: Long, b: Long): Long =
      if (a % b == 0) b
      else gcd(b, a % b)

    def lcm(a: Long, b: Long): Long =
      a * b / gcd(a, b)

    val res =
      if (A.map(div2count).forall(_ == div2count(A(0)))) {
        val l = A.reduce(lcm)
        (1L to (M / l) by 2).size
      } else 0

    println(res)
  }

}
