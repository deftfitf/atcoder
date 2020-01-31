package jp.atcoder.abc143.a;

import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(Math.max(A - 2 * B, 0));
    }
}
