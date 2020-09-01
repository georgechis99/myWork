public class Main {

    public static void main(String[] args) {

        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");
        SoccerPlayer messi = new SoccerPlayer("Messi");

        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        adelaideCrows.addPlayer(joe);
//        adelaideCrows.addPlayer(pat);
//        adelaideCrows.addPlayer(beckham);

        System.out.println(adelaideCrows.numPlayers());

        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(pat);

        Team<SoccerPlayer> fTeam1 = new Team<>("Team 1");
        fTeam1.addPlayer(beckham);

        Team<SoccerPlayer> fTeam2 = new Team<>("Team 2");
        fTeam2.addPlayer(messi);

        fTeam1.matchResult(fTeam2,10,55);
//        fTeam1.matchResult(baseballTeam,10,5);

    }
}
