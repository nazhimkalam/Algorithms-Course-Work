/*
 *  Name: Nazhim Kalam
 *  Student ID:2019281
 *  UoW: w1761265
 *  Algorithms - Coursework 01
 */

/*
*   Important Note
*   - When you wish to create a new node, make sure u also add edges to connect to the new node, else the max flow cannot be
*     found. (since there is no edges connected to the sink node in order to find the max flow between source to sink)
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    // Input path for the data input file
    private static final String INPUT_FILE_PATH = "inputData/data.txt";

    // Creating an instance of the Graph Class
    private static Graph graphOptions;

    // This is a 2D matrix which stores the data of the graph representation
    public static int[][] graph_data;

    // MAIN MENU
    public static void main(String[] args) {
        // Reading all data from input file and creating a graph matrix
        graphOptions = new Graph();
        graph_data = readDataFromFile();

        // Calling the Display Menu and Result Function
        displayMenu();
    }

    private static void executeFordFulkerson(){
        // Stopwatch to start and end the timer to get the time taken for completion
        Stopwatch timer = new Stopwatch();
        System.out.println();

        // Making sure that graph data is present to proceed
        if (graph_data != null) {

            // Viewing the created matrix (if necessary)
            System.out.println(" This is the Adjacent Matrix of the Current Graph \n");
            graphOptions.visualizeGraph(graph_data);
            System.out.println();

            // Displaying the result to the user by calling the ford fulkerson algorithm
            System.out.println(" The maximum generated flow is " + FordFulkerson.fordFulkerson(graph_data, 0,
                    graph_data.length - 1));

            // Displaying out the time taken to complete the algorithm
            System.out.println(" Elapsed time (Time taken) = " + timer.elapsedTime() + " seconds");

        }
    }

    // Display Menu and Result Function
    private static void displayMenu() {
        // Asking user if he needs to insert, delete edge from a graph or if he needs to quit the program
        System.out.println("\n | MAIN MENU ==========>" +
                "\n | Enter " +
                "\n | (1) Insert an Edge " +
                "\n | (2) Delete an Edge " +
                "\n | (3) Insert a Node " +
                "\n | (4) Delete a Node " +
                "\n | (5) Check if edge exists " +
                "\n | (6) Perform max flow finding " +
                "\n | (any other key) to quit the program");
        System.out.print("\n = Enter your option: ");
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

        } else if(option.trim().equalsIgnoreCase("5")){
            // Checking if an edge is present in between 2 vertices
            checkingEdge();
        }
        else if(option.trim().equalsIgnoreCase("6")){
            // Performing the max flow finding
            executeFordFulkerson();
        }
        else {
            System.out.println(" Quitting the program...");
            System.exit(200);
        }

        displayMenu();
    }

    // Checking if an edge is present
    private static void checkingEdge() {
        // Getting an integer array of inputs from the user
        int[] edgeDetails = getEdgeInfo(false);

        // Calling the insert edge method from the user option class to perform the operation
        boolean edgePresent = graphOptions.existsEdge(edgeDetails[0], edgeDetails[1], graph_data);
        System.out.println();

        // Displaying result
        if(edgePresent){
            System.out.println(" Yes, there is an edge present between the vertex " + edgeDetails[0] + " and " + edgeDetails[1]);
        }else{
            System.out.println(" No, there is no edge present between the vertex " + edgeDetails[0] + " and " + edgeDetails[1]);
        }
    }

    // Deleting a Node from your current Graph
    private static void deletingNode() {

        // Getting the node number to be deleted from the user via the console
        int deleteNode = validatingIntegers(" Enter the node you wish to delete from the current graph (Positive Integer): ");

        // Validating for positive inputs
        while (deleteNode < 0){
            deleteNode = validatingIntegers(" Enter the node you wish to delete from the current graph (Positive Integer): ");
        }

        // Calling the delete node method to delete an existing node from the graph
        graph_data = graphOptions.deleteNode(deleteNode, graph_data);

    }

    // Inserting a Node to your current Graph
    private static void insertingNode() {
        // Calling the insert node method to add a new node automatically in ascending order of the numbers
        graph_data = graphOptions.insertNode(graph_data);

    }

    // Deleting an edge from your current graph
    private static void deletingEdge() {
        // Getting an integer array of inputs from the user
        int[] edgeDetails = getEdgeInfo(false);

        // Calling the delete edge method from the user option class to perform the operation
        graph_data = graphOptions.deleteEdge(edgeDetails, graph_data);

    }

    // Inserting an edge from your current graph
    private static void insertingEdge() {
        // Getting an integer array of inputs from the user
        int[] edgeDetails = getEdgeInfo(true);

        // Calling the insert edge method from the user option class to perform the operation
        graph_data = graphOptions.insertEdge(edgeDetails, graph_data);

    }

    /*
    @param insertingEdge: boolean to check if we are inserting edge or updating existing edge
     */
    // A function to get inputs related to Edge
    private static int[] getEdgeInfo(boolean insertingEdge) {

        int fromNode = validatingIntegers(" Enter the 'from' node value (Positive Integers expected): ");
        // Positive Integer Validation
        while(fromNode < 0){
            fromNode = validatingIntegers(" Enter the 'from' node value (Positive Integers expected): ");
        }

        int toNode = validatingIntegers(" Enter the 'to' node value (Positive Integers expected): ");
        // Positive Integer Validation
        while(toNode < 0){
            toNode = validatingIntegers(" Enter the 'to' node value (Positive Integers expected): ");
        }

        if (insertingEdge) {
            int capacityValue = validatingIntegers(" Enter the capacity value (Positive Integers expected): ");

            // Positive Integer Validation
            while(capacityValue < 0){
                capacityValue = validatingIntegers(" Enter the capacity value (Positive Integers expected): ");
            }
            return new int[]{fromNode, toNode, capacityValue};
        }

        return new int[]{fromNode, toNode};
    }

    /*
    @param message: string for specific messages to be displayed
     */
    // validates the Integers
    public static int validatingIntegers(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        while (!input.hasNextInt()) {

            /* we get the user input and check if the user has entered a valid integer or not and then validate asking
               integer input again until condition satisfied */
            System.out.println("\n Invalid input, please enter a valid positive integer!");
            System.out.print(" " + message);
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
            return graphOptions.generateGraph(graphInputData);
        } else {
            // Returns null if no file found
            return null;
        }
    }
}

