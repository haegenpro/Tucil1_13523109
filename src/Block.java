public class Block {
    int height;
    int width;
    int[][] shape;
    public void create(int height, int width) {
        this.height = height;
        this.width = width;
        shape = new int[height][width];
    }
}
