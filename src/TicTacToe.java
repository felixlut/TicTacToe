import java.util.Scanner;

public class TicTacToe {

    // For the game logic
    private final Board board;
    private final char player1;
    private final char player2;

    // For user input
    private final Scanner scanner;

    public TicTacToe(char player1, char player2, int height, int width) {
        board = new Board(height, width);
        this.player1 = player1;
        this.player2 = player2;

        scanner = new Scanner(System.in);
    }

    /**
     * Deal with the pre- and post logic of the game
     * This entails starting the game, asing for rematch, etc
     * @return whether or not to play again
     */
    public boolean playTicTacToe() {
        System.out.println("Welcome to TicTacToe, lets play!");
        System.out.println("X will begin, pick a slot by typing the x and y coordinates of the slot one at a time!");

        char markInTurn = player1;
        boolean winner = false;
        while(!board.boardIsFull() && !winner) {
            // Print the board
            System.out.println(board);

            // Declare who's turn it is
            System.out.println(markInTurn + ":s time to play!");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            // Failsafe for invalid coordinates
            while (!board.setMark(x, y, markInTurn)) {
                System.out.println("That slot is either taken or (" + x + ", " + y + ") isn't a valid coordinate");
                System.out.println("Please try again");
                x = scanner.nextInt();
                y = scanner.nextInt();
            }

            // If the mark lead to a victory, the game is over!
            if (board.winner(markInTurn)) {
                winner = true;
            } else {
                // Otherwise, flip the player in turn
                if (markInTurn == player1) {
                    markInTurn = player2;
                } else {
                    markInTurn = player1;
                }
            }
        }
        // Print the final board
        System.out.println(board);

        // Reset the board in case someone want to play again
        board.resetBoard();

        // Check if there is a winner, or if there is a tie
        if (winner) {
            System.out.println("Congratulations to " + markInTurn + "!");
        } else {
            System.out.println("It's a tie!");
        }

        // Check if the player's want to play again
        System.out.println("Type 1 to play again");
        int next = scanner.nextInt();
        return next == 1;
    }
}
