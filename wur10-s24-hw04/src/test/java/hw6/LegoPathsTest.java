package hw6;
import org.junit.jupiter.api.Test;
//import java.util.*;
//import hw4.Graph;

public class LegoPathsTest{

    @Test
    public void MyTest(){

        String file = "data/medium_professor.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Jessica Allen","Natasha Bryant");
        //System.out.println(answer);
        assert (answer != null);

        
    }

    @Test
    public void MyTest3(){

        String file = "data/medium_professor.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Alexander Moody","Terrance Kim");
        //System.out.println(answer);
        assert (answer.equals("path from Alexander Moody to Terrance Kim:\nAlexander Moody to Carol Hines with weight 1.000\nCarol Hines to Mr. Matthew Johnson DDS with weight 0.333\nMr. Matthew Johnson DDS to Terrance Kim with weight 0.500\ntotal cost: 1.833\n"));

        
    }


    @Test
    public void MyTest2(){

        String file = "data/medium_professor.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Andre Gilmore","Ian Crosby");
        //System.out.println(answer);
        assert (answer.equals("path from Andre Gilmore to Ian Crosby:\nAndre Gilmore to Brian Johnson with weight 0.333\nBrian Johnson to Cheryl Jones with weight 0.333\nCheryl Jones to Brian Davidson with weight 0.333\nBrian Davidson to Ian Crosby with weight 0.500\ntotal cost: 1.500\n"));

    }
    
    @Test
    public void test1(){/*modify this later */

        String file = "data/lego1990.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("31367 Green Duplo Egg Base to 98138pr0080 Pearl Gold Tile Round 1 x 1 with Blue", "Yellow and Black Minecraft Print");

        assert (answer != null);

        //assert (answer.equals("path from 31367 Green Duplo Egg Base to 98138pr0080 Pearl Gold Tile Round 1 x 1 with Blue, Yellow and Black Minecraft Print:\n31367 Green Duplo Egg Base to 3437 Green Duplo Brick 2 x 2 with weight 1.000\n3437 Green Duplo Brick 2 x 2 to 3437 Red Duplo Brick 2 x 2 with weight 0.003\n3437 Red Duplo Brick 2 x 2 to 41250 Blue Ball, Hard Plastic, 51mm (approx. 6 studs diameter) with weight 0.053\n41250 Blue Ball, Hard Plastic, 51mm (approx. 6 studs diameter) to 2780 Black Technic Pin with Friction Ridges Lengthwise and Center Slots with weight 0.100\n2780 Black Technic Pin with Friction Ridges Lengthwise and Center Slots to 98138pr0080 Pearl Gold Tile Round 1 x 1 with Blue, Yellow and Black Minecraft Print with weight 1.000\ntotal cost: 2.156\n"));

    }/*potentially disable this test */

    @Test
    public void test2(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Testing","testing2");
        //System.out.println(answer);

        assert (answer.equals("unknown part Testing\nunknown part testing2\n"));
    }

    @Test
    public void test3(){
        String file = "data/lego1960.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("3005 White Brick 1 x 1","3007 Blue Brick 2 x 8");
        assert (answer.equals("path from 3005 White Brick 1 x 1 to 3007 Blue Brick 2 x 8:\n3005 White Brick 1 x 1 to 3007 Blue Brick 2 x 8 with weight 0.111\ntotal cost: 0.111\n"));
        //System.out.println(answer);
    }

    @Test
    public void test4(){
        String file = "data/lego1960.csv";
        LegoPaths x = new LegoPaths();

        x.createNewGraph(file);

        String result = x.findPath("257pr0001 Yellow HO Scale Bedford Moving Van - Side Indicators - LEGO Transport in White", "257pr0003 Yellow HO Scale Bedford Moving Van - Front Indicators - LEGO Transport in Gold");
        //System.out.println(result);
        assert (result.equals("path from 257pr0001 Yellow HO Scale Bedford Moving Van - Side Indicators - LEGO Transport in White to 257pr0003 Yellow HO Scale Bedford Moving Van - Front Indicators - LEGO Transport in Gold:\nno path found\n"));
    }

    @Test
    public void testUnknown(){
        String file = "data/lego1960.csv";
        LegoPaths x = new LegoPaths();

        x.createNewGraph(file);

        String result = x.findPath("DamDadiDoo", "257pr0003 Yellow HO Scale Bedford Moving Van - Front Indicators - LEGO Transport in Gold");
        //System.out.println(result);
        assert (result.equals("unknown part DamDadiDoo\n"));
    }

    @Test
    public void testUnknown2(){
        String file = "data/lego1960.csv";
        LegoPaths x = new LegoPaths();

        x.createNewGraph(file);

        String result = x.findPath("257pr0003 Yellow HO Scale Bedford Moving Van - Front Indicators - LEGO Transport in Gold","DamDadiDoo");
        //System.out.println(result);
        assert (result.equals("unknown part DamDadiDoo\n"));
    }

    @Test
    public void test5(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("3001a White Brick 2 x 4 without Cross Supports","3002a White Brick 2 x 3 without Cross Supports");
        assert (answer != null);
        //System.out.println(answer);
    }

    @Test
    public void test6(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("3001a White Brick 2 x 4 without Cross Supports","3002a White Brick 2 x 3 without Cross Supports");
        assert (answer != null);
        //System.out.println(answer);
    }

    @Test
    public void ChrisMTest1(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("14pr0005 White Road Sign Round with No Overtaking Print","3001mib Blue Minitalia Brick 2 x 4 with Bottom Tubes");
        assert (answer.equals("path from 14pr0005 White Road Sign Round with No Overtaking Print to 3001mib Blue Minitalia Brick 2 x 4 with Bottom Tubes:\n14pr0005 White Road Sign Round with No Overtaking Print to 739pr0001 White Road Sign Octagon with Stop Sign Print with weight 1.000\n739pr0001 White Road Sign Octagon with Stop Sign Print to 3004 Red Brick 1 x 2 with weight 0.125\n3004 Red Brick 1 x 2 to 3001a Red Brick 2 x 4 without Cross Supports with weight 0.007\n3001a Red Brick 2 x 4 without Cross Supports to 3011 Red Duplo Brick 2 x 4 with weight 0.200\n3011 Red Duplo Brick 2 x 4 to upn0371 White String Cord Thick with weight 0.100\nupn0371 White String Cord Thick to 3001mib Blue Minitalia Brick 2 x 4 with Bottom Tubes with weight 1.000\ntotal cost: 2.432\n"));
        //System.out.println(answer);
    }

    @Test
    public void ChrisMTest2(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("3007b Red Brick 2 x 8 without Bottom Tubes","3005pr9926 White Brick 1 x 1 with Blue 'O' Print (Bold Font)");
        assert (answer.equals("path from 3007b Red Brick 2 x 8 without Bottom Tubes to 3005pr9926 White Brick 1 x 1 with Blue 'O' Print (Bold Font):\nno path found\n"));
        //System.out.println(answer);
    }

    @Test
    public void ChrisMTest3(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("3001 White Brick 2 x 4","3404ac01 Red Turntable 4 x 4 - Old Type Complete - Faceted");
        assert (answer.equals("path from 3001 White Brick 2 x 4 to 3404ac01 Red Turntable 4 x 4 - Old Type Complete - Faceted:\n3001 White Brick 2 x 4 to 3004 White Brick 1 x 2 with weight 0.125\n3004 White Brick 1 x 2 to 3004 Red Brick 1 x 2 with weight 0.009\n3004 Red Brick 1 x 2 to 3020 Red Plate 2 x 4 with weight 0.013\n3020 Red Plate 2 x 4 to 3404ac01 Red Turntable 4 x 4 - Old Type Complete - Faceted with weight 0.083\ntotal cost: 0.231\n"));
        //System.out.println(answer);
    }

    @Test
    public void ChrisMTest4(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("1043Apr0043 Modulex Black Modulex Tile 3 x 4  with White 'U' print, without Internal Supports","3185 Green Fence 1 x 4 x 2");
        assert (answer.equals("path from 1043Apr0043 Modulex Black Modulex Tile 3 x 4  with White 'U' print, without Internal Supports to 3185 Green Fence 1 x 4 x 2:\nno path found\n"));
        //System.out.println(answer);
    }

    @Test
    public void ChrisMTest5(){
        String file = "data/lego1980.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("3659 Red Brick Arch 1 x 4","upn0225 Black Homemaker Hair Short");
        assert (answer.equals("path from 3659 Red Brick Arch 1 x 4 to upn0225 Black Homemaker Hair Short:\n3659 Red Brick Arch 1 x 4 to 3005 Red Brick 1 x 1 with weight 0.091\n3005 Red Brick 1 x 1 to 3004 Red Brick 1 x 2 with weight 0.007\n3004 Red Brick 1 x 2 to 3003a Yellow Brick 2 x 2 without Inside Ridges with weight 0.011\n3003a Yellow Brick 2 x 2 without Inside Ridges to upn0225 Black Homemaker Hair Short with weight 0.250\ntotal cost: 0.359\n"));
        //System.out.println(answer);
    }

    @Test
    public void smallTest(){
        String file = "data/very_small.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);

        String answer = x.findPath("Yuri","George");
        assert (answer.equals("path from Yuri to George:\nYuri to Joe with weight 1.000\nJoe to Eric with weight 1.000\nEric to George with weight 1.000\ntotal cost: 3.000\n"));
        //System.out.println(answer);
    }

    /*    
    public static void main(String[] arg) {

        long startTime = System.nanoTime();
        
        String file =  "data/lego1960.csv";
        LegoPaths x = new LegoPaths();
        x.createNewGraph(file);
        x.createNewGraph(file);
        x.createNewGraph(file);
        x.createNewGraph(file);
        x.createNewGraph(file);
        x.createNewGraph(file);
        x.createNewGraph(file);
        x.createNewGraph(file);
        
        String answer; 
        //answer = x.findPath("3065 Yellow Brick 1 x 2 without Bottom Tube","776pr0012 White Flag on Flagpole with Portugal Print");
        answer = x.findPath("702 White Brick 4 x 4 Corner","702 Red Brick 4 x 4 Corner");
        
        //answer = x.findPath("3005 White Brick 1 x 1","3007 Blue Brick 2 x 8");
        //answer = x.findPath("14pr0005 White Road Sign Round with No Overtaking Print","3001mib Blue Minitalia Brick 2 x 4 with Bottom Tubes");
        //answer = x.findPath("1083 Modulex Tile Gray Modulex Tile 3 x 8 without Internal Supports","3007 Blue Brick 2 x 8");

        System.out.println(answer);


        long endTime = System.nanoTime();
        long durationInNanoseconds = endTime - startTime;
        double durationInSeconds = durationInNanoseconds / 1_000_000_000.0; // Convert to seconds
        System.out.println("Execution time: " + durationInSeconds + " seconds");
		
	}*/

}