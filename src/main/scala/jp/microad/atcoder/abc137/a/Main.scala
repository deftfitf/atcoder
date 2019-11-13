package jp.microad.atcoder.abc137.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B = sc.nextInt()
    println(((A + B) :: (A - B) :: (A * B) :: Nil).max)
  }

}
