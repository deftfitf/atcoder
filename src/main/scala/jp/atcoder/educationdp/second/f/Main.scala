package jp.atcoder.educationdp.second.f

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S, T = sc.nextLine().toCharArray
    val SL = S.length
    val TL = T.length

    // dp[i][j] := Sのi番目までとTのj番目までの文字列の最長共通部分列の長さ
    // S[i] == T[j] => dp[i][j] = dp[i-1][j-1] + 1
    // i番目とj番目の文字列が異なる場合,
    // S[i] != T[j] => dp[i][j] = max(dp[i][j-1], dp[i-1][j])
    // dp[0][0] = 0
    val dp = Array.fill(SL+1, TL+1)(0)
    // では文字列情報はどのように渡すか? 更新が発生している時, 文字列が結合されている
    // 下から持ち上げられてきたとき, 文字列情報がそのまま持ち上げられている
    // なので, 文字列リテラルを保持しておく(インスタンス共有されるから足りるやろ)
    // ↑ 文字列結合がネックになってTLE
    // 移動情報を使って辿ることにする.
    // その場の文字を使った場合, c[i][j] = 0
    // [i][j-1]の文字を使った場合, c[i][j] = 1
    // [i-1][j]の文字を使った場合, c[i][j] = 2 と記録して c[SL][TL]から辿る
    val c = Array.fill(SL+1, TL+1)(-1)
    for {
      i <- 0 until SL
      j <- 0 until TL
    } if (S(i) == T(j)) {
      dp(i+1)(j+1) = dp(i)(j) + 1
      c(i+1)(j+1) = 0
    } else if (dp(i+1)(j) > dp(i)(j+1)) {
      dp(i+1)(j+1) = dp(i+1)(j)
      c(i+1)(j+1) = 1
    } else {
      dp(i+1)(j+1) = dp(i)(j+1)
      c(i+1)(j+1) = 2
    }

    // 辿る
    def printC(S: Array[Char],
               dp: Array[Array[Int]], c: Array[Array[Int]]): String = {
      @scala.annotation.tailrec
      def loop(i: Int, j: Int, str: List[Char]): List[Char] =
        if (!(i == 0 && j == 0)) {
          c(i)(j) match {
            case 0 => loop(i-1, j-1, S(i-1) :: str)
            case 1 => loop(i,   j-1, str)
            case 2 => loop(i-1, j,   str)
            case _ => str
          }
        } else str
      loop(SL, TL, Nil).mkString
    }

    println(printC(S, dp, c))
  }

}
