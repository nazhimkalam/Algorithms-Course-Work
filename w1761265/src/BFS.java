/*
 *  Name: Nazhim Kalam
 *  Student ID:2019281
 *  UoW: w1761265
 *  Algorithms - Coursework 01
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

// This class is used to perform BFS (Breadth First Search on given Graph data)
public class BFS {

    /*
     Using BFS as the searching algorithm (Breadth-first search)
     This function returns "true" if there is a path from source 's' to sink 't' in the residual graph.
     It also fills parent[] array to store the path

     @param Graph: Contains the Adjacent Matrix Graph data
     @param source: Starting node or the source node of the graph
     @param target: Ending node or the sink node of the graph
     @param totalVertices: Integer number of the total number of vertices present in the graph
    */
    public static boolean bfs(int[][] Graph, int source, int target, int[] parent, int totalVertices) {

        System.out.println("\n Performing BFS (Breadth-first search)...");

        /* Creating and Initializing the visited array with 'false' indicated all vertices aren't visited initially. */
        boolean[] visited = new boolean[totalVertices];
        for (int index = 0; index < totalVertices; ++index) {
            visited[index] = false;
        }

        /* Creating a queue and we enqueue the source vertex and mark source vertex as visited. */
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;     // source is always visited at the beginning
        parent[source] = -1;        /* -1 is never given to a node, it's used to identify that there's no node
                                      before the source node */

        /* This is the normal standard BFS (Breadth-first search) Loop */
        while (queue.size() != 0) {

            /* returns the first element of queue and removes it from the queue
             returns null if queue is empty. */
            int firstElement = queue.poll();

            /* Once a connection to the target/sink vertex is found we stop the BFS process and
               set to its parent */
            for (int index = 0; index < totalVertices; index++) {
                if (!visited[index] && Graph[firstElement][index] > 0) {
                    /* if the node hasn't been visited previously and
                       if a capacity exists outward from a node */
                    queue.add(index);             // adding all the nodes into the queue in increasing order of end node
                    visited[index] = true;        // marking the node which was just added to the queue as visited
                    parent[index] = firstElement;  // stores the node before node v (which is node u) at the index v

                }
            }
        }

        /* If there is an augmenting path from the source to the sink then we display the found path */
        ArrayList<Integer> augmentingPath = new ArrayList<>();
        if(visited[target]){
            /* Creating the Augmenting path pattern to be displayed to the console */
            for (int vertex = target; vertex != source; vertex = parent[vertex]) {
                augmentingPath.add(vertex);
            }
            augmentingPath.add(0);
            Collections.reverse(augmentingPath);

            System.out.print(" Augmenting Path Found: ");
            for (int index = 0; index < augmentingPath.size() - 1; index++) {
                System.out.print(augmentingPath.get(index) + " --> ");
            }
            System.out.println(augmentingPath.get(augmentingPath.size()-1));

        }

        /* Displaying Message if no Augmenting path found */
        if(!visited[target]){
            System.out.println(" No Augmenting path found from Source to the Target...");
        }

        /* The result either (true meaning that connection is found and false meaning no connection found )
           will be returned (If we reached sink in BFS starting from source, then
           return true, else false) */
        return visited[target];
    }

}


