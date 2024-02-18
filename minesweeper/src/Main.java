import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Main {
    public static Scanner reader = new Scanner(System.in);
    public static boolean gameOver = false;

    public static void interpret(String input, GameBoard board) {
        try {
            String[] split = input.split(" ");
            if (split[0].equalsIgnoreCase("flag")) {
                board.flagTile(split[1].charAt(1)-65,split[1].charAt(0)-65);
            } else if (split[0].equalsIgnoreCase("reveal")) {
                board.revealTile(split[1].charAt(1)-65,split[1].charAt(0)-65);
            } else {
                System.out.println("Command wasn't recognised.");
            }
        } catch (Exception e) {
            System.out.println("Coordinate wasn't recognised.");
        }
    }

    public static void main(String[] args) {

        // set variables
        int rows = 8;
        int columns = 10;
        int mines = 10;
        GameBoard board = new GameBoard(rows, columns, mines);

        while(!gameOver) {
            GameBoard.drawBoard();
            System.out.println("Type flag/reveal then coordinate (horizontal then vertical)");
            interpret(reader.nextLine(), board);
            if (board.flags == mines) {
                board.revealAll();
                if(!gameOver) {
                    GameBoard.drawBoard();
                    System.out.println("!!!!!!!!!!!!!!!");
                    System.out.println("CONGRATULATIONS");
                    System.out.println("!!!!!!!!!!!!!!!");
                    gameOver = true;
                }
            }

        }

    }
}
