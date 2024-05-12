package hw4;
import java.util.*;

/** Graph represents a mutable graph of nodes with directed edges.*/
public class Graph1<T,E> {

    //private ArrayList<ArrayList<ArrayList<E>>> matrix;
    private HashMap<T,HashMap<T,ArrayList<E>>> adjList;
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
    //
    //  
    //   In other words,
    //     * There is always 0 or more nodes and edges (non negative)
    //     * There are no duplicates in names (nodes)
    //     * There are no duplicates edges (edges with same parent, same child, same label)
    // (A representation invariant tells us something that is true for all valid instances of a Graph)


    /** @effects Constructs a new Graph with zero nodes and zero edges.*/
    public Graph1() {
        adjList = new HashMap<>();
        names = new ArrayList<>();
        hashNames = new HashMap<>(); 
        num_edges = 0;
        num_nodes = 0;

        checkRep();
    }

    /** @param another another Graph to be copied.
        @requires another != null and this != null
        @effects Sets this new Graph's attributes to the same ones as another Graph's. Includes number of nodes, number of edges, adjacency matrix, name of nodes,hash map of nodes.
    */
    public Graph1(Graph1<T,E> another) {

        this.num_edges = another.num_edges;
        this.num_nodes = another.num_nodes;
        
        this.names = new ArrayList<>(another.names);
        this.adjList = new HashMap<>(another.adjList);
        this.hashNames = new HashMap<>(another.hashNames);

        checkRep();
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

        HashMap<T,ArrayList<E>> temp = new HashMap<>();
        
        adjList.put(s,temp);
        
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

        if (!adjList.get(from).containsKey(to)){
            ArrayList<E> a = new ArrayList<>();
            adjList.get(from).put(to, a);
            num_edges++;
        }
        else{

            if (adjList.get(from).get(to).contains(label)){
                System.out.println("This shouldn't show up 6 times."); 
                return;}
            
            else adjList.get(from).get(to).add(label);
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
        return new ArrayList<>(adjList.get(from).get(to));
        
    }

    /** Sets edge label.
     * @requires this != null
        @param from The node which the edge stems from
        @param to The node which the edge points to
        @param value The edge that will replace the set of edges
        @throws RuntimeException if from or to don't exist as nodes in the list of labels
        @modifies clears the set of edges and adds value as the singular edge
    */
    public void setEdgeLabel(T from, T to, E value){
        int x = findNode(from);
        int y = findNode(to);
        
        if (!adjList.get(from).containsKey(to)){
            ArrayList<E> a = new ArrayList<>();
            adjList.get(from).put(to, a);
        } 
        if (x == -1 || y == -1) throw new RuntimeException("One or both nodes not found");
        else{
       
            num_edges-=adjList.get(x).get(y).size()-1; //problem line

            adjList.get(x).get(y).clear();
            adjList.get(x).get(y).add(value);
        }
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
        for (Map.Entry<T,ArrayList<E>> node : adjList.get(s).entrySet()){
            temp.add(node.getKey());
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

}
