
public class Horse extends Piece implements Move {

    public Horse(String label, boolean isWhite) {
        super(label, isWhite);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        //to avoid ambiguity and code multiplication
        int x = start.getX();
        int y = start.getY();
        int nx = end.getX();
        int ny = end.getY();

        if (!(nx == x - 2 && ny == y + 1) && !(nx == x - 1 && ny == y + 2) &&
                !(nx == x + 1 && ny == y + 2) && !(nx == x + 2 && ny == y + 1) &&
                !(nx == x + 2 && ny == y - 1) && !(nx == x + 1 && ny == y - 2) &&
                !(nx == x - 1 && ny == y - 2) && !(nx == x - 2 && ny == y - 1)) {

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