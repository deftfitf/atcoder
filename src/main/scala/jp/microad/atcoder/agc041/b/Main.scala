package jp.microad.atcoder.agc041.b

object Main {

  // TODO: ....
  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val M, V, P = sc.nextLong()
    val A = Array.fill(N)(sc.nextLong()).sorted(Ordering.Long.reverse)

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
    def solve(N: Int, M: Long, V: Long, P: Long, A: Array[Long]): Int = {
      def check(i: Int): Boolean = {
        if (i + 1 <= P) return true
        if (A(i) + M < A(P.toInt - 1)) return false
        def loop(j: Int, acm: Long): Long =
          if (j < i) loop(j + 1, acm + (((A(i) + M - A(j)) min M) max 0))
          else acm + (N - i - 1) * M
        loop(P.toInt - 1, P * M) >= (M * V)
      }

      def lowerBounds2(): Int = {
        def loop(left: Int, right: Int): Int = {
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (check(mid)) loop(mid, right)
            else loop(left, mid)
          } else left
        }
        loop(-1, N)
      }
      lowerBounds2() + 1
    }

    println(solve(N, M, V, P, A))
  }

}
