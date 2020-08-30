import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece implements Move {

    public Bishop(String label, boolean isWhite) {
        super(label, isWhite);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        //checking if the bishop moves diagonally
        int incrementX = Math.abs(start.getX() - end.getX());
        int incrementY = Math.abs(start.getY() - end.getY());
        if (incrementX == incrementY) {

            ///checking if there is not a same color piece in the way
            //moving NW
            if (end.getX() < start.getX() && end.getY() < start.getY()) {
                int x = start.getX() - 1;
                int y = start.getY() - 1;
                while (x != end.getX() && y != end.getY()) {
                    if (board.getSpot(x, y).getPiece() != null) {

                        if (board.getSpot(x, y).getPiece().isWhite() == this.isWhite()) {
                            return false;
                        }
                        x--;
                        y--;
                    } else {
                        x--;
                        y--;
                    }
                }
            } else

                //moving NE
                if (end.getX() < start.getX() && end.getY() > start.getY()) {
                    int x = start.getX() - 1;
                    int y = start.getY() + 1;
                    while (x != end.getX() && y != end.getY()) {
                        if (board.getSpot(x, y).getPiece() != null) {
                            if (board.getSpot(x, y).getPiece().isWhite() == this.isWhite()) {
                                return false;
                            }
                            x--;
                            y++;
                        } else {
                            x--;
                            y++;
                        }
                    }
                } else

                    //moving SW
                    if (end.getX() > start.getX() && end.getY() < start.getY()) {
                        int x = start.getX() + 1;
                        int y = start.getY() - 1;
                        while (x != end.getX() && y != end.getY()) {
                            if (board.getSpot(x, y).getPiece() != null) {
                                if (board.getSpot(x, y).getPiece().isWhite() == this.isWhite()) {
                                    return false;
                                }
                                x++;
                                y--;
                            } else {
                                x++;
                                y--;
                            }
                        }
                    } else

                        //moving SE
                        if (end.getX() > start.getX() && end.getY() > start.getY()) {
                            int x = start.getX() + 1;
                            int y = start.getY() + 1;
                            while (x != end.getX() && y != end.getY()) {
                                if (board.getSpot(x, y).getPiece() != null) {
                                    if (board.getSpot(x, y).getPiece().isWhite() == this.isWhite()) {
                                        return false;
                                    }
                                    x++;
                                    y++;
                                } else {
                                    x++;
                                    y++;
                                }
                            }
                        } else {
                            return false;
                        }
        } else {
            return false;
        }
        return super.canMove(board, start, end);
    }


    @Override
    public boolean move(Board board, Player player1, Player player2, int x, int y) {
        Spot start = board.getSpotOfPiece(this);
        Spot end = board.getSpot(x, y);
        if (canMove(board, start, end)) {
            if (board.getSpot(x, y).getPiece() != null) {
                player2.getDeadPieces().add(board.getSpot(x, y).getPiece());
                player2.getAvailablePieces().remove(board.getSpot(x, y).getPiece());
            }
            board.getSpotOfPiece(this).setPiece(null);
            board.getSpot(x, y).setPiece(this);
            return true;
        } else {
            System.out.println("ERROR");
            return false;
        }
    }

    @Override
    public List<Spot> legalMoves(Board board) {
        List<Spot> legalSpots = new ArrayList<>();
        Spot start = board.getSpotOfPiece(this);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spot end = board.getSpot(i, j);
                if (canMove(board, start, end)) {
                    legalSpots.add(end);
                }
            }
        }
        return legalSpots;
    }
}
