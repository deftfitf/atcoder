package lib.math

/**
  * 整数系の静的関数ライブラリ
  */
object IntLib {

  /**
    * 最大公約数
    */
  def gcd(a: Int, b: Int): Int =
    if (a % b == 0) b
    else gcd(b, a % b)

  /**
    * 最小公倍数
    */
  def lcm(a: Int, b: Int): Int =
    a * b / gcd(a, b)

  def combinations(n: Int, r: Int): Int = {
    val nCr = Array.fill(n+1, r+1)(0)
    (0 to r).foreach(i => nCr(i)(i) = 1)
    (0 to n).foreach(i => {nCr(i)(0) = 1; nCr(i)(1) = i})
    def recursive(n: Int, r: Int): Int = {
      if (n >= 0 && r >= 0) {
        if (nCr(n)(r) > 0) nCr(n)(r)
        else {
          nCr(n)(r) = recursive(n-1,r-1) + combinations(n-1,r)
          nCr(n)(r)
        }
      } else 0
    }
    recursive(n, r)
  }

}
