package stormTP;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import stormTP.core.Stream;
import stormTP.core.TortoiseManager;

import static javax.swing.UIManager.get;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

  
    
    /**
     * Test pour question 1
     */
    public void testTortoiseFilter()
    {
    	    	 assertEquals(1, 1);
    }
    
//    
//    /**
//     * Test1 pour question 2
//     */
    public void test1TortoisecomputeRank()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "{\"id\":1,\"top\":896,\"nom\":\""+nomsBinome+"\",\"position\":189,\"nbDevant\":6,\"nbDerriere\":3,\"total\":10}";
    	String output = "{\"id\":1,\"top\":896,\"nom\":\""+nomsBinome+"\",\"rang\":\"7\",\"nbTotal\":10}";

      	System.out.println("@Test test1TortoiseComputeRank()");

    	System.out.println("input: " +input);

    	String result = tm.computeRank(1, 896, nomsBinome, 6, 3, 10).getJSON_V2();


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }
//    
//    
//    /**
//     * Test2 pour question 2
//     */
    public void test2TortoisecomputeRank()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "{\"id\":3,\"top\":3,\"nom\":\""+nomsBinome+"\",\"position\":2,\"nbDevant\":1,\"nbDerriere\":4,\"total\":10}";
    	String output = "{\"id\":3,\"top\":3,\"nom\":\""+nomsBinome+"\",\"rang\":\"2ex\",\"nbTotal\":10}";

      	System.out.println("@Test test2TortoiseComputeRank()");

    	System.out.println("input: " +input);

    	String result = tm.computeRank(3, 3, nomsBinome, 1, 4, 10).getJSON_V2();


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }
//    
//    
//    
//    /**
//     * Test1 pour question 3
//     */
    public void test1TortoisecomputePoints()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "(\"4\", 10)";


      	System.out.println("@Test test1TortoiseComputePoints()");

    	System.out.println("input: " +input);

    	int output = tm.computePoints("4", 10);
    	int result = 6;


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }
//    
//    
//    
//    /**
//     * Test2 pour question 3
//     */
//
    public void test2TortoisecomputePoints()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "(\"2ex\", 10)";


      	System.out.println("@Test test1TortoiseComputePoints()");

    	System.out.println("input: " +input);

    	int output = tm.computePoints("2ex", 10);
    	int result = 8;


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }
//    
//    
//    /**
//     * Test pour question 4
//     */
//
    public void testTortoiseSpeed()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "(20, 35, 10, 17)";


      	System.out.println("@Test test1TortoiseComputeSpeed()");

    	System.out.println("input: " +input);

    	double output = tm.computeSpeed(20, 35, 10, 17);
    	double result = 0.46;


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }
//    
//    
//    
//    /**
//     * Test1 pour question 5
//     */

    public void test1TortoiseRankEvolution()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "2, 2";


      	System.out.println("@Test test1TortoiseRankEvolution("+ input +")");

    	System.out.println("input: " +input);

    	String output = tm.giveRankEvolution(2, 2);
    	String result = TortoiseManager.CONST;


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }

//    /**
//     * Test2 pour question 5
//     */
//
    public void test2TortoiseRankEvolution()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "1, 3";


      	System.out.println("@Test test2TortoiseRankEvolution("+ input +")");

    	System.out.println("input: " +input);

    	String output = tm.giveRankEvolution(1, 3);
    	String result = TortoiseManager.PROG;


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }
//    
//    /**
//     * Test3 pour question 5
//     */
//
    public void test3TortoiseRankEvolution()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String input = "6, 2";


      	System.out.println("@Test test3TortoiseRankEvolution("+ input +")");

    	System.out.println("input: " +input);

    	String output = tm.giveRankEvolution(6, 2);
    	String result = TortoiseManager.REGR;


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }

//    
//    
//    /**
//     * Test4 pour question 5
//     */
//
    public void testTortoiseAverageRank()
    {
    	int dossard = 1;
    	String nomsBinome = "Toto-Titi";

    	TortoiseManager tm = new TortoiseManager(dossard,nomsBinome);

    	String[] input = new String[6];
    	input[0] = "2";
    	input[1] = "1ex";
    	input[2] = "3";
    	input[3] = "4ex";
    	input[4] = "2ex";
    	input[5] = "1";


      	System.out.println("@Test testGiveAverageRank()");

    	System.out.println("input: " + get(input));

    	int output = tm.giveAverageRank(input);
    	int result = 2;


    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();

    	 assertEquals(result , output );
    }

//    
    private static String get(String[] t){
    	String res = " ";
    	for(int i = 0; i < t.length -1; i++){
    		res += t[i] + ", ";
    	}
    	res += t[t.length -1] + " ";
    	return res;
    }
