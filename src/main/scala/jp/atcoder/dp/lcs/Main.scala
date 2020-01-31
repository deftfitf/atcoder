package jp.atcoder.dp.lcs

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S, T = sc.nextLine()

    val dp = Array.fill(S.length + 1, T.length + 1)(0)

    var i = 0
    while (i < S.length) {
      var j = 0
      while (j < T.length) {
        if (S(i) == T(j))
          dp(i+1)(j+1) = (dp(i)(j) + 1) max dp(i)(j+1) max dp(i+1)(j)
        else
          dp(i+1)(j+1) = dp(i)(j+1) max dp(i+1)(j)
        j += 1
      }
      i += 1
    }
    println(dp(S.length)(T.length))
  }

}
