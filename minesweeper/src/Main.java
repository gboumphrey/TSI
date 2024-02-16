public class Main {

    public static void main(String[] args) {

        // set variables
        int rows = 8;
        int columns = 8;
        int mines = 10;
        GameBoard board = new GameBoard(rows, columns, mines);
        GameBoard.drawBoard();
        board.revealTile(4, 3);
        GameBoard.drawBoard();


    }
}
