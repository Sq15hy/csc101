// DO NOT TOUCH THIS FILE!
package edu.nyu.cs.assignment3.tests;

import edu.nyu.cs.assignment3.Blackjack;

// import junit for testing framework
import java.util.Random;
import org.junit.Test;
import org.junit.ClassRule;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.SystemOutRule; // system rules lib - useful for capturing system output
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.util.ArrayList;
import java.util.Arrays;

//import gradescope tests
// import jh61b.grader.GradedTest;

public class BlackjackTest {

    long seed = 252352352;

    // @Rule
    // public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @ClassRule
    public static final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    @Test
    // @GradedTest(name="Test welcome", max_score=5)
    public void testWelcome() {
        // checks whether program behaves as expected
        systemInMock.provideLines("stand");
        try {
            // run the program and get the results
            TestOutput actual = TestOutput.getOne(1, systemOutRule);
            System.out.println();
            assertTrue("No welcome message in the first line," + 
                        "or the printout does not match the template.", 
                        actual.isWelcome);
        }
        catch (Exception e) {
            assertEquals("Program should not crash!", 
                        "Program crashed when testing for welcome message: " + e); 
                        // fail the test if any exception occurs
        }
    }

    @Test
    // @GradedTest(name="Test user cards displayed", max_score=10)
    public void testUserCardsDisplayed() {
        long testSeed;
        ArrayList<Integer> correctUserCards;
        // checks whether program behaves as expected
        for (int i=0; i<2; i++) {
            if (i == 0) {
                testSeed = 666;
                systemInMock.provideLines("stand");
                correctUserCards = new ArrayList<>(Arrays.asList(7, 6));
            } else {
                testSeed = 6666;
                correctUserCards = new ArrayList<>(Arrays.asList(10, 10, 8));
                systemInMock.provideLines("hit");
            }
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(testSeed, systemOutRule);
                System.out.println();
                assertTrue("No demonstration of user's cards, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            actual.usersCards.size() != 0);
                assertEquals("User's cards you print do not match the correct answer.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.", 
                            correctUserCards, actual.usersCards);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing for display of user's cards: " + e); 
                            // fail the test if any exception occurs
            }
        }
    }

    @Test
    // @GradedTest(name="Test dealers cards displayed", max_score=10)
    public void testDealersCardsDisplayed() {
        long testSeed;
        ArrayList<Integer> correctDealerCards;
        // checks whether program behaves as expected
        for (int i=0; i<2; i++) {
            if (i == 0) {
                testSeed = 666;
                systemInMock.provideLines("hit", "stand");
                correctDealerCards = new ArrayList<>(Arrays.asList(9, 5));
            } else {
                testSeed = 6666;
                correctDealerCards = new ArrayList<>(Arrays.asList(10, 5));
                systemInMock.provideLines("hit");
            }
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(testSeed, systemOutRule);
                System.out.println();
                assertTrue("No demonstration of dealer's cards, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            actual.dealersCards.size() != 0);
                assertEquals("Dealer's cards you print do not match the correct answer.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            correctDealerCards, actual.dealersCards);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing for display of user's cards: " + e); 
                            // fail the test if any exception occurs
            }
        }
    }


