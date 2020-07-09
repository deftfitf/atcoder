package jp.atcoder.square869120.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N, 2)(sc.nextLong())

    // 全ての買い物客の移動時間の合計の最小値
    // こういうケースでは、
    // 入り口をS, 出口をEとすると,
    // ある客の二つのマスがAi, Biなら, その客の最小の移動時間は,
    // 必ずAiとBiは訪れる必要があるので, abs(Ai - Bi)は確実に入る
    //
    // しかし, 全てのS, Eについて調べると, 間に合わない. Sだけでも全て調べると間に合わない
    // Nが小さいのでNについて調べる
    // 基本, それぞれの目的のマスを開始地点もしくは終了地点にした方がいい
    // なので, 全ての開始地点, 終了地点の候補として, Ai, Biを調べる
    // S を N^2 通り, Eを N^2 通り 調べる
    val min = (for {
      s <- A.flatten
      e <- A.flatten
      sum = (for {
        Array(a, b) <- A
      } yield (a - b).abs + (((a - s).abs + (b - e).abs) min ((b - s).abs + (a - e).abs))).sum
    } yield sum).min

    println(min)
  }

}
