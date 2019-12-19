package jp.microad.atcoder.abc109.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, X = sc.nextInt()
    val x = Array.fill(N)(sc.nextInt() - X)
    // Dの到達可能性？
    // +D, -Dにしか移動できないということは
    // x' = X + nD で表せる箇所にしか到達できないということ
    // 初期化時に x_? について, -X をしておく
    // すると各xについて x % D == 0 を調べればよくなる
    // O(N)ではまだ通らないので D を log Nぐらいで見つけたい...
    // 全ての x について -X しておくと nD で表せて
    // かつ最大のDを見つけたいということはDは最大公約数になる
    // 累積GCDしたらそれがD
    def gcd(a: Int, b: Int): Int =
      if (a % b == 0) b
      else gcd(b, a % b)

    val D = x.reduce((a, b) => gcd(a, b)).abs
    println(D)
  }

}
