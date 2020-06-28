package jp.atcoder.petrozavodsk001.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    sc.nextLine()
    // 席は空席、男性が座っている、女性が座っているのどちらか
    // ただし、同性同士が隣り合う席に座っていることはありません
    // Nが3以上の奇数の場合、空席は少なくとも1つは存在することが示せる
    // Nのみが与えられ、各席の状態は与えられない。
    // どれか1つの空席の番号を当てることです。
    // そのために、あなたは次のクエリを繰り返し送ることができます。
    // 整数iを選ぶ、席iが空席なら正答となる。そうでなければ席iに座っている人の性別が知らされる
    // クエリを高々20回まで送ることで、どれか１つの空席の番号を当ててください

    // とりあえず1回クエリした時
    // 男が返ってくる => 隣は女である必要がある
    // 女が返ってくる => 隣は男である必要がある
    // ある男の席を起点とすると、その並びは
    // 男,女,男,女,...,男,女,空席,女 のパターン
    // 男,女,男,女,...,女,男,空席,男 のパターン
    // がある.
    // 椅子の席が奇数であることから、一度クエリした時に反対側の2つをクエリすることで
    // ズレの傾向を読むことができるかも？
    // 一度適当な箇所をクエリした時に
    // iをクエリする => 男が返ってくる =>
    // i + (N / 2) をクエリする =>
    // i から (N / 2) をクエリする、ということは、偶数番移動なので、
    // そこに歪み(空席)がなければ、男,女,の順なので、男が返ってくるはず
    // 男が返ってくれば, i ~ i + (N / 2) の間には空席はないものとみなす
    // そして, (i + (N / 2)) + 1 ~ (i - 1) の新しい領域について考える必要がある
    // しかしこのケース, 空席が2つ以上中に含まれている場合には回答できない?
    // 空席が存在する場合に必ず歪みが存在すると仮定しているので
    // 二分探索の場合、最大100000席探索するのに13回あれば十分なので、
    // 残り7回の探索について考える必要がある？
    // (right - left / 2)は常に偶数
    // leftが常に偶数の時、oppositeは常に偶数、opposite - 1は常に奇数
    // r - l が偶数の場合 r, lが異性なら空席が含まれる
    // r - l が奇数の場合, r, lが同性なら空席が含まれる
    def solve(): Unit = {
      def query(n: Int): String = {
        println(n) // query start
        val result = sc.nextLine() // Vacant, Male, Female
        result match {
          case "Vacant" => sys.exit()
          case sex => sex
        }
      }
      val leftSex = query(0)
      @scala.annotation.tailrec
      def recursive(left: Int, right: Int): Unit =
        if (right - left > 1) {
          val center = (right + left) / 2
          val centerSex = query(center)
          if ((center % 2 == 0 && centerSex == leftSex) || (center % 2 == 1 && centerSex != leftSex)) {
            recursive(center, right)
          } else {
            recursive(left, center)
          }
        } else query(left)
      recursive(0, N)
    }
    solve()
  }

}
