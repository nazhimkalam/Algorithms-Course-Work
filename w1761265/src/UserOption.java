/*
   Name: Nazhim Kalam
   Student ID:2019281
   UoW: w1761265
   Algorithms - Coursework 01
 */

// This class contains methods which represent the user options
public class UserOption {

    // Insert Node
    public static int[][] insertNode(int[][] graph){

        // Setting up a new graph to update with the new set of values
        int newGraphSize = graph.length + 1;
        int[][] updatedGraph = new int[newGraphSize][newGraphSize];

        /* Creating a copy of the data from the previous graph and
           adding the other node and initializing the new node added edges (weights) to 0 */
        for (int rowData = 0; rowData < graph.length; rowData++) {
            System.arraycopy(graph[rowData], 0, updatedGraph[rowData], 0, graph.length);
        }

        // Output message and returning the updated new graph
        System.out.println(" Inserted a New Node Successful!");

        // returns the updated graph
        return updatedGraph;
    }

    /* Delete Node (We are only removing all its edge of the node which has to be deleted which also means that
       the node is still present but not connected with in the graph with the other nodes, hence its considered as removed) */
    public static void deleteNode(int deleteNode, int[][] graph){

        // Checking if the node is present and then deleting the respective node from the graph
        if(!(deleteNode > graph.length-1 || deleteNode < 0)){

            // we are settings all the edges related to the deleting node to 0.
            for (int colData = 0; colData < graph.length; colData++) {
                graph[deleteNode][colData] = 0;
                graph[colData][deleteNode] = 0;
            }

            // Success Message
            System.out.println(" Node removed successfully from the graph (by isolating the node)!");

        }else{
            // When user enters an invalid node to be deleted
            System.out.println(" You have entered an invalid node which is not present in the graph to be deleted!");
        }

    }

    // Delete Edge Method
    public static void deleteEdge(int[] edgeDetails, int[][] graph_data){

        // Setting the Edge with the given input details to 0 (removing the edge)
        if ((edgeDetails[0] < graph_data.length && edgeDetails[0] > -1) &&
                (edgeDetails[1] < graph_data.length  && edgeDetails[1] > -1)) {
            graph_data[edgeDetails[0]][edgeDetails[1]] = 0;
            System.out.println(" Edge removed successfully!");

        } else {
            // If invalid edges, displays another output to the user
            System.out.println(" Your inputs seems to be invalid please try again.");
        }

    }

    // Insert Edge Method
    public static void insertEdge(int[] edgeDetails, int[][] graph_data){

        // Checking if it is a valid edge
        if ((edgeDetails[0] < graph_data.length && edgeDetails[0] > -1) &&
                (edgeDetails[1] < graph_data.length  && edgeDetails[1] > -1)) {

            // Adding a new edge to the graph
            if (graph_data[edgeDetails[0]][edgeDetails[1]] == 0) {
                graph_data[edgeDetails[0]][edgeDetails[1]] = edgeDetails[2];
                System.out.println(" Edge added successfully!");

            } else {
                // If there is an edge already present, then we override that particular edge itself
                graph_data[edgeDetails[0]][edgeDetails[1]] = edgeDetails[2];
                System.out.println(" Overriding edge, since there is an edge already with these inputs");
                System.out.println(" Edge added successfully!");

            }
        } else {
            // When user enters invalid edge numbers
            System.out.println(" Your inputs seems to be invalid please try again.");
        }
    }

}
