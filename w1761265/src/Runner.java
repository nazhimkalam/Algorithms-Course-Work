import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    // Input path for the data input file
    private static final String INPUT_FILE_PATH = "inputData/bridge_1.txt";

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
        // Stopwatch to start and end the timer to get the time taken for completion
        Stopwatch timer = new Stopwatch();

        System.out.println("================================");
        // Making sure that graph data is present to proceed
        if (graph_data != null) {

            // Viewing the created matrix (if necessary)
            System.out.println("\n> This is the Adjacent Matrix for a given Graph <\n");
            viewingGraphMatrix(graph_data);
            System.out.println();

            // Displaying the result to the user by calling the ford fulkerson algorithm
            System.out.println("The maximum generated flow is " + FordFulkerson.fordFulkerson(graph_data, 0,
                    graph_data.length - 1));

            // Displaying out the time taken to complete the algorithm
            System.out.println("Elapsed time = " + timer.elapsedTime());

            // Asking user if he needs to insert, delete edge from a graph or if he needs to quit the program
            System.out.println("\n| MAIN MENU" +
                    "\n| Enter " +
                    "\n| (1) Insert an Edge, " +
                    "\n| (2) Delete an Edge, " +
                    "\n| (3) Insert a Node, " +
                    "\n| (4) Delete a Node, " +
                    "\n| (any other key) to quit the program.");
            System.out.print("\n Enter your option: ");
            Scanner input = new Scanner(System.in);

            // Getting the user option
            String option = input.nextLine();

            // Handle Conditions
            if (option.trim().equalsIgnoreCase("1")){
                // Inserting an edge
                insertingEdge();

            } else if (option.trim().equalsIgnoreCase("2")){
                // Deleting an edge
                deletingEdge();

            } else if(option.trim().equalsIgnoreCase("3")){
                // Inserting a Node
                insertingNode();

            } else if(option.trim().equalsIgnoreCase("4")){
                // Deleting a Node
                deletingNode();

            } else {
                System.out.println("Quitting the program...");
                System.exit(200);
            }

            // Calling the Menu
            displayMenuAndResult();
        }
    }

    // Deleting a Node from your current Graph
    private static void deletingNode() {

    }

    // Inserting a Node to your current Graph
    private static void insertingNode() {
        // Calling the insert node method to add a new node automatically in ascending order of the numbers
        graph_data = UserOption.insertNode(graph_data);

        // Updated Graph Data Visualization
        viewingGraphMatrix(graph_data);
    }

    // method used to print out the graph matrix
    private static void viewingGraphMatrix(int[][] graph_data) {
        for (int[] data_row : graph_data) {
            for (int j = 0; j < graph_data.length; j++) {
                System.out.print(data_row[j] + " ");
            }
            System.out.println();
        }
    }

    // Deleting an edge from your current graph
    private static void deletingEdge() {
        // Getting an integer array of inputs from the user
        int[] edgeDetails = getEdgeInfo(false);

        // Calling the delete edge method from the user option class to perform the operation
        UserOption.deleteEdge(edgeDetails, graph_data);
    }

    // Inserting an edge from your current graph
    private static void insertingEdge() {
        // Getting an integer array of inputs from the user
        int[] edgeDetails = getEdgeInfo(true);

        // Calling the insert edge method from the user option class to perform the operation
        UserOption.insertEdge(edgeDetails, graph_data);
    }

    // A function to get inputs related to Edge
    private static int[] getEdgeInfo(boolean insertingEdge) {
        int from_node = validatingIntegers("Enter the 'from' node value (Integers expected): ");
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
        // Getting the graph data from the file
        ArrayList<String> graphInputData = ReadDataFile.readingData(INPUT_FILE_PATH);

        if (graphInputData.size() != 0) {
            // Generating the Adjacent Matrix for the Graph
            return Graph.generateGraph(graphInputData);
        } else {
            // Returns null if no file found
            return null;
        }
    }
}

// Time Taken (Repeat 3 times and get the average time)
// bridge_1.txt  --> (0.001, 0.002, 0.002)  ---> max flows: 2
// bridge_2.txt  --> (0.002, 0.001, 0.001)  ---> max flows: 2
// bridge_3.txt  --> (0.001, 0.002, 0.003)  ---> max flows: 2
// bridge_4.txt  --> (0.001, 0.002, 0.002)  ---> max flows: 2
// bridge_5.txt  --> (0.002, 0.003, 0.003)  ---> max flows: 2
// bridge_6.txt  --> (0.003, 0.003, 0.005)  ---> max flows: 2
// bridge_7.txt  --> (0.011, 0.012, 0.013)  ---> max flows: 2
// bridge_8.txt  --> (0.016, 0.023, 0.026)  ---> max flows: 2
// bridge_9.txt  --> (0.043, 0.038, 0.036)  ---> max flows: 2

// ladder_1.txt  --> (0.003, 0.002, 0.001)  ---> max flows:4
// ladder_2.txt  --> (0.002, 0.004, 0.004)  ---> max flows:7
// ladder_3.txt  --> (0.007, 0.005, 0.004)  ---> max flows:13
// ladder_4.txt  --> (0.009, 0.01, 0.007)  ---> max flows:25
// ladder_5.txt  --> (0.018, 0.024, 0.02)  ---> max flows:49
// ladder_6.txt  --> (0.041, 0.051, 0.045)  ---> max flows:97
// ladder_7.txt  --> (0.165, 0.176, 0.16)  ---> max flows:193
// ladder_8.txt  --> (1.046, 1.128, 1.083)  ---> max flows:385
// ladder_9.txt  --> (6.937, 7.043, 7.107)  ---> max flows:769

// References used
// https://www.programiz.com/dsa/ford-fulkerson-algorithm
// https://www.youtube.com/watch?v=5hPfm_uqXmw&list=PLdo5W4Nhv31bbKJzrsKfMpo_grxuLl8LU&index=81