/*
 *  Name: Nazhim Kalam
 *  Student ID:2019281
 *  UoW: w1761265
 *  Algorithms - Coursework 01
 */

import java.util.ArrayList;

public abstract class GraphADT {

    // Returns the generated Adjacency Matrix
    public abstract int[][] generateGraph(ArrayList<String> inputData);

    // Prints out the graph and displays
    public abstract void visualizeGraph(int[][] graph_data, boolean otherStatistics);

    // Returns if the edge is present or not
    public abstract boolean existsEdge(int vertexOne, int vertexTwo, int[][] graph);

    // Returns a graph after inserting an edge into the graph
    public abstract int[][] insertEdge(int[] edgeDetails, int[][] graph_data);

    // Returns a graph after deleting an edge from the graph
    public abstract int[][] deleteEdge(int[] edgeDetails, int[][] graph_data);

    // Returns a graph after inserting a node into the graph
    public abstract int[][] insertNode(int[][] graph);

    // Returns a graph after deleting a node from the graph
    public abstract int[][] deleteNode(int deleteNode, int[][] graph);

}
