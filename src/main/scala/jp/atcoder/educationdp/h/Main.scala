package jp.atcoder.educationdp.h

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W = sc.nextInt(); sc.nextLine()
    val A = Array.fill(H)(sc.nextLine().toCharArray)

    val dp = Array.fill(H, W)(0)

    var i = 0
    while (i < H && A(i)(0) == '.') {
      dp(i)(0) = 1
      i += 1
    }
    i = 0
    while (i < W && A(0)(i) == '.') {
      dp(0)(i) = 1
      i += 1
    }

    val N = 1000000000 + 7

    i = 1
    while (i < H) {
      var j = 1
      while (j < W) {
        if (A(i)(j) == '.')
          dp(i)(j) = (dp(i-1)(j) + dp(i)(j-1)) % N
        j += 1
      }
      i += 1
    }

    println(dp(H-1)(W-1))
  }

}
