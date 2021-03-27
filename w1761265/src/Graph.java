/*
   Name: Nazhim Kalam
   Student ID:2019281
   UoW: w1761265
   Algorithms - Coursework 01
 */

import java.util.ArrayList;

// This class is used to Generate an Adjacent Matrix for a given Graph
public class Graph {

    // This method returns the created Adjacent Matrix
    public static int[][] generateGraph(ArrayList<String> inputData){
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
            int x_index = Integer.parseInt(split_data[0].trim());
            int y_index = Integer.parseInt(split_data[1].trim());
            int value = Integer.parseInt(split_data[2].trim());

            // Adding edge
            addEdge(x_index, y_index, value, graph_data);
        }
        return graph_data;
    }

    // Adding edge to the graph
    private static void addEdge(int x_index, int y_index, int value, int[][] graph_data) {
        graph_data[x_index][y_index] = value;
    }

    // Visualize graph
    public static void visualizeGraph(int[][] graph_data){
        // Assuming that we arent dealing with integers greater than 999 as of now

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

        System.out.println("\n = Total Number of Nodes present: " + graph_data.length);
        System.out.println(" = Total Number of Edges present: " + edge_count);
        System.out.println(" = The Source Node is: 0");
        System.out.println(" = The Target Node is: " + (graph_data.length - 1));

    }

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
