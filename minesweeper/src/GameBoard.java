public class GameBoard {

    public static Tile[][] board;
    public int flags = 0;

    public GameBoard(int rows, int columns, int mines) {
        board = new Tile[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Tile();
            }
        }
        populateBoard(rows, columns, mines);
    }
    private void populateBoard(int rows, int columns, int mines) {
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
                board[i][j].setNumber(minesAround(i, j));
            }
        }
    }
    private int minesAround(int x, int y) {
        if(board[x][y].getNumber() == 9){return 9;} //do not overwrite mines
        int count = 0;
        for(int i = x-1; i<=x+1; i++) {
            for(int j = y-1; j<=y+1; j++){
                try {
                    if(board[i][j].getNumber() == 9) {
                        count++;
                    }
                } catch(ArrayIndexOutOfBoundsException ignore) { }
            }
        }
        return count;
    }
    public static void drawBoard() {
        System.out.print("# ");
        for (int k = 0; k<board[0].length; k++) {
            System.out.print(Character.toString((char)k+65)+ " ");
        }
        System.out.println();
        for (int i = 0; i<board.length; i++) {
            System.out.print(Character.toString((char)i+65) + " ");
            for (int j = 0; j<board[i].length; j++) {
                System.out.print(board[i][j].drawTile() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void revealTile(int x, int y) {
        int number = board[x][y].reveal();
        if(number==9) {
            Main.gameOver = true;
            System.out.println("!!!!!!!!!");
            System.out.println("GAME OVER");
            System.out.println("!!!!!!!!!");
        }
        if(number==0) {
            revealAround(x, y);
        }
    }

    public void flagTile(int x, int y) {
        int number = board[x][y].toggleFlagged();
        flags += number;
    }
    private void revealAround(int x, int y) {
        for(int i = x-1; i<=x+1; i++) {
            for(int j = y-1; j<=y+1; j++){
                try {
                    if(board[i][j].check()) {
                        revealTile(i, j);
                    }
                }catch (ArrayIndexOutOfBoundsException ignore) { }
            }
        }
    }
    public void revealAll() {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                revealTile(i, j);
            }
            System.out.println();
        }
    }
}
