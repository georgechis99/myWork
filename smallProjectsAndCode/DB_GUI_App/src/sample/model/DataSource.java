package sample.model;

import javafx.beans.property.SimpleIntegerProperty;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    //connection String + naming conventional Strings
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\ACER DEMO\\Desktop\\Faculta\\Java\\" +
            "GitHub Respository\\myWork\\smallProjectsAndCode\\DB_GUI_App\\" + DB_NAME;

    //constants for the ALBUMS
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    //constants for the ARTISTS
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    //constants for the SONGS
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final String COLUMN_SONG_ARTIST = "artist";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    //constants for ORDERING Lists
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    //constants for BUILDING QUERIES easier

    //ALBUMS by ARTIST
    public static final String QUERY_ALBUMS_BY_ARTIST_START = "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME +
            " FROM " + TABLE_ALBUMS + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME +
            " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT = " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME +
            " COLLATE NOCASE ";

    //ALL ARTISTS
    public static final String QUERY_ALL_ARTISTS_START = "SELECT * FROM " + TABLE_ARTISTS;

    public static final String QUERY_ALL_ARTISTS_SORT = " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " COLLATE NOCASE ";

    //SONG + ALBUM + ARTIST where the song is the parameter
//    SELECT artists.name , albums.name , songs.track FROM songs INNER JOIN albums ON songs.album = albums._id
//    INNER JOIN artists ON albums.artist = artists._id
//    WHERE songs.title = "Go Your Own Way"
//    ORDER BY artists.name,albums.name COLLATE NOCASE ASC
    public static final String QUERY_ARTIST_FOR_SONG_START = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " , " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " , " + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." +
            COLUMN_ALBUM_ID + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_SORT = " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " , " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    //query builder to CREATE VIEW (artist_list)
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW + " AS SELECT " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " AS " + COLUMN_SONG_ARTIST + " , " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + " , " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + " , " +
            TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " , " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " , " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    //query builder to interrogate the created VIEW
    //SELECT name,album,track FROM artist_list WHERE title = "etc"
    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTIST_NAME + " , " + COLUMN_SONG_ALBUM + " , " +
            COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE +
            " = \"";

    //Same select as aboce but for PREPARED STATEMENT !!! --> this ensures protection against injection attacks
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + " , " + COLUMN_SONG_ALBUM + " , " +
            COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE +
            " = ?";

    //prepared query to INSERT ARTIST
    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            "(" + COLUMN_ARTIST_NAME + ") VALUES(?)";

    //prepared query to INSERT ALBUM
    public static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS +
            "(" + COLUMN_ALBUM_NAME + " , " + COLUMN_ALBUM_ARTIST + ") VALUES (? , ?)";

    //prepared query to INSERT SONG
    public static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS +
            "(" + COLUMN_SONG_TRACK + " , " + COLUMN_SONG_TITLE + " , " + COLUMN_SONG_ALBUM +
            ") VALUES(? , ? , ?)";

    public static final String TABLE_USERS = "users";

    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PHONE = "phone";

    //INSERT USER
    public static final String INSERT_USER = "INSERT INTO " + TABLE_USERS +
            "(" + COLUMN_USER_USERNAME + " , " + COLUMN_USER_PASSWORD + " , " + COLUMN_USER_EMAIL + " , " + COLUMN_USER_PHONE +
            ") VALUES(? , ? , ?, ?)";


    //queries on ARTISTS , and ALBUMS to check if the artist/album containing the SONG we want to ADD already exist (returns the _id IF FOUND)
    public static final String QUERY_ARTISTS = "SELECT " + COLUMN_ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE " +
            COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUMS = "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " +
            COLUMN_ALBUM_NAME + " = ?";

    public static final String QUERY_ALBUMS_BY_ARTIST_ID = "SELECT * FROM " + TABLE_ALBUMS + " WHERE " +
            COLUMN_ALBUM_ARTIST + " = ? ORDER BY " + COLUMN_ALBUM_NAME + " COLLATE NOCASE";

    //////////////////////////////////////////////////////////////////////////////
    //querying the tableview (only 25 records)
    public static final String QUERY_VIEW = "SELECT * FROM " + TABLE_ARTIST_SONG_VIEW +" LIMIT ?" ;

    //querying the full tableview
    public static final String QUERY_VIEW_FULL = "SELECT * FROM " + TABLE_ARTIST_SONG_VIEW ;

    //gets the total number of records in the DB
    public static final String RECORD_COUNT = "SELECT COUNT(*) FROM " + TABLE_ARTIST_SONG_VIEW;

    //query to filter by artist
    public static final String ARTIST_SEARCH = "SELECT * FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + "name" +
            " LIKE \"%" + "?" + "%\"";

    //query to filter by album
    public static final String ALBUM_SEARCH = "SELECT * FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_ALBUM +
            " LIKE \"" + "%?%" + "\"";

    //query to filter by song title
    public static final String SONG_TITLE_SEARCH = "SELECT * FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE +
            " LIKE \"" + "%?%" + "\"";

    private Connection conn;

    private PreparedStatement querySongInfoView; //better than a simple statement because : 1) it is only declared/close once
    //                                   2) it ensures protection from injection attacks (WE CAN"T INJECT SQL STATEMENTS,
    //                                   ONLY STRINGS FOR THE PARAMETERS)
    //                               eg. Go Your Own Way " or 1=1 or " --> this will display all that data in a table
    //                        not just data for the respective song ; if we use PreparedStatement this will not be possible
    // usage : SELECT * FROM ? WHERE title = ? --> .setString(1, parameter1); this is for the FIRST question mark "?"
    //                                         --> .setString(2 , parameter2); this is for the SECOND question mark "?"
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtists;
    private PreparedStatement queryAlbums;
    private PreparedStatement queryAlbumsByArtistId;

    private PreparedStatement insertIntoUsers;

    private PreparedStatement queryView;
    private PreparedStatement queryViewFull;

    private PreparedStatement searchForArtist;
    private PreparedStatement searchForAlbum;
    private PreparedStatement searchForSongTitle;


    //private constructor, static instance and getInstance() method to MAKE THE CLASS SINGLETON
    private static DataSource instance = new DataSource();

    private DataSource() {
    }

    public static DataSource getInstance() {
        return instance;
    }


    //method to open the DataSource
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);

            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONG);

            queryArtists = conn.prepareStatement(QUERY_ARTISTS);
            queryAlbums = conn.prepareStatement(QUERY_ALBUMS);
            queryAlbumsByArtistId = conn.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);

            insertIntoUsers = conn.prepareStatement(INSERT_USER);

            queryView = conn.prepareStatement(QUERY_VIEW);
            queryViewFull = conn.prepareStatement(QUERY_VIEW_FULL);

            searchForArtist = conn.prepareStatement(ARTIST_SEARCH);
            searchForAlbum = conn.prepareStatement(ALBUM_SEARCH);
            searchForSongTitle = conn.prepareStatement(SONG_TITLE_SEARCH);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
            return false;
        }
    }

    //method to close the DataSource
    public void close() {
        try {
            if (querySongInfoView != null) {
                querySongInfoView.close();
            }
            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if (queryArtists != null) {
                queryArtists.close();
            }
            if (queryAlbums != null) {
                queryAlbums.close();
            }

            if (queryAlbumsByArtistId != null) {
                queryAlbumsByArtistId.close();
            }

            if(insertIntoUsers != null) {
                insertIntoUsers.close();
            }

            if(queryView != null){
                queryView.close();
            }

            if(queryViewFull != null){
                queryViewFull.close();
            }

            if(searchForArtist != null){
                searchForArtist.close();
            }

            if(searchForAlbum != null){
                searchForAlbum.close();
            }

            if(searchForSongTitle != null){
                searchForSongTitle.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public int getViewCount() {

        try (Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(RECORD_COUNT)) {

            return resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("Count query faileddd: " + e.getMessage());
            return -1;
        }
    }

    public List<Record> queryView(int limit) {

        try {

            queryView.setString(1,String.valueOf(limit));
            ResultSet resultSet = queryView.executeQuery();

            List<Record> records = new ArrayList<>();
            while (resultSet.next()) {

                //this is just a slow-down simulation so we can observe the progressBar working
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted : " + e.getMessage());
                }

                Record record = new Record();
                record.setArtist(resultSet.getString(1));
                record.setAlbum(resultSet.getString(2));
                record.setTrack(resultSet.getInt(3));
                record.setTitle(resultSet.getString(4));

                records.add(record);
            }
            return records;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Record> searchBasedOn(PreparedStatement searchStatement,String searchCriteria) {

        try{

            searchStatement.setString(1,searchCriteria);
            ResultSet resultSet = searchStatement.executeQuery();

            List<Record> records = new ArrayList<>();
            while (resultSet.next()) {

                Record record = new Record();
                record.setArtist(resultSet.getString(1));
                record.setAlbum(resultSet.getString(2));
                record.setTrack(resultSet.getInt(3));
                record.setTitle(resultSet.getString(4));

                records.add(record);
                System.out.println("Artist: " + record.getArtist() + " Song: " + record.getTitle());
            }

            return records;
        } catch (Exception e){
            System.out.println("Error at search : " + e.getMessage());
            return null;
        }
    }

    public List<Record> searchArtist(String artist) {
        return searchBasedOn(searchForArtist,artist);
    }

    public List<Record> searchAlbum(String album) {
        return searchBasedOn(searchForAlbum,album);
    }

    public List<Record> searchSong(String songTitle) {
        return searchBasedOn(searchForSongTitle,songTitle);
    }


    private int insertArtist(String name) throws SQLException { //this method will ONLY THROW the SQLException,
        //  rather than handling it , because we will catch and handle it in the insertSong method ! ( because we want to make sure IN THAT method
        //  that all the previous actions were done successfully )

        queryArtists.setString(1, name); //this query returns the artist with the "name" name (if found)
        ResultSet results = queryArtists.executeQuery();

        if (results.next()) {
            return results.getInt(1); // this in case we found the artist in the DB and we no longer need
            // to insert him/her , and we just return the _id where he/she is located
        } else {
            //Insert the artist (if not previously found)
            insertIntoArtists.setString(1, name);

            int affectedRows = insertIntoArtists.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }
            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for the artist");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {  //this method will ONLY THROW the SQLException,
        //  rather than handling it , because we will catch and handle it in the insertSong method ! ( because we want to make sure IN THAT method
        //  that all the previous actions were done successfully )

        queryAlbums.setString(1, name);
        ResultSet results = queryAlbums.executeQuery();

        if (results.next()) {
            return results.getInt(1); // this in case we found the album in the DB and we no longer need
            // to insert it , and we just return the _id where it is located
        } else {
            //Insert the album (if not previously found)
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);

            int affectedRows = insertIntoAlbums.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album!");
            }
            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for the album");
            }
        }
    }

    public void insertSong(String title, String artist, String album, int track) {

        try {

            conn.setAutoCommit(false); //to begin a transaction (auto-commit is set to false so that the db can rollback
            // any changes in case something goes wrong and the transaction cannot be done FULLY SUCCESSFULL)

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);

            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);


            int affectedRows = insertIntoSongs.executeUpdate();  //this line executes the ACTUAL INSERT of the song and returns
            //  information about its effect on the DB (usually the
            //  "affectedRows" sould be 1 --> only one row of the table sould be affected if we add a song)
            if (affectedRows == 1) {
                conn.commit(); //this finishes the transaction and makes the previous changes PERMANENT to the DB
            } else {
                throw new SQLException("Song insert failed!");
            }

        } catch (Exception e) {
            System.out.println("Insert song exception : " + e.getMessage());
            try {
                System.out.println("Performing rollback!");
                conn.rollback();  // this performs a "ROLLBACK" on the previous changes , so they will not be saved to
                //  the DB (this is in case something went wrong , so we  want the changes to be done
                //  ONLY if everything was successful , OR NOT AT ALL
            } catch (SQLException e2) {
                System.out.println("Oh boy........ " + e.getMessage()); // this would is very unlikely to happen and it would be BAD
            }
        } finally {
            try {
                System.out.println("Reseting default commit behavior ");
                conn.setAutoCommit(true);  // finishing the transaction "STATE" (the auto-commit is eventually set to AUTO again
                //  so all the changes will be automatically saved to the DB
            } catch (SQLException e3) {
                System.out.println("Couldn't reset auto-commit :" + e3.getMessage()); // thi is BAD
            }
        }
    }

    public void insertUser(String username, String password, String email, String phone){

        try {
            insertIntoUsers.setString(1,username);
            insertIntoUsers.setString(2,password);
            insertIntoUsers.setString(3,email);
            insertIntoUsers.setString(4,phone);

            int affectedRows = insertIntoUsers.executeUpdate();

            if(affectedRows != 1) {
                System.out.println("There was a problem inserting user!");
            } else {
                System.out.println("User registered successfully!");
            }
        } catch (SQLException e) {
            System.out.println("User registration failed : " + e.getMessage());
        }
    }


    //    public List<Artist> queryArtists(int sortOrder) {
