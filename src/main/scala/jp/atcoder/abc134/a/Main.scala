package jp.atcoder.abc134.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    val res = {A.sorted.sliding(3)}.forall {
      _.reduce(_ ^ _) == 0
    }
    val res2 = (A(N-1) ^ A(0) ^ A(1)) == 0
    res && res2 match {
      case true => "Yes"
      case false => "No"
    }
    println(res)
  }

}
