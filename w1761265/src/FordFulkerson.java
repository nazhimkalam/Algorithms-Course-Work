import java.util.LinkedList;

// Ford Fulkerson Algorithm used to find the maximum flow of a Graph
public class FordFulkerson {
    public static int tot_Vertex;

    // Returns tne maximum flow from s to t in the given graph
    public static String fordFulkerson(int[][] data, int source, int target) {

        // Variables
        tot_Vertex = target + 1;    //Total Number of vertices the flow network
        int u;                      // starting node
        int v;                      // ending node
        int[][] resGraph = new int[tot_Vertex][tot_Vertex];   // initializing residual graph

        // We are creating a residual graph by filling the residual graph with the given capacities from the
        // original graph.
        // In the Residual graph resGraph[x][y] indicates the residual capacity of edge from x to y.
        // If rGraph[i][j] is 0, then there is No edge Else if its not 0 then there IS AN Edge
        for (u = 0; u < tot_Vertex; u++) {
            for (v = 0; v < tot_Vertex; v++) {
                resGraph[u][v] = data[u][v];
            }
        }
        System.out.println("Creating the residual graph...");

        // The parent_arr is used to store the found path and is filled by BFS
        int[] parent_arr = new int[tot_Vertex];
        // initialized with tot_Vertex because the max path can have all the number of vertices in the graph
        // (same node can't be visited more than once)

        // Initially we set the flow to 0, because there is no flow initially
        int maximum_flow = 0;

        // Updating the residual values of edges by augmenting the flow while there is a path from source to sink.
        while (bfs(resGraph, source, target, parent_arr)) {
            System.out.println("--->");

            // By using BFS we are finding the (minimum residual capacity) of the edges along the path which can be
            // filled with.
            System.out.println("Finding the minimum residual capacity...");
            int path_flow = Integer.MAX_VALUE;
            // assigning the maximum value an integer possible - because minimum residual capacity is needed to be found

            for (v = target; v != source; v = parent_arr[v]) {
                u = parent_arr[v];

                // getting the minimum value out of path_flow and rGraph[u][v] (capacity of edge in residual graph)
                path_flow = Math.min(path_flow, resGraph[u][v]);
            }

            System.out.println("Updating the residual capacities of the edges...");

            // Updating the residual capacities of the edges and also reversing the edges along the path.
            for (v = target; v != source; v = parent_arr[v]) {
                u = parent_arr[v];

                // decreasing capacity from the flow sent; to get the residual capacity (forward direction)
                resGraph[u][v] -= path_flow;

                // adding the flow sent to the reverse capacity; to get the residual capacity (backward direction)
                resGraph[v][u] += path_flow;
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
        visited[source] = true;  // source is always visited at the beginning
        parent[source] = -1;    // -1 is never given to a node, it's used to identify that there's no node before the source node

        // This is the normal standard BFS (Breadth-first search) Loop
        while (queue.size() != 0) {
            int x_index = queue.poll();      // returns the first element of queue and removes it from the queue
            // returns null if queue is empty.

            // Once a connection to the target/sink vertex is found we stop the BFS process and
            // set to its parent
            for (int y_index = 0; y_index < tot_Vertex; y_index++) {
                if (!visited[y_index] && Graph[x_index][y_index] > 0) {
                    // if the node hasn't been visited previously and
                    // if a capacity exists outward from a node

                    queue.add(y_index);         // adding all the nodes into the queue in increasing order of end node
                    parent[y_index] = x_index;  // stores the node before node v (which is node u) at the index v
                    visited[y_index] = true;    // marking the node which was just added to the queue as visited
                }
            }
        }

        // The result either (true meaning that connection is found and false meaning no connection found)
        // will be returned (If we reached sink in BFS starting from source, then
        //        return true, else false)
        return visited[target];
    }
}
