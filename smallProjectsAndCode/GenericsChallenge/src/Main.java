// Create a generic class to implement a league table for a sport.
// The class should allow teams to be added to the list, and store
// a list of teams that belong to the league.
//
// Your class should have a method to print out the teams in order,
// with the team at the top of the league printed first.
//
// Only teams of the same type should be added to any particular
// instance of the league class - the program should fail to compile
// if an attempt is made to add an incompatible team.

import java.util.Collection;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        League<Team<BasketballTeam>> nba = new League<>("NBA");
        League<Team<FootballTeam>> nfl = new League<>("NFL");

        BasketballTeam hawks = new BasketballTeam("Atlanta Hawks",55);
        BasketballTeam lakers = new BasketballTeam("LA Lakers",85);
        BasketballTeam bulls = new BasketballTeam("Chicago Bulls",60);
        BasketballTeam mavs = new BasketballTeam("Dallas Mavericks",78);
        BasketballTeam warriors = new BasketballTeam("Golden State Warriors",48);
        BasketballTeam pistons = new BasketballTeam("Detroit Pistons",79);

        FootballTeam team1 = new FootballTeam("Team 1",50);
        FootballTeam team2 = new FootballTeam("Team 2",75);
        FootballTeam team3 = new FootballTeam("Team 3",100);

        nba.addTeam(hawks);
        nba.addTeam(lakers);
        nba.addTeam(bulls);
        nba.addTeam(mavs);
        nba.addTeam(warriors);
        nba.addTeam(pistons);
        System.out.println();

        nba.printTeams(nba.getTeams());

        Collections.sort(nba.getTeams());
        nba.printTeams(nba.getTeams());

        nfl.addTeam(team1);
        nfl.addTeam(team2);
        nfl.addTeam(team3);
        System.out.println();

        nfl.printTeams(nfl.getTeams());

        Collections.sort(nfl.getTeams());
        nfl.printTeams(nfl.getTeams());
    }
}
