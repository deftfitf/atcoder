package jp.atcoder.typicaldp.e

@annotation.DP.DigitDP
object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val D = sc.nextInt(); sc.nextLine()
    val N = sc.nextLine()
    val L = N.length

    // dp0[i][j] := N未満であることが確定していないi桁目までの数の和sが
    // 　　　　　　　　s % D = jとなるものの個数
    // dp1[i][j] := N未満であることが確定しているi桁目までの数の和sが
    //              s % D = jとなるものの個数
    // と定義すると,
    // dp0[i+1][(j+d)%D] += dp0[i][j] みたいなので更新可能?
    // 最終的にdp0[L][0] + dp1[L][0]が答え?
    val dp = Array.fill(2, L+1, D)(0L)
    dp(0)(0)(0) = 1
    for {
      i <- 0 until L
      n =  N.charAt(i) - '0'
      j <- 0 until D
      f <- 0 until 2
      d <- 0 to (if (f == 1) 9 else n)
    } if (f == 1 || d < n) {
      dp(1)(i+1)((j+d)%D) = (dp(1)(i+1)((j+d)%D) + dp(f)(i)(j)) % 1000000007
    } else {
      dp(0)(i+1)((j+d)%D) = (dp(0)(i+1)((j+d)%D) + dp(f)(i)(j)) % 1000000007
    }

    println((dp(0)(L)(0)+dp(1)(L)(0)-1) % 1000000007)
  }

}
