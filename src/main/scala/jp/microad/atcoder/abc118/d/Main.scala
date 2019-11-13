package jp.microad.atcoder.abc118.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val A = Array.fill(M)(sc.nextInt())
    val MatchOf = Array(0, 2, 5, 5, 4, 5, 6, 3, 7, 6)

    // i本のマッチ棒を使って作れる整数の中で最大のもの
    // 整数の中で最大のものは, 大きい数順に並べることになる
    // 明らかに, 既に付いている先頭の文字列よりも小さいものが来た場合
    // 最大になることはないので捨てる
    val dp = Array.fill(N+1)("")

    var i = 0
    while (i < M) {
      val matches = MatchOf(A(i))
      if (matches <= N) {
        dp(matches) = "" + A(i)
      }
      i += 1
    }

    def insert(str: String, value: Int): String = {
      var idx = str.indexOf(value)
      if (idx != -1) {
        str.substring(0, idx) + value + str.substring(idx)
      } else if (value >= str.head.toString.toInt) value + str
      else str + value
    }

    i = 1
    while (i <= N) {
      if (dp(i).nonEmpty) {
        var j = 0
        while (j < M) {
          val matches = MatchOf(A(j))
          if (i + matches <= N) {
            val newValue = insert(dp(i), A(j))
            if (dp(i + matches).nonEmpty) {
              if (newValue.length == dp(i + matches).length) {
                if (newValue > dp(i + matches))
                  dp(i + matches) = newValue
              } else if (newValue.length > dp(i + matches).length) {
                dp(i + matches) = newValue
              }
            } else {
              dp(i + matches) = newValue
            }
          }
          j += 1
        }
      }
      i += 1
    }

    println(dp(N))
  }

}
