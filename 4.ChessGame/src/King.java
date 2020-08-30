import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King extends Piece implements Move{

    public King(String label, boolean isWhite) {
        super(label, isWhite);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        if(Math.abs(start.getX() - end.getX()) > 1 || Math.abs(start.getY() - end.getY()) > 1){
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
}
