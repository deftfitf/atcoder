package jp.atcoder.abc145.c.j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main implements Runnable {

    private static List<List<Integer>> permutations(int N) {
        List<List<Integer>> lst = new ArrayList<>();
        boolean[] used = new boolean[N];
        int[] acm = new int[N];
        for (int i = 0; i < N; i++) {
            acm[0] = i;
            used[i] = true;
            dfs(lst, N, 1, used, acm);
            used[i] = false;
        }
        return lst;
    }

    private static void dfs(List<List<Integer>> lst, int N, int idx, boolean[] used, int[] acm) {
        if (idx >= N) {
            lst.add(Arrays.stream(acm).boxed().collect(Collectors.toList()));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (used[i]) continue;

            acm[idx] = i;
            used[i] = true;
            dfs(lst, N, idx+1, used, acm);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 16 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] px = new int[N];
        int[] py = new int[N];
        IntStream.range(0, N).forEach(i -> {
            px[i] = sc.nextInt();
            py[i] = sc.nextInt();
        });

        List<List<Integer>> routes = permutations(N);
        List<Double> distances = routes.stream().map(route ->
                IntStream.range(1, N).asDoubleStream().reduce(0, (r, i) -> {
                    int x1 = px[route.get((int)i)];
                    int y1 = py[route.get((int)i)];
                    int x2 = px[route.get((int)i-1)];
                    int y2 = py[route.get((int)i-1)];
                    double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                    return r + distance;
                })
        ).collect(Collectors.toList());

        Double sum = distances.stream().reduce(0.0, Double::sum);

        System.out.println(sum / distances.size());
    }
}
