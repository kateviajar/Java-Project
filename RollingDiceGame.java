/** Application Purpose:The user can input his information and place a bet to play the dice game.
 *  Author: Pao-Hua Chien
 *  Date: 29th Nov. 2020
 *  Time: 12:20pm
 */
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//Declare a class called RollingDiceGame
public class RollingDiceGame
{
    public static void main(String [] args)
    {
        //Ask the user to input first name and last name
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first name.");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your Last name.");
        String lastName = scanner.nextLine();
        //concatenate them into a full name
        String name = firstName.concat(" ").concat(lastName);

        //Ask the user to input balance that should greater than zero
        double balance = 0.0;
        boolean continueInputBalance = true; //check whether the input balance is valid
        //if the input balance is less than or equal to zero, ask the user to try again
        while (continueInputBalance)
        {

            try
            {
                System.out.println("Please enter your balance. (The balance entered should greater than zero)");
                balance = scanner.nextDouble();
                if (balance > 0.0) //input balance should greater than zero
                {
                    continueInputBalance = false; //input balance is valid, stop looping
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(e.getMessage());
                scanner.nextLine(); //discard input, let the user to try again
                System.out.println("The input is invalid. Please try again.");
            }
        }
        System.out.println();//to next line

        //instantiate a Players object based on the information given by the user
        Players player = new Players(name, balance);

        boolean continuePlay = true;//to indicate whether the user wants to play again

        //Play the first run, then ask the user to choose to continue or to end the game.
        do
        {
            //Ask the user to place a bet
            double bet = 0.0;
            Scanner input = new Scanner(System.in);
            boolean continueInput = true; //check whether the input is successful
            System.out.println("Please enter the amount of bet you want to place:");

            //check whether the input is valid, if not, ask the user to input again
            do
            {
                try
                {
                    bet = input.nextDouble(); //get the bet input

                    //if the bet is between 0 - balance, it's a valid input
                    if (bet > 0.0 && bet <= player.getBalance())
                    {
                        System.out.println("The amount of bet you placed is " + bet);
                        continueInput = false; //The input is successful, stop looping
                    }
                    else
                    {
                        System.out.printf("The input is invalid. Range: 0 < bet <= %.2f%n", player.getBalance());
                        System.out.println("Please enter again.");
                    }
                }
                catch(InputMismatchException e) //if the input is not a number, such as string
                {
                    System.out.println(e.getMessage());
                    input.nextLine(); //discard input, let the user to try again
                    System.out.println("The input is invalid! Please enter a number again.");
                }
            }while (continueInput);

            //Display the current balance after placing a bet
            player.placeBet(bet);
            System.out.println("Current balance: " + player.getBalance());
            System.out.println("---------------------------------------------");

            //The player rolls the dice
            boolean continueLoopOfPlayer = true; //check whether the player rolls dice successfully
            boolean continueLoopOfBanker = true; //check whether the banker rolls dice successfully
            int resultOfPlayer = 0; //store the sum of dice for the player
            int resultOfBanker = 0; //store the sum of dice for the banker
            int odds = 0; // for calculating the winning bet

            System.out.printf("It is the player's turn to roll the dice.%n%n");
            do
            {
                //call the method rollDice
                int [] frequency = Dice.rollDice();
                System.out.println( "Frequency of six faces: " + Arrays.toString(frequency));

                //call the method judgeResult
                resultOfPlayer = Dice.judgeResult(frequency);

                if (resultOfPlayer == 0)
                {
                    System.out.println("Faces of 4 dice are different, or 3 out of 4 dice have the same face.");
                    System.out.println("Dice need to be rolled again.");
                }
                else if (resultOfPlayer == 13)
                {
                    System.out.println("Four dice have the same face!");
                    System.out.println("You win the game!"); //the player win the game directly
                    odds = 3;
                    continueLoopOfPlayer = false; //Successful rolling, stop looping
                }
                else if (2 <= resultOfPlayer && resultOfPlayer <= 12)
                {
                    continueLoopOfPlayer = false; //Successful rolling, stop looping
                }

                System.out.println();

            }while (continueLoopOfPlayer);


            System.out.printf("The player %s obtained the total number: %d%n", player.getName(), resultOfPlayer);
            System.out.println("---------------------------------------------");

            //if 2<= resultOfPlayer <=12, it's banker's turn to roll the dice
            System.out.printf("It is the banker's turn to roll the dice.%n%n");
            if (2 <= resultOfPlayer && resultOfPlayer <= 12)
            {
                do
                {
                    //call the method rollDice
                    int[] frequency = Dice.rollDice();

                    //call the method judgeResult
                    resultOfBanker = Dice.judgeResult(frequency);

                    if (2 <= resultOfBanker && resultOfBanker <= 12)
                    {
                        continueLoopOfBanker = false;
                    }
                }while (continueLoopOfBanker);
            }

            System.out.printf("%nThe banker obtained the total number: %d%n", resultOfBanker);
            System.out.println("---------------------------------------------");

            //compare resultOfPlayer with resultOfBanker
            //The player wins the game
            if (resultOfPlayer > resultOfBanker)
            {
                odds = 2;
                System.out.println("You win the game.");
            }
            //The game is a draw
            else if (resultOfPlayer == resultOfBanker)
            {
                odds = 1;
                System.out.println("The game is a draw.");
            }
            else //The player loses the game
            {
                odds =0;
                System.out.println("You lose the game.");
            }

            //Display the updated balance after the game finished
            player.updateBalance(bet, odds);
            System.out.printf("The latest balance of %s is %.2f%n", player.getName(), player.getBalance());

            //if the latest balance is zero, then end the game
            if (player.getBalance() == 0.0)
            {
                continuePlay = false;
                System.out.println("Your balance is zero, the game is ended!");
            }
            //if the user's balance is greater than zero
            else if (player.getBalance() > 0.0)
            {
                //Ask the user to choose to continue the game or to end the game
                System.out.printf("%nPlease choose one:%n");
                System.out.println("1 -- Continue to play the game.");
                System.out.println("2 -- End the game.");

                int option = input.nextInt(); //get the choice from the user
                switch (option)
                {
                    case 1:
                        continuePlay = true;
                        System.out.println("Let's play again.");
                        break;
                    case 2:
                        continuePlay = false;
                        System.out.println("The game is ended.");
                        break;
                    default:
                        continuePlay = false;
                        System.out.println("You didn't choose to continue the game. The game is ended.");
                }
            }

        }while(continuePlay);
    }
}
