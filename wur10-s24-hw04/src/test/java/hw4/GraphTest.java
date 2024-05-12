package hw4;
import org.junit.jupiter.api.Test;
import java.util.*;

public final class GraphTest {

    


    ///////////////////CONSTRUCTOR TESTS//////////////////
    @Test
    public void noArgument(){
        Graph<String,String> g = new Graph<String,String>();
        assert (g.getNumEdges() == 0);
        assert (g.getNumNodes() == 0);
    }

    //copy constructor test
    @Test
    public void copyTest1(){
        Graph<String,String> a = new Graph<String,String>();
        Graph<String,String> b = new Graph<String,String>(a);
        assert (a != b);
        assert (a.getNumEdges() == b.getNumEdges());
        assert (a.getNumNodes() == b.getNumNodes());
    }

    @Test
    public void copyTest2(){

        Graph<String,String> a = new Graph<String,String>();
        a.addNode("a");
        a.addNode("b");
        a.addEdge("a", "b", "1");
        
        Graph<String,String> b = new Graph<String,String>(a);
        
        assert (a != b);
        assert (a.getNumEdges() == b.getNumEdges());
        assert (a.getNumNodes() == b.getNumNodes());
    }


    ////////SETTER TEST/////////

    @Test
    public void setEdgeTest(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("1");
        a.addNode("2");
        a.addEdge("1", "2", "A");
        a.addEdge("1", "2", "B");
        a.addEdge("1", "2", "C");

        assert (a.getNumEdges() == 3);

        a.setEdgeLabel("1", "2", "alone");

        assert (a.getNumEdges() == 1);
    }


    ////////////////GETTER TESTS////////////////////
    @Test
    public void getNumNodesTest1(){
        Graph<String,String> a = new Graph<String,String>();
        assert (a.getNumNodes() == 0);
    }

    @Test
    public void getNumNodesTest2(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");

        assert (a.getNumNodes() == 3);
    }

    @Test
    public void getNumNodesTest3(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addNode("C");
        a.addNode("C");
        a.addNode("C");
        a.addNode("C");
        a.addNode("C");

        assert (a.getNumNodes() == 3);
    }

    @Test
    public void getNumEdgesTest1(){
        Graph<String,String> a = new Graph<String,String>();
        assert (a.getNumEdges() == 0);
    }

