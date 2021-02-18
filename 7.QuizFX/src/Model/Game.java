package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Game instance = null;
    private User user;
    private List<Round> rounds = new ArrayList<>();
    private int score = 0;

    private Game(){

    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }
}
