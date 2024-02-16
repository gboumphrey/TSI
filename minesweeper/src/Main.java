import java.lang.Math;

public class Main {


    public static void main(String[] args) {

        // set variables
        int rows = 8;
        int columns = 8;
        int mines = 10;
        /*
        //create the board
        Tile[][] board = new Tile[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = new Tile();
            }
        }

        //populate the board with mines
        for(int i = 0; i<mines; i++) {
            boolean inProgress = true;
            while(inProgress) {
                int x = (int)(Math.random()*rows);
                int y = (int)(Math.random()*columns);
                if(board[x][y].getNumber()!=9) {
                    board[x][y].setNumber(9);
                    inProgress = false;
                }
            }
        }

        //populate the board with numbers
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<columns; j++) {
                board[i][j].setNumber(exploreAround(board, i, j));
            }
        }

        //print the game board (testing purposes)

        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<columns; j++) {
                System.out.print(board[i][j].getNumber());
            }
            System.out.println();
        }
         */
        GameBoard board = new GameBoard(rows, columns, mines);
        GameBoard.drawBoard();


    }
}