/*

 Time Taken (Repeat 3 times and get the average time)
 bridge_1.txt  --> (0.001, 0.001, 0.002)  ---> max flows:  2
 bridge_2.txt  --> (0.003, 0.005, 0.004)  ---> max flows:  2
 bridge_3.txt  --> (0.015, 0.006, 0.004)  ---> max flows:  2
 bridge_4.txt  --> (0.015, 0.010, 0.016)  ---> max flows:  2
 bridge_5.txt  --> (0.016, 0.015, 0.015)  ---> max flows:  2
 bridge_6.txt  --> (0.063, 0.08, 0.068)   ---> max flows:  2
 bridge_7.txt  --> (0.188, 0.187, 0.195)  ---> max flows:  2
 bridge_8.txt  --> (0.62, 0.566, 1.032)   ---> max flows:  2
 bridge_9.txt  --> (3.213, 4.223, 3.855)  ---> max flows:  2

 ladder_1.txt  --> (0.0, 0.001, 0.001)  ---> max flows:   4
 ladder_2.txt  --> (0.015, 0.016, 0.016)  ---> max flows: 7
 ladder_3.txt  --> (0.01, 0.01, 0.015)  ---> max flows:   13
 ladder_4.txt  --> (0.015, 0.015, 0.037)  ---> max flows: 25
 ladder_5.txt  --> (0.051, 0.061, 0.062)  ---> max flows: 49
 ladder_6.txt  --> (0.535, 0.12, 0.139)  ---> max flows:  97
 ladder_7.txt  --> (0.685, 0.473, 0.716)  ---> max flows: 193
 ladder_8.txt  --> (2.758, 2.948, 3.333)  ---> max flows: 385
 ladder_9.txt  --> (11.457, 11.64, 12.707) ---> max flows:769

 References used
 https://www.programiz.com/dsa/ford-fulkerson-algorithm
 https://www.youtube.com/watch?v=5hPfm_uqXmw&list=PLdo5W4Nhv31bbKJzrsKfMpo_grxuLl8LU&index=81
 https://stackoverflow.com/questions/2218322/what-is-better-adjacency-lists-or-adjacency-matrices-for-graph-problems-in-c
 https://medium.com/@jithmisha/solving-the-maximum-flow-problem-with-ford-fulkerson-method-3fccc2883dc7

 */