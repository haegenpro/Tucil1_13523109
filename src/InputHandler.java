import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    public static Block[] readBlocks(Scanner scanner, int numBlocks) {
        ArrayList<String> lines = new ArrayList<>();
        int emptyCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                emptyCount++;
                if (emptyCount == 2) {
                    break;
                }
            } else {
                emptyCount = 0;
                lines.add(line);
            }
        }
        Block[] blocks = new Block[numBlocks];
        int lineIndex = 0;
        int blocksProcessed = 0;
        while (blocksProcessed < numBlocks && lineIndex < lines.size()) {
            String currentLine = lines.get(lineIndex);
            if (currentLine.isEmpty()) {
                lineIndex++;
                continue;
            }
            char currentChar = currentLine.trim().charAt(0);
            ArrayList<String> blockLines = new ArrayList<>();
            blockLines.add(currentLine);
            lineIndex++;
            while (lineIndex < lines.size()) {
                String nextLine = lines.get(lineIndex);
                if (nextLine.isEmpty()) {
                    lineIndex++;
                    continue;
                }
                if (nextLine.trim().charAt(0) == currentChar) {
                    blockLines.add(nextLine);
                    lineIndex++;
                } else {
                    break;
                }
            }
            int blockHeight = blockLines.size();
            int blockWidth = 0;
            for (String row : blockLines) {
                blockWidth = Math.max(blockWidth, row.length());
            }
            Block block = new Block();
            block.create(blockHeight, blockWidth);
            block.setType(currentChar);
            for (int r = 0; r < blockHeight; r++) {
                String row = blockLines.get(r);
                for (int c = 0; c < blockWidth; c++) {
                    char ch = (c < row.length()) ? row.charAt(c) : ' ';
                    block.set(r, c, (ch == currentChar) ? 1 : 0);
                }
            }
            blocks[blocksProcessed++] = block;
        }
        if (blocksProcessed != numBlocks) {
            System.err.println("Jumlah block yang diberikan tidak cukup.");
            return null;
        }
        return blocks;
    }
}

