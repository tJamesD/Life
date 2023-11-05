import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Board {


    private int[][] boardState;
    private int[][] nextBoardState;
    private int rows;
    private int cols;
    Random rand;

    //Constructor creates initial dead state.
    public Board(int rows, int cols) {
        boardState = new int[rows][cols];
        nextBoardState = new int[rows][cols];
        rand = new Random();

        this.rows = rows;
        this.cols = cols;
    }

    //Returns boardState
    public int[][] getBoardState() {
        return boardState;
    }

    public int[][] getNextBoardState() {
        return nextBoardState;
    }

    //Prints boardState nicely
    public void printBoardState() {
        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                System.out.print(boardState[row][col]);
            }
            System.out.println();
        }
    }

    //tutorial method
    public void printDeadState(int row, int height) {

    }

    public void render() {
        for (int col = 0; col < boardState.length + 2; col++) {
            System.out.print("-");
        }
        System.out.println();
        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                if (col == 0) {
                    System.out.print("|");
                }
                if (boardState[row][col] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
                if (col == boardState[row].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        for (int col = 0; col < boardState.length + 2; col++) {
            System.out.print("-");
        }
    }

    private void resetToDeadState() {
        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                boardState[row][col] = 0;
            }
        }
    }

    public void setRandomState() {
        double rN;
        int newCellState = 0;

        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                rN = rand.nextDouble();
                if (rN >= 0.5) {
                    newCellState = 1;
                } else {
                    newCellState = 0;
                }
                boardState[row][col] = newCellState;
            }
        }
    }

    public void setBoardState(int[][] newBoardState) {
        boardState = new int[newBoardState.length][newBoardState[0].length];
        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                boardState[row][col] = newBoardState[row][col];
            }
        }
        rows = boardState.length;
        cols = boardState[0].length;
    }

    public int[][] tutDeadState(int rows, int cols) {
        int[][] deadState = new int[rows][cols];
        return deadState;
    }

    public int[][] tutRandomState(int rows, int cols) {
        int[][] randomState = new int[rows][cols];
        double rN;
        int newCellState = 0;

        for (int row = 0; row < randomState.length; row++) {
            for (int col = 0; col < randomState[row].length; col++) {
                rN = rand.nextDouble();
                if (rN >= 0.5) {
                    newCellState = 1;
                } else {
                    newCellState = 0;
                }
                randomState[row][col] = newCellState;
            }
        }
        return randomState;
    }

    public void tutRenderState(int[][] gameState) {
        for (int col = 0; col < gameState[0].length + 2; col++) {
            System.out.print("-");
        }
        System.out.println();
        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                if (col == 0) {
                    System.out.print("|");
                }
                if (gameState[row][col] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
                if (col == gameState[row].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        for (int col = 0; col < gameState[0].length + 2; col++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public int[][] tutNextBoardState(int[][] gameState) {
        int[][] nextBoardState = new int[gameState.length][gameState[0].length];

        for (int row = 0; row < boardState.length; row++) {
            for (int col = 0; col < boardState[row].length; col++) {
                //Corner cases
                if ((row == 0 && col == 0) || (row == 0 && col == cols - 1) || (row == rows - 1 && col == 0) || (row == rows - 1 && col == cols - 1)) {
                    int aliveCount = countCornerPop(row, col, gameState);
                    if (gameState[row][col] == 0 && aliveCount == 3) {
                        nextBoardState[row][col] = 1;
                    } else if (gameState[row][col] == 1 && aliveCount <= 1) {
                        nextBoardState[row][col] = 0;
                    } else if (gameState[row][col] == 1 && aliveCount <= 3) {
                        nextBoardState[row][col] = 1;
                    } else if (gameState[row][col] == 1 && aliveCount > 3) {
                        nextBoardState[row][col] = 0;
                        System.out.println("ALIVE COUNT FOR CORNERS SHOULD ONLY EVER BE 3 OR LESS");
                    }

                } else if (row == 0 || col == 0 || row == rows - 1 || col == cols - 1) {
                    int aliveCount = countEdgePop(row, col, gameState);
                    //System.out.print("aliveCount " + aliveCount);
                    if (gameState[row][col] == 0 && aliveCount == 3) {
                        nextBoardState[row][col] = 1;
                        //System.out.println("TEST1");
                    } else if (gameState[row][col] == 1 && aliveCount <= 1) {
                        nextBoardState[row][col] = 0;
                        //System.out.println("TEST2");
                    } else if (gameState[row][col] == 1 && aliveCount <= 3) {
                        nextBoardState[row][col] = 1;
                        //System.out.println("TEST3");
                    } else if (gameState[row][col] == 1 && aliveCount > 3) {
                        nextBoardState[row][col] = 0;
                        //System.out.println("TEST4");
                    }
                } else {
                    int aliveCount = countNormPop(row, col, gameState);
                    if (gameState[row][col] == 0 && aliveCount == 3) {
                        nextBoardState[row][col] = 1;
                        //System.out.println("TEST1");
                    } else if (gameState[row][col] == 1 && aliveCount <= 1) {
                        nextBoardState[row][col] = 0;
                        //System.out.println("TEST2");
                    } else if (gameState[row][col] == 1 && aliveCount <= 3) {
                        nextBoardState[row][col] = 1;
                        //System.out.println("TEST3");
                    } else if (gameState[row][col] == 1 && aliveCount > 3) {
                        nextBoardState[row][col] = 0;
                        //System.out.println("TEST4");
                    }
                }
            }
        }

        return nextBoardState;
    }

    public int countEdgePop(int row, int col, int[][] gameState) {

        int[] oneCountArray = new int[5];
        int oneCount = 0;

        if (row == 0) {
            oneCountArray[0] = gameState[row][col - 1];
            oneCountArray[1] = gameState[row][col + 1];
            oneCountArray[2] = gameState[row + 1][col];
            oneCountArray[3] = gameState[row + 1][col - 1];
            oneCountArray[4] = gameState[row + 1][col + 1];
        }
        if (col == 0) {
            oneCountArray[0] = gameState[row - 1][col];
            oneCountArray[1] = gameState[row - 1][col + 1];
            oneCountArray[2] = gameState[row][col + 1];
            oneCountArray[3] = gameState[row + 1][col];
            oneCountArray[4] = gameState[row + 1][col + 1];
        }

        if (row == rows - 1) {
            oneCountArray[0] = gameState[row][col - 1];
            oneCountArray[1] = gameState[row][col + 1];
            oneCountArray[2] = gameState[row - 1][col];
            oneCountArray[3] = gameState[row - 1][col - 1];
            oneCountArray[4] = gameState[row - 1][col + 1];
        }
        if (col == cols - 1) {
            oneCountArray[0] = gameState[row - 1][col];
            oneCountArray[1] = gameState[row - 1][col - 1];
            oneCountArray[2] = gameState[row][col - 1];
            oneCountArray[3] = gameState[row + 1][col];
            oneCountArray[4] = gameState[row + 1][col - 1];
        }

        for (int i = 0; i < oneCountArray.length; i++) {
            if (oneCountArray[i] == 1) {
                oneCount++;
            }
        }


        return oneCount;
    }

    public int countCornerPop(int row, int col, int[][] gameState) {
        int[] oneCountArray = new int[3];
        int oneCount = 0;

        if (row == 0 && col == 0) {
            oneCountArray[0] = gameState[row][col + 1];
            oneCountArray[1] = gameState[row + 1][col];
            oneCountArray[2] = gameState[row + 1][col + 1];

        }
        if (row == 0 && col == cols - 1) {
            oneCountArray[0] = gameState[row][col - 1];
            oneCountArray[1] = gameState[row + 1][col];
            oneCountArray[2] = gameState[row + 1][col - 1];

        }
        if (row == rows - 1 && col == 0) {
            oneCountArray[0] = gameState[row - 1][col];
            oneCountArray[1] = gameState[row][col + 1];
            oneCountArray[2] = gameState[row - 1][col + 1];

        }
        if (row == rows - 1 && col == cols - 1) {
            oneCountArray[0] = gameState[row - 1][col];
            oneCountArray[1] = gameState[row][col - 1];
            oneCountArray[2] = gameState[row - 1][col - 1];
        }

        for (int i = 0; i < oneCountArray.length; i++) {
            if (oneCountArray[i] == 1) {
                oneCount++;
            }
        }

        return oneCount;
    }

    public int countNormPop(int row, int col, int[][] gameState) {
        int[] oneCountArray = new int[8];
        int oneCount = 0;

        oneCountArray[0] = gameState[row - 1][col - 1];
        oneCountArray[1] = gameState[row - 1][col];
        oneCountArray[2] = gameState[row - 1][col + 1];
        oneCountArray[3] = gameState[row][col - 1];
        oneCountArray[4] = gameState[row][col + 1];
        oneCountArray[5] = gameState[row + 1][col - 1];
        oneCountArray[6] = gameState[row + 1][col];
        oneCountArray[7] = gameState[row + 1][col + 1];


        for (int i = 0; i < oneCountArray.length; i++) {
            if (oneCountArray[i] == 1) {
                oneCount++;
            }
        }

        return oneCount;
    }

    public int[][] readStateFromFile(String fileName) {
        int rowCount = 0;
        int colCount = 0;
        int[][] errorArray = new int[1][1];
        try {
            Scanner rowCounter = new Scanner(new File(fileName));
            Scanner fileReader = new Scanner(new File(fileName));
            try {
                while (rowCounter.hasNextLine()) {
                 String line = rowCounter.nextLine();
                 rowCount++;
                 colCount = line.length();
                }
                rowCounter.close();

                int[][] boardArray = new int[rowCount][colCount];

               // while(fileReader.hasNext()) {

                    for(int row = 0; row<boardArray.length;row++) {
                        String line = fileReader.nextLine();
                        for(int col = 0; col<boardArray[row].length; col++) {
                            boardArray[row][col] = Integer.parseInt(String.valueOf(line.charAt(col)));
                        }
                    }
                //}
                fileReader.close();

                return boardArray;

            } catch (InputMismatchException e) {
                System.out.println("BAD FILE FORMAT");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            System.exit(1);
        }
        return errorArray;
    }

}
