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


}
