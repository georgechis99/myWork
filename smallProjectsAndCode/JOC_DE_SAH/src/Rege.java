public class Rege extends Piesa implements Mutari2{

    public String symbol;
    public Rege() {
    }

    public String getSymbol() {
        return symbol;
    }

    public Rege(int x, int y, String numePiesa, int player) {
        super(x, y, numePiesa,player);
        symbol = "R";
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

    @Override
    public void mutaJos() {
        int xNou = getX();
        int yNou = getY() - 1;
        if (valideazaMutare(xNou,yNou)) {
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDreapta() {
        int xNou = getX() + 1;
        int yNou = getY();
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaStanga() {
        int xNou = getX() - 1;
        int yNou = getY();
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }
}
