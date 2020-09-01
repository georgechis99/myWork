public class Regina extends Piesa implements Mutari3, Mutari4 {

    public String symbol;
    public Regina() {
    }

    public String getSymbol() {
        return symbol;
    }

    public Regina(int x, int y, String numePiesa, int player) {
        super(x, y, numePiesa, player);
        if (player == 1) {
            symbol = "r ";
        }else{
            symbol = "r";
        }
    }

    @Override
    public void mutaSus(int nrPozitii) {
        int xNou = getX();
        int yNou = getY() + nrPozitii;
        if (valideazaMutare(xNou, yNou)) {
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaJos(int nrPozitii) {
        int xNou = getX();
        int yNou = getY() - nrPozitii;
        if (valideazaMutare(xNou, yNou)) {
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDreapta(int nrPozitii) {
        int xNou = getX() + nrPozitii;
        int yNou = getY();
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaStanga(int nrPozitii) {
        int xNou = getX() - nrPozitii;
        int yNou = getY();
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDiagonalaSusDreapta(int nrPozitii) {
        int xNou = getX() + nrPozitii;
        int yNou = getY() + nrPozitii;
        if (valideazaMutare(xNou, yNou)) {
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
        if (valideazaMutare(xNou, yNou)) {
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
        if (valideazaMutare(xNou, yNou)) {
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
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }
}