//
//        //to allow the method to SORT the ARTISTS in ascending/descending/random ORDER , depending on the sortOrder
//        // parameter (sortOrder = { 1 -> random , 2 -> ASC , 3 -> DESC }
//        StringBuilder sb = new StringBuilder(QUERY_ALL_ARTISTS_START);
//        if (sortOrder != ORDER_BY_NONE) {
//            sb.append(QUERY_ALL_ARTISTS_SORT);
//            if (sortOrder == ORDER_BY_DESC) {
//                sb.append("DESC");
//            } else {
//                sb.append("ASC");
//            }
//        }
//
//        System.out.println("SQL statement = " + sb.toString());
//
//        //to execute the SELECT statement and the populate the ARTISTS List
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sb.toString())) {
//
//            List<Artist> artists = new ArrayList<>();
//            while (results.next()) {
//
//                Artist artist = new Artist();
//                artist.setId(results.getInt(INDEX_ARTIST_ID));
//                artist.setName(results.getString(INDEX_ARTIST_NAME));
//                artists.add(artist);
//            }
//            return artists;
//
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }
//    }

    public List<Album> queryAlbumsForArtistId(int id) {

        System.out.println(QUERY_ALBUMS_BY_ARTIST_ID + " | \"" + "?" + "\" =" + id);
        try {
            queryAlbumsByArtistId.setInt(1, id);
            ResultSet results = queryAlbumsByArtistId.executeQuery();

            List<Album> albums = new ArrayList<>();
            while (results.next()) {
                Album album = new Album();
                album.setId(results.getInt(1));
                album.setName(results.getString(2));
                album.setArtistId(id);

                albums.add(album);
            }

            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed : " + e.getMessage());
            return null;
        }
    }

