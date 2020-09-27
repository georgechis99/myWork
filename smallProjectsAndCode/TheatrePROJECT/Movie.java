public abstract class Movie {
    String movieName;
    String movieDuration;
    String releaseDate;
    String genere;
    String description;

    public void generalInfo() {
        System.out.println(movieName + " | " + movieDuration + " | " + releaseDate + " | " + genere);
    }

    public void getMovieInfo() {
        generalInfo();
        System.out.println();
        System.out.println(description);
        System.out.println();
    }

}
