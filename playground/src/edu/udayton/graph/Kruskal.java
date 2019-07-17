
import java.util.*;
import java.util.stream.Collectors;

public class Kruskal {
    public static void main(String[] args) {
        int numCities = 6;
        List<Integer> ra1 = new ArrayList<>();
        ra1.add(1);
        ra1.add(2);
        List<Integer> ra2 = new ArrayList<>();
        ra2.add(2);
        ra2.add(3);
        List<Integer> ra3 = new ArrayList<>();
        ra3.add(4);
        ra3.add(5);
        List<Integer> ra4 = new ArrayList<>();
        ra4.add(3);
        ra4.add(5);
        List<List<Integer>> roadsAvailable = new ArrayList<>();
        roadsAvailable.add(ra1);
        roadsAvailable.add(ra2);
        roadsAvailable.add(ra3);
        roadsAvailable.add(ra4);

        List<Integer> nr1 = new ArrayList<>();
        nr1.add(1);
        nr1.add(6);
        nr1.add(410);
        List<Integer> nr2 = new ArrayList<>();
        nr2.add(2);
        nr2.add(4);
        nr2.add(800);
        List<List<Integer>> newRoads = new ArrayList<>();
        newRoads.add(nr1);
        newRoads.add(nr2);
        System.out.println(getMinimumCostToContruct(6, 4, roadsAvailable, 2, newRoads));
    }

    public static int getMinimumCostToContruct(int numTotalAvailableCities, int numTotalAvailableRoads,
                                               List<List<Integer>> roadsAvailable, int numNewRoadsConstruct, List<List<Integer>> newRoads) {
        Set<Edge> edges = buildEdges(roadsAvailable, newRoads);
        return kruskalMST(numTotalAvailableCities, edges);
    }

    private static Set<Edge> buildEdges(List<List<Integer>> roadsAvailable, List<List<Integer>> costNewRoads) {
        Set<Edge> edges = new HashSet<>();
        for (List<Integer> road : roadsAvailable) {
            edges.add(new Edge(road.get(0), road.get(1), 0));
        }
        for (List<Integer> road : costNewRoads) {
            edges.add(new Edge(road.get(0), road.get(1), road.get(2)));
        }
        return edges;
    }

    private static int kruskalMST(int numCities, Set<Edge> edges) {
        Queue<Edge> pq = new PriorityQueue<>(edges);
        UF uf = new UF(numCities + 1);
        int mstSize = 0;
        int totalCost = 0;
        while (!pq.isEmpty() && mstSize < numCities - 1) {
            Edge edge = pq.poll();
            if (!uf.connected(edge.u, edge.v)) {
                uf.union(edge.u, edge.v);
                totalCost += edge.cost;
                mstSize++;
            }
        }
        return totalCost;
    }
}

class Edge implements Comparable<Edge> {
    int v;
    int u;
    int cost;

    public Edge(int v, int u, int cost) {
        this.v = v;
        this.u = u;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge that) {
        return Integer.compare(this.cost, that.cost);
    }
}

class UF {
    private int[] parent; // parent[i] = parent of i
    private byte[] rank; // rank[i] = rank of subtree rooted at i (never more than 31)

    public UF(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pr = find(p);
        int qr = find(q);
        if (pr == qr)
            return;
        if (rank[pr] < rank[qr]) {
            parent[pr] = qr;
        } else {
            parent[qr] = pr;
            if (rank[pr] == rank[qr])
                rank[pr]++;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}