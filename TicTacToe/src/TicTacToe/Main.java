package TicTacToe;


import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();



    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};

        printBoard(gameBoard);


        while(true) {
            Scanner scanner = new Scanner(System.in);
            int move;
            while (true) {
                System.out.print("Enter your move (1-9): ");
                try {
                    move = scanner.nextInt();
                    if (move < 1 || move > 9 ) {
                        System.out.println("Input out of range.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input.");
                    scanner.next();
                }
            }


            while(playerPositions.contains(move) || cpuPositions.contains(move)) {
                System.out.println("Position taken, enter a new move");
                move = scanner.nextInt();
            }

            showMove(gameBoard, move, "Player");
            String result = checkWin(gameBoard);
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = random.nextInt(9) + 1;
            }
            showMove(gameBoard, cpuPos, "CPU");

            printBoard(gameBoard);
            result = checkWin(gameBoard);
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void showMove(char[][] gameBoard, int move, String user) {

        char symbol = ' ';

        if(user.equals("Player")) {
            symbol = 'X';
            playerPositions.add(move);
        } else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPositions.add(move);
        }

        switch(move) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }
    }

    public static String checkWin(char[][] gameBoard) {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for(List l : winningConditions) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations you won!!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU wins! Better luck next time!";
            }
            if (playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a TIE!";
            }
        }
        return "";
    }
}


