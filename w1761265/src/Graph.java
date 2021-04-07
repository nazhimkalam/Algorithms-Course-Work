/*
 *  Name: Nazhim Kalam
 *  Student ID:2019281
 *  UoW: w1761265
 *  Algorithms - Coursework 01
 */

import java.util.ArrayList;

// This class is used to Generate an Adjacent Matrix for a given Graph
public class Graph extends GraphADT{

    /*
     @param inputData: Contains a list of strings which were read from the text file
    */
    @Override
    public int[][] generateGraph(ArrayList<String> inputData) {
        // This method returns the created Adjacent Matrix
        // Setting the size of the matrix
        int matrix_size = Integer.parseInt(inputData.get(0).trim());

        // Starting to create the graph matrix
        int[][] graph_data = new int[matrix_size][matrix_size];

        System.out.println(" Converting graph data into a 2D matrix representation (Adjacent Matrix)...");
        // Initializing all the elements of the graph in the 2D array with the value 0
        for (int row = 0; row < matrix_size; row++) {
            for (int column = 0; column < matrix_size; column++) {
                graph_data[row][column] = 0;
            }
        }

        // Adding the edge data values to the respective positions in the 2D matrix
        for (int item = 1; item < inputData.size(); item++) {
            // Extracting the data to create the graph
            String[] split_data = inputData.get(item).split(" ");
            int fromNode = Integer.parseInt(split_data[0].trim());
            int toNode = Integer.parseInt(split_data[1].trim());
            int capacityValue = Integer.parseInt(split_data[2].trim());

            // Adding edge
            addEdge(fromNode, toNode, capacityValue, graph_data);
        }
        return graph_data;
    }

    /*
     @param fromNode: starting node
     @param toNode: ending node
     @param capacityValue: capacity value
     @param graph_data: graph data 2D matrix
    */
    // Adding edge to the graph when creating the graph
    private static void addEdge(int fromNode, int toNode, int capacityValue, int[][] graph_data) {
        graph_data[fromNode][toNode] = capacityValue;
    }

    /*
    @param graph_data: graph data 2D matrix
    @param otherStatistics: displaying other statistics such as number of edges, node etc...
     */
    @Override
    public void visualizeGraph(int[][] graph_data, boolean otherStatistics) {
        // Assuming that we aren't dealing with integers greater than 999 as of now (for graph visualization)

        // Code for displaying the graph to the console
        int edge_count = 0;
        int rowCounter = 0;

        // Creating the column indexes for the graph matrix
        // Organising the graph matrix with proper spacing to create proper columns
        String[][] graph_representation = organiseGraph(graph_data);
        System.out.print("  * |");
        for (int index = 0; index < graph_representation.length; index++) {
            if(index < 10){
                System.out.print("   " + index);
            }else if(index < 100){
                System.out.print("  " + index);
            }else{
                System.out.print(" " + index);
            }
        }
        System.out.println("\n");

        for (String[] data_row : graph_representation) {

            // Creating the row indexes
            if(rowCounter < 10){
                System.out.print("  " + rowCounter + " | ");
            }else if(rowCounter < 100){
                System.out.print(" " + rowCounter + " | ");
            }else{
                System.out.print(rowCounter + " | ");
            }

            for (int index = 0; index< graph_representation.length; index++) {
                // incrementing edge value
                if(Integer.parseInt(data_row[index].trim()) > 0){
                    edge_count++;
                }

                System.out.print(data_row[index] + " ");
            }
            System.out.println();
            rowCounter++;
        }

        // Displaying other statistics of the graph such as number of nodes/vertices, edges, source and target node
        if(otherStatistics){
            System.out.println("\n = Total Number of Nodes/Vertices present: " + graph_data.length);
            System.out.println(" = Total Number of Edges present: " + edge_count);
            System.out.println(" = The Source Node is: 0");
            System.out.println(" = The Target Node is: " + (graph_data.length - 1));
        }

    }

