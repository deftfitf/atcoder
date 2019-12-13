package ant.poj3276.jv;

import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    public static class Tuple2<A, B> {
        private final A a;
        private final B b;

        public Tuple2(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getA() {
            return a;
        }

        public B getB() {
            return b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new java.util.Scanner(System.in);

        int N = sc.nextInt();
        char[] S = new char[N];
        for (int i = 0; i < N; i++) {
            S[i] = sc.nextLine().charAt(0);
        }

        Supplier<Tuple2<Integer, Integer>> solve = () -> {
            int[] F = new int[N+1];
            int K = 0;
            int M = N+1;
            int inverted = 0;
            for (int k = 0; k <= N; k++) {
                int m = 0;
                for (int i = 1; i <= N; i++) {
                    if (inverted % 2 == 0) {
                        if (S[i-1] == 'B') {
                            F[i] = 1;
                        } else {
                            F[i] = 0;
                        }
                    } else {
                        if (S[i-1] == 'B') {
                            F[i] = 0;
                        } else {
                            F[i] = 1;
                        }
                    }
                    inverted += F[i];
                    m += F[i];
                    if (i - k + 1 >= 0) {
                        inverted -= F[i - k + 1];
                    }
                }
                if (m < M) {
                    M = m;
                    K = k;
                }
            }
            return new Tuple2<>(K, M);
        };

        Tuple2<Integer, Integer> result = solve.get();

        System.out.printf("%d %d", result.getA(), result.getB());
    }

}
