package hw4;
import java.util.*;

/** Graph represents a mutable graph of nodes with directed edges.*/
public class Graph<T,E> {

    private ArrayList<ArrayList<ArrayList<E>>> matrix;
    private ArrayList<T> names;
    private HashMap<T,Integer> hashNames;
    private int num_edges;
    private int num_nodes;

    // Abstraction Function:
    //   A Graph g is a arraylist matrix of E, such that for matrix[x][y][z], 
    //   x is the origin node and y is the node pointed to, with z being the arraylist of nodes with parent x and child y.
    //   An empty arraylist in the adjacency matrix = no edge between node, and all else signifies a present edge.

    // Representation invariant:
    //   assert (num_edges >= 0 && num_nodes >= 0);
    //
    //   Set<String> nameSet = new HashSet<>(names);
    //   assert (names.size() == nameSet.size());
    //
    //  for (int i = 0; i < matrix.get(0).size(); i++){
    //      for (int j = 0; j < matrix.get(0).size(); j++){
    //          Set<String> labelSet = new HashSet<>(matrix.get(i).get(j));
    //          assert(matrix.get(i).get(j).size() == labelSet.size());
    //      }
    //   }
    //
    //  
    //   In other words,
    //     * There is always 0 or more nodes and edges (non negative)
    //     * There are no duplicates in names (nodes)
    //     * There are no duplicates edges (edges with same parent, same child, same label)
    // (A representation invariant tells us something that is true for all valid instances of a Graph)


    /** @effects Constructs a new Graph with zero nodes and zero edges.*/
    public Graph() {
        matrix = new ArrayList<ArrayList<ArrayList<E>>>();
        names = new ArrayList<T>();
        hashNames = new HashMap<>(); 
        num_edges = 0;
        num_nodes = 0;

        checkRep();
    }

    /** @param another another Graph to be copied.
        @requires another != null and this != null
        @effects Sets this new Graph's attributes to the same ones as another Graph's. Includes number of nodes, number of edges, adjacency matrix, name of nodes,hash map of nodes.
    */
    public Graph(Graph<T,E> another) {

        this.num_edges = another.num_edges;
        this.num_nodes = another.num_nodes;
        
        this.names = new ArrayList<T>(another.names);
        this.matrix = new ArrayList<ArrayList<ArrayList<E>>>(another.matrix);
        this.hashNames = new HashMap<>(another.hashNames);

        //checkRep();
        //too expensive
    }

    /**
     * Checks that the representation invariant holds.
     * @ensures representation invariant holds
     * @throws RuntimeException if the rep invariant is violated.
     **/
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        assert (num_edges >= 0);
        assert (num_nodes >= 0);
        Set<T> nameSet = new HashSet<>(names);
        assert (names.size() == nameSet.size());

