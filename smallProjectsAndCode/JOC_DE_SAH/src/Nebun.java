public class Nebun extends Piesa implements Mutari4 {

    public  String symbol;
    public Nebun() {
    }

    public String getSymbol() {
        return symbol;
    }

    public Nebun(int x, int y, String numePiesa, int player) {
        super(x, y, numePiesa,player);
        symbol = "N";
    }

    @Override
    public void mutaDiagonalaSusDreapta(int nrPozitii) {
        int xNou = getX() + nrPozitii;
        int yNou = getY() + nrPozitii;
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDiagonalaSusStanga(int nrPozitii) {
        int xNou = getX() - nrPozitii;
        int yNou = getY() + nrPozitii;
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDiagonalaJosDreapta(int nrPozitii) {
        int xNou = getX() + nrPozitii;
        int yNou = getY() - nrPozitii;
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDiagonalaJosStanga(int nrPozitii) {
        int xNou = getX() - nrPozitii;
        int yNou = getY() - nrPozitii;
        if (valideazaMutare(xNou,yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }
}
