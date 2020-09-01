package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer(100, 0, false);
        for (int i = 0; i <= 105; i++) {
            printer.printingPage();
            if (printer.getTonerLevel() <= 0) {
                System.out.println("You ran out of toner. Filling up...");
                printer.fillUpToner();
                break;
            }
        }
    }
}