//    
//    
//    /**
//     * Test pour partie 4
//     */
    public void testPodium()
    {

    	String input = "{ \"rabbits\":[ ";
    	input += "{\"id\":0,\"top\":896,\"nom\":\"RogerRabbit\",\"position\":194,\"nbDevant\":4,\"nbDerriere\":5,\"total\":10},";
    	input += "{\"id\":1,\"top\":896,\"nom\":\"BugsBunny\",\"position\":189,\"nbDevant\":6,\"nbDerriere\":3,\"total\":10},";
    	input += "{\"id\":2,\"top\":896,\"nom\":\"Panpan\",\"position\":199,\"nbDevant\":2,\"nbDerriere\":7,\"total\":10},";
    	input += "{\"id\":3,\"top\":896,\"nom\":\"Caerbannog\",\"position\":185,\"nbDevant\":8,\"nbDerriere\":1,\"total\":10},";
    	input += "{\"id\":4,\"top\":896,\"nom\":\"Oswald\",\"position\":192,\"nbDevant\":5,\"nbDerriere\":4,\"total\":10},";
    	input += "{\"id\":5,\"top\":896,\"nom\":\"Jojo\",\"position\":182,\"nbDevant\":9,\"nbDerriere\":0,\"total\":10},";
    	input += "{\"id\":6,\"top\":896,\"nom\":\"Coco\",\"position\":206,\"nbDevant\":1,\"nbDerriere\":8,\"total\":10},";
    	input += "{\"id\":7,\"top\":896,\"nom\":\"JuddyHopps\",\"position\":198,\"nbDevant\":3,\"nbDerriere\":6,\"total\":10},";
    	input += "{\"id\":8,\"top\":896,\"nom\":\"LapinBlanc\",\"position\":187,\"nbDevant\":7,\"nbDerriere\":2,\"total\":10},";
    	input += "{\"id\":9,\"top\":896,\"nom\":\"Basil\",\"position\":217,\"nbDevant\":0,\"nbDerriere\":9,\"total\":10}";
    	input += "] }";

    	String input2 = "{ \"rabbits\":[ ";
    	input2 += "{\"id\":0,\"top\":123,\"nom\":\"RogerRabbit\",\"position\":4,\"nbDevant\":8,\"nbDerriere\":1,\"total\":10},";
    	input2 += "{\"id\":1,\"top\":123,\"nom\":\"BugsBunny\",\"position\":11,\"nbDevant\":4,\"nbDerriere\":4,\"total\":10},";
    	input2 += "{\"id\":2,\"top\":123,\"nom\":\"Panpan\",\"position\":15,\"nbDevant\":1,\"nbDerriere\":7,\"total\":10},";
    	input2 += "{\"id\":3,\"top\":123,\"nom\":\"Caerbannog\",\"position\":5,\"nbDevant\":7,\"nbDerriere\":2,\"total\":10},";
    	input2 += "{\"id\":4,\"top\":123,\"nom\":\"Oswald\",\"position\":11,\"nbDevant\":4,\"nbDerriere\":4,\"total\":10},";
    	input2 += "{\"id\":5,\"top\":123,\"nom\":\"Jojo\",\"position\":14,\"nbDevant\":3,\"nbDerriere\":6,\"total\":10},";
    	input2 += "{\"id\":6,\"top\":123,\"nom\":\"Coco\",\"position\":48,\"nbDevant\":0,\"nbDerriere\":9,\"total\":10},";
    	input2 += "{\"id\":7,\"top\":123,\"nom\":\"JuddyHopps\",\"position\":8,\"nbDevant\":6,\"nbDerriere\":3,\"total\":10},";
    	input2 += "{\"id\":8,\"top\":123,\"nom\":\"LapinBlanc\",\"position\":15,\"nbDevant\":1,\"nbDerriere\":7,\"total\":10},";
    	input2 += "{\"id\":9,\"top\":123,\"nom\":\"Basil\",\"position\":1,\"nbDevant\":9,\"nbDerriere\":0,\"total\":10}";
    	input2 += "] }";

    	String output = "{\"top\":896,\"marcheP1\":[{\"nom\":\"Basil\"}],\"marcheP2\":[{\"nom\":\"Coco\"}],\"marcheP3\":[{\"nom\":\"Panpan\"}]}";
    	String output2 = "{\"top\":123,\"marcheP1\":[{\"nom\":\"Coco\"}],\"marcheP2\":[{\"nom\":\"LapinBlanc\"},{\"nom\":\"Panpan\"}],\"marcheP3\":[{\"nom\":\"Jojo\"}]}";

    	System.out.println("@Test testPodium()");

    	System.out.println("input: " +input);

    	String result = TortoiseManager.getPodium(input).replace(" ", "");
    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();
    	assertEquals(result , output );
        System.out.println("@Test2 testPodium()");
     	System.out.println("input: " +input2);
        String result2 = TortoiseManager.getPodium(input2).replace(" ", "");
     	System.out.println("output: " + output2);
     	System.out.println("result: " + result2);
     	System.out.println();
        assertEquals(result2 , output2 );
    }

//
//    
//    
//    
//    
}
