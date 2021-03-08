import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    // This is a 2D matrix which stores the data of the graph representation
    public static int[][] graph_data;

    // MAIN MENU
    public static void main(String[] args) {

        // Reading all data from input file and creating a graph matrix
        graph_data = readDataFromFile();

        // Calling the Display Menu and Result Function
        displayMenuAndResult();

    }

    // Display Menu and Result Function
    private static void displayMenuAndResult() {
        System.out.println("================================");
        // Making sure that graph data is present to proceed
        if (graph_data != null) {

            // Viewing the created matrix (if necessary)
//            for (int i = 0; i < graph_data.length; i++) {
//                for (int j = 0; j < graph_data.length; j++) {
//                    System.out.print(graph_data[i][j] + " ");
//                }
//                System.out.println();
//            }

            // Creating an instance of the FordFulkerson Algorithm
            FordFulkerson algorithm = new FordFulkerson();

            // Displaying the result to the user
            System.out.println("The maximum generated flow is " + algorithm.fordFulkerson(graph_data, 0, graph_data.length - 1));

            // Asking user if he needs to insert, delete edge from a graph or if he needs to quit the program
            System.out.println("Enter (1) Insert an edge, (2) Delete an edge, (any other key) to quit the program.");
            Scanner input = new Scanner(System.in);

            // Getting the user option
            String option = input.nextLine();

            // Handle Conditions
            if (option.trim().equalsIgnoreCase("1")) {
                // Inserting an edge
                insertingEdge();
                displayMenuAndResult();

            } else if (option.trim().equalsIgnoreCase("2")) {
                // Deleting an edge
                deletingEdge();
                displayMenuAndResult();

            } else {
                System.out.println("Quitting the program...");
                System.exit(200);
            }
        }
    }

    // Deleting an edge function
    private static void deletingEdge() {

        // Getting an integer array of inputs from the user
        int[] edgeDetails = getEdgeInfo(false);

        // Setting the Edge with the given input details to 0 (removing the edge)
        if (edgeDetails[0] < graph_data.length || edgeDetails[1] < graph_data.length) {
            graph_data[edgeDetails[0]][edgeDetails[1]] = 0;
            System.out.println("Edge removed successfully!");

        } else {
            System.out.println("Your inputs seems to be invalid please try again.");
        }
    }

    // Inserting an edge function
    private static void insertingEdge() {

        // Getting an integer array of inputs from the user
        int[] edgeDetails = getEdgeInfo(true);

        // Checking if edge is present and overriding else we normally add the edge
        if (edgeDetails[0] < graph_data.length || edgeDetails[1] < graph_data.length) {
            if (graph_data[edgeDetails[0]][edgeDetails[1]] != 0) {
                graph_data[edgeDetails[0]][edgeDetails[1]] = edgeDetails[2];
                System.out.println("Edge added successfully!");

            } else {
                graph_data[edgeDetails[0]][edgeDetails[1]] = edgeDetails[2];
                System.out.println("Overriding edge, since there is an edge already with these inputs");
                System.out.println("Edge added successfully!");

            }
        } else {
            System.out.println("Your inputs seems to be invalid please try again.");
        }
    }

    // Get Inputs related to Edge function
    private static int[] getEdgeInfo(boolean insertingEdge) {
        int from_node = validatingIntegers("Enter the 'from' node value (Integers expected):");

        int to_node = validatingIntegers("Enter the 'to' node value (Integers expected):");

        if (insertingEdge) {
            int capacity_value = validatingIntegers("Enter the capacity value (Integers expected):");
            return new int[]{from_node, to_node, capacity_value};
        }

        return new int[]{from_node, to_node};
    }

    // validates the Integers
    public static int validatingIntegers(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        while (!input.hasNextInt()) {

            // we get the user input and check if the user has entered a valid integer or not and then validate asking
            // integer input again until condition satisfied
            System.out.println("\n Invalid input, please enter a valid integer!");
            System.out.print(message);
            input.next();

        }
        return input.nextInt();
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
            System.out.println("File not found!");
        }

        if (inputData.size() != 0) {
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
                int x_index = Integer.parseInt(split_data[0]);
                int y_index = Integer.parseInt(split_data[1]);
                int value = Integer.parseInt(split_data[2]);

                graph_data[x_index][y_index] = value;
            }
            return graph_data;

        } else {
            // Returns null if no file found
            return null;
        }
    }
}


// References used
// https://www.programiz.com/dsa/ford-fulkerson-algorithm
// https://www.youtube.com/watch?v=5hPfm_uqXmw&list=PLdo5W4Nhv31bbKJzrsKfMpo_grxuLl8LU&index=81