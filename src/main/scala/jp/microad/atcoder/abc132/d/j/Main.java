package jp.microad.atcoder.abc132.d.j;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(), K = sc.nextInt();
        BigInteger[][] nCr = new BigInteger[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                nCr[i][j] = BigInteger.ZERO;
            }
        }
        nCr[0][0] = BigInteger.valueOf(1);
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= Math.min(i, K); j++) {
                if (j - 1 >= 0)
                    nCr[i][j] = nCr[i-1][j-1].add(nCr[i-1][j]);
                else
                    nCr[i][j] = nCr[i-1][j];
            }
        }

        BigInteger MAX = BigInteger.valueOf((long)(Math.pow(10, 9) + 7));
        for (int i = 1; i <= K; i++) {
            System.out.println((nCr[N-K+1][i].multiply(nCr[K-1][i-1]).mod(MAX)));
        }
    }
}
