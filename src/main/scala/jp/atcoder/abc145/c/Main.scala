package jp.atcoder.abc145.c

object Main extends Runnable {

  def main(args: Array[String]): Unit = {
    new Thread(null, Main, "", 16 * 1024 * 1024).start()
  }

  override def run(): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val P = new Array[(Int, Int)](N)
    (0 until N) foreach { i =>
      val x, y = sc.nextInt()
      P(i) = (x, y)
    }

    val routes = P.permutations.map { routes =>
      routes.sliding(2).map { route =>
        Math.sqrt(Math.pow(route(0)._1 - route(1)._1, 2) +
          Math.pow(route(0)._2 - route(1)._2, 2))
      }. sum
    }. toList
    println(routes.sum / routes.length)
  }

}
