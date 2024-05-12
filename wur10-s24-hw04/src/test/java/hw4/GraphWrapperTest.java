package hw4;

import org.junit.jupiter.api.Test;
import java.util.*;

public final class GraphWrapperTest{
    @Test
    public void noArgumentNullTest(){
        GraphWrapper g = new GraphWrapper();
        assert (g != null);
    }

    @Test
    public void AcquireChildTest(){

        GraphWrapper w = new GraphWrapper();
        w.addNode("A");
        w.addNode("B");
        w.addEdge("A", "B", "2");
        Iterator<String> i = w.listChildren("A");

        assert ((i).next().equals("B(2)"));
    }

    @Test

    public void ReflexiveTest(){
        GraphWrapper w = new GraphWrapper();
        w.addNode("A");
        w.addNode("B");
        w.addEdge("A", "A", "1");

        assert (w.listChildren("A").next().equals("A(1)"));
    }
}