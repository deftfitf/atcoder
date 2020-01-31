package jp.atcoder.abc136.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val H = Array.fill(N)(sc.nextInt())

    def solve(): Boolean = {
      var i = 0
      while (i < N - 1) {
        if (H(i+1) < H(i))
          if (H(i+1) + 1 >= H(i)) {
            H(i+1) += 1
          } else {
            return false
          }
        i += 1
      }
      true
    }

    println(if (solve()) "Yes" else "No")
  }

}