import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Piece> availablePieces;
    private List<Piece> deadPieces;
    private boolean whiteSide;
    private String side;

    public Player(boolean whiteSide, Board board) {
        if (whiteSide) {
            side = "White";
        } else {
            side = "Black";
        }
        this.whiteSide = whiteSide;
        deadPieces = new ArrayList<>();
        availablePieces = new ArrayList<>();

        if (this.isWhiteSide()) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 8; j++) {
                    availablePieces.add(board.getSpot(i, j).getPiece());
                }
            }
        } else {
            for (int i = 6; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    availablePieces.add(board.getSpot(i, j).getPiece());
                }
            }
        }
    }

    public String getSide() {
        return side;
    }

    public List<Piece> getAvailablePieces() {
        return availablePieces;
    }

    public List<Piece> getDeadPieces() {
        return deadPieces;
    }

    public void printAvailablePieces() {
        System.out.println();
        System.out.println("Available pieces: ");
        for (Piece p : availablePieces) {
            System.out.print(p.getLabel() + " ");
        }
    }

    public void printDeadPieces() {
        System.out.println();
        System.out.print(side + " dead pieces: ");
        for (Piece p : deadPieces) {
            System.out.print(p.getLabel() + " ");
        }
    }

    public boolean isWhiteSide() {
        return whiteSide;
    }
}
