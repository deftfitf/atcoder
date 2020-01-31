package jp.atcoder.abc073.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E, R = sc.nextInt()
    val r = Array.fill(R)(sc.nextInt()).map(_ - 1)

    val INF = Int.MaxValue
    val G = Array.fill(V, V)(INF)
    var D = Array.fill(V, V)(INF)
    (0 until E) foreach { _ =>
      val u, v, w = sc.nextInt()
      G(u-1)(v-1) = w
      G(v-1)(u-1) = w
      D(u-1)(v-1) = w
      D(v-1)(u-1) = w
    }
    (0 until V) foreach { i =>
      D(i)(i) = 0
    }

    var k = 0
    while (k < V) {
      var u = 0
      val Dn = Array.fill(V, V)(INF)
      while (u < V) {
        var v = 0
        while (v < V) {
          if (D(u)(k) != INF && D(k)(v) != INF)
            Dn(u)(v) = D(u)(v) min (D(u)(k) + D(k)(v))
          else
            Dn(u)(v) = D(u)(v)
          v += 1
        }
        u += 1
      }
      D = Dn
      k += 1
    }

    val result = r.permutations.map { permutation =>
      permutation.sliding(2).foldLeft(0)((b, n) => {
        b + D(n(0))(n(1))
      })
    }. min

    println(result)
  }

}
