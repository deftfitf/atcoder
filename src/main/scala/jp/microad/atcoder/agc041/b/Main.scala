package jp.microad.atcoder.agc041.b

object Main {

  // TODO: ....
  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M, V, P = sc.nextInt()
    val A = Array.fill(N)(sc.nextLong()).sorted

    // 最初のスコアからM人のジャッジがV問のスコアを1ずつあげる
    // その際に問題が大きい順に並べられP問が選ばれる
    // 同スコアの場合は任意に決定されるということは, P+X問実際には選択されうるということ
    // 可能性判定すれば良い
    // 問題によって後から加算されるスコアが変動したりしない
    // まず問題の初期スコアで昇順ソートしておいて
    // c(Ai) := 初期点Aiを持つ問題が問題セットに採用される可能性を持つか？
    // という関数で二分探索すれば良さそう
    // では、そのような判定をどのように行うか？というところである
    // 無難に考えると, 対象の点数が最大まで加算された場合に採用できるか？
    // という制約をさらに加える (= Ai + M, 全員のジャッジがその問題を選んだケース)
    // 自分の点は最大に加算し, それ以外の V - 1問 について小さい順に M点加算させた場合
    // 自分の点がP問内に入るか？という判定である,
    // 実際に全てに加算させる必要はなく
    // 小さい順に並べた時の P min (V - 1) 番目の点数 以上の場合, 採用できる！
    // ↑ 怪しい
    // 既に選択した問題が 最大上昇の M 点加算されるという想定だと,
    // 元々のスコアが Ai より以下のものに対してはどうやっても勝てる（同スコアなら選ばれるので）
    // (N - |Ai以下の数|) >= V の時, Ai以上の数も選んでいく必要がある
    // (V - (N - |Ai以下の数|)) 問だけ, M人のジャッジが選ぶことになる
    // ((Aij + M) < Aij') のような Aij'が存在する場合, そもそも勝てないので, それらの問題は
    // 毎回選択することにする.
    // (実際には (V - (N - |Ai以下の数|)) - (|(Aij + M) < Aij'となるAij'|))問だけ, M人のジャッジが選ぶことになる
    // 各Aij'問題で, どれだけ上げられるか?の許容量は
    // ((Aij + M) - Aij') >= 0の場合, (((Aij + M) - Aij') min M)回だけ選択されても良い
    // もし, この許容量内で収まる場合には問題なく選べる
    // 一回で減らせるのは(((Aij + M) - Aij') min M)までだが,
    // これを ((V - (N - |Ai以下の数|)) * M) 点減らし切れればOK
    // もし減らしきらないうちに ((Aij + M) < Aij') のような Aij'が出てきてしまうと
    // ceil(残りの点 / M)の選択し分だけ勝てないものが出てくるので, それが > P の時NGです
    def solve(N: Int, M: Int, V: Int, P: Int, A: Array[Long]): Int = {
      def check(i: Int): Boolean = {
        val aLowerN = lowerBounds(A(i)) // A(i)以下の問題数
        val aMLowerN = lowerBounds(A(i) + M)
        val fixedProblems = N - aMLowerN // 常に勝てない問題数
        if (fixedProblems >= P) return false // 常に勝てない問題数がP以上ならNG
        val OK = (aLowerN.toLong * M.toLong) +
          ((aLowerN until (aMLowerN min (N - P))).map(j => (A(i) + M) - A(j)).sum) +
          ((P - 1) * M.toLong)
        OK >= (M * V)
      }
      // lower 以下の A の要素数を返す
      def lowerBounds(lower: Long): Int = {
        def loop(left: Int, right: Int): Int = {
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (A(mid) > lower) loop(left, mid)
            else loop(mid, right)
          } else right
        }
        loop(-1, N)
      }
      def lowerBounds2(): Int = {
        def loop(left: Int, right: Int): Int = {
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (check(mid)) loop(left, mid)
            else loop(mid, right)
          } else right
        }
        loop(-1, N)
      }
      N - lowerBounds2()
    }

    println(solve(N, M, V, P, A))
  }

}
