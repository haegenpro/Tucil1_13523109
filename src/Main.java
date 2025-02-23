import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("\u001B[33m" + "BLOCK PUZZLE AUTO SOLVER\n");
        System.out.println("\u001B[32m" + "Input file name located at the /test/testcase folder:" + "\u001B[0m");
        String filename = consoleScanner.nextLine().trim();
        System.out.println();
        Board board = new Board();
        Block[] blocks = null;
        int numBlocks = 0;

        try (Scanner fileScanner = new Scanner(new FileReader(new File("../test/testcase/" + filename)))) {
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
        } catch (FileNotFoundException e) {
            System.out.println("\u001B[36m" + "Error: File not found. Please check the file name and try again." + "\u001B[0m");
            consoleScanner.close();
            return;
        } catch (InputMismatchException e) {
            System.out.println("\u001B[36m" + "Error: The file contains data in an unexpected format. Please verify that all numbers and values are correct." + "\u001B[0m");
            consoleScanner.close();
            return;
        } catch (NoSuchElementException e) {
            System.out.println("\u001B[36m" + "Error: The input file is missing required values." + "\u001B[0m");
            consoleScanner.close();
            return;
        } catch (Exception e) {
            System.out.println("\u001B[36m" + "An unexpected error occurred: " + "\u001B[0m" + e.getMessage());
            consoleScanner.close();
            return;
        }

        if (blocks == null) {
            consoleScanner.close();
            return;
        }
        boolean[] used = new boolean[numBlocks];
        long startTime = System.currentTimeMillis();
        if (Bruteforce.solve(board, blocks, used)) {
            long endTime = System.currentTimeMillis();
            board.printColored();
            System.out.println("\u001B[36m" + "Time spent: " + (endTime - startTime) + " ms\n");
            System.out.println("\u001B[31m" + "# of cases reviewed: " + Bruteforce.iterations + "\n");
            System.out.println("\u001B[33m" + "Would you like to save the solution? (yes/no)" + "\u001B[0m");
            String save = consoleScanner.next().trim().toLowerCase();
            if (save.equals("yes")) {
                System.out.println("\u001B[32m" + "Enter filename:" + "\u001B[0m");
                String savename = consoleScanner.next().trim();
                board.save("../test/solutions/" + savename);
                System.out.println("\u001B[36m" + "Solution saved as " + savename + " in the /test/solutions folder." + "\u001B[0m");
            } else if (!save.equals("no")) {
                System.out.println("\u001B[36m" + "Unrecognized input. No solution will be saved." + "\u001B[0m");
            }
        } else {
            System.out.println("\u001B[36m" + "No solution found." + "\u001B[0m");
        }
        consoleScanner.close();
    }
}
