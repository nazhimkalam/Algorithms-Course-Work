/*
 *   Name: Nazhim Kalam
 *   Student ID:2019281
 *   UoW: w1761265
 *   Algorithms - Coursework 01
 */

import java.util.LinkedList;

// This class is used to perform BFS (Breadth First Search on given Graph data)
public class BFS {

    // Using BFS as the searching algorithm (Breadth-first search)
    // This function returns "true" if there is a path from source 's' to sink 't' in the residual graph.
    // It also fills parent[] array to store the path
    public static boolean bfs(int[][] Graph, int source, int target, int[] parent_arr, int tot_Vertex) {

        System.out.println(" Performing BFS (Breadth-first search)...");

        // Creating and Initializing the visited array with 'false' indicated all vertices aren't visited initially.
        boolean[] visited = new boolean[tot_Vertex];
        for (int index = 0; index < tot_Vertex; ++index) {
            visited[index] = false;
        }

        // Creating a queue and we enqueue the source vertex and mark source vertex as visited.
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;  // source is always visited at the beginning
        parent_arr[source] = -1;    // -1 is never given to a node, it's used to identify that there's no node
                                // before the source node

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
                    parent_arr[y_index] = x_index;  // stores the node before node v (which is node u) at the index v
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
