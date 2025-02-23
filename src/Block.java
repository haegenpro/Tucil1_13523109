import java.util.ArrayList;

public class Block {
    private int height;
    private int width;
    public int[][] shape;
    private char type;
    public ArrayList<Block> orientations = new ArrayList<>();

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

    public Block cloneBlock() {
        Block newBlock = new Block();
        newBlock.create(this.height, this.width);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                newBlock.set(i, j, this.shape[i][j]);
            }
        }
        newBlock.setType(this.type);
        return newBlock;
    }

    public Block getRotated() {
        Block newBlock = new Block();
        newBlock.create(this.width, this.height);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                newBlock.set(j, this.height - i - 1, this.shape[i][j]);
            }
        }
        newBlock.setType(this.type);
        return newBlock;
    }

    public Block getMirrored() {
        Block newBlock = new Block();
        newBlock.create(this.height, this.width);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                newBlock.set(i, this.width - j - 1, this.shape[i][j]);
            }
        }
        newBlock.setType(this.type);
        return newBlock;
    }

    public void computeOrientations() {
        orientations.clear();
        Block original = this.cloneBlock();
        orientations.add(original);
        Block current = original;
        for (int i = 1; i < 4; i++) {
            current = current.getRotated();
            orientations.add(current);
        }
        Block mirrored = this.getMirrored();
        orientations.add(mirrored);
        current = mirrored;
        for (int i = 1; i < 4; i++) {
            current = current.getRotated();
            orientations.add(current);
        }
    }

    public void print() {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                System.out.print(shape[i][j]);
            }
            System.out.println();
        }
    }
}