    @Test
    // @GradedTest(name="Test user win scenarios", max_score=20)
    public void testUserWinScenarios() {
        long testSeed;
        // checks whether program behaves as expected
        for (int i=0; i<2; i++) {
            if (i == 0) {
                testSeed = 99;
                systemInMock.provideLines("hit", "stand");
            }
            else {
                testSeed = 98;
                systemInMock.provideLines("hit", "hit", "hit", "stand");
            }
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(testSeed, systemOutRule);
                System.out.println();
                assertTrue("User should win but your program does not show that, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            actual.isUserWon);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing various user win scenarios: " + e); 
                            // fail the test if any exception occurs
            }
        }
    }

    @Test
    // @GradedTest(name="Test dealer win scenarios", max_score=20)
    public void testDealerWinScenarios() {
        long testSeed;
        // checks whether program behaves as expected
        for (int i=0; i<2; i++) {
            if (i == 0) {
                testSeed = 150;
                systemInMock.provideLines("hit","stand");
            } else {
                testSeed = 170;
                systemInMock.provideLines("stand");
            }
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(testSeed, systemOutRule);
                System.out.println();
                assertTrue("Dealer should win but your program does not show that, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            actual.isDealerWon);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing various dealer win scenarios: " + e); 
                            // fail the test if any exception occurs
            }
        }
    } 

    @Test
    // @GradedTest(name="Test tie scenarios", max_score=20)
    public void testTieScenarios() {
        // checks whether program behaves as expected
        long testSeed;
        for (int i = 0; i < 2; i++){
            if (i == 0) {
                testSeed = 95;
                systemInMock.provideLines("hit", "stand");  // a tie scenario
            } else {
                testSeed = seed;
                systemInMock.provideLines("stand");  // a tie scenario

            }
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(testSeed, systemOutRule);
                System.out.println();
                assertTrue("This is a tie but your program does not show that, " +  
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            actual.isTie);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing various tie scenarios: " + e); 
                            // fail the test if any exception occurs
            }
        }

    }    

    @Test
    // @GradedTest(name="Test dealer bust scenarios", max_score=20)
    public void testDealerBustScenarios() {
        long testSeed;
        // checks whether program behaves as expected
        for (int i=0; i<2; i++) {
            if (i == 0) {
                testSeed = 1000;
                systemInMock.provideLines("stand");
            }
            else{
                testSeed = 140;
                systemInMock.provideLines("hit", "stand");
            } 

            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(testSeed, systemOutRule);
                System.out.println();
                assertTrue("Dealer should bust but your program does not show that, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.", 
                            actual.isDealerBust);
                assertTrue("User should win but your program does not show that, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            actual.isUserWon);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing various dealer bust scenarios: " + e); 
                            // fail the test if any exception occurs
            }
        }
    }    

    @Test
    // @GradedTest(name="Test user bust scenarios", max_score=20)
    public void testUserBustScenarios() {
        long testSeed;
        // checks whether program behaves as expected
        for (int i=0; i<2; i++) {
            if (i == 0) {
                testSeed = 7567;    // user bust at the beginning
                systemInMock.provideLines("hit", "hit", "hit");
            } else {
                testSeed = 1000;
                systemInMock.provideLines("hit", "hit");    // user bust after two hits
            }
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(testSeed, systemOutRule);
                System.out.println();
                assertTrue("User should bust but your program does not show that, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.", 
                            actual.isUserBust);
                assertTrue("Dealer should win but your program does not show that, " + 
                            "or the printout does not match the template.\n" + 
                            "You may also fail this test if you do not strictly follow the instruction " +
                            "to create cards or actions of user or dealer.",  
                            actual.isDealerWon);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing various user bust scenarios: " + e); 
                            // fail the test if any exception occurs
            }
        }
    }    

    
    @Test
    // @GradedTest(name="Test for any outcome", max_score=75)
    public void testForAnyOutcome() {
        Random random = new Random(seed);
        // checks whether program behaves as expected
        for (int i=0; i<25; i++) {
            double rand = random.nextDouble();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(random.nextLong(), systemOutRule);
                System.out.println();
                
                // test the print of welcome message
                assertTrue("No welcome message in the first line," + 
                            "or the printout does not match the template.", 
                            actual.isWelcome);                    
                

                // test cards display
                assertTrue("No demonstration of user's cards, " + 
                            "or the printout does not match the template.", 
                            actual.usersCards.size() != 0);

                assertTrue("No demonstration of dealer's cards, " + 
                            "or the printout does not match the template.", 
                            actual.dealersCards.size() != 0);

                // only one result
                assertFalse("Your program shows two winners, " + 
                            "or shows a winner and a tie at the same time.", 
                            actual.isUserWon && actual.isDealerWon || 
                            actual.isUserWon && actual.isTie || 
                            actual.isDealerWon && actual.isTie);

                // test for no outcome
                assertTrue("No demonstration of the result, i.e. someone win or it's a tie", 
                            actual.isUserWon || actual.isDealerWon || actual.isTie);
              
                /* test for user bust scenerios */
                // test false negatives
                assertFalse("Your program does not show the user is bust even when the card total is " 
                            + actual.userTotal + ", which is bigger than 21", 
                            actual.userTotal > 21 && !actual.isUserBust);
            
                // test for false positive
                assertFalse("Your program shows the user is bust even when the card total is " 
                            + actual.userTotal + ", which is smaller than 21", 
                            actual.userTotal <= 21 && actual.isUserBust);

                /* test for dealer bust scenerios */
                // test false negatives
                assertFalse("Your program does not show the dealer is bust even when the card total is " 
                            + actual.dealerTotal + ", which is bigger than 21", 
                            actual.dealerTotal > 21 && !actual.isDealerBust);

                // test for false positive
                assertFalse("Your program shows the dealer is bust even when the card total is " 
                            + actual.dealerTotal + ", which is smaller than 21", 
                            actual.dealerTotal <= 21 && actual.isDealerBust);

                /* test for dealer win scenerios */
                // test for false positive
                assertFalse("Your program shows the dealer wins despite the dealer has " + actual.dealerTotal + 
                            " while the user has " + actual.userTotal + 
                            " and the user busts=" + actual.isUserBust + ".", 
                            actual.isDealerWon && !((actual.dealerTotal > actual.userTotal) || actual.isUserBust));
       
                // test for false negative
                assertFalse("Your program does not show the dealer wins despite the dealer has " + actual.dealerTotal + 
                            " while the user has " + actual.userTotal + 
                            " and the dealer busts=" + actual.isUserBust + ".", 
                            !(actual.dealerTotal > 21) && !actual.isDealerWon && 
                            ((actual.dealerTotal > actual.userTotal) || actual.isUserBust));

                /* test for user win scenerios */
                // test for false positive
                assertFalse("Your program shows the user wins despite the user has " + actual.userTotal + 
                            " while the dealer has " + actual.dealerTotal + 
                            " and the dealer busts=" + actual.isDealerBust + ".", 
                            actual.isUserWon && !((actual.userTotal > actual.dealerTotal) || actual.isDealerBust));
          
                // test for false negative
                assertFalse("Your program does not show the user wins despite the user has " + actual.userTotal + 
                            " while the dealer has " + actual.dealerTotal + 
                            " and the dealer busts=" + actual.isDealerBust + ".", 
                            !(actual.userTotal > 21) && !actual.isUserWon && 
                            ((actual.userTotal > actual.dealerTotal) || actual.isDealerBust));

                /* test for tie scenerios */
                // false positive tie
                assertFalse("Your program shows a tie even though it shows the dealer scored " + actual.dealerTotal + 
                            " while the user scored " + actual.userTotal, 
                            actual.isTie && !(actual.dealerTotal == actual.userTotal));

                // false negative tie
                assertFalse("Your program does not announce a tie, even when the dealer's total is " + actual.dealerTotal + 
                            " and the user's is " + actual.userTotal, 
                            !actual.isTie && actual.userTotal == actual.dealerTotal);
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", 
                            "Program crashed when testing for either a win of any kind or a tie: " + e); 
                            // fail the test if any exception occurs
            }
        }
    }

} // Test class

