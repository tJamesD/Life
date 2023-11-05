//import java.util.Arrays;

public class Life {
    public static void main(String[] args) {

        LifeTest lifeTest = new LifeTest();
        lifeTest.wholeStateBoard();

        Board gameBoard = new Board(20, 30);
        int[][] readBoard = gameBoard.readStateFromFile(args[0]);

        for(int row = 0; row<readBoard.length;row++) {
            for(int col = 0; col<readBoard[row].length; col++) {
                System.out.print(readBoard[row][col]);
            }
            System.out.println();
        }
        //LifeTest lifeTest = new LifeTest();
        //int[][] deadState = gameBoard.tutDeadState(20,30);
        //gameBoard.tutRenderState(deadState);

        //System.out.println();

            gameBoard.setBoardState(readBoard);
            gameBoard.tutRenderState(gameBoard.getBoardState());
            while(true) {
                gameBoard.setBoardState(gameBoard.tutNextBoardState(gameBoard.getBoardState()));
                gameBoard.tutRenderState(gameBoard.getBoardState());
                try {
                    Thread.sleep(100);
                }
                catch(InterruptedException e) {
                    System.out.println("ERROR");
                }
            }

        /*
        int[][] randomState = gameBoard.tutRandomState(10,10);
        gameBoard.setBoardState(randomState);
        gameBoard.tutRenderState(gameBoard.getBoardState());

        while(true) {
            randomState = gameBoard.tutNextBoardState(gameBoard.getBoardState());
            gameBoard.setBoardState(randomState);
            gameBoard.tutRenderState(gameBoard.getBoardState());
        }
        */


        //lifeTest.testDeadStayDead();
        //lifeTest.testDeadCell3Pop();
        /*
        for(int row = 0; row< randomState.length;row++) {
            for(int col = 0; col<randomState[row].length;col++) {
                System.out.print(randomState[row][col]);

            }
            System.out.println();
        }
    */


        //gameBoard.tutRenderState(randomState);
    /*
        gameBoard.printBoardState();
        System.out.println();
        gameBoard.setRandomState();
        gameBoard.printBoardState();
        System.out.println();

     */
/*
        while(true) {
            System.out.println();
            gameBoard.setRandomState();
            gameBoard.render();
            System.out.println();
            try {
                Thread.sleep(600);
            }
            catch(InterruptedException e) {
                System.out.println("ERROR");
            }
        }
        */


    }

}
