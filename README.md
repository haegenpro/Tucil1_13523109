# Block Puzzle Auto Solver

## Overview
Block Puzzle Auto Solver is a Java program designed to solve block puzzle problems using a brute-force (backtracking) algorithm. The program reads input from a text file, processes board and block definitions (in either DEFAULT or CUSTOM mode), and attempts to completely cover the board using the provided blocks. Once a solution is found, the program displays the solution along with information such as the total number of cases reviewed and the execution time.

## Features

- **Brute-force Algorithm:** Explores all possible block placements and orientations (rotations and mirrors) using backtracking.
- **Input Modes:** Supports both DEFAULT mode (empty board) and CUSTOM mode (pre-filled board via input).
- **Colored Output:** Displays the solution using ANSI color codes for clear visualization.
- **Performance Metrics:** Reports the total number of cases reviewed and the execution time in milliseconds.
- **File I/O:** Reads puzzle data from external text files and optionally saves the solution output.

## Program Requirements and Installation
- **Java JDK 8 or higher:** This program requires the JDK to compile and run.
- **Ubuntu Terminal (optional):** For running commands like `ls`, `pwd`, and `tree`.

## How to Compile the Program
Make sure you are in the project directory Tucil1_13523109. To compile all Java files, run:

```bash
javac -d bin src/*.java
```
This command compiles the source files and outputs the compiled classes into the bin folder.

## How to Run

**1. Clone the Repository**
```bash
git clone https://github.com/haegenpro/Tucil1_13523109
```

**2. To run the program, use the following command:**
```bash
java -cp bin Main
```

**3. Once the program is running:**

It will prompt you to enter the input file name (Please put the file in Tucil1_13523109/testcase).
The program reads the board configuration and block definitions from the input file.
The brute-force algorithm searches for a solution and displays:
The execution time (in milliseconds).
The total number of cases reviewed.
The solution with colored output.
You will have the option to save the solution to an output file if desired.

## Repository Structure

```python
BlockPuzzleAutoSolver/
├── README.md
├── bin/
│   ├── Block.class
│   ├── Board.class
│   ├── Bruteforce.class
│   ├── InputHandler.class
│   └── Main.class
├── doc/
├── src/
│   ├── Block.java
│   ├── Board.java
│   ├── Bruteforce.java
│   ├── InputHandler.java
│   └── Main.java
├── test/
│   ├── sol1.txt
│   ├── sol2.txt
│   └── sol3.txt
└── testcase/
    ├── test1.txt
    ├── test2.txt
    ├── test3.txt
    ├── test4.txt
    ├── test5.txt
    ├── test6.txt
    └── test7.txt
```

## Author / Creator
Name: Haegen Quinston

Email: haegenquinston@gmail.com

Contact: +62 811 239 1710 (Whatsapp)

Feel free to modify or add any additional details as needed for your project!

## License
This application is licensed under the terms of the MIT License (©).