//    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
//
//        //SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artist._id WHERE artists.name = "Carole King" ORDER BY albums.name COLLATE NOCASE ASC
//        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
//
//        sb.append(artistName);
//        sb.append("\"");
//
//        if (sortOrder != ORDER_BY_NONE) {
//            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
//            if (sortOrder == ORDER_BY_DESC) {
//                sb.append("DESC");
//            } else {
//                sb.append("ASC");
//            }
//        }
//
//        System.out.println("SQL statement = " + sb.toString());
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sb.toString())) {
//
//            List<String> albums = new ArrayList<>();
//            while (results.next()) {
//                albums.add(results.getString(1));
//            }
//
//            return albums;
//
//        } catch (SQLException e) {
//            System.out.println("Query failed :" + e.getMessage());
//            return null;
//        }
//
//    }

//    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {
//
//        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
//        sb.append(songName);
//        sb.append("\"");
//
//        if (sortOrder != ORDER_BY_NONE) {
//            sb.append(QUERY_ARTIST_FOR_SONG_SORT);
//            if (sortOrder == ORDER_BY_DESC) {
//                sb.append("DESC");
//            } else {
//                sb.append("ASC");
//            }
//        }
//
//        System.out.println("SQL statement = " + sb.toString());
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sb.toString())) {
//
//            List<SongArtist> songArtists = new ArrayList<>();
//            while (results.next()) {
//                SongArtist songArtist = new SongArtist();
//                songArtist.setArtistName(results.getString(1));
//                songArtist.setAlbumName(results.getString(2));
//                songArtist.setTrack(results.getInt(3));
//                songArtists.add(songArtist);
//            }
//
//            return songArtists;
//
//        } catch (SQLException e) {
//            System.out.println("Qeury failed: " + e.getMessage());
//            return null;
//        }
//    }

//    public void querySongsMetadata() {
//        String sql = "SELECT * FROM " + TABLE_SONGS;
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sql)) {
//
//            ResultSetMetaData meta = results.getMetaData();
//            int numColumns = meta.getColumnCount();
//            for (int i = 1; i <= numColumns; i++) {
//                System.out.format("Column %d in the songs table is named %s\n", i, meta.getColumnName(i));
//            }
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//        }
//    }

//    public int getCount(String table) {
//        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sql)) {
//
//            int count = results.getInt("count");
//            int min = results.getInt("min_id");
//
//            System.out.format("Count = %d, Min = %d\n", count, min);
//            return count;
//
//        } catch (SQLException e) {
//            System.out.println("Quey failed: " + e.getMessage());
//            return -1;
//        }
//    }

//    //method to create the artist_list VIEW
//    public boolean createViewForSongArtists() {
//
//        System.out.println(CREATE_ARTIST_FOR_SONG_VIEW);
//        try (Statement statement = conn.createStatement()) {
//
//            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
//            return true;
//
//        } catch (SQLException e) {
//            System.out.println("Create View failed: " + e.getMessage());
//            return false;
//        }
//    }

//    //method to query the VIEW
//    //SELECT name,album,track FROM artist_list WHERE title = "etc"
//    public List<SongArtist> querySongInfoView(String title) {
//
//        try {
//            querySongInfoView.setString(1, title);
//            ResultSet results = querySongInfoView.executeQuery();
//
//            List<SongArtist> songArtists = new ArrayList<>();
//            while (results.next()) {
//
//                SongArtist songArtist = new SongArtist();
//                songArtist.setArtistName(results.getString(1));
//                songArtist.setAlbumName(results.getString(2));
//                songArtist.setTrack(results.getInt(3));
//
//                songArtists.add(songArtist);
//            }
//
//            return songArtists;
//
//        } catch (SQLException e) {
//            System.out.println("View query failed : " + e.getMessage());
//            return null;
//        }
//    }
}

