import java.util.ArrayList;
import java.util.List;

public class Piece {

    private String label;
    private boolean isWhite;

    public Piece() {
    }

    public Piece(Piece piece) {
        label = piece.label;
        isWhite = piece.isWhite;
    }

    public Piece(String label, boolean isWhite) {
        this.label = label;
        this.isWhite = isWhite;
    }

    public String getLabel() {
        return label;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean canMove(Board board, Spot start, Spot end) {

        //checking if the spot is actually on the board
        if (end.getX() > 7 || end.getY() > 7 || end.getX() < 0 || end.getY() < 0) {
            return false;
        }
        //checking if at the destination there is no same color piece as the piece we want to move
        if (end.getPiece() != null) {
            return end.getPiece().isWhite() != this.isWhite();
        }
        return true;
    }

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

