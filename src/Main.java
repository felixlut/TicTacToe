public class Main {

    static void testFunction() {
        System.out.println("Hello!");
    }

    public static void main(String[] args) {

        // 1 Board
        int height = 3;
        int width = 3;

        // 2 players
        char player1 = 'X';
        char player2 = 'O';

        testFunction();

        // The actual game
        TicTacToe ticTacToe = new TicTacToe(player1, player2, height, width);

        boolean again = ticTacToe.playTicTacToe();
        while (again) {
            again = ticTacToe.playTicTacToe();
        }

    }
}
