package jp.microad.atcoder.abc121.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W, h, w = sc.nextInt()

    println((H * W) - ((h * W) + (w * H) - (h * w)))
  }

}
