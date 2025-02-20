import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
    private int height; private int width;
    public int[][] board;
    public char[][] boardChar;
    public static final String ANSI_RESET = "\u001B[0m";
    public static Map<Character, String> colorMap = new HashMap<>();
    public Board() {
        colorMap.put('A', "\u001B[31m");
        colorMap.put('B', "\u001B[32m");
        colorMap.put('C', "\u001B[33m");
        colorMap.put('D', "\u001B[34m");
        colorMap.put('E', "\u001B[35m");
        colorMap.put('F', "\u001B[36m");
        colorMap.put('G', "\u001B[37m");
        colorMap.put('H', "\u001B[38;5;208m");
        colorMap.put('I', "\u001B[38;5;214m");
        colorMap.put('J', "\u001B[38;5;220m");
        colorMap.put('K', "\u001B[38;5;226m");
        colorMap.put('L', "\u001B[38;5;190m");
        colorMap.put('M', "\u001B[38;5;154m");
        colorMap.put('N', "\u001B[38;5;118m");
        colorMap.put('O', "\u001B[38;5;82m");
        colorMap.put('P', "\u001B[38;5;45m");
        colorMap.put('Q', "\u001B[38;5;105m");
        colorMap.put('R', "\u001B[38;5;159m");
        colorMap.put('S', "\u001B[38;5;111m");
        colorMap.put('T', "\u001B[38;5;117m");
        colorMap.put('U', "\u001B[38;5;123m");
        colorMap.put('V', "\u001B[38;5;129m");
        colorMap.put('W', "\u001B[38;5;135m");
        colorMap.put('X', "\u001B[38;5;141m");
        colorMap.put('Y', "\u001B[38;5;147m");
        colorMap.put('Z', "\u001B[38;5;153m");
    }
    public void create(int h, int w) {
        this.height = h;
        this.width = w;
        this.board = new int[height][width];
        this.boardChar = new char[height][width];
    }
    public void createCustom(int h, int w, Scanner scanner) {
        create(h, w);
        for (int i = 0; i < h; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < w; j++) {
                char c = line.charAt(j);
                if (c == '.') {
                    this.board[i][j] = 1;
                    this.boardChar[i][j] = '.';
                }
            }
        }
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
    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    public void printColored() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char ch = boardChar[i][j];
                if (ch == '\u0000' | ch == '.') {
                    System.out.print(" ");
                } 
                else {
                    String color = colorMap.getOrDefault(ch, ANSI_RESET);
                    System.out.print(color + ch + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public void save(String filename) {
        try {
            File file = new File(filename);
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    char ch = boardChar[i][j];
                    if (ch == '\u0000' | ch == '.') {
                        writer.write(" ");
                    } else {
                        writer.write(ch);
                    }
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}