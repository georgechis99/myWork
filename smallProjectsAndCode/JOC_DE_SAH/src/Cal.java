public class Cal extends Piesa implements Mutari5{

    public  String symbol;
    public Cal() {
    }

    public String getSymbol() {
        return symbol;
    }

    public Cal(int x, int y, String numePiesa, int player) {
        super(x, y, numePiesa,player);
        symbol = "C";
    }

    @Override
    public void mutaSusDreapta() {
        int xNou = getX() + 1;
        int yNou = getY() + 3;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaSusStanga() {
        int xNou = getX() - 1;
        int yNou = getY() + 3;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaJosDreapta() {
        int xNou = getX() + 1;
        int yNou = getY() - 3;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaJosStanga() {
        int xNou = getX() - 1;
        int yNou = getY() - 3;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDreaptaSus() {
        int xNou = getX() + 3;
        int yNou = getY() + 1;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaDreaptaJos() {
        int xNou = getX() + 3;
        int yNou = getY() - 1;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaStangaSus() {
        int xNou = getX() - 3;
        int yNou = getY() + 1;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }

    @Override
    public void mutaStangaJos() {
        int xNou = getX() - 3;
        int yNou = getY() - 1;
        if (valideazaMutare(xNou, yNou)) {
            setX(xNou);
            setY(yNou);
        } else {
            System.out.println("Mutare imposibila!");
        }
    }
}