package com.company;

import com.company.model.Artist;
import com.company.model.DataSource;
import com.company.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //opening the DataSource
        DataSource dataSource = new DataSource();
        if(!dataSource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        //using the SELECT * FROM ARTISTS query to populate a list of ALL ARTISTS
        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_ASC);
        if(artists == null){
            System.out.println("No artists!");
            return;
        }

        for(Artist artist : artists){
            System.out.println("ID = " +artist.getId() + ", Name = " + artist.getName());
        }

        //using a query to SELECT ALBUMS BY ARTIST NAME
        List<String> albumsForArtist = dataSource.queryAlbumsForArtist("Pink Floyd",DataSource.ORDER_BY_ASC);

        for(String album : albumsForArtist){
            System.out.println(album);
        }

        //using a query to get ARTIST+ALBUM+TRACK by SONG NAME
        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Heartless",DataSource.ORDER_BY_ASC);
        if(songArtists == null){
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtists){
            System.out.println("Artist = " + artist.getArtistName() +
                    " Album = " + artist.getAlbumName() +
                    " Track = " +artist.getTrack());
        }

        //metadata for SONGS TABLE
        dataSource.querySongsMetadata();

        int count = dataSource.getCount(DataSource.TABLE_SONGS);

        dataSource.createViewForSongArtists();

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter a song title: ");
//        String title = scanner.nextLine();
//
//        songArtists = dataSource.querySongInfoView(title);
//        if(songArtists.isEmpty()){
//            System.out.println("Couldn't find the info for the song!");
//            return;
//        }

        for(SongArtist artist : songArtists){
            System.out.println("FROM VIEW : Artist = " + artist.getArtistName() + " | Album = " + artist.getAlbumName() +
                                " | Track = " + artist.getTrack());
        }

        //trying to insert a SONG
        dataSource.insertSong("Cudi Montage" , "Kanye West & Kid Cudi", "KIDS SEE GHOSTS",7);

        //closing thr DataSource
        dataSource.close();
    }
}
