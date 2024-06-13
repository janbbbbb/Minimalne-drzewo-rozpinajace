public class Main {
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 0, 4);
        graph.addEdge(2, 0, 4);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 5, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 2, 3);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 2, 4);
        graph.addEdge(4, 3, 3);
        graph.addEdge(5, 2, 2);
        graph.addEdge(5, 4, 3);

        graph.kruskalMST();
        graph.primMST();
    }
}