package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        int count = 1;
        while(count != 6){
            System.out.println("Count value : " + count);
            count++;
        }

        //doWhile always executes the code at least once
        //this example shows how count was 6, then it was
        //incremented once so the While statement will never
        //be true, so it will basically run an infinite loop
//        count = 6;
//        do{
//            System.out.println("Count value was : " + count);
//            count++;
//        }while(count != 6);
        int number = 4;
        int finishNumber = 20;
        int countEven = 0 ;

        while(number <= finishNumber){
            number++;
            if(!isEvenNumber(number)){
                continue;
            }

                System.out.println("Even number " + number);
                countEven++;
            if(countEven >= 5)
                break;
        }

        System.out.println("Total number of even numbers found = " + countEven);
    }

    public static boolean isEvenNumber(int Number){
        if(Number%2 == 0)
            return true;
        else
            return false;
    }

}
