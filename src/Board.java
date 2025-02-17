public class Board {
    int height;
    int width;
    int[][] board;
    public void create(int height, int width) {
        this.height = height;
        this.width = width;
        board = new int[height][width];
    }
}
