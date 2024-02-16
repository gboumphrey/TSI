public class GameBoard {

    public static Tile[][] board;

    public GameBoard(int rows, int columns, int mines) {
        board = new Tile[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Tile();
            }
        }
        populateBoard(rows, columns, mines);
    }
    public int exploreAround(int x, int y) {
        if(board[x][y].getNumber() == 9){return 9;} //do not overwrite mines
        int count = 0;
        for(int i = x-1; i<=x+1; i++) {
            for(int j = y-1; j<=y+1; j++){
                try {
                    if(board[i][j].getNumber() == 9) {
                        count++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {

                }
            }
        }
        return count;
    }

    public static void drawBoard() {
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                System.out.print(tile.drawTile());
            }
            System.out.println();
        }
    }


    public void populateBoard(int rows, int columns, int mines) {
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
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                board[i][j].setNumber(exploreAround(i, j));
            }
        }

    }
}
