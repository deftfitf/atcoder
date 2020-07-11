package jp.atcoder.abc150.c

object Main2 {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val P = Array.fill(N)(sc.nextInt()).toIndexedSeq
    val Q = Array.fill(N)(sc.nextInt()).toIndexedSeq

    // 辞書順にpermutationを出して, PとQが出現したタイミングを記録しておいて, その差分を求める
    val r = ((1 to N).permutations.indexWhere(_ == P) - (1 to N).permutations.indexWhere(_ == Q)).abs
    println(r)
  }

}
