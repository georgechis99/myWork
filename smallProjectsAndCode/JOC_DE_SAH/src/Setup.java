public class Setup {

    private String[][] tablaDeSah = new String[9][9];

    public Setup() {
    }


    public void startSetup() throws NullPointerException {

        try {
            Piesa[] piese = new Piesa[34];

            piese[1] = new Pion(1, 2, "P1_1", 1);
            piese[2] = new Pion(2, 2, "P1_2", 1);
            piese[3] = new Pion(3, 2, "P1_3", 1);
            piese[4] = new Pion(4, 2, "P1_4", 1);
            piese[5] = new Pion(5, 2, "P1_5", 1);
            piese[6] = new Pion(6, 2, "P1_6", 1);
            piese[7] = new Pion(7, 2, "P1_7", 1);
            piese[8] = new Pion(8, 2, "P1_8", 1);

            piese[9] = new Pion(1, 7, "P2_1", 2);
            piese[10] = new Pion(2, 7, "P2_2", 2);
            piese[11] = new Pion(3, 7, "P2_3", 2);
            piese[12] = new Pion(4, 7, "P2_4", 2);
            piese[13] = new Pion(5, 7, "P2_5", 2);
            piese[14] = new Pion(6, 7, "P2_6", 2);
            piese[15] = new Pion(7, 7, "P2_7", 2);
            piese[16] = new Pion(8, 7, "P2_8", 2);

            piese[17] = new Tura(1, 1, "T1_1", 1);
            piese[18] = new Tura(8, 1, "T1_2", 1);

            piese[19] = new Tura(1, 8, "T2_1", 2);
            piese[20] = new Tura(8, 8, "T2_2", 2);

            piese[21] = new Cal(2, 1, "C1_1", 1);
            piese[22] = new Cal(7, 1, "C1_2", 1);

            piese[23] = new Cal(2, 8, "C2_1", 2);
            piese[24] = new Cal(7, 8, "C2_2", 2);

            piese[25] = new Nebun(3, 1, "N1_1", 1);
            piese[26] = new Nebun(6, 1, "N1_2", 1);

            piese[27] = new Nebun(3, 8, "N2_1", 2);
            piese[28] = new Nebun(6, 8, "N2_2", 2);

            piese[29] = new Rege(4, 1, "R1", 1);

            piese[30] = new Rege(4, 8, "R2", 2);

            piese[31] = new Regina(5, 1, "r1", 1);

            piese[32] = new Regina(5, 8, "r2", 2);

            int buff = 1;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    if (piese[buff].getX() == j && piese[buff].getY() == i) {
                        tablaDeSah[i][j] = piese[buff].getNumePiesa();
                    } else {
                        tablaDeSah[i][j] = "0";
                    }
                    buff++;
                }
            }
        } catch (Exception e){
            System.out.println("Error!");
    }

//        for (int i = 1; i < 9; i++) {
//            for (int j = 1; j < 9; j++) {
//                tablaDeSah[i][j] = "0";
//            }
//        }
//
//        for (int i = 1; i < 9; i++) {
//            tablaDeSah[i][2] = Pion.symbol;
//            tablaDeSah[i][7] = Pion.symbol;
//        }
//
//
//        tablaDeSah[1][1] = Tura.symbol;
//        tablaDeSah[1][8] = Tura.symbol;
//        tablaDeSah[8][1] = Tura.symbol;
//        tablaDeSah[8][8] = Tura.symbol;
//
//        tablaDeSah[2][1] = Cal.symbol;
//        tablaDeSah[7][1] = Cal.symbol;
//        tablaDeSah[2][8] = Cal.symbol;
//        tablaDeSah[7][8] = Cal.symbol;
//
//        tablaDeSah[3][1] = Nebun.symbol;
//        tablaDeSah[6][1] = Nebun.symbol;
//        tablaDeSah[3][8] = Nebun.symbol;
//        tablaDeSah[6][8] = Nebun.symbol;
//
//        tablaDeSah[4][1] = Rege.symbol;
//        tablaDeSah[4][8] = Rege.symbol;
//
//        tablaDeSah[5][1] = Regina.symbol;
//        tablaDeSah[5][8] = Regina.symbol;


    }

    void printSetup() {

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                System.out.print(tablaDeSah[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
