package hw4;

import java.util.*;

public class GraphWrapper{
    private Graph<String,String> graph;

    public GraphWrapper(){
        graph = new Graph<String,String>();
    }   

    public void addNode(String nodeData){
        graph.addNode(nodeData);
    }

    public void addEdge(String parentNode, String childNode, String edgeLabel){
        graph.addEdge(parentNode, childNode, edgeLabel);
    }

    public Iterator<String> listNodes(){
        ArrayList<String> temp = graph.getNodeList();
        Collections.sort(temp);
        Iterator<String> x = temp.iterator();
        return x;
    }

    public Iterator<String> listChildren(String parentNode){
        ArrayList<String> children = graph.getChildren(parentNode);
        ArrayList<String> x = new ArrayList<String>();
        Collections.sort(children);
        for (int i = 0; i < children.size(); i ++){
            for (int j = 0; j < graph.getEdgeLabel(parentNode, children.get(i)).size(); j++){
                x.add(children.get(i)+"("+graph.getEdgeLabel(parentNode, children.get(i)).get(j)+")");
            }
        }
        Iterator<String> i = x.iterator();
        return i;
    }
}