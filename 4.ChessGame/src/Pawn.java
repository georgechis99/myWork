import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece implements Move {

    private boolean wasMoved = false;
    public Pawn(String label, boolean isWhite) {
        super(label, isWhite);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        //to allow the pawn to move diagonally to capture another piece
        if (this.isWhite()) {
            //exception: if this is the pawn's first move it can move two spots
            if(!wasMoved){
                if (Math.abs(startX - endX) > 2) {
                    return false;
                }
                if (Math.abs(startY - endY) > 1) {
                    return false;
                }
            }else{
                //to make sure the pawn only moves one spot at a time
                if (Math.abs(startX - endX) > 1) {
                    return false;
                }
                if (Math.abs(startY - endY) > 1) {
                    return false;
                }
            }
            //to make sure the pawn only goes forward
            if (startX > endX) {
                return false;
            }

            if(endY != startY){
                if(board.getSpot(endX,endY).getPiece() == null || board.getSpot(endX,endY).getPiece().isWhite()){
                    return false;
                }
            }
        } else {
            //exception: if this is the pawn's first move it can move two spots
            if(!wasMoved){
                if (Math.abs(startX - endX) > 2) {
                    return false;
                }
                if (Math.abs(startY - endY) > 1) {
                    return false;
                }
            }else{
                //to make sure the pawn only moves one spot at a time
                if (Math.abs(startX - endX) > 1) {
                    return false;
                }
                if (Math.abs(startY - endY) > 1) {
                    return false;
                }
            }
            //to make sure the pawn only goes forward
            if (startX < endX) {
                return false;
            }

            if(endY != startY){
                if(board.getSpot(endX,endY).getPiece() == null || !board.getSpot(endX,endY).getPiece().isWhite()){
                    return false;
                }
            }
        }
        return super.canMove(board, start, end);
    }


    @Override
    public boolean move(Board board, Player player1, Player player2, int x, int y) {
        Spot start = board.getSpotOfPiece(this);
        Spot end = board.getSpot(x, y);
        if (canMove(board, start, end)) {
            wasMoved = true;
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
