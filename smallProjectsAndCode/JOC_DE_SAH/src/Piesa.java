public class Piesa {

    private int x;
    private int y;
    private String numePiesa;
    private int player;

    public Piesa() {
    }

    Piesa(int x, int y, String numePiesa,int player) {
        if (x > 0 && x < 9 && y > 0 && y < 9) {
            this.x = x;
            this.y = y;
            this.numePiesa = numePiesa;
            this.player = player;
        } else {
            System.out.println("Coordonatele introduse nu se afla pe tabla de sah!");
        }
    }

    public String getNumePiesa() {
        return numePiesa;
    }

    public int getPlayer() {
        return player;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public void setNumePiesa(String numePiesa) {
        this.numePiesa = numePiesa;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    void coordonatePiesa() {
        System.out.println(this.numePiesa + " se afla pe pozitia [" + x + ";" + y + "].");
    }

    boolean valideazaMutare(int xNou, int yNou) {
        return xNou > 0 && xNou < 9 && yNou > 0 && yNou < 9;
    }

    void iaPiesa(){
        setX(-1);
        setY(-1);
        System.out.println(getNumePiesa() + " a fost eliminat/a de catre adversar!");
    }
}
