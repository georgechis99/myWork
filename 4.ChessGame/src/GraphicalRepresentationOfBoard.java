public class GraphicalRepresentationOfBoard {

    //this is a schematic to keep track of the pieces on the board
    public static void boardSchematic(Board board) {
        System.out.print("         ");
        for (int i = 0; i < 8; i++){
            System.out.print("  " + i + "  ");
        }
        System.out.println();
        System.out.print("       __");
        for (int i = 0; i < 8; i++){
            System.out.print("_____");
        }
        for (int i = 0; i < 8; i++) {
            System.out.println();
            System.out.print("     |");
            System.out.println();
            System.out.print("  " + i + "  " + "|   ");
            for (int j = 0; j < 8; j++) {
                if (board.getSpot(i, j).getPiece() == null) {
                    System.out.print(" __  ");
                } else {
                    if(board.getSpot(i, j).getPiece().getLabel().length() == 3){
                        System.out.print(" " + board.getSpot(i, j).getPiece().getLabel() + " ");
                    } else if(board.getSpot(i, j).getPiece().getLabel().length() == 2){
                        System.out.print(" " + board.getSpot(i, j).getPiece().getLabel() + "  ");
                    } else if(board.getSpot(i, j).getPiece().getLabel().length() == 1){
                        System.out.print("  " + board.getSpot(i, j).getPiece().getLabel() + "  ");
                    }
                }
            }
        }
        System.out.println();
        System.out.print("       __");
        for (int i = 0; i < 8; i++){
            System.out.print("_____");
        }
        System.out.println();
        GameImplementation.player1.printDeadPieces();
        GameImplementation.player2.printDeadPieces();
        System.out.println();
    }


}