// convenience class
class TestOutput {
    public TestOutput(String output) {
            output = output.trim(); // remove any leading/trailing whitespace
            this.lines = output.split("\n"); // split program output by line break
            this.usersCards = new ArrayList<Integer>();
            this.dealersCards = new ArrayList<Integer>();
            this.isWelcome = false;
            this.isUserBust = false;
            this.isDealerBust = false;
            this.isUserWon = false;
            this.isDealerWon = false;
            this.isTie = false;
            
            /* check for presence/absence of specific messages */ 
            // check welcome massage
            int size = lines.length;
            if (size >= 1 && lines[0].trim().startsWith("Welcome to Blackjack!")) {
                this.isWelcome = true;
            }
            // check the last line
            if (size >= 1 && lines[size - 1].startsWith("Dealer wins!")) {
                this.isDealerWon = true;
            }
            if (size >= 1 && lines[size - 1].startsWith("You win!")) {
                isUserWon = true;
            }
            if (size >= 1 && lines[size - 1].startsWith("Tie!")) {
                this.isTie = true;
            }

            // check whether user busts
            for (String line : lines) {
                line = line.trim();
                // check user's cards
                if (line.startsWith("Your cards are: ")) {
                    // get the numbers out of the line
                    this.usersCards = TestOutput.getNumbers(lines[size - 3]);
                }
                // check dealer's cards
                if (line.startsWith("The dealer's cards are: ")) {
                    // get the numbers out of the line
                    this.dealersCards = TestOutput.getNumbers(lines[size - 2]);
                }
                // check whether dealer busts
                if (line.startsWith("The dealer has bust!")) {
                    this.isDealerBust = true;
                }
                if (line.startsWith("You have bust!")) {
                    this.isUserBust = true;
                }
            } // for each line

            this.dealerTotal = TestOutput.getTotal(dealersCards);
            this.userTotal = TestOutput.getTotal(usersCards);
    }

    public static ArrayList<Integer> getNumbers(String line) {
        String[] words = line.split("[ ,]+"); // split by space or comma
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i=0; i<words.length; i++) {
            words[i] = words[i].replaceAll("[^0-9]", ""); // extract just the number from string
            if (words[i].length() > 0) {
                nums.add(Integer.parseInt(words[i])); // add to array list
            }
        }
        return nums;
    }

    public static int getTotal(ArrayList<Integer> nums) {
        int total = 0;
        for (int i=0; i<nums.size(); i++) {
            total += nums.get(i);
        }        
        return total;
    }

    public static TestOutput getOne(long seed, SystemOutRule systemOutRule) throws Exception {
        String[] args = {Long.toString(seed)};
        try {
            systemOutRule.clearLog();
            systemOutRule.enableLog(); // start capturing System.out
            Blackjack.main(args);
            String output = systemOutRule.getLogWithNormalizedLineSeparator().trim();
            TestOutput actual = new TestOutput(output);
            return actual;
        }
        catch (Exception e) {
            throw e;
        }

    }

    public String[] lines;
    public ArrayList<Integer> usersCards = new ArrayList<Integer>();
    public ArrayList<Integer> dealersCards = new ArrayList<Integer>();
    public boolean isWelcome = false;
    public boolean isUserBust = false;
    public boolean isDealerBust = false;
    public boolean isUserWon = false;
    public boolean isDealerWon = false;
    public boolean isTie = false;
    public int dealerTotal = 0;
    public int userTotal = 0;
}
