package lib.math

object LongLib {

  /**
    * 最大公約数
    */
  def gcd(a: Long, b: Long): Long =
    if (a % b == 0) b
    else gcd(b, a % b)

  /**
    * 最小公倍数
    */
  def lcm(a: Long, b: Long): Long =
    a * b / gcd(a, b)

}
