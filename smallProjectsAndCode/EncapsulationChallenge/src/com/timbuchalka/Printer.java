package com.timbuchalka;

public class Printer {

    private int tonerLevel = 50;
    private int numberOfPagesPrinted = 0;
    private boolean duplexPrinter;

    public Printer(int tonerLevel, int numberOfPagesPrinted, boolean duplexPrinter) {
        if(tonerLevel >= 0 && tonerLevel <= 100){
            this.tonerLevel = tonerLevel;
            System.out.println("Toner level is " + tonerLevel + "%");
        }else{
            System.out.println("Toner level must be between 0% and 100%! Default value of 50% was set.");
        }
        this.numberOfPagesPrinted = numberOfPagesPrinted;
        System.out.println("Until now " + numberOfPagesPrinted + " pages were printed.");
        this.duplexPrinter = duplexPrinter;
        if(duplexPrinter){
            System.out.println("This is a duplex printer.");
        }else{
            System.out.println("This is not a duplex printer.");
        }
    }

    public int getTonerLevel() {
        return tonerLevel;
    }

    public int getNumberOfPagesPrinted() {
        return numberOfPagesPrinted;
    }

    public boolean isDuplexPrinter() {
        return duplexPrinter;
    }

    public void fillUpToner(){
        tonerLevel = 100;
        System.out.println("Toner was fully filled up. (100%)");
    }

    public void printingPage(){
        if(duplexPrinter){
            System.out.println("Duplicated page was printed.");
            numberOfPagesPrinted += 2;
            tonerLevel -= 2;
            System.out.println("Until now " + numberOfPagesPrinted + " pages were printed.");
            System.out.println("Toner level is " + tonerLevel + "%");
        }else{
            System.out.println("Page was printed.");
            numberOfPagesPrinted++;
            tonerLevel--;
            System.out.println("Until now " + numberOfPagesPrinted + " pages were printed.");
            System.out.println("Toner level is " + tonerLevel + "%");
        }
    }
}
