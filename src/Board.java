public class Board {
    private int height; private int width;
    public int[][] board;
    public char[][] boardChar;
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
    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(boardChar[i][j]);
            }
            System.out.println();
        }
    }
}
