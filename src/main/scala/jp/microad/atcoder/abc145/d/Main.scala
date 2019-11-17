package jp.microad.atcoder.abc145.d

//object Main {
//
////  def main(args: Array[String]): Unit = {
////    new Thread(null, Main, "", 16 * 1024 * 1024).start()
////  }
////
////  override def run(): Unit = {
////    val upper = Math.pow(10, 9).toInt + 7
////    val sc = new java.util.Scanner(System.in)
////
////    val X, Y = sc.nextInt()
////
////    def solve(X: Int, Y: Int): Int = {
////      import scala.collection.mutable
////      val visit = mutable.Set[(Int, Int)]()
////      def dfs(x: Int, y: Int): Int = {
////        if (x == 0 && y == 0) 1
////        else {
////          visit.add((x, y))
////          (((if (x > 1 && y > 0 && !visit.contains(x - 2, y - 1)) dfs(x - 2, y - 1) else 0).toLong +
////            (if (x > 0 && y > 1 && !visit.contains(x - 1, y - 2)) dfs(x - 1, y - 2) else 0).toLong) % upper).toInt
////        }
////      }
////      dfs(X, Y)
////    }
////
////    println(solve(X, Y))
////  }
//
////  def main(args: Array[String]): Unit = {
////    val upper = Math.pow(10, 9).toInt + 7
////    val interval = 3
////    val sc = new java.util.Scanner(System.in)
////
////    val X, Y = sc.nextInt()
////    val dp = Array.fill(interval, X+1)(0)
////    dp(0)(0) = 1
////
////    var _y = 0
////    while (_y < Y) {
////      val y = _y % interval
////      var x = 0
////      while (x < X) {
////        if (x < X - 1) dp((y + 1) % interval)(x + 2) += dp(y)(x) % upper
////        if (_y < Y - 1) dp((y + 2) % interval)(x + 1) += dp(y)(x) % upper
////        x += 1
////      }
////      _y += 1
////    }
////
////    println(dp(Y % interval)(X))
////  }
//
////  def combinations(n: Int, r: Int): Int = {
////    val upper = Math.pow(10, 9).toInt + 7
////    val nCr = Array.fill(n+1, r+1)(0)
////    (0 to r).foreach(i => nCr(i)(i) = 1)
////    (0 to n).foreach(i => {nCr(i)(0) = 1; nCr(i)(1) = i})
////    def recursive(n: Int, r: Int): Int = {
////      if (n >= 0 && r >= 0) {
////        if (nCr(n)(r) > 0) nCr(n)(r)
////        else {
////          nCr(n)(r) = ((recursive(n-1,r-1).toLong + combinations(n-1,r).toLong) % upper).toInt
////          nCr(n)(r)
////        }
////      } else 0
////    }
////    recursive(n, r)
////  }
//
//  def combinations(n: Int, r: Int): Int = {
//    val upper = Math.pow(10, 9).toInt + 7
//    var factorial = BigInt(1)
//    var inverse = BigInt(1)
//
//    var i = n - r + 1
//    while (i <= n) {
//      factorial *= i
//      i += 1
//    }
//    i = 1
//    while (i <= r) {
//      inverse *= i
//      i += 1
//    }
//    (factorial / inverse % upper).toInt
//  }
//
//  def main(args: Array[String]): Unit = {
//    val sc = new java.util.Scanner(System.in)
//
//    val X, Y = sc.nextInt()
//    val n = (X + Y) / 3
//    val r = (2 * Math.min(X, Y) - Math.max(X, Y)) / 3
//    val result = combinations(n, r)
//
//    println(result)
//  }
//
//}
