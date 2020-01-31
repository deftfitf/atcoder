package jp.atcoder.abc051.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val sx, sy, tx, ty = sc.nextInt()

    // (sx, sy) -> (sx, ty) -> (tx, ty) ->
    // (tx, sy) -> (sx, sy) -> (sx - 1, sy) ->
    // (sx - 1, ty + 1) -> (tx, ty + 1) -> (tx, ty) ->
    // (tx + 1, ty) -> (tx + 1, sy + 1) -> (sx, sy + 1) ->
    // (sx, sy)
    val r =
    ((sy until ty).map(_ => 'U') ++
    (sx until tx).map(_ => 'R') ++
    (sy until ty).map(_ => 'D') ++
    (sx to    tx).map(_ => 'L') ++
    (sy to    ty).map(_ => 'U') ++
    (sx to    tx).map(_ => 'R') ++ Seq('D', 'R') ++
    (sy to    ty).map(_ => 'D') ++
    (sx to    tx).map(_ => 'L') ++ Seq('U')).mkString("")

    println(r)
  }

}
