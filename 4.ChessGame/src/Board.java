public class Board {

    private Spot[][] board;

    public Board() {
        this.board = new Spot[8][8];
        this.resetBoard();
    }

    public Spot[][] getBoard() {
        return board;
    }

    public Spot getSpot(int x, int y) {
        return board[x][y];
    }

    public Spot getSpotOfPiece(Piece piece) {
        int x = 0, y = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() == piece) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        return board[x][y];
    }

    public void resetBoard() {

        //initializing white pieces
        for (int j = 0; j < 8; j++) {
            board[1][j] = new Spot(1, j, new Pawn("P" + j, true));
        }
        board[0][0] = new Spot(0, 0, new Castle("C1", true));
        board[0][7] = new Spot(0, 7, new Castle("C2", true));

        board[0][1] = new Spot(0, 1, new Horse("H1", true));
        board[0][6] = new Spot(0, 6, new Horse("H2", true));

        board[0][2] = new Spot(0, 2, new Bishop("B1", true));
        board[0][5] = new Spot(0, 5, new Bishop("B2", true));

        board[0][4] = new Spot(0, 4, new Queen("Q", true));
        board[0][3] = new Spot(0, 3, new King("K", true));

        //initializing black pieces
        for (int j = 0; j < 8; j++) {
            board[6][j] = new Spot(6, j, new Pawn("P" + j + "*", false));
        }
        board[7][0] = new Spot(7, 0, new Castle("C1*", false));
        board[7][7] = new Spot(7, 7, new Castle("C2*", false));

        board[7][1] = new Spot(7, 1, new Horse("H1*", false));
        board[7][6] = new Spot(7, 6, new Horse("H2*", false));

        board[7][2] = new Spot(7, 2, new Bishop("B1*", false));
        board[7][5] = new Spot(7, 5, new Bishop("B2*", false));

        board[7][4] = new Spot(7, 4, new Queen("Q*", false));
        board[7][3] = new Spot(7, 3, new King("K*", false));

        //initializing the remaining spots
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Spot(i, j, null);
            }
        }
    }
}
