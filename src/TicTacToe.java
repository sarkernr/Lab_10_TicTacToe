import java.util.Scanner;

public class TicTacToe {
    // Class level variables
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X"; // X will always move first

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            clearBoard();
            display();

            while (true) {
                int row = SafeInput.getRangedInt(scanner, "Enter the row (1-3): ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(scanner, "Enter the column (1-3): ", 1, 3) - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    if (isWin(currentPlayer)) {
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    } else if (isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        break;
                    }
                    // Switch players
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                    display();
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }

            // Prompt for playing again
            playAgain = SafeInput.getYNConfirm(scanner, "Do you want to play again? (y/n): ");
            currentPlayer = "X"; // Reset player to X for new game
        }
        scanner.close();
    }

    // sets all the board elements to a space
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    // shows the Tic Tac Toe game used as part of the prompt for the users move choice
    private static void display() {
        System.out.println("  1 2 3");
        for (int i = 0; i < ROW; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // returns true if there is a space at the given proposed move coordinates which means it is a legal move.
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    // checks to see if there is a win state on the current board for the specified player (X or O) This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }


    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }


    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}