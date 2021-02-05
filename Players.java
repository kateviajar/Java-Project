/** Application Purpose: This class will hold the information of players (name and balance), also have methods to update balance.
 *  Author: Pao-Hua Chien
 *  Date: 28th Nov. 2020
 *  Time: 9:00pm
 */
public class Players
{
    //Create instance variables
    private String name;
    private double balance; //remaining money

    //create a default constructor
    public Players()
    {

    }

    //create a constructor that has 2 arguments
    public Players(String name, double balance)
    {
        this.name = name;
        if (balance > 0.0)
        {
            this.balance = balance;
        }
    }

    //Create setters for each variable
    public void setName(String name)
    {
        this.name = name;
    }
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    //Create getters for each variable
    public String getName()
    {
        return name;
    }
    public double getBalance()
    {
        return balance;
    }

    //create a method to update balance after placing a bet
    public void placeBet(double bet)
    {
        balance = balance - bet; //the updated balance after placing a bet
    }

    //create a method to update balance after finishing the dice game
    public void updateBalance(double bet, int odds)
    {
        balance = balance + bet*(double) odds; //updated balance after the game finished
    }

}
