public class Board {

    // Final, since we don't ever want an existing board to change size
    private final char[][] grid;
    private final int height;
    private final int width;

    /**
     * Constructor for the board
     * @param height: The height of the board
     * @param width: The width of the board
     */
    public Board(int height, int width) {
        // Set the size of the board
        this.height = height;
        this.width = width;
        grid = new char[height][width];

        // Add a ' ' to mark the slot as empty
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                getGrid()[i][j] = ' ';
            }
        }
    }

    /**
     * Empty the board of marks to be able to play again
     */
    public void resetBoard(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    /**
     * Set the desired slot to the mark
     * @param x: The x coordinate
     * @param y: The y coordinate
     * @param mark: The mark to set
     * @return if the mark was set successfully
     */
    public boolean setMark(int x, int y, char mark) {
        // Check the size of the board
        if (x < 0 | x >= height
                | y < 0 | y >= width) {
            return false;
        } else if (getMark(x, y) != ' ')
            // Check if the slot is already marked
            return false;
        else {
            // Otherwise we can set it
            grid[x][y] = mark;
            return true;
        }
    }

    /**
     * Iterate over the board and check if every slot is filled
     * @return if the board is full or not
     */
    public boolean boardIsFull() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (getMark(i, j) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if mark is 3 in a row anywhere on the board (horizontal, vertical and diagonal)
     * @param mark: The mark
     * @return True if there is a 3 in a row for the mark, False otherwise
     */
    public boolean winner(char mark) {
        return horizontalWinner(mark) | verticalWinner(mark) | diagonalWinner(mark);
    }

    /**
     * Helper function to winner(), this will perform all horizontal checks
     * @param mark: The mark to check the win for
     * @return if there is 3 mark in a horizontal row
     */
    private boolean horizontalWinner(char mark) {
        for (int i = 0; i < height; i++) {
            int count = 0;
            for (int j = 0; j < width; j++) {
                if (getMark(i, j) == mark) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    /**
     * Helper function to winner(), this will perform all vertical checks
     * @param mark: The mark to check the win for
     * @return if there is 3 mark in a vertical row
     */
    private boolean verticalWinner(char mark) {
        for (int i = 0; i < width; i++) {
            int count = 0;
            for (int j = 0; j < height; j++) {
                if (getMark(j, i) == mark) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    /**
     * Warning: BAD SOLUTION HERE, WONT WORK AT ALL IF THE SIZE OF THE BOARD CHANGES
     * Helper function to winner(), this will perform all diagonal checks
     * @param mark: The mark to check the win for
     * @return if there is 3 mark in a diagonal
     */
    private boolean diagonalWinner(char mark) {
        // Top left to bottom right
        int count = 0;
        for (int i = 0; i < height; i++) {
            if (getMark(i, i) == mark) {
                count++;
                if (count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Top right to bottom left
        count = 0;
        for (int i = 0; i < height; i++) {
            if (getMark(i, width - i - 1) == mark) {
                count++;
                if (count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }

    public char getMark(int x, int y) {
        return getGrid()[x][y];
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        // We want the general form of the print to look something like this:
        // -------
        // |X|O|X|
        // -------
        // |O|X|O|
        // -------
        // |X|O|X|
        // -------

        // This will be our line separator, ------- with size dependant of the board size
        String lineSeparator = "-".repeat(getWidth()*2 + 1);

        // Since Strings are immutable we will use a StringBuilder to avoid copying
        // the string every time we want to add anything (good practises in java)
        StringBuilder sb = new StringBuilder(lineSeparator + '\n');
        // NOTE: '\n' creates a so called newline, basically it tells the string to \n
        // continue on the next line (like this line!)

        // This loop will take care of printing the actual contents of the board
        for (int i = 0; i < getHeight(); i++) {
            // The "|X|O|X|" part
            sb.append('|');
            for (int j = 0; j < getWidth(); j++) {
                sb.append(getMark(i, j)).append('|');
            }
            // The "-------" part
            sb.append('\n').append(lineSeparator).append('\n');
        }

        // Build the String
        return sb.toString();
    }
}
