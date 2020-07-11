package aoj.ALDS1.ALDS1_13_A

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val K = sc.nextInt()
    val P = Array.fill(K, 2)(sc.nextInt())

    // 8クイーン問題, 全てのクイーンが他の全てのクイーンと重ならないように配置する
    // 既にk個のクイーンが配置されている状態で行う
    // 1行につき1つしか置くことができないことが確定しており,
    // さらに1列につき1つしか置くことができないことが確定している
    // つまり, どの列をどのタイミングでとるか?というのを探せばいい
    // これは順列で表現でき, 8! = 40320 通り. 各試行ごとに, 置けるか置けないかを初期化する
    val used = new Array[Boolean](8)
    P.foreach { case Array(r, _) => used(r) = true }
    val arr = Array.fill(8, 8)(false)
    val Q = Array.fill(8, 8)(false)
    val vector = List((0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1))
    def fill(r: Int, c: Int): Unit = {
      Q(r)(c) = true
      arr(r)(c) = true
      for {
        (dx, dy) <- vector
      } {
        var y = r + dy
        var x = c + dx
        while (x >= 0 && x < 8 && y >= 0 && y < 8) {
          arr(y)(x) = true
          y += dy
          x += dx
        }
      }
    }

    def clear(): Unit = {
      for {
        r <- 0 until 8
        c <- 0 until 8
      } {
        arr(r)(c) = false
        Q(r)(c) = false
      }
      P.foreach { case Array(r, c) => fill(r, c) }
    }

    (0 until 8).permutations.find { permutation =>
      clear()
      permutation.zipWithIndex.foldLeft(true)(
        (able, i) =>
          if (!able || used(i._2)) able
          else if (!arr(i._2)(i._1)) {
            fill(i._2, i._1)
            true
          } else false)
    }
    println(Q.map(_.map(e => if (e) 'Q' else '.').mkString).mkString("\n"))
  }

}
