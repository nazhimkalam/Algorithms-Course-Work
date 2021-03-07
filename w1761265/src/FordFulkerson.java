import java.util.LinkedList;

public class FordFulkerson {
    public static int tot_Vertex;

    // This is the fordFulkerson Algorithm and we are applying it
    public static String fordFulkerson(int[][] data, int source, int target) {

        // Variables
        tot_Vertex = target + 1;
        int x_index, y_index;
        int GraphData[][] = new int[tot_Vertex][tot_Vertex];

        // We are creating a residual graph by filling the residual graph with the given capacities from the original graph.
        for (x_index = 0; x_index < tot_Vertex; x_index++) {
            for (y_index = 0; y_index < tot_Vertex; y_index++) {
                GraphData[x_index][y_index] = data[x_index][y_index];
            }
        }

        // The parent_arr is used to store the path and is filled by BFS
        int parent_arr[] = new int[tot_Vertex];

        // Initially we set the flow to 0
        int maximum_flow = 0;

        // Updating the residual values of edges by augmenting the flow while there is a path from source to sink.
        while (bfs(GraphData, source, target, parent_arr)) {

            // By using BFS we are finding the minimum residual capacity of the edges along the path which can be filled with.
            int path_flow = Integer.MAX_VALUE;

            for (y_index = target; y_index != source; y_index = parent_arr[y_index]) {
                x_index = parent_arr[y_index];
                path_flow = Math.min(path_flow, GraphData[x_index][y_index]);
            }

            for (y_index = target; y_index != source; y_index = parent_arr[y_index]) {
                x_index = parent_arr[y_index];
                GraphData[x_index][y_index] -= path_flow;
                GraphData[y_index][x_index] += path_flow;
            }

            // Adding the path flows to the overall flow
            maximum_flow += path_flow;
        }

        // Returning the overall flow, which is also the maximum flow of the graph
        return String.valueOf(maximum_flow);
    }

    // Using BFS as a searching algorithm
    public static boolean bfs(int Graph[][], int s, int t, int p[]) {
        boolean visited[] = new boolean[tot_Vertex];
        for (int i = 0; i < tot_Vertex; ++i)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        p[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < tot_Vertex; v++) {
                if (visited[v] == false && Graph[u][v] > 0) {
                    queue.add(v);
                    p[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t] == true);
    }
}
