import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameImplementation {

    //instantiating the board
    public static Board board = new Board();

    //instantiating the two players
    public static Player player1 = new Player(false, board);
    public static Player player2 = new Player(true, board);

    //mapping pieces objects with string keys for enhanced user experience
    private static Map<String, Piece> whitePiecesMap = new HashMap<>();
    private static Map<String, Piece> blackPiecesMap = new HashMap<>();

    //instantiating objects for pieces on chess board to get their reference
    //black pieces
    static Pawn p0b = (Pawn) (board.getSpot(6, 0).getPiece());
    static Pawn p1b = (Pawn) (board.getSpot(6, 1).getPiece());
    static Pawn p2b = (Pawn) (board.getSpot(6, 2).getPiece());
    static Pawn p3b = (Pawn) (board.getSpot(6, 3).getPiece());
    static Pawn p4b = (Pawn) (board.getSpot(6, 4).getPiece());
    static Pawn p5b = (Pawn) (board.getSpot(6, 5).getPiece());
    static Pawn p6b = (Pawn) (board.getSpot(6, 6).getPiece());
    static Pawn p7b = (Pawn) (board.getSpot(6, 7).getPiece());

    static Castle c1b = (Castle) (board.getSpot(7, 0).getPiece());
    static Castle c2b = (Castle) (board.getSpot(7, 7).getPiece());

    static Horse h1b = (Horse) (board.getSpot(7, 1).getPiece());
    static Horse h2b = (Horse) (board.getSpot(7, 6).getPiece());

    static Bishop b1b = (Bishop) (board.getSpot(7, 2).getPiece());
    static Bishop b2b = (Bishop) (board.getSpot(7, 5).getPiece());

    static Queen qb = (Queen) (board.getSpot(7, 4).getPiece());

    static King kb = (King) (board.getSpot(7, 3).getPiece());

    //white pieces
    static Pawn p0w = (Pawn) (board.getSpot(1, 0).getPiece());
    static Pawn p1w = (Pawn) (board.getSpot(1, 1).getPiece());
    static Pawn p2w = (Pawn) (board.getSpot(1, 2).getPiece());
    static Pawn p3w = (Pawn) (board.getSpot(1, 3).getPiece());
    static Pawn p4w = (Pawn) (board.getSpot(1, 4).getPiece());
    static Pawn p5w = (Pawn) (board.getSpot(1, 5).getPiece());
    static Pawn p6w = (Pawn) (board.getSpot(1, 6).getPiece());
    static Pawn p7w = (Pawn) (board.getSpot(1, 7).getPiece());

    static Castle c1w = (Castle) (board.getSpot(0, 0).getPiece());
    static Castle c2w = (Castle) (board.getSpot(0, 7).getPiece());

    static Horse h1w = (Horse) (board.getSpot(0, 1).getPiece());
    static Horse h2w = (Horse) (board.getSpot(0, 6).getPiece());

    static Bishop b1w = (Bishop) (board.getSpot(0, 2).getPiece());
    static Bishop b2w = (Bishop) (board.getSpot(0, 5).getPiece());

    static Queen qw = (Queen) (board.getSpot(0, 4).getPiece());

    static King kw = (King) (board.getSpot(0, 3).getPiece());

    //instantiating a scanner to read input from the user
    private static Scanner scanner = new Scanner(System.in);

    static File outputFile;
    static FileWriter writer;

    public static void instantiateMaps() {
        //populating the maps                      // these maps are used to associate each object(Piece) with a String
        //black                                        // key for ease of access (the keys will validate the Strings
        blackPiecesMap.put("p0b", p0b);                // taken from the user input)
        blackPiecesMap.put("p1b", p1b);
        blackPiecesMap.put("p2b", p2b);
        blackPiecesMap.put("p3b", p3b);
        blackPiecesMap.put("p4b", p4b);
        blackPiecesMap.put("p5b", p5b);
        blackPiecesMap.put("p6b", p6b);
        blackPiecesMap.put("p7b", p7b);
        blackPiecesMap.put("c1b", c1b);
        blackPiecesMap.put("c2b", c2b);
        blackPiecesMap.put("h1b", h1b);
        blackPiecesMap.put("h2b", h2b);
        blackPiecesMap.put("b1b", b1b);
        blackPiecesMap.put("b2b", b2b);
        blackPiecesMap.put("qb", qb);
        blackPiecesMap.put("kb", kb);

        //white
        whitePiecesMap.put("p0w", p0w);
        whitePiecesMap.put("p1w", p1w);
        whitePiecesMap.put("p2w", p2w);
        whitePiecesMap.put("p3w", p3w);
        whitePiecesMap.put("p4w", p4w);
        whitePiecesMap.put("p5w", p5w);
        whitePiecesMap.put("p6w", p6w);
        whitePiecesMap.put("p7w", p7w);
        whitePiecesMap.put("c1w", c1w);
        whitePiecesMap.put("c2w", c2w);
        whitePiecesMap.put("h1w", h1w);
        whitePiecesMap.put("h2w", h2w);
        whitePiecesMap.put("b1w", b1w);
        whitePiecesMap.put("b2w", b2w);
        whitePiecesMap.put("qw", qw);
        whitePiecesMap.put("kw", kw);

    }

    public static void startChessGame() throws IOException {        // this mathod actually handles the game

        //creating the file to get game information (moves details and so)
        outputFile = new File("src\\outputfile.txt");
        try {
            writer = new FileWriter("outputfile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("                 <<<CHESS GAME STARTED>>>");
        System.out.println();
        GraphicalRepresentationOfBoard.boardSchematic(board);
        int movesCount = 0;
        boolean wFirstMove = true;
        boolean bFirstMove = true;
        while (true) {
            if (gameOver(player1, player2, kb, kw)) {
                System.out.println("Moves made: " + movesCount);
                System.exit(0);
            }
            if (movesCount % 2 == 0) {       //WHITE MOVES
                while (true) {
                    //checking if white king is in chess
                    if(chess(player1,kw)){
                        System.out.println();
                        System.out.println("White king is being attacked! Make a move to save him!");
                    }
                    if (moveWhite(scanner)) {
                        // checking if chess mate
                        if(chess(player1,kw)){
                            player2.getAvailablePieces().remove(kw);
                        }
                        movesCount++;
                        if(wFirstMove){
                            movesCount--;
                            wFirstMove = false;
                        }
                        GraphicalRepresentationOfBoard.boardSchematic(board);
                        break;
                    } else {
                        System.out.println("Invalid piece/move. Try again!");
                    }
                }
            } else {                         //BLACK MOVES
                while (true) {
                    //checking if black king is in chess
                    if(chess(player2,kb)){
                        System.out.println();
                        System.out.println("Black king is being attacked! Make a move to save him!");
                    }

                    if (moveBlack(scanner)) {
                        // checking if chess mate
                        if(chess(player2,kb)){
                            player1.getAvailablePieces().remove(kb);
                        }
                        movesCount++;
                        if(bFirstMove){
                            movesCount--;
                            bFirstMove = false;
                        }
                        GraphicalRepresentationOfBoard.boardSchematic(board);
                        break;
                    } else {
                        System.out.println("Invalid piece/move. Try again!");
                    }
                }
            }
        }
    }

    public static boolean moveWhite(Scanner scanner) throws IOException {
        System.out.println();
        System.out.println("WHITE moves: ");
        System.out.print("Piece: ");
        String input = scanner.nextLine();
        if (whitePiecesMap.containsKey(input)) {
            Piece p = whitePiecesMap.get(input);
            if(!printLegal((Piece & Move) p)){
                return false;
            }
            System.out.println();
            System.out.print("X: ");
            int x = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Y: ");
            int y = scanner.nextInt();
            scanner.nextLine();
            if (x > 7 || y > 7 || x < 0 || y < 0) {
                return false;
            }
            return movePiece(p, x, y);
        } else {
            return false;
        }
    }

    public static boolean moveBlack(Scanner scanner) throws IOException {  //method to move a black piece
        System.out.println();
        System.out.println("BLACK moves: ");
        System.out.print("Piece: ");  //asking for input
        String input = scanner.nextLine();  //getting input
        if (blackPiecesMap.containsKey(input)) {  //validating the input
            Piece p = blackPiecesMap.get(input);
            if(!printLegal((Piece & Move) p)){  //checking if the piece cannot make any moves , then it will give you
                return false;                      // an error message and get you to try again
            }
            System.out.println();
            System.out.print("X: "); // X coordinates for the move
            int x = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Y: ");
            int y = scanner.nextInt(); // Y coordinates for the move
            scanner.nextLine();
            if (x > 7 || y > 7 || x < 0 || y < 0) { //validating the coordinates
                return false;
            }
            return movePiece(p, x, y);  // actually move the piece on the board
        } else {
            return false;
        }
    }

    public static void printLegalMoves(List<Spot> legalMoves) { // method to display all the legal moves a piece can make
        System.out.println("Legal moves: ");
        for (Spot moves : legalMoves) {
            System.out.print("[" + moves.getX() + "," + moves.getY() + "]");
            System.out.print(" ; ");
        }
    }

    public static <T extends Piece & Move> boolean printLegal(T t) {  //method called in the printLegalMoves
        List<Spot> legalSpots = t.legalMoves(board);
        if(legalSpots.size() == 0){
            return false;
        }
        printLegalMoves(legalSpots);
        return true;
    }

    public static boolean movePiece(Piece p, int x, int y) throws IOException {  //static method which calls the move() method which is
        Player p1;                                               // overriden in each Piece derived class (Pawn, Castle, etc.
        Player p2;                                               // So this method checks the type of Piece derived objects
        if (p.isWhite()) {                                       // and calls the accordingly overriden move() method
            p1 = player2;
            p2 = player1;
        } else {
            p1 = player1;
            p2 = player2;
        }
        //these next 2 lines get information of the move made and write it to a file
        String moveData = getMoveData(p, x, y);
        writer.write(moveData);
        if (!(p1.getAvailablePieces().contains(p))) {
            return false;
        }
        if (p instanceof Pawn) {
            return ((Pawn) p).move(board, p1, p2, x, y);
        } else if (p instanceof Castle) {
            return ((Castle) p).move(board, p1, p2, x, y);
        } else if (p instanceof Horse) {
            return ((Horse) p).move(board, p1, p2, x, y);
        } else if (p instanceof Bishop) {
            return ((Bishop) p).move(board, p1, p2, x, y);
        } else if (p instanceof Queen) {
            return ((Queen) p).move(board, p1, p2, x, y);
        } else if (p instanceof King) {
            return ((King) p).move(board, p1, p2, x, y);
        } else {
            System.out.println("ERROR");
            return false;
        }
    }

    public static boolean gameOver(Player p1, Player p2, King k1, King k2) throws IOException {  //this method checks if the game is over
        if (!(p1.getAvailablePieces().contains(k1))) {                          // by checking if one of the kings has
            System.out.println();                                               // been killed (not the most traditional
            System.out.println("                 <<<GAME OVER>>>");             // way of thinking if we are thinking of
            System.out.println("                 <<<WHITE WINS>>>");            // the real chess game, but it does the job)
            writer.close();
            return true;
        }
        if (!(p2.getAvailablePieces().contains(k2))) {
            System.out.println();
            System.out.println("                 <<<GAME OVER>>>");
            System.out.println("                 <<<BLACK WINS>>>");
            writer.close();
            return true;
        }
        return false;
    }

    //checking if the king is under attack
    public static boolean chess(Player p, King k) {     //beware to call this method with the king of one player and the
        List<Piece> allPieces = p.getAvailablePieces();   // opposite player, as the method searches through all the
        Spot kingSpot = board.getSpotOfPiece(k);          // other player's moves to check if they can reach the
        for (Piece piece : allPieces) {                   // (other's player) king
            List<Spot> pieceLegalSpots = piece.legalMoves(board);
            for (Spot spot : pieceLegalSpots) {
                if (spot.equals(kingSpot)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String getMoveData(Piece p, int x, int y){
        StringBuilder moveData = new StringBuilder();
        Spot start = board.getSpotOfPiece(p);
        moveData.append(p.getLabel()).append(" : [").append(start.getX()).append(",").append(start.getY()).append("] -> [").append(x).append(",").append(y).append("]");
        moveData.append("\n");
        return moveData.toString();
    }
}
