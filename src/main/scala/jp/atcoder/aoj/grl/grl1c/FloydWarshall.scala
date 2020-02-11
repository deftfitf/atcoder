package jp.atcoder.aoj.grl.grl1c

object FloydWarshall {

  val INF = Int.MaxValue

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E = sc.nextInt()
    val D = Array.fill(V, V)(INF)
    for (v <- 0 until V) D(v)(v) = 0
    for (_ <- 0 until E) {
      val s, t, d = sc.nextInt()
      D(s)(t) = d
    }

    val DD = floydWarshall(V, D)
    val r = DD
      .map(_.map(d =>
        if (d == INF) "INF" else d.toString).mkString(" "))
      .mkString("\n")

    val result = if ((0 until V).forall(v => DD(v)(v) == 0)) r
    else "NEGATIVE CYCLE"
    println(result)
  }

  @annotation.Graph.WarshallFloyd
  def floydWarshall(V: Int, D: Array[Array[Int]]): Array[Array[Int]] = {
    for {
      k <- 0 until V
      u <- 0 until V
      v <- 0 until V
    } if (D(u)(k) != INF && D(k)(v) != INF) {
      D(u)(v) = D(u)(v) min (D(u)(k) + D(k)(v))
    }
    D
  }

}
