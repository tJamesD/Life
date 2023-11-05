public class LifeTest {
    Board testBoard;
    int[][] correctGameState;

    public LifeTest() {
     testBoard = new Board(3,3);
     correctGameState = new int[3][3];
    }

    public Boolean testDeadStayDead() {

        int[][] nextGameState = testBoard.tutNextBoardState(testBoard.getBoardState());


        correctGameState[0][0] = 0;
        correctGameState[0][1] = 0;
        correctGameState[0][2] = 0;
        correctGameState[1][0] = 0;
        correctGameState[1][1] = 0;
        correctGameState[1][2] = 0;
        correctGameState[2][0] = 0;
        correctGameState[2][1] = 0;
        correctGameState[2][2] = 0;

        for(int row = 0; row<correctGameState.length;row++) {
            for(int col = 0; col<correctGameState[row].length; col++) {
                if(nextGameState[row][col]!= correctGameState[row][col]) {
                    System.out.println("DEAD STAY DEAD FAILED");
                    return false;
                }
            }
        }
        System.out.println("DEAD STAY DEAD PASSED");
        return true;
    }

    public Boolean testDeadCell3Pop() {

        int[][] passedGameState = new int[3][3];

        passedGameState[0][0] = 0;
        passedGameState[0][1] = 0;
        passedGameState[0][2] = 1;
        passedGameState[1][0] = 0;
        passedGameState[1][1] = 1;
        passedGameState[1][2] = 1;
        passedGameState[2][0] = 0;
        passedGameState[2][1] = 0;
        passedGameState[2][2] = 0;

        testBoard.setBoardState(passedGameState);
        int[][] nextGameState = testBoard.tutNextBoardState(testBoard.getBoardState());

        correctGameState[0][0] = 0;
        correctGameState[0][1] = 1;
        correctGameState[0][2] = 1;
        correctGameState[1][0] = 0;
        correctGameState[1][1] = 1;
        correctGameState[1][2] = 1;
        correctGameState[2][0] = 0;
        correctGameState[2][1] = 0;
        correctGameState[2][2] = 0;

        for(int row = 0; row<correctGameState.length;row++) {
            for(int col = 0; col<correctGameState[row].length; col++) {
                System.out.print(nextGameState[row][col]);
            }
            System.out.println();
        }

        for(int row = 0; row<correctGameState.length;row++) {
            for(int col = 0; col<correctGameState[row].length; col++) {
                if(nextGameState[row][col]!= correctGameState[row][col]) {
                    System.out.println("DEAD CELL 3 POP FAILED");
                    return false;
                }
            }
        }
        System.out.println("DEAD CELL 3 POP PASSED");
        return true;

    }

    public boolean wholeStateBoard() {
        int [][] passedGameState = new int[5][5];
        correctGameState = new int[5][5];

        passedGameState[0][0] = 0;
        passedGameState[0][1] = 0;
        passedGameState[0][2] = 1;
        passedGameState[0][3] = 1;
        passedGameState[0][4] = 1;
        passedGameState[1][0] = 1;
        passedGameState[1][1] = 1;
        passedGameState[1][2] = 0;
        passedGameState[1][3] = 0;
        passedGameState[1][4] = 1;
        passedGameState[2][0] = 0;
        passedGameState[2][1] = 1;
        passedGameState[2][2] = 1;
        passedGameState[2][3] = 1;
        passedGameState[2][4] = 0;
        passedGameState[3][0] = 1;
        passedGameState[3][1] = 0;
        passedGameState[3][2] = 1;
        passedGameState[3][3] = 0;
        passedGameState[3][4] = 1;
        passedGameState[4][0] = 0;
        passedGameState[4][1] = 0;
        passedGameState[4][2] = 0;
        passedGameState[4][3] = 1;
        passedGameState[4][4] = 0;

        correctGameState[0][0] = 0;
        correctGameState[0][1] = 1;
        correctGameState[0][2] = 1;
        correctGameState[0][3] = 1;
        correctGameState[0][4] = 1;
        correctGameState[1][0] = 1;
        correctGameState[1][1] = 0;
        correctGameState[1][2] = 0;
        correctGameState[1][3] = 0;
        correctGameState[1][4] = 1;
        correctGameState[2][0] = 0;
        correctGameState[2][1] = 0;
        correctGameState[2][2] = 0;
        correctGameState[2][3] = 0;
        correctGameState[2][4] = 1;
        correctGameState[3][0] = 0;
        correctGameState[3][1] = 0;
        correctGameState[3][2] = 0;
        correctGameState[3][3] = 0;
        correctGameState[3][4] = 1;
        correctGameState[4][0] = 0;
        correctGameState[4][1] = 0;
        correctGameState[4][2] = 0;
        correctGameState[4][3] = 1;
        correctGameState[4][4] = 0;

        testBoard.setBoardState(passedGameState);
        int[][] nextGameState = testBoard.tutNextBoardState(testBoard.getBoardState());

        for(int row = 0; row<correctGameState.length;row++) {
            for(int col = 0; col<correctGameState[row].length; col++) {
                if(nextGameState[row][col]!= correctGameState[row][col]) {
                    System.out.println("5x5 failed");
                    return false;
                }
            }
        }
        System.out.println("LIFE PASSED");
        return true;

    }

}
