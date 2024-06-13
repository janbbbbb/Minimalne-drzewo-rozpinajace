import java.util.*;

class Graph {
    private int vertices;  // liczba wierzchołków
    private LinkedList<Edge> edges;  // lista krawędzi

    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    class Subset {
        int parent, rank;
    }

    Graph(int vertices) {
        this.vertices = vertices;
        edges = new LinkedList<>();
    }

    void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    // Metoda Kruskala
    void kruskalMST() {
        Edge[] result = new Edge[vertices];
        int e = 0;
        int i = 0;

        Collections.sort(edges);

        Subset[] subsets = new Subset[vertices];
        for (int v = 0; v < vertices; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while (e < vertices - 1) {
            Edge nextEdge = edges.get(i++);
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        System.out.println("Minimalne Drzewo Rozpinające metoda Kruskala:");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);

        if (subsets[rootX].rank < subsets[rootY].rank)
            subsets[rootX].parent = rootY;
        else if (subsets[rootX].rank > subsets[rootY].rank)
            subsets[rootY].parent = rootX;
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Metoda Prima
    void primMST() {
        boolean[] mstSet = new boolean[vertices];
        Edge[] key = new Edge[vertices];
        int[] parent = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            key[i] = new Edge(-1, -1, Integer.MAX_VALUE);
            parent[i] = -1;
        }

        key[0].weight = 0;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (Edge edge : edges) {
                if (edge.src == u || edge.dest == u) {
                    int v = (edge.src == u) ? edge.dest : edge.src;
                    if (!mstSet[v] && edge.weight < key[v].weight) {
                        key[v].weight = edge.weight;
                        parent[v] = u;
                    }
                }
            }
        }

        System.out.println("Minimalne Drzewo Rozpinające metoda Prima:");
        for (int i = 1; i < vertices; i++)
            System.out.println(parent[i] + " -- " + i + " == " + key[i].weight);
    }

    int minKey(Edge[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < vertices; v++)
            if (!mstSet[v] && key[v].weight < min) {
                min = key[v].weight;
                minIndex = v;
            }
        return minIndex;
    }
}
