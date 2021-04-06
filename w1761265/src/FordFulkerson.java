/*
 *  Name: Nazhim Kalam
 *  Student ID:2019281
 *  UoW: w1761265
 *  Algorithms - Coursework 01
 */

/* Ford Fulkerson Algorithm used to find the maximum flow of a Graph */
public class FordFulkerson {
    public static int totalVertices;
    public static Graph graphOptions = new Graph();

    /*
    Returns tne maximum flow from source to target in the given graph
    @param data: Contains the Adjacent Matrix Graph data
    @param source: Starting node or the source node of the graph
    @param target: Ending node or the sink node of the graph
    */
    public static String fordFulkerson(int[][] data, int source, int target) {

        // Variables
        totalVertices = target + 1;        //Total Number of vertices the flow network
        int fromNode;                      // starting node
        int toNode;                        // ending node
        int[][] residualGraph = new int[totalVertices][totalVertices];   // initializing residual graph

        /* We are creating a residual graph by filling the residual graph with the given capacities from the
           original graph.
           In the Residual graph resGraph[fromNode][toNode] indicates the residual capacity of edge from (fromNode) to (toNode).
           If rGraph[fromNode][toNode] is 0, then there is No edge Else if its not 0 then there IS AN Edge
        */
        for (fromNode = 0; fromNode < totalVertices; fromNode++) {
            for (toNode = 0; toNode < totalVertices; toNode++) {
                residualGraph[fromNode][toNode] = data[fromNode][toNode];
            }
        }
        System.out.println(" Creating the residual graph... \n");

        // Visualize the Residual Graph
        graphOptions.visualizeGraph(residualGraph);

        /* The parent is used to store the found path and is filled by BFS
           initialized with tot_Vertex because the max path can have all the number of vertices in the graph
           (same node can't be visited more than once) */
        int[] parent = new int[totalVertices];

        // Initially we set the flow to 0, because there is no flow initially
        int maximum_flow = 0;

        // Updating the residual values of edges by augmenting the flow while there is a path from source to sink.
        while (BFS.bfs(residualGraph, source, target, parent, totalVertices)) {

            /* By using BFS we are finding the (minimum residual capacity) of the edges along the path which can be
               filled with. */
            System.out.println(" Finding the minimum residual capacity (bottleneck capacity)...");
            int path_flow = Integer.MAX_VALUE;
            // assigning the maximum value an integer possible - because minimum residual capacity is needed to be found

            for (toNode = target; toNode != source; toNode = parent[toNode]) {
                fromNode = parent[toNode];

                // getting the minimum value out of path_flow and rGraph[u][v] (capacity of edge in residual graph)
                path_flow = Math.min(path_flow, residualGraph[fromNode][toNode]);
            }

            System.out.println(" The bottleneck capacity value from the residual graph found is : " + path_flow);
            System.out.println(" Updating the residual capacities of the edges... \n");

            // Updating the residual capacities of the edges and also reversing the edges along the path.
            for (toNode = target; toNode != source; toNode = parent[toNode]) {
                fromNode = parent[toNode];

                // decreasing capacity from the flow sent; to get the residual capacity (forward direction)
                residualGraph[fromNode][toNode] -= path_flow;

                // adding the flow sent to the reverse capacity; to get the residual capacity (backward direction)
                residualGraph[toNode][fromNode] += path_flow;
            }

            // Visualize the Updated Residual Graph
            graphOptions.visualizeGraph(residualGraph);

            // Adding the path flows to the overall flow
            maximum_flow += path_flow;
            System.out.println(" Adding the path flows to the overall flow...");
            System.out.println(" Updating the maximum flow to = " + maximum_flow);

        }
        System.out.println();

        // Returning the overall flow, which is also the maximum flow of the graph
        return String.valueOf(maximum_flow);
    }
}

/*
 ABOUT FORD FULKERSON ALGORITHM
 - The Ford Fulkerson Algorithm is used to find the maximum flow from the Source Node 's' to the Target Node 't'
 - The flow on an edge doesn't exceed the given capacity of the edge.
 - For all the other node except for the source and the sink node, the total incoming flow is equal to the total outgoing
   flow at a node.

 HOW TO IMPLEMENT THE FORD FULKERSON ALGORITHM
 - Residual Capacity is 0 if there is no edge between two vertices of the residual graph.
 - We can initialize the residual graph as original graph as there is no flow in the beginning.
 - At start the Residual Capacity is equal to the Original Capacity.
 - We have to find the augmenting path from the source to the sink node.
 - For this we can use either BFS ot DFS to the residual graph.
 - BFS is used here because it promises to find the shortest possible path with the maximum flow where DFS doesn't.
 - So using BFS we can find the shortest path from source to sink.
 - BFS makes use of the parent[] array to store the found paths.
 - We now traverse through the found path and find possible flow through this path by finding minimum residual capacity,
   along the path.
 - Once we get the found path with the flow capacity we add it to the overall flow.
 - Now it's very important to update the residual capacity in the residual graph.
 - We now subtract path flow from all edges along the path (forward edge).
 - We also add path flow along the reverse edge (backward edge).
 - This process repeats until there is no more augmenting paths available to update the residual graph.
 */