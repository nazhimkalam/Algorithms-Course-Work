import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Coursework {

    public static void main(String[] args) {

        // Reading all data from input file and creating a graph matrix
        int[][] graph_data = readDataFromFile();
        for (int i = 0; i < graph_data.length; i++) {
            for (int j = 0; j < graph_data.length; j++) {
                System.out.print(graph_data[i][j] + " ");
            }
            System.out.println();
        }


    }

    // This method will return a 2D Matrix of the graph data representation.
    private static int[][] readDataFromFile() {

        // Stores collection of lines from the input file
        ArrayList<String> inputData = new ArrayList<>();

        // This block of code is used to read the data from the input file
        // Also add every sentence one by one into the collection (inputData)
        System.out.println("Reading data from file...");
        try {
            File myObj = new File("data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputData.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Setting the size of the matrix
        int matrix_size = Integer.parseInt(inputData.get(0));

        // Starting to create the graph matrix
        int[][] graph_data = new int[matrix_size][matrix_size];

        System.out.println("Converting graph data into a 2D matrix representation...");
        // Initializing all the elements of the graph in the 2D array with the value 0
        for (int row = 0; row < matrix_size; row++) {
            for (int column = 0; column < matrix_size; column++) {
                graph_data[row][column] = 0;
            }
        }

        // Adding the edge data values to the respective positions in the 2D matrix
        for (int item = 1; item < inputData.size(); item++) {
            String[] split_data = inputData.get(item).split(" ");
            int x_coordinate = Integer.parseInt(split_data[0]);
            int y_coordinate = Integer.parseInt(split_data[1]);
            int value = Integer.parseInt(split_data[2]);

            graph_data[x_coordinate][y_coordinate] = value;
        }

        return graph_data;
    }

}

// https://www.programiz.com/dsa/ford-fulkerson-algorithm [FOLLOWING THIS CODE]
// https://www.youtube.com/watch?v=5hPfm_uqXmw&list=PLdo5W4Nhv31bbKJzrsKfMpo_grxuLl8LU&index=81