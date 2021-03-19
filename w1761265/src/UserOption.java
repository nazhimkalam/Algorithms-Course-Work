
// This class contains methods which represent the user options
public class UserOption {

    // Insert Node
    public static int[][] insertNode(int[][] graph){
        System.out.println(graph.length);

        int newGraphSize = graph.length + 1;
        int[][] updatedGraph = new int[newGraphSize][newGraphSize];

        // Creating a copy of the data from the previous graph and
        // adding the other node and initializing the new node added edges (weights) to 0
        for (int rowData = 0; rowData < graph.length; rowData++) {
            System.arraycopy(graph[rowData], 0, updatedGraph[rowData], 0, graph.length);
        }

        // Output message and returning the updated new graph
        System.out.println("Inserted a New Node Successful!");

        return updatedGraph;

    }

    // Delete Node
    public static void deleteNode(String node, int[][] graph){

    }

    // Delete Edge Method
    public static void deleteEdge(int[] edgeDetails, int[][] graph_data){
        // Setting the Edge with the given input details to 0 (removing the edge)
        if (edgeDetails[0] < graph_data.length && edgeDetails[1] < graph_data.length) {
            graph_data[edgeDetails[0]][edgeDetails[1]] = 0;
            System.out.println("Edge removed successfully!");

        } else {
            System.out.println("Your inputs seems to be invalid please try again.");
        }
    }

    // Insert Edge Method
    public static void insertEdge(int[] edgeDetails, int[][] graph_data){
        // Checking if edge is present and overriding else we normally add the edge
        if (edgeDetails[0] < graph_data.length && edgeDetails[1] < graph_data.length) {
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
}
