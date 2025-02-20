import java.util.Scanner;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        // Scanner for console input
        Scanner consoleScanner = new Scanner(System.in);
        
        System.out.println("\u001B[32m" + "BLOCK PUZZLE AUTO SOLVER\n");
        System.out.println("\u001B[33m" + "Input txt file name:");
        String filename = consoleScanner.nextLine();
        Board board = new Board();
        Block[] blocks = null;
        int numBlocks = 0;
        try (Scanner fileScanner = new Scanner(new FileReader(new File("../test/" + filename)))) {
            int height = fileScanner.nextInt();
            int width = fileScanner.nextInt();
            numBlocks = fileScanner.nextInt();
            fileScanner.nextLine();
            String caseType = fileScanner.nextLine().trim();
            if (caseType.equals("CUSTOM")) {
                board.createCustom(height, width, fileScanner);
            } else {
                board.create(height, width);
            }
            blocks = InputHandler.readBlocks(fileScanner, numBlocks);
        } catch (Exception e) {
            System.out.println("File not found.");
            consoleScanner.close();
            return;
        }
        if (blocks == null) {
            consoleScanner.close();
            return;
        }
        boolean[] used = new boolean[numBlocks];
        long startTime = System.currentTimeMillis();     
        // Brute force algorithm
        if (Bruteforce.solve(board, blocks, used)) {
            long endTime = System.currentTimeMillis();
            board.printColored();
            System.out.println("Time spent: " + (endTime - startTime) + " ms\n");
            System.out.println("# of cases reviewed: " + Bruteforce.iterations + "\n");
            System.out.println("Would you like to save the solution? (yes/no)");
            String save = consoleScanner.next();
            if (save.equals("yes")) {
                System.out.println("Enter filename:");
                String savename = consoleScanner.next();
                board.save("../test/" + savename);
            }
        } else {
            System.out.println("No solution found.");
        }
        consoleScanner.close();
    }
}
