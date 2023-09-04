/* 
Preston Johnson
Apr 13, 2023
1:25-2:15
Tic Tac Toe Programming Assignment
*/
import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {
   private static char[][] board = new char[3][3];

    //method to check if player won
    public static boolean checkWin(char player) {
        // Checks rows for win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Checks columns for win
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Check diagonals for win
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Method to print the board
    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " |");
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        char continuePlay = 'Y';
        char player = 'X';
        //Game loop
        while (continuePlay == 'Y') {
             // Initialize the board
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = '_';
                }
            }
            //Print out start of game
            System.out.println("Let's play Tic-Tac-Toe!");
            System.out.println("When prompted, enter desired row and column numbers");
            System.out.println("Example: 1 3\n");
            System.out.println("| _ | _ | X |");
            System.out.println("| _ | _ | _ |");
            System.out.println("| _ | _ | _ |\n");
            System.out.println("Let's play!");
            System.out.println("Player X starts!\n");
            printBoard();

            player = 'X';
            int row = -1;
            int col = -1;
            while (true) {
                System.out.println("Enter row and column for player " + player);
                //Checks for valid inputs
                while (true) {
                    try {
                        row = scnr.nextInt() - 1;
                        col = scnr.nextInt() - 1;
                        if (row < 3 && row > -1 && col > -1 && col < 3) {
                            break;
                        }
                        else {
                            while (row > 2 || col > 2) {
                                System.out.println("Please enter valid row and col numbers from 1 to 3:");
                                row = scnr.nextInt() - 1;
                                col = scnr.nextInt() - 1;
                            }
                            break;
                        }
                    }
                    catch (InputMismatchException e) {
                        scnr.next();
                        System.out.println("Please enter valid row and col numbers from 1 to 3:");
                    }
                }
                
                //Checks if square is empty
                if (board[row][col] != '_') {
                    System.out.println("That spot is full!");
                    continue;
                }
                
                //Puts the player into desired square               
                board[row][col] = player;
                
                //Prints the updated board
                printBoard();
                System.out.println("");
                
                //Checks if the player has won
                if (checkWin(player)) {
                    System.out.println("Player " + player + " WINS!\n");
                    System.out.println("Do you want to play again? Y or N");
                    continuePlay = scnr.next().charAt(0);
                    //Repeats scnr until valid input
                    while (continuePlay != 'Y' && continuePlay != 'N') {
                        System.out.println("Please enter valid input: Y or N\n");
                        System.out.println("Do you want to play again? Y or N");
                        continuePlay = scnr.next().charAt(0);
                    }
                    break;
                }
               
                //Checks if tie
                boolean isFull = true;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == '_') {
                            isFull = false;
                            break;
                        }
                    }
                    if (!isFull) {
                        break;
                    }
                }
                if (isFull) {
                    System.out.println("\nIt's a TIE!");
                    System.out.println("Do you want to play again? Y or N");
                    continuePlay = scnr.next().charAt(0);
                    while (continuePlay != 'Y' && continuePlay != 'N') {
                        System.out.println("Please enter valid input: Y or N\n");
                        System.out.println("Do you want to play again? Y or N");
                        continuePlay = scnr.next().charAt(0);
                    }
                    break;
                }
                // Switch to the other player
                player = (player == 'X') ? 'O' : 'X';
            }
        }
    }
}
