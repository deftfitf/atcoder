package jp.atcoder.abc153.d;

import java.util.Scanner;

public class Main {

    private static long solve(long H) {
        if (H <= 1L) {
            return 1L;
        } else {
            return 2L * solve((long)Math.floor(H / (double)2)) + 1L;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long H = sc.nextLong();
        // ツリーのノード数分だけ攻撃する必要がある.
        System.out.println(solve(H));
    }
}