    @Test
    public void getNumEdgesTest2(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addEdge("A", "B","1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        assert (a.getNumEdges() == 3);
    }

    @Test
    public void getNumEdgesTest3(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        
        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");
    
        assert (a.getNumEdges() == 3);
    }

    @Test
    public void getNumEdgesTest4(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");
        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        assert (a.getNumEdges() == 3);
    }

    @Test
    public void getNumEdgesTest5(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");
        a.addEdge("A", "B", "2");
        a.addEdge("B", "A", "2");
        a.addEdge("A", "C", "2");

        assert (a.getNumEdges() == 6);
    }

    @Test
    public void no_node(){
        Graph<String,String> a = new Graph<String,String>();

        assert (a.findNode("A") ==- 1);
        assert (a.findNode("B") == -1);
        assert (a.findNode("C") == -1);
    }

    @Test
    public void found_the_node(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addEdge("A", "B", "1");

        assert (a.findNode("A") == 0);
        assert (a.findNode("B") == 1);
        assert (a.findNode("C") == 2);
    }

    @Test
    public void found_no_node(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addEdge("A", "B", "1");

        assert (a.findNode("A") == 0);
        assert (a.findNode("B") == 1);
        assert (a.findNode("C") == 2);
        assert (a.findNode("D") == -1);
    }

    @Test
    public void getLabelTest(){

        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addEdge("A", "B","1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        assert (a.getEdgeLabel("A", "B").contains("1"));
        assert (a.getEdgeLabel("B", "B").size() == 0);
        assert (a.getEdgeLabel("A", "C").contains("1"));

        assert (a.getNumEdges() == 3);

        a.addEdge("A", "C", "2");

        assert (a.getNumEdges() == 4);

        assert (a.getEdgeLabel("A", "C").contains("2"));
        assert (a.getEdgeLabel("A","C").contains("1"));

    }

    @Test
    public void getChildrenTest(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addNode("D");
        a.addEdge("A", "B", "1");
        a.addEdge("A", "C", "1");
        a.addEdge("A", "D", "1");

        ArrayList<String> siblings = a.getChildren("A");

        assert (siblings.get(0) == "B");
        assert (siblings.get(1) == "C");
        assert (siblings.get(2) == "D");
    }

    @Test
    public void noChildrenTest(){
        
        Graph<String,String> a = new Graph<String,String>();
        
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addNode("D");

        a.addEdge("A","A","12");
        a.addEdge("A","B","12");
        a.addEdge("A","C","12");
        a.addEdge("A","D","12");
        a.addEdge("B","A","12");
        a.addEdge("B","B","12");
        a.addEdge("B","C","12");
        a.addEdge("B","D","12");
        a.addEdge("C","A","12");
        a.addEdge("C","B","12");
        a.addEdge("C","C","12");
        a.addEdge("C","D","12");

        ArrayList<String> no_child = a.getChildren("D");

        assert (no_child.size() == 0);
    
    }

    @Test
    public void OrphanTest(){
        Graph<String,String> orphanage = new Graph<String,String>();
        orphanage.addNode("A");
        orphanage.addNode("B");
        orphanage.addNode("C");
        orphanage.addNode("D");
        orphanage.addEdge("A", "B", "1");
        orphanage.addEdge("A", "C", "1");
        orphanage.addEdge("A", "D", "1");

        ArrayList<String> siblings = orphanage.getChildren("A");

        assert (siblings.get(0) == "B");
        assert (siblings.get(1) == "C");
        assert (siblings.get(2) == "D");

        assert (orphanage.getNumEdges() == 3);

        orphanage.addEdge("A","B", "");
        orphanage.addEdge("A","C", "");
        orphanage.addEdge("A","D", "");

        assert (orphanage.getNumEdges() == 6);

        siblings = orphanage.getChildren("A");

        assert (siblings.size() == 3);

    }





    ////////////////MISCELLANEOUS TESTS//////////////////////

    @Test
    public void EdgeReflexiveCheck(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");
        a.addEdge("A", "A", "1");

        assert (a.getNumEdges() == 4);
    }

    @Test
    public void noEdgeTest(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addNode("D");
        a.addNode("E");
        a.addNode("F");
        a.addNode("G");
        a.addNode("H");
        a.addNode("I");

        ArrayList<String> no_child = a.getChildren("A");
        assert (a.getNumEdges() == 0);
        assert (a.getNumNodes() == 9);
        assert (no_child.size() == 0);
    
    }

    @Test
    public void ToString1NodeTest(){

        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");

        String temp = a.toString();


        assert (temp.equals("x       \n"));
    }

    @Test
    public void ToStringTest(){

        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        a.addNode("D");

        String temp = a.toString();

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                assert (a.getEdgeLabel("A", "B").size() == 0);
            }
        }
        assert (temp.equals("x       x       x       x       \nx       x       x       x       \nx       x       x       x       \nx       x       x       x       \n") );
    }


    @Test
    public void ToStringMTest(){

        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");

        a.addEdge("A", "B", "1");
        a.addEdge("A", "B", "2");
        a.addEdge("A", "A", "1");
        a.addEdge("A", "A", "2");
        
        a.addEdge("B", "A", "1");
        a.addEdge("B", "A", "2");
        a.addEdge("B", "B", "1");
        a.addEdge("B", "B", "2");

        String temp = a.toString();

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                assert (a.getEdgeLabel("A", "B").contains("1"));
                assert (a.getEdgeLabel("A", "B").contains("2"));
            }
        }
        assert (temp.equals("m       m       \nm       m       \n"));
    }

    ///////////////////// new functions/////////////////////

    @Test
    public void clearTest(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        
        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        a.clear();
    
        assert (a.getNumEdges() == 0);
    }

    @Test
    public void nullSetLabelTest(){
        Graph<String,String> a = new Graph<String,String>();
        a.addNode("A");
        a.addNode("B");
        a.addNode("C");
        
        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        a.addEdge("A", "B", "1");
        a.addEdge("B", "A", "1");
        a.addEdge("A", "C", "1");

        a.setEdgeLabel("A", "A", null);
    
        assert (a.getChildren("A").size() == 0);
    }

}