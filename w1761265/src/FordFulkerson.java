import java.util.LinkedList;

public class FordFulkerson {
    public static int tot_Vertex;

    // This is the fordFulkerson Algorithm and we are applying it
    public static String fordFulkerson(int[][] data, int source, int target) {

        // Variables
        tot_Vertex = target + 1;
        int x, y;
        int[][] resGraph = new int[tot_Vertex][tot_Vertex];

        // We are creating a residual graph by filling the residual graph with the given capacities from the original graph.
        // In the Residual graph resGraph[x][y] indicates the residual capacity of edge from x to y.
        // If rGraph[i][j] is 0, then there is No edge Else if its not 0 then there IS AN Edge
        for (x = 0; x < tot_Vertex; x++) {
            for (y = 0; y < tot_Vertex; y++) {
                resGraph[x][y] = data[x][y];
            }
        }
        System.out.println("Creating the residual graph...");

        // The parent_arr is used to store the found path and is filled by BFS
        int[] parent_arr = new int[tot_Vertex];

        // Initially we set the flow to 0
        int maximum_flow = 0;

        // Updating the residual values of edges by augmenting the flow while there is a path from source to sink.
        while (bfs(resGraph, source, target, parent_arr)) {
            System.out.println("--->");

            // By using BFS we are finding the (minimum residual capacity) of the edges along the path which can be
            // filled with.
            System.out.println("Finding the minimum residual capacity...");
            int path_flow = Integer.MAX_VALUE;
            for (y = target; y != source; y = parent_arr[y]) {
                x = parent_arr[y];
                path_flow = Math.min(path_flow, resGraph[x][y]);
            }

            System.out.println("Updating the residual capacities of the edges...");
            // Updating the residual capacities of the edges.
            // Also reversing the edges along the path.
            for (y = target; y != source; y = parent_arr[y]) {
                x = parent_arr[y];
                resGraph[x][y] -= path_flow;
                resGraph[y][x] += path_flow;
            }

            System.out.println("Path flow value found equal to " + path_flow);

            // Adding the path flows to the overall flow
            maximum_flow += path_flow;
            System.out.println("Adding the path flows to the overall flow...");

        }
        System.out.println("--->");

        // Returning the overall flow, which is also the maximum flow of the graph
        return String.valueOf(maximum_flow);
    }

    // Using BFS as the searching algorithm (Breadth-first search)
    // This function returns "true" if there is a path from source 's' to sink 't' in the residual graph.
    // It also fills parent[] array to store the path
    public static boolean bfs(int[][] Graph, int source, int target, int[] parent) {

        System.out.println("Performing BFS (Breadth-first search)...");
        // Creating and Initializing the visited array with 'false' indicated all vertices aren't visited initially.
        boolean[] visited = new boolean[tot_Vertex];
        for (int index = 0; index < tot_Vertex; ++index) {
            visited[index] = false;
        }

        // Creating a queue and we enqueue the source vertex and mark source vertex as visited.
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        // This is the normal standard BFS (Breadth-first search) Loop
        while (queue.size() != 0) {
            int x_index = queue.poll();

            // Once a connection to the target/sink vertex is found we stop the BFS process and
            // set to its parent
            for (int y_index = 0; y_index < tot_Vertex; y_index++) {
                if (!visited[y_index] && Graph[x_index][y_index] > 0) {
                    queue.add(y_index);
                    parent[y_index] = x_index;
                    visited[y_index] = true;
                }
            }
        }

        // The result either (true meaning that connection is found and false meaning no connection found)
        // will be returned
        return visited[target];
    }
}
