package hw5;
import java.util.*;
import hw4.Graph;

/*represents a mutable data parser that find the lexicographically cheapest route from professor to professor via common classes. */
public class ProfessorPaths{

    private Map<String, Set<String>> classes; 
    private Set<String> profs;
    private Graph<String,String> graph;


    /**Graph Creation Method
     * @modifies classes. Sets classes to a new Hash map if uninitialized.  Else clear any pre-existing data. 
     * @effects Then populates with csv professor and their classes data using readData();
     * @midifies profs. Sets profs to a new Hash set if uninitialized. Else clear any pre-existing data. 
     * @effects Then populates with csv professor data using readData();
     * @modifies graph. Sets graph to new Graph to erase pre-existing graph data.
     * The graph is of type string T and string E, where T is name of the professors, and E is the name of the classes 
     * Then goes through each element in classes map in order to create nodes and edges for professors that share the same classes.  
     * @param file the name of the file to read data from.
     */
    public void createNewGraph(String file){
        
        if (classes == null) classes = new HashMap<String, Set<String>>();
        else classes.clear();

		if (profs == null) profs = new HashSet<String>();
        else profs.clear();

        //done this way to save on memory

        try{
            ProfessorParser.readData(file, classes, profs);
        }
        
        catch (Exception e){
            e.printStackTrace();
        }

        graph = new Graph<String,String>();

        for (Map.Entry<String,Set<String>> entry : classes.entrySet()){
            
            for (String first : entry.getValue()){
                
                for (String second : entry.getValue()){
    
                    if (first.equals(second)) continue;
                    
                    graph.addNode(first);
                    graph.addNode(second);

                    graph.addEdge(first, second, entry.getKey());
                    graph.addEdge(second, first, entry.getKey());
                }    
            }
        }

    }

    /**Find Shortest Path Method 
     * @param p1 First professor. The starting professor.
     * @param p2 Second professor. The professor that you want to end up at.
     * @return a String answer. answer contains the lexicographically least shortest path from professor p1 to professor p2.
     * answer  may be "no path found" to indicate a lack of path between p1 and p2.
     * If no professor(s) exists, return those cases in answer instead of a path.
    */
    public String findPath(String p1, String p2){

        boolean p1_exist = profs.contains(p1);
        boolean p2_exist = profs.contains(p2);

        if (!p1_exist && p1.equals(p2)) return "unknown professor " + p1 + "\n";
        if (!p1_exist && !p2_exist) return "unknown professor " + p1 + "\nunknown professor " + p2 + "\n";
        if (!p1_exist) return "unknown professor " + p1 + "\n";
        if (!p2_exist) return "unknown professor " + p2 + "\n";
        
        String answer = "path from " + p1 + " to " + p2 + ":\n";

        if (p1.equals(p2)) return answer;

        return answer + BFS(p1,p2);
    }


    /**Modified Breadth-First Search
     * @param p1 First professor. The starting professor.
     * @param p2 Second professor. The professor you want to end up at.
     * @return a String answer. answer contains the lexicographically least shortest path from professor p1 to p2.
     * If no path exists, answer is set to "no path found".
     */
    private String BFS (String p1, String p2){

        LinkedList<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        ArrayList<ArrayList<String>> pathlist = new ArrayList<>();
        ArrayList<String> children;

        queue.add(p1);
        visited.add(p1);

        try {
            children = graph.getChildren(p1); //arraylist of children
        }
        catch(Exception e){
            children = new ArrayList<>();
            queue.poll();
        }

            for (int i = 0; i < children.size(); i ++){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(p1);
                pathlist.add(temp);
            }/*initialization of pathlist*/

        while (!queue.isEmpty()){
            
            String parent = queue.poll();//dequeue

            if (parent.equals(p2)) break; //we've reached our destination

            children = graph.getChildren(parent);
            Collections.sort(children); //sort the children lexicographically

            for (int i = pathlist.size()-1; i >= 0; i--){

                if (pathlist.get(i).getLast().equals(parent)){ //find corresponding path that has parent

                    ArrayList<String> tempCopy = pathlist.get(i); //copy the parent to continue the path

                    for (int j = 0; j < children.size(); j++){

                        if (visited.contains(children.get(j))) continue; //skip if node is already in queue

                        ArrayList<String> temp = new ArrayList<>(tempCopy);
                        temp.add(children.get(j));
                        pathlist.add(temp);

                        visited.add(children.get(j));
                        queue.add(children.get(j));

                    }
                }
            }
        }
        
        for (int i = pathlist.size()-1; i >= 0 ;i--){
            try{if (
                graph.getEdgeLabel(pathlist.get(i).getLast(), p2).size()!=0) pathlist.get(i).add(p2);
                else pathlist.remove(i);
            }
            catch(Exception e){
                pathlist.clear();
            }
        }/*add destination node if the last node connects with an edge. Else, remove path. */

        ArrayList<String> temp = new ArrayList<>();

        if (pathlist.size()!=0){
            temp = pathlist.get(0);

            //god drinks java
        }

        String answer;
        
        if (pathlist.size() == 0)  answer = "no path found\n";
        else answer = "";

        for (int i = 0; i < temp.size()-1; i++){
            
            ArrayList<String> edges = graph.getEdgeLabel(temp.get(i), temp.get(i+1));
            String edge = Collections.min(edges);
            answer += temp.get(i) + " to " + temp.get(i+1)+" via "+ edge + "\n";

        } 
        //Get the edges and pick the lexi minimum        

        return answer;
        
        //then return the selected path as a single string.
    }


}