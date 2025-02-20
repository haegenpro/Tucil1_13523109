public class Bruteforce {
    public static long iterations = 0;
    public static boolean solve(Board board, Block[] blocks, boolean[] used) {      
        iterations++;
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (allBlocksUsed(used)) {
                    if (findEmptyCell(board) == null) {
                        return true;
                    }
                    return false;
                }
                for (int i = 0; i < blocks.length; i++) {
                    if (!used[i]) {
                        Block originalBlock = blocks[i];
                        for (int orient = 0; orient < 8; orient++) {
                            // Cloning original block
                            Block orientedBlock = cloneBlock(originalBlock);
                            if (orient >= 4) {
                                orientedBlock.mirror();
                            }
                            int rotations = orient % 4;
                            for (int r = 0; r < rotations; r++) {
                                orientedBlock.rotate();
                            }
                            if (canPlace(orientedBlock, board, row, col)) {
                                placeBlock(orientedBlock, board, row, col);
                                used[i] = true;
                                if (solve(board, blocks, used)) {
                                    return true;
                                }
                                removeBlock(orientedBlock, board, row, col);
                                used[i] = false;
                            }
                        }
                    }
                }
            }
        }
        if (allBlocksUsed(used)) {
            return true;
        }
        return false;
    }
    private static boolean allBlocksUsed(boolean[] used) {
        for (boolean flag : used) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }
    private static int[] findEmptyCell(Board board) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getElmt(i, j) == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    private static boolean canPlace(Block block, Board board, int row, int col) {
        if (row + block.getHeight() > board.getHeight() || col + block.getWidth() > board.getWidth()) {
            return false;
        }
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getElmt(i, j) == 1 && board.getElmt(row + i, col + j) == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    private static void placeBlock(Block block, Board board, int row, int col) {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getElmt(i, j) == 1) {
                    board.setChar(row + i, col + j, block.getType());
                }
            }
        }
    }
    private static void removeBlock(Block block, Board board, int row, int col) {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getElmt(i, j) == 1) {
                    board.removeChar(row + i, col + j);
                }
            }
        }
    }
    private static Block cloneBlock(Block block) {
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
