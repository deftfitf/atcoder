package jp.microad.atcoder.educationdp.f

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine()
    val T = sc.nextLine()

    val dp = Array.fill(S.length+1, T.length+1)(0)
    val dpi = Array.fill(S.length+1, T.length+1)(-1)
    val dpj = Array.fill(S.length+1, T.length+1)(-1)

    var i = 0
    while (i < S.length) {
      var j = 0
      while (j < T.length) {
        if (S(i) == T(j)) {
          if ((dp(i)(j) + 1) > dp(i + 1)(j + 1)) {
            dp(i + 1)(j + 1) = dp(i)(j) + 1
            dpi(i + 1)(j + 1) = i
            dpj(i + 1)(j + 1) = j
          }
        }
        if (dp(i + 1)(j) > dp(i + 1)(j + 1)) {
          dp(i + 1)(j + 1) = dp(i + 1)(j)
          dpi(i + 1)(j + 1) = dpi(i + 1)(j)
          dpj(i + 1)(j + 1) = dpj(i + 1)(j)
        }
        if (dp(i)(j + 1) > dp(i + 1)(j + 1)) {
          dp(i + 1)(j + 1) = dp(i)(j + 1)
          dpi(i + 1)(j + 1) = dpi(i)(j + 1)
          dpj(i + 1)(j + 1) = dpj(i)(j + 1)
        }
        j += 1
      }
      i += 1
    }

    i = dpi(S.length)(T.length)
    var j = dpj(S.length)(T.length)
    var bldr = new StringBuilder
    while (i >= 0 || j >= 0) {
      if (S(i) == T(j))
        bldr.append(S(i))
      val _i = dpi(i)(j)
      val _j = dpj(i)(j)
      i = _i
      j = _j
    }
    println(bldr.result().reverse)
  }

}