        for (int i = 0; i < matrix.size(); i++){
            for (int j = 0; j < matrix.size(); j++){
                Set<E> labelSet = new HashSet<>(matrix.get(i).get(j));
                assert(matrix.get(i).get(j).size() == labelSet.size());
            }
        }
    }

    /**@return number of nodes in the Graph 
     * @requires this != null
    */
    public int getNumNodes() {
        return num_nodes;
    }

     /** @return number of edges in the Graph
      * @requires this != null
    */
    public int getNumEdges(){
        return num_edges;
    }
    
    /** Node Search Operation.
        @param node
        @requires this != null
        @return index if edge exists in Graph, return -1 if edge does not exist. 
    */
    public int findNode(T node) {

        if (hashNames.containsKey(node)){
            return hashNames.get(node);
        }
        return -1;
    }

    /** Node Addition Operation.
     * @requires this != null
        @param s The label of the node to be added.
        @modifies Graph to insert node s into arraylist of names and adjacency matrix of nodes.
        @modifies Resizes the matrix to be (old size + 1) * (old size + 1).
        @modifies Set Each new index as empty Arraylist to signify no edge present.
        @modifies hashNames to include an entry of s and the index of s in names.
        Does nothing if node s is already in the Graph.
    */
    public void addNode(T s) {

        if (findNode(s) != -1) return; // Node already exists

        names.add(s); //add s into names
        hashNames.put(s,names.size()-1); //keeps a record of s and its index in names
        num_nodes++;

        // Extend the adjacency matrix
        for (ArrayList<ArrayList<E>> row : matrix) {
            row.add(new ArrayList<E>());
        }
        ArrayList<ArrayList<E>> temp = new ArrayList<>();

        for (int i = 0; i < num_nodes; i++){
            temp.add(new ArrayList<E>());
        }

        matrix.add(temp);

        //checkRep();
        
    }

    /** Edge Addition Operation.
     * @requires this != null
        @param from The node which the edge stems from
        @param to The node which the edge points to
        @param label The label of the edge
        @throws RuntimeException if from or to don't exist as nodes in the list of labels
        @modifies Adds the edge into matrix. Does nothing if edge with same label, parent, and child already exists.
    */
    public void addEdge(T from, T to, E label){
        int x = findNode(from);
        int y = findNode(to);
        if (x == -1 || y == -1) throw new RuntimeException("One or both nodes not found");
        else{
            if (matrix.get(x).get(y).contains(label)) return;
            else matrix.get(x).get(y).add(label);
            num_edges++;
            
        }
        //checkRep();
    }

    /** Returns edge label.
     * @requires this != null
        @param from The node which the edge stems from
        @param to The node which the edge points to
        @throws RuntimeException if from or to don't exist as nodes in the list of labels
        @returns arraylist of edge labels of specified edge.
    */
    public ArrayList<E> getEdgeLabel(T from, T to){
        int x = findNode(from);
        int y = findNode(to);
        if (x == -1 || y == -1) throw new RuntimeException("One or both nodes not found");
        else{
            return matrix.get(x).get(y);
            
        }
    }

    /** Sets edge label.
     * @requires this != null
        @param from The node which the edge stems from
        @param to The node which the edge points to
        @param value The edge that will replace the set of edges
        @throws RuntimeException if from or to don't exist as nodes in the list of labels
        @modifies clears the set of edges and adds value as the singular edge
        if value is null, simply clear the arraylist stored at matrix[x]
    */
    public void setEdgeLabel(T from, T to, E value){
        int x = findNode(from);
        int y = findNode(to);
        if (x == -1 || y == -1) throw new RuntimeException("One or both nodes not found");
        else{
            num_edges-=matrix.get(x).get(y).size()-1;
            if (value == null){
                matrix.get(x).clear();
                return;
            }
            matrix.get(x).get(y).set(0,value);
        }
    }

    /** Sets edge label.
     *  @requires this != null
        @modifies clears the entire adjacency matrix
        @modifies num_edges to 0. 
        @modifies num_nodes to 0.
        @modifies names to be empty.
        @modifies hashNames to be empty.
    */
    public void clear(){
        matrix.clear();
        num_edges = 0;
        num_nodes = 0;
        names.clear();
        hashNames.clear();
    }

    /** Gets list of children of Node s.
     * @requires this != null
        @param s the node that we will find children of
        @throws RuntimeException if node s is not in the graph
        @return an arraylist of the names of the children
    */
    public ArrayList<T> getChildren(T s){
        int index = findNode(s);
        if (index == -1) throw new RuntimeException("One or both nodes not found.");
        ArrayList<T> temp = new ArrayList<T>();
        for (int i = 0; i < this.matrix.get(index).size(); i++){
            if (matrix.get(index).get(i).size() != 0) temp.add(names.get(i));
        }
        return temp;
    }

    /** Gets list of all nodes in Graph.
     * @requires this != null
        @return a copy of names, the arraylist of all nodes in the graph
    */
    public ArrayList<T> getNodeList(){
        return new ArrayList<T>(names);
    }

    /**@requires this != null 
     * @return a String representing the graph.
        The returned string shows the adjacency matrix for the nodes
        Shows the edge label, and "m" if there is more than one. If there is nothing, show "x".
    */
    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < num_nodes; i++){
            for (int j = 0; j < num_nodes; j++){
                if (matrix.get(i).get(j).size() == 0) temp +=String.format("%-" + 8 + "s", "x");
                else if (matrix.get(i).get(j).size()>1) temp +=String.format("%-" + 8 + "s", "m");
                else temp += String.format("%-" + 8 + "s", matrix.get(i).get(j).get(0));
            }
            temp+="\n";
        }
        return temp;
    }

}
