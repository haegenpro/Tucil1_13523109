public class Bruteforce {
    public static boolean solve(Board board, Block[] blocks, boolean[] used) {      
        // Find the next empty cell on the board
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
                
                // Optionally, iterate over different orientations:
                // for (each orientation of currentBlock) { ... }
                for (int j = 1; j < 9; j++) {
                    if (j % 4 == 0) {
                        currentBlock.mirror();
                    } else {
                        currentBlock.rotate();
                    }
                    // Check if currentBlock can be placed at (row, col)
                    if (canPlace(currentBlock, board, row, col)) {
                        // Place the block
                        placeBlock(currentBlock, board, row, col);
                        used[i] = true;
                        
                        // Recursively attempt to solve with the updated board
                        if (solve(board, blocks, used)) {
                            return true;
                        }
                        
                        // Backtrack: remove the block and mark it as unused
                        removeBlock(currentBlock, board, row, col);
                        used[i] = false;
                    }
                }
            }
        }
        return false; // No valid block found for this cell: trigger backtracking
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
        // Ensure the block fits in the board bounds and does not overlap any existing block
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
    
    // Helper method to place a block on the board
    private static void placeBlock(Block block, Board board, int row, int col) {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getElmt(i, j) == 1) {
                    board.setChar(row + i, col + j, block.getType());
                }
            }
        }
    }
    
    // Helper method to remove a block from the board (for backtracking)
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
