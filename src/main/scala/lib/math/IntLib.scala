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

}
