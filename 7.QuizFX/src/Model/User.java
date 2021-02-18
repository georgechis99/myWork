package Model;

public class User {

    private String username;
    private String password;
    private double score;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
