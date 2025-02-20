import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private int height; private int width;
    public int[][] board;
    public char[][] boardChar;
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_WHITE  = "\u001B[37m";
    public static Map<Character, String> colorMap = new HashMap<>();
    public Board() {
        colorMap.put('A', ANSI_RED);
        colorMap.put('B', ANSI_GREEN);
        colorMap.put('C', ANSI_YELLOW);
        colorMap.put('D', ANSI_BLUE);
        colorMap.put('E', ANSI_PURPLE);
        colorMap.put('F', ANSI_CYAN);
        colorMap.put('G', ANSI_WHITE);
    }
    public void create(int h, int w) {
        this.height = h;
        this.width = w;
        board = new int[height][width];
        boardChar = new char[height][width];
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getElmt(int h, int w) {
        return board[h][w];
    }
    public char getChar(int h, int w) {
        return boardChar[h][w];
    }
    public void setChar(int h, int w, char value) {
        board[h][w] = 1;
        boardChar[h][w] = value;
    }
    public void removeChar(int h, int w) {
        board[h][w] = 0;
        boardChar[h][w] = '\u0000';
    }
    public void printColored() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char ch = boardChar[i][j];
                if (ch == '\u0000'| ch == '.') {
                    System.out.print(" ");
                } else {
                    // Get the color for this character (default to no color)
                    String color = colorMap.getOrDefault(ch, ANSI_RESET);
                    System.out.print(color + ch + ANSI_RESET);
                }
            }
            System.out.println();
        }
    }
    public void save(String filename) {
        try {
            File file = new File(filename);
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    writer.write(boardChar[i][j]);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}