package jp.atcoder.abc144.c;

import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner sc = new java.util.Scanner(System.in);

        long N = sc.nextLong();
        double upper = Math.sqrt(N);
        long min = N;
        for (int i = 1; i <= upper; i++) {
            if (N % i == 0) {
                long j = N / i;
                min = Math.min(min, i + j - 2);
            }
        }
        System.out.println(min);
    }

}