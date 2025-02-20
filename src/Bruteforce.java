public class Bruteforce {
    public static long iterations = 0;
    public static boolean solve(Board board, Block[] blocks, boolean[] used) {      
        iterations++;

        int[] pos = findEmptyCell(board);
        if (pos == null) {
            if (allBlocksUsed(used)) {
                return true;
            }
            return false;
        }
        if (allBlocksUsed(used)) {
            return false;
        }
        int row = pos[0];
        int col = pos[1];
        // Try each block that hasn't been used yet
        for (int i = 0; i < blocks.length; i++) {
            if (!used[i]) {
                Block currentBlock = blocks[i];
                for (int j = 1; j < 9; j++) {
                    iterations++;

                    if (j % 4 == 0) {
                        currentBlock.mirror();
                    } 
                    else {
                        currentBlock.rotate();
                    }
                    if (canPlace(currentBlock, board, row, col)) {
                        placeBlock(currentBlock, board, row, col);
                        used[i] = true;
                        if (solve(board, blocks, used)) {
                            return true;
                        }
                        removeBlock(currentBlock, board, row, col);
                        used[i] = false;
                    }
                }
            }
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
}
