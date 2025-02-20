public class Block {
    private int height; private int width;
    public int[][] shape;
    private char type;
    public void create(int h, int w) {
        this.height = h;
        this.width = w;
        shape = new int[height][width];
    }
    public void set(int h, int w, int value) {
        shape[h][w] = value;
    }
    public void setType(char t) {
        type = t;
    }
    public char getType() {
        return type;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getElmt(int h, int w) {
        return shape[h][w];
    }
    public void rotate() {
        int[][] newShape = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newShape[j][height - i - 1] = shape[i][j];
            }
        }
        int temp = height;
        height = width;
        width = temp;
        shape = newShape;
    }
    public void mirror() {
        int[][] newShape = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newShape[i][width - j - 1] = shape[i][j];
            }
        }
        shape = newShape;
    }
    public void print(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(shape[i][j]);
            }
            System.out.println();
        }
    }
    public static Block cloneBlock(Block block) {
        Block newBlock = new Block();
        newBlock.create(block.getHeight(), block.getWidth());
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                newBlock.set(i, j, block.getElmt(i, j));
            }
        }
        newBlock.setType(block.getType());
        return newBlock;
    }
}
