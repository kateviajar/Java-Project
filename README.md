# Java-Project: Taiwanese dice game - "sip-pat-a"
## Project Introduction
This dice game based on a Taiwanese dice game called "sip-pat-a". This game requires 4 dice. 
The player places a bet, then rolls the dice. A score that the player obtained is to calculate the sum of two out of four dice based upon the game rules. 
Then, the banker rolls the dice, and get his score. The last step is to compare their scores to see who wins the game.

### Game Rules
- Rule 1: 
  If the faces of 4 dice are totally different, or three out of four dice have the same face, the player/ banker needs to roll again. 
  For example, {2,5,3,1} or {3,3,5,3}. 
  
- Rule 2: 
  If two dice have the same face, the others have different faces. The score is to count the sum of those two dice that have different faces. 
  For example, {1,3,5,1} the score is 3+5=8. 
  If two dice have the same face, the others have the same face. The score is to count the sum of the pair of dice with higher number. 
  For example, {2,2,5,5,} the score is 5+5=10. 
  If the player wins the game, the odds are 1/1. (You bet 10 dollars, then you will win 10 dollars.). 
  
- Rule 3: 
  If the faces of 4 dice are the same face, the player wins the game directly. The odds are 2/1. 
  (You bet 10 dollars, then you will win 20 dollars.) For example, {2,2,2,2}. 
  
## Project Structures
There are 3 classes in the project. 
  
### First Class
The first class called “**Dice**” contains two static methods. The method rollDice is to generate random numbers for 4 dice. 
The method judgeResult is to judge the 4 dice rolled by the method rollDice based upon the rules of the game. 
If the rule 1 is met, the method judgeResult returns 0 for indication. If the rule 2 is met, the method judgeResult calculates the score and returns it to caller. 
If the rule 3 is met, the method judgeResult returns 13 for indication.

### Second Class
The second class called “**Players**” holds the information of a player, such as name, balance. It contains two constructors, setters and getters. 
There are two more methods in this class called placeBet and updateBalance separately. 
The method placeBet will calculate the remaining balance after the player place a bet. 
The method updateBalance will update the balance after each run of the game is finished. 

### Third Class
The third class called “**RollingDiceGame**” contains the main method to execute the dice game. 
In this calss, it will ask the user to input name and balance for instantiate a player object. Then the user can place the amount of a bet for playing the game. 
If the user’s balance becomes zero, the game will be ended. Otherwise, the program will ask the user to choose continuing the game or ending the game. 




