package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        if(score < 5000 && score > 1000) {
            System.out.println("Your score was greater than 1000 , but lesser than 5000");
        }
            else if(score <1000){
            System.out.println("Your score was lesser than 1000");
        }
            else
        {
            System.out.println("Your score was greater than 5000");
        }

            if(gameOver)
            {
                int finalScore = score + (levelCompleted * bonus);
                System.out.println("Your final score was :" + finalScore);
            }

        int secondScore = 10000;
        int secondLevelCompleted = 8;
        int secondBonus = 200;

        if(gameOver) {
            int secondFinalScore = secondScore + (secondLevelCompleted * secondBonus);
            System.out.println(secondFinalScore);
        }
    }
}
