package jp.microad.atcoder.abc145.b;

import java.util.Arrays;
import java.util.Scanner;

public class B {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] d = new int[N];
        Arrays.setAll(d, (e) -> sc.nextInt());

        long sum = 0;
        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                sum += d[i] * d[j];
            }
        }
        System.out.println(sum);
    }

}
