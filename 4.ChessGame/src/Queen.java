import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queen extends Piece implements Move {

    public Queen(String label, boolean isWhite) {
        super(label, isWhite);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        //checking if the queen moves diagonally
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
            //for moving in straight line
            //moving vertically
            if (start.getY() == end.getY() && start.getX() != end.getX()) {
                //moving north
                if (start.getX() > end.getX()) {
                    int y = start.getY();
                    for (int i = start.getX() - 1; i >= end.getX(); i--) {
                        if (board.getSpot(i, y).getPiece() != null) {
                            if (board.getSpot(i, y).getPiece().isWhite() == this.isWhite()) {
                                return false;
                            }
                        }
                    }
                } else
                    //moving south
                    if (start.getX() < end.getX()) {
                        int y = start.getY();
                        for (int i = start.getX() + 1; i <= end.getX(); i++) {
                            if (board.getSpot(i, y).getPiece() != null) {
                                if (board.getSpot(i, y).getPiece().isWhite() == this.isWhite()) {
                                    return false;
                                }
                            }
                        }
                    }
            } else
                ///moving horizontally
                if (start.getX() == end.getX() && start.getY() != end.getY()) {
                    //moving right
                    if (start.getY() < end.getY()) {
                        int x = start.getX();
                        for (int i = start.getY() + 1; i <= end.getY(); i++) {
                            if (board.getSpot(x, i).getPiece() != null) {
                                if (board.getSpot(x, i).getPiece().isWhite() == this.isWhite()) {
                                    return false;
                                }
                            }
                        }
                    }
                    //moving left
                    if (start.getY() > end.getY()) {
                        int x = start.getX();
                        for (int i = start.getY() - 1; i >= end.getY(); i--) {
                            if (board.getSpot(x, i).getPiece() != null) {
                                if (board.getSpot(x, i).getPiece().isWhite() == this.isWhite()) {
                                    return false;
                                }
                            }
                        }
                    }
                } else {
                    return false;
                }
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
