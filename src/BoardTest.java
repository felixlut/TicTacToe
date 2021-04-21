import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void boardIsFull() {
        Board board = new Board(3, 3);
        char mark = 'X';

        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                board.setMark(i, j, mark);
            }
        }

        boolean boardFull = board.boardIsFull();

        assertTrue(boardFull);
    }

    @Test
    void horizontal_winner() {
        Board board = new Board(3, 3);
        char mark = 'X';

        board.setMark(0, 0, mark);
        board.setMark(0, 1, mark);
        board.setMark(0, 2, mark);
        boolean xWin = board.winner(mark);

        assertTrue(xWin);
    }

    @Test
    void vertical_winner() {
        Board board = new Board(3, 3);
        char mark = 'X';

        board.setMark(0, 0, mark);
        board.setMark(1, 0, mark);
        board.setMark(2, 0, mark);
        boolean xWin = board.winner(mark);

        assertTrue(xWin);
    }

    @Test
    void diagonal_winner() {
        Board board = new Board(3, 3);
        char mark = 'X';

        board.setMark(0, 0, mark);
        board.setMark(1, 1, mark);
        board.setMark(2, 2, mark);
        boolean xWin = board.winner(mark);

        assertTrue(xWin);
    }
}