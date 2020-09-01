public class Pion extends Piesa implements Mutari1 {

    public String symbol;
    public Pion() {
    }

    public  String getSymbol() {
        return symbol;
    }

    public Pion(int x, int y, String numePiesa, int player) {
        super(x, y, numePiesa,player);
        symbol = "P";
    }

    @Override
    public void mutaSus() {
        int xNou = getX();
        int yNou = getY() + 1;
        if (valideazaMutare(xNou,yNou)) {
            setY(yNou);

        } else {
            System.out.println("Mutare imposibila!");
        }
    }


}
