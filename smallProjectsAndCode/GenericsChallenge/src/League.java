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

import java.util.ArrayList;
import java.util.Collection;

public class League<T extends Team> {

    private String name;
    private ArrayList<T> teams = new ArrayList<>();

    public League(String name) {
        this.name = name;
    }

    public ArrayList<T> getTeams() {
        return teams;
    }

    public boolean addTeam(T team) {
        if (teams.contains(team)) {
            System.out.println(team.getName() + " is already in the league.");
            return false;
        } else {
            teams.add(team);
            System.out.println(team.getName() + " introduced in the league " + this.name);
            return true;
        }
    }

    public void printTeams(ArrayList<T> teams){
        for(Object o : teams){
            System.out.println(((Team)o).getName());
        }
        System.out.println();
    }
}
