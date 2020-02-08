// 辺を表す構造体, (行き先G[from][i]で手に入る,容量,逆辺 G[to][rev])
struct edge { int to, cap, rev; }

vector<edge> G[MAX_V]; // グラフの隣接リスト表現(残余ネットワーク)
bool used[MAX_V]; // DFSですでに調べたかのグラフ

// fromからtoへ向かう容量cap辺をグラフに追加する
void add_edge(int from, int to, int cap) {
    G[from].push_back((edge){to, cap, G[to].size()});
    G[to].push_back((edge){from, 0, G[from].size() - 1});
}

// 増加パスをDFSで探す, DFSなので, 毎回列挙順は同じになり,
// 無限ループに陥る心配はない
int dfs(int v, int t, int f) {
    if (v == t) return f;
    used[v] = true;
    for (int i = 0; i < G[v].size(); i++) {
        edge &e = G[v][i];
        if (!used[e.to] && e.cap > 0) {
            int d = dfs(e.to, t, min(f, e.cap));
            if (d > 0) {
                e.cap -= d;
                G[e.to][e.rev].cap += d;
                return d;
            }
        }
    }
    return 0;
}

int max_flow(int s, int t) {
    int flow = 0;
    for (;;) {
        memset(used, 0, sizeof(used));
        int f = dfs(s, t, INF);
        if (f == 0) return flow;
        flow += f;
    }
}