package hw6;
import java.util.*;
import hw5.ProfessorParser;
import hw4.Graph;

public class LegoPaths{

    private Map<String, Set<String>> sets;
    private Set<String> parts;
    private Graph<String,Double> graph;
    private String file_name;

     /**Graph Creation Method
     * @modifies sets. Sets sets to a new Hash map if uninitialized.  Else clear any pre-existing data. 
     * @effects Then populates with csv part and their sets data using readData();
     * @midifies parts. Sets parts to a new Hash set if uninitialized. Else clear any pre-existing data. 
     * @effects Then populates with csv part data using readData();
     * @modifies graph. Sets graph to new Graph to erase pre-existing graph data. 
     * @modifies file_name. If it is null or not the previous file name, set file_name to the String parameter.
     * Then goes through each element in classes map in order to create nodes and edges for parts that share the same sets.
     * The graph is of type String T and Double E, where T is the name of the parts, and E is the weight between two parts.
     * Does nothing if file_name matches the String parameter. This is to prevent needless use of resources in building the same graph.
     * @param file the name of the file to read data from.
     */
    public void createNewGraph(String filename){

        if (file_name!=null && file_name.equals(filename)) return;

        if (sets == null) sets = new HashMap<String, Set<String>>();
        else sets.clear();

		if (parts == null) parts = new HashSet<String>();
        else parts.clear();

        file_name = filename;

        //avoid creating new HashSet each time to save memory

        try{
            ProfessorParser.readData(filename, sets, parts);
        }
        
        catch (Exception e){
            e.printStackTrace();
        }

        if (graph!=null) graph.clear();

        graph = new Graph<String,Double>();

        HashSet<String> skip = new HashSet<>(); //list of nodes at current map that has had their edges updated and can be skipped

        for (Map.Entry<String,Set<String>> entry : sets.entrySet()){

            skip = new HashSet<>();
            //skip.clear();
            
            for (String first : entry.getValue()){

                graph.addNode(first);

                if (graph.getEdgeLabel(first, first).size() == 0) graph.addEdge(first, first, 0.0);
                //initialize node pointing to itself to 0.0
                
                for (String second : entry.getValue()){
    
                    
                    graph.addNode(second);

                    if (skip.contains(second)) continue;
                    //skip if edges for first and second nodes have already been set

                    if (first.equals(second)) continue;
                    //avoid changing 0.0 weight of node pointing to itself

                    ArrayList<Double> edge = graph.getEdgeLabel(first, second);

                    if (edge.size() == 0){
                        graph.addEdge(first, second,1.0);
                        graph.addEdge(second, first,1.0);
                    } //if no weight exists, set to 1.0
                    
                    else{
                        graph.setEdgeLabel(first, second, 1.0 / (1.0/(edge.get(0))+ 1.0));
                        graph.setEdgeLabel(second, first, 1.0 / (1.0/(graph.getEdgeLabel(second, first).get(0))+ 1.0) );
                    } 
                    //if a previous weight exists, modify by finding the reciprocal of the weight, adding 1, then finding the reciprocal of the sum
                    
                }
                skip.add(first);    
            }
        }
        //skip.clear();
        skip = new HashSet<>();
    }




     /**Find Cheapest Path Method 
     * @param p1 First part. The starting part.
     * @param p2 Second part. The part that you want to end up at.
     * @return a String answer. answer contains the greedy cheapest by weight path from part p1 to part p2.
     * answer  may be "no path found" to indicate a lack of path between p1 and p2.
     * If no part(s) exists, return those cases in answer instead of a path.
    */
    public String findPath(String p1, String p2){

        boolean p1_exist = parts.contains(p1);
        boolean p2_exist = parts.contains(p2);

        if (!p1_exist && p1.equals(p2)) return "unknown part " + p1 + "\n";
        if (!p1_exist && !p2_exist) return "unknown part " + p1 + "\nunknown part " + p2 + "\n";
        if (!p1_exist) return "unknown part " + p1 + "\n";
        if (!p2_exist) return "unknown part " + p2 + "\n";
        
        String answer = "path from " + p1 + " to " + p2 + ":\n";

        if (p1.equals(p2)){
            answer += "total cost: 0.000\n";
            return answer;
        } 

        return answer + DFS(p1,p2);
    }



    /**Modified Depth-First Search
     * @param p1 First part. The starting part.
     * @param p2 Second part. The part you want to end up at.
     * @return a String answer. answer contains the cheapest Djikstra's weight path from professor p1 to p2.
     * If no path exists, answer is set to "no path found".
     */
    public String DFS(String p1, String p2){

        PriorityQueue<HashMap<Double, ArrayList<String>>> activePaths = new PriorityQueue<>(Comparator.comparingDouble(m -> m.keySet().iterator().next()));
        //priority queue of maps. Each map stores a single element, storing the total weight as the key and the path as the value.

        Set<String> visited = new HashSet<>(); //list of nodes already traveled
        
        String answer  = "";
        String minDest = "";
        Double key     = 0.0;

        boolean path_found = false;

        ArrayList<String> temp = new ArrayList<>();
        temp.add(p1);
        HashMap<Double,ArrayList<String>> map = new HashMap<>();
        map.put(0.0,temp);
        activePaths.add(map);

        HashMap<Double,ArrayList<String>> minPath = new HashMap<Double, ArrayList<String>>();

        while (!activePaths.isEmpty()){

            minPath = activePaths.poll();
            //remove the cheapest path (sorted by double value)

            for (Map.Entry<Double, ArrayList<String>> entry : minPath.entrySet()) {
                
                key = entry.getKey();
                minDest = entry.getValue().get(entry.getValue().size()-1);
                break; // Exit loop after first entry

            }/*retrieve the destination of the minimum path stored in the map of size 1*/

            if (minDest.equals(p2)){ 
                path_found = true;
                break;
            }/*path has been found, break out of loop */

            if (visited.contains(minDest)) continue;
            //next node in current min path has already been traversed

            ArrayList<String> children = graph.getChildren(minDest);
            ArrayList<String> newPath  = new ArrayList<String>(minPath.get(key));
            ArrayList<String> tempPath;
            

            for (int i = 0; i < children.size(); i++ ){
                if (!visited.contains(children.get(i))){

                    tempPath = new ArrayList<String>(newPath);
                    tempPath.add(children.get(i));
                    double total = key + graph.getEdgeLabel(minDest,children.get(i)).get(0);
                    
                    HashMap<Double,ArrayList<String>> tempMap = new HashMap<>();

                    tempMap.put(total,tempPath);
                    activePaths.add(tempMap);

                }
            }
            visited.add(minDest);
        }

        if (!path_found){
            return "no path found\n";
        } 

        for (int i = 0; i < minPath.get(key).size()-1; i++){
            
            double edge = graph.getEdgeLabel(minPath.get(key).get(i), minPath.get(key).get(i+1)).get(0);
            
            answer += minPath.get(key).get(i) + " to " + minPath.get(key).get(i+1)+" with weight "+ String.format("%.3f",edge) + "\n";

        } 

        if (path_found) answer+="total cost: " + String.format("%.3f",key) + "\n";

        return answer;

    }
    
}