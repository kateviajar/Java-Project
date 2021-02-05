/** Application Purpose: Create a class to contain two methods, rollDice and judgeResult.
 *  Author: Pao-Hua Chien
 *  Date: 15th Dec. 2020
 *  Time: 10:00pm
 */

import java.util.Arrays;
import java.util.Random;

//Declare a class called Dice
public class Dice
{

    static final int NUMBER_OF_DICE = 4; //the game needs 4 dice

    //Use a frequency array to count what face of dice is occurred
    //Define the sorted frequency array that has 5 rows and 7 columns for rules 1-3 judgement
    //row0 is for the rule1 that faces of 4 dice are totally different
    //row1 is for the rule1 that three out of four dice have the same face
    //row2 is for the rule2 that there are two pair of dice
    //row3 is for the rule2 that two dice have the same face, the others have different faces
    //row4 is for the rule3 that faces of 4 dice are the same
    static int[][] frequencyOfRule = {{0,0,0,1,1,1,1}, {0,0,0,0,0,1,3}, {0,0,0,0,0,2,2}, {0,0,0,0,1,1,2}, {0,0,0,0,0,0,4}};

    //Create a method for rolling dice
    public static int[] rollDice()
    {
        int[] rollingDice = new int[NUMBER_OF_DICE]; //for storing the result of rolling dice
        int[] frequency = new int[7]; // dice face frequency counters(index 0 will be ignored)
        //instantiate a random object
        Random random = new Random();

        for (int i = 0; i < rollingDice.length; i++)
        {
            //generate a random number between 1 - 6, and store it to the array
            rollingDice[i] = random.nextInt(6)+1;

            //count the frequency of faces
            ++frequency[rollingDice[i]];
        }
        //display the result of rolling dice
        System.out.println("The faces of 4 dice are: " + Arrays.toString(rollingDice));

        return frequency;
    }

    //create a method for judging the result after rolling dice
    public static int judgeResult(int[] frequency)
    {
        int resultOfRolling = 0; //for storing the score of rolling dice
        //check the faces after rolling
        int[] sortFrequency = frequency.clone(); //copy the frequency array to sortFrequency array
        Arrays.sort(sortFrequency); //sort the frequency array

        //compare sortFrequency array with rule1 - rule3 frequencyOfRule array
        //row0 is for the rule1 that faces of 4 dice are totally different
        //row1 is for the rule1 that three out of four dice have the same face
        if(Arrays.equals(sortFrequency, frequencyOfRule[0]) || Arrays.equals(sortFrequency, frequencyOfRule[1]))
        {
            resultOfRolling = 0;
        }

        //row2 is for the rule2 that there are two pair of dice
        else if (Arrays.equals(sortFrequency, frequencyOfRule[2]))
        {
            //calculate the value by finding the index of int 2 from frequency array
            int indexOfTwo = 0;
            for (int i = 0; i < frequency.length; i++)
            {
                if (frequency[i] == 2)
                {
                    indexOfTwo = i; //find the biggest index that has frequency 2
                }
            }
            resultOfRolling = 2*indexOfTwo; //calculate the sum of the pair dice that has the bigger number
        }

        //row3 is for the rule2 that two dice have the same face, the others have different faces
        else if (Arrays.equals(sortFrequency, frequencyOfRule[3]))
        {
            //calculate the value by finding the index of int 1 from frequency array
            for (int i = 0; i < frequency.length; i++)
            {
                if (frequency[i] == 1)
                {
                    resultOfRolling = resultOfRolling + i; //calculate the sum of the rest two dice
                }
            }
        }

        //row4 is for the rule3 that faces of 4 dice are the same
        else if (Arrays.equals(sortFrequency, frequencyOfRule[4]))
        {
            resultOfRolling =13;
        }

        return resultOfRolling;
    }
}