    /*
    @param graph_data: graph data 2D matrix
    @param fromVertex: starting node
    @param toVertex: ending node
     */
    @Override
    public boolean existsEdge(int fromVertex, int toVertex, int[][] graph) {
        // Checking if an edge is present in between the vertices given by the user

        // Checking for valid vertex entered by the user
        if ((fromVertex < graph.length && fromVertex > -1) && (toVertex < graph.length  && toVertex > -1)) {
            // if capacity between 2 vertices are equal to 0, then it means that there is not edge over there
            // return true if edge present else false if not present
            return graph[fromVertex][toVertex] != 0;
        }
        return false;

    }

    /*
    @param graph_data: graph data 2D matrix
    @param edgeDetails: details of the edge in an array format
     */
    @Override
    public int[][] insertEdge(int[] edgeDetails, int[][] graph_data){
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
        return graph_data;
    }

    /*
    @param graph_data: graph data 2D matrix
    @param edgeDetails: details of the edge in an array format
     */
    @Override
    public int[][] deleteEdge(int[] edgeDetails, int[][] graph_data) {
        // Delete Edge Method

        // Setting the Edge with the given input details to 0 (removing the edge)
        if ((edgeDetails[0] < graph_data.length && edgeDetails[0] > -1) &&
                (edgeDetails[1] < graph_data.length  && edgeDetails[1] > -1)) {
            graph_data[edgeDetails[0]][edgeDetails[1]] = 0;
            System.out.println(" Edge removed successfully!");

        } else {
            // If invalid edges, displays another output to the user
            System.out.println(" Your inputs seems to be invalid please try again.");
        }

        return graph_data;
    }

    /*
    @param graph: graph data 2D matrix
     */
    @Override
    public int[][] insertNode(int[][] graph) {

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

    /*
    @param graph: graph data 2D matrix
    @param deleteNode: the value of the node to be deleted from the graph
     */
    @Override
    public int[][] deleteNode(int deleteNode, int[][] graph) {
        /* Delete Node (We are only removing all its edge of the node which has to be deleted which also means that
       the node is still present but not connected with in the graph with the other nodes, hence its considered as removed) */

        // Checking if the node is present and then deleting the respective node from the graph
        if(!(deleteNode > graph.length-1 || deleteNode < 0)){

            if(deleteNode == (graph.length - 1)){
                // This means we are deleting the last node from the graph
                int[][] updated_graph = new int[graph.length - 1][graph.length - 1];

                for (int row = 0; row < graph.length - 1; row++) {
                    System.arraycopy(graph[row], 0, updated_graph[row], 0, graph.length - 1);
                }
                return updated_graph;
            }else{
                // we are settings all the edges related to the deleting node to 0.
                for (int colData = 0; colData < graph.length; colData++) {
                    graph[deleteNode][colData] = 0;
                    graph[colData][deleteNode] = 0;
                }

                // Success Message
                System.out.println(" Node removed successfully from the graph (by isolating the node)!");

                return graph;
            }

        }else{
            // When user enters an invalid node to be deleted
            System.out.println(" You have entered an invalid node which is not present in the graph to be deleted!");
        }
        return graph;
    }

    /*
   @param graph_data: graph data 2D matrix
    */
    // Organising the graph before printing it out
    private static String[][] organiseGraph(int[][] graph_data) {
        String[][] graph_representation = new String[graph_data.length][graph_data.length];

        // Creating the organised Adjacent Matrix
        for (int row_data = 0; row_data < graph_data.length; row_data++) {
            for (int col_data = 0; col_data < graph_data.length; col_data++) {
                if(graph_data[row_data][col_data]< 10){
                    graph_representation[row_data][col_data] = "  " + graph_data[row_data][col_data];
                }else if(graph_data[row_data][col_data]< 100){
                    graph_representation[row_data][col_data] = " " + graph_data[row_data][col_data];
                }else{
                    graph_representation[row_data][col_data] = "" + graph_data[row_data][col_data];
                }
            }
        }

        return graph_representation;
    }
}
