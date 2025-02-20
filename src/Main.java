import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        int numBlocks = scanner.nextInt();
        Board board = new Board();
        scanner.nextLine(); // Consume remaining line after P
        String caseType = scanner.nextLine().trim(); // Read the S line (case type)
        if (caseType.equals("CUSTOM")) {
            board.createCustom(height, width, scanner);
        }
        else {
            board.create(height, width);
        }
        Block[] blocks = InputHandler.readBlocks(scanner, numBlocks);
        if (blocks == null) {
            scanner.close();
            return;
        }
        boolean[] used = new boolean[numBlocks];
        long startTime = System.currentTimeMillis();
        // Brute force algorithm
        if (Bruteforce.solve(board, blocks, used)) {
            long endTime = System.currentTimeMillis();
            System.out.println("Waktu pencarian: " + (endTime - startTime) + " ms\n");
            board.printColored();
            System.out.println("Banyak kasus yang ditinjau: " + Bruteforce.iterations + "\n");
            System.out.println("Apakah anda ingin menyimpan solusi? (ya/tidak)");
            String save = scanner.next();
            if (save.equals("ya")) {
                System.out.println("Masukkan nama file:");
                String filename = scanner.next();
                board.save(filename);
            }
        } 
        else {
            System.out.println("No solution found.");
        }
        scanner.close();
    }
}