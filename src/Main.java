import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        Board board = new Board();
        board.create(height, width);
        int numBlocks = scanner.nextInt();
        //scanner.nextLine(); // Consume remaining line after P
        //String caseType = scanner.nextLine().trim(); // Read the S line (case type)
        ArrayList<String> lines = new ArrayList<>();
        int emptyCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                emptyCount++;
                if (emptyCount == 2) {  // Break on two consecutive empty lines
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
            char currentChar = currentLine.charAt(0);
            ArrayList<String> blockLines = new ArrayList<>();
            blockLines.add(currentLine);
            lineIndex++;
            // Group consecutive lines with the same first character
            while (lineIndex < lines.size()) {
                String nextLine = lines.get(lineIndex);
                if (nextLine.isEmpty()) {
                    lineIndex++;
                    continue;
                }
                char nextChar = nextLine.charAt(0);
                if (nextChar == currentChar) {
                    blockLines.add(nextLine);
                    lineIndex++;
                } else {
                    break;
                }
            }
            // Determine block dimensions
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
                    if (ch == currentChar) {
                        block.set(r, c, 1);
                    } else {
                        block.set(r, c, 0);
                    }
                }
            }
            blocks[blocksProcessed++] = block;
        }
        if (blocksProcessed != numBlocks) {
            System.err.println("Not enough block definitions provided.");
            scanner.close();
            return;
        }
        // Brute force algorithm
        boolean[] used = new boolean[numBlocks];
        if (Bruteforce.solve(board, blocks, used)) {
            board.printColored();
            System.out.println("Banyak kasus yang ditinjau: " + Bruteforce.iterations);
            System.out.println("Apakah anda ingin menyimpan solusi? (ya/tidak)");
            String save = scanner.next();
            if (save.equals("ya")) {
                System.out.println("Masukkan nama file:");
                String filename = scanner.next();
                board.save(filename);
            }
        } else {
            System.out.println("No solution found.");
        }
        scanner.close();
    }
}