public class Tura extends Piesa implements Mutari3 {

    public  String symbol;
    public Tura() {
    }

    public  String getSymbol() {
        return symbol;
    }

    public Tura(int x, int y, String numePiesa, int player) {
        super(x, y, numePiesa,player);
        symbol = "T";
    }

    @Override
    public void mutaSus(int nrPozitii) {
        int xNou = getX();
        int yNou = getY() + nrPozitii;
        if (valideazaMutare(xNou,yNou)) {
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaJos(int nrPozitii) {
        int xNou = getX();
        int yNou = getY() - nrPozitii;
        if (valideazaMutare(xNou,yNou)) {
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDreapta(int nrPozitii) {
        int xNou = getX() + nrPozitii;
        int yNou = getY();
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaStanga(int nrPozitii) {
        int xNou = getX() - nrPozitii;
        int yNou = getY();
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }
}
