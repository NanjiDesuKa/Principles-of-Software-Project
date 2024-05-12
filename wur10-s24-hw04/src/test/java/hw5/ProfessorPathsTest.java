package hw5;

//import static org.junit.jupiter.api.Assertions.*;
// org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import java.util.*;

public final class ProfessorPathsTest {

    @Test
    public void small_test(){

        String file = "data/small.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Kim Johnson", "Dana Cook");

        assert (answer.equals("path from Kim Johnson to Dana Cook:\nno path found\n"));

    }

    @Test
    public void large_test(){

        String file = "data/large.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Jennifer Cohen", "Amy Perez");

        assert (answer.equals("path from Jennifer Cohen to Amy Perez:\nJennifer Cohen to Kayla Wright via PSYC-8966\nKayla Wright to Ann Newman via ECON-9226\nAnn Newman to Christian Walker via BIOL-1791\nChristian Walker to Amy Perez via STSO-4652\n"));

    }

    @Test
    public void medium_test(){

        String file = "data/medium.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("John Spears", "Traci Morales");

        assert (answer.equals("path from John Spears to Traci Morales:\nJohn Spears to Adam Carlson via MATH-3254\nAdam Carlson to Gerald Estrada via ARCH-6521\nGerald Estrada to Kevin Bond via STSO-5189\nKevin Bond to Kayla Wilson via LANG-4682\nKayla Wilson to Jeremy Shepherd via ECSE-3617\nJeremy Shepherd to Traci Morales via MATH-3935\n"));
    }

    @Test
    public void George_test(){
        String file = "data/very_small.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("George", "Eric");
        assert (answer.equals("path from George to Eric:\nGeorge to Eric via ARCH-1100\n"));
        //adjacent node test

        answer = x.findPath("George", "Joe");
        assert (answer.equals("path from George to Joe:\nGeorge to Eric via ARCH-1100\nEric to Joe via CSCI-2100\n"));
        //length two test

        answer = x.findPath("George", "David");
        assert (answer.equals("path from George to David:\nGeorge to Eric via ARCH-1100\nEric to Joe via CSCI-2100\nJoe to David via LANG-2100\n"));
        //length three test

        answer = x.findPath("George", "Yuri");
        assert (answer.equals("path from George to Yuri:\nGeorge to Eric via ARCH-1100\nEric to Joe via CSCI-2100\nJoe to Yuri via MANE-9000\n"));
        //length three test

        answer = x.findPath("George", "Yiru");
        assert (answer.equals("unknown professor Yiru\n"));
        //unknown professor test

        answer = x.findPath("George", "Sam");
        assert (answer.equals("path from George to Sam:\nGeorge to Eric via ARCH-1100\nEric to Sam via CSCI-2100\n"));

        //all of these test the wiping of 

    }

    @Test
    public void Travis_test(){
        String file = "data/very_small.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer;

        answer = x.findPath("Travis", "Joe");
        assert (answer.equals("path from Travis to Joe:\nTravis to David via LANG-3100\nDavid to Joe via LANG-2100\n"));

        answer = x.findPath("Travis", "Eric");
        assert (answer.equals("path from Travis to Eric:\nTravis to David via LANG-3100\nDavid to Joe via LANG-2100\nJoe to Eric via CSCI-2100\n"));

        answer = x.findPath("Travis", "George");

        //assorted path tests




        assert (answer.equals("path from Travis to George:\nTravis to David via LANG-3100\nDavid to Joe via LANG-2100\nJoe to Eric via CSCI-2100\nEric to George via ARCH-1100\n"));
        //single long path test
    }

    @Test
    public void test1(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Konstantin Kuzmin","Shianne M. Hulbert");

        assert (answer.equals("path from Konstantin Kuzmin to Shianne M. Hulbert:\nKonstantin Kuzmin to Shianne M. Hulbert via CSCI-1100\n"));
        //adjacent node test
    }

    @Test
    public void test2(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Mohammed J. Zaki", "Wilfredo Colon");

        assert (answer.equals("path from Mohammed J. Zaki to Wilfredo Colon:\nMohammed J. Zaki to David Eric Goldschmidt via CSCI-2300\nDavid Eric Goldschmidt to Michael Joseph Conroy via CSCI-4430\nMichael Joseph Conroy to Alan R Cutler via CHEM-1200\nAlan R Cutler to Wilfredo Colon via CHEM-1100\n"));
        //given test case
    }

    @Test
    public void test3(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Willy Wazoo","Malik Magdon-Ismail");

        assert (answer.equals("unknown professor Willy Wazoo\n"));
        //unknown professor test

    }

    @Test
    public void test4(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("David Eric Goldschmidt","Hugh Johnson");

        assert (answer.equals("path from David Eric Goldschmidt to Hugh Johnson:\nno path found\n"));
        //existing professor nonexistent path test

    }

    @Test
    public void test5(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Willy Wazoo","Waldo");

        assert (answer.equals("unknown professor Willy Wazoo\nunknown professor Waldo\n"));
        //two unknown professors test

    }

    @Test
    public void test6(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Billur Aksoy","Billur Aksoy");

        assert (answer.equals("path from Billur Aksoy to Billur Aksoy:\n"));
        //professor to self test

    }

    @Test
    public void test7(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Willy Wazoo","Willy Wazoo");

        assert (answer.equals("unknown professor Willy Wazoo\n"));
        //unknown professor to self test

    }

    @Test
    public void test8(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);
        String answer = x.findPath("Achille Messac","David Eric Goldschmidt");
        assert (answer.equals("path from Achille Messac to David Eric Goldschmidt:\nAchille Messac to Michael Joseph Conroy via ENGR-2050\nMichael Joseph Conroy to David Eric Goldschmidt via CSCI-4430\n"));
        //testing sorting by lexicographically least
    }

    @Test
    public void test9(){
        String file =  "data/courses.csv";
        ProfessorPaths x = new ProfessorPaths();
        x.createNewGraph(file);
        String answer = x.findPath("Konstantin Kuzmin","David Eric Goldschmidt");
        assert (answer.equals("path from Konstantin Kuzmin to David Eric Goldschmidt:\nKonstantin Kuzmin to David Eric Goldschmidt via CSCI-1100\n"));
        //adjacent node test
    }
    
}