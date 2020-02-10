package jp.atcoder.codefestival.y2014.d

@annotation.DP.DigitDP
object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A = sc.nextLong()
    val K = sc.nextInt()

    // つまり, 整数A以下の, 壊れない範囲の一番大きな数字との差
    // AはLong型で足りる.
    // dp[f][i][s] := fはN未満フラグ, iは決定した桁数, sは, 0 until 2^10の数で,
    //                各ビットがその数を使っていることを示す
    //                0000010101は, 0, 2, 4が使われていることを示し
    //                dp[f][i][0000010101]はi桁目まで決められた時の
    //                0, 2, 4が使われている時点で最大の整数を示す
    // ビットの状態数Sとすると, O (2 * L * S * 10)で更新可能で
    // S = 2^10 = 1024 なので, 十分に間に合う
    // 更新は
    // dp[f][i+1][(s | (1 << d))] = max{dp[f][i+1][(s | (1 << d))], dp[f][i][s]}
    //                            if (s & d) != d なら (既にその状態で新しく追加する数を使っていないなら)
    // みたいな感じで更新できそう
    // なお, 既にi-1桁目までで, i個の数字を使っていて小さい数が存在していた場合には
    // その小さな数字が今後これ以降大きくなることはないという性質で, 更新が正しくなる.
    // 最終的な答えば, max(f = 0 ~ 1, i = 0 ~ 1023){ dp[f][L][i] }の値

    // と思ったが, 問題の意味を履き違えていた
    // K種類の文字まで使っても大丈夫
    // なので
    // d_bit = (1 << d)
    // cor = 10 ^ (L - i - 1)
    // dp[f][i+1][s | d_bit] =
    //   max { dp[f][i+1][s | d_bit], dp[f][i][s] + d * cor }
    // としておき, 最後に, s の各桁のbitの合計 <= Kのdp[f][L][s]の最大を取ればいい
    // また, A以下という制約もないので, 一桁上げて計算する

    // 下がってくるパターンと上がってくるパターン, 両方求めて小さい方が答えになる

    val R1 = digitDP1(A.toString, K)
    val R2 = digitDP2("0" + A.toString, K)
    println((A - R1).abs min (A - R2).abs)
  }

  def digitDP1(S: String, K: Int): Long = {
    val L = S.length
    val UNDEFINED = Long.MinValue
    val dp = Array.fill(2, L+1, 1024)(UNDEFINED)
    dp(0)(0)(0) = 0
    for {
      i <- 0 until L
      ni = S.charAt(i) - '0'
      f <- 0 until 2
      d <- 0 to (if (f == 1) 9 else ni)
      s <- 0 until 1024
      bit = if (s == 0 && d == 0) 0 else 1 << d // 最初に0を使った場合, それは使ったことにはならない.
      cor = Math.pow(10, L - i - 1).toLong
    } if (f == 1 || d < ni) {
      if (dp(f)(i)(s) != UNDEFINED) {
        dp(1)(i+1)(s | bit) =
          dp(1)(i+1)(s | bit) max (dp(f)(i)(s) + d * cor)
      }
    } else {
      if (dp(f)(i)(s) != UNDEFINED)
        dp(0)(i+1)(s | bit) =
          dp(0)(i+1)(s | bit) max (dp(f)(i)(s) + d * cor)
    }

    val statusRange = (0 until 1024).filter(s => sumBit(s) <= K)
    (0 until 2).foldLeft(0L)((max, f) =>
      statusRange.foldLeft(max)((max, s) =>
        max max dp(f)(L)(s)))
  }

  def digitDP2(S: String, K: Int): Long = {
    val L = S.length
    val UNDEFINED = Long.MaxValue
    val dp = Array.fill(2, L+1, 1024)(UNDEFINED)
    dp(0)(0)(0) = 0
    for {
      i <- 0 until L
      ni = S.charAt(i) - '0'
      f <- 0 until 2
      d <- (if (f == 1) 0 else ni) to 9
      s <- 0 until 1024
      bit = if (s == 0 && d == 0) 0 else 1 << d
      cor = Math.pow(10, L - i - 1).toLong
    } if (f == 1 || d > ni) {
      if (dp(f)(i)(s) != UNDEFINED) {
        dp(1)(i+1)(s | bit) =
          dp(1)(i+1)(s | bit) min (dp(f)(i)(s) + d * cor)
      }
    } else {
      if (dp(f)(i)(s) != UNDEFINED)
        dp(0)(i+1)(s | bit) =
          dp(0)(i+1)(s | bit) min (dp(f)(i)(s) + d * cor)
    }

    val statusRange = (0 until 1024).filter(s => sumBit(s) <= K)
    (0 until 2).foldLeft(Long.MaxValue)((min, f) =>
      statusRange.foldLeft(min)((min, s) =>
        min min dp(f)(L)(s)))
  }

  // ビットの立っている数を合わせる
  def sumBit(n: Int): Int = {
    def loop(n: Int, acm: Int): Int =
      if (n > 0) loop(n - (n & -n), acm + 1)
      else acm
    loop(n, 0)
  }

}
