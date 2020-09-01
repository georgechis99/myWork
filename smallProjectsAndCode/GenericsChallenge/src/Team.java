public abstract class Team<T> implements Comparable<Team<T>>{

    private String name;
    private int ranking;

    public Team(String name, int ranking) {
        this.name = name;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    @Override
    public int compareTo(Team<T> o) {
        if(this.ranking > o.ranking){
            return -1;
        }else if(this.ranking < o.ranking){
            return 1;
        }else{
            return 0;
        }
    }
}
