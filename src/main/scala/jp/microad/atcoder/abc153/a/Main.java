package jp.microad.atcoder.abc153.a;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new java.util.Scanner(System.in);

        int H = sc.nextInt();
        int A = sc.nextInt();
        int ans = (int)Math.ceil(H / (double)A);

        System.out.println(ans);
    }

}
