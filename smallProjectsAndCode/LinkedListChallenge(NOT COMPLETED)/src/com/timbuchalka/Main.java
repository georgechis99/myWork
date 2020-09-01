// Create a program that implements a playlist for songs
// Create a Song class having Title and Duration for a song.
// The program will have an Album class containing a list of songs.
// The albums will be stored in an ArrayList
// Songs from different albums can be added to the playlist and will appear in the list in the order
// they are added.
// Once the songs have been added to the playlist, create a menu of options to:-
// Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
// List the songs in the playlist
// A song must exist in an album before it can be added to the playlist (so you can only play songs that
// you own).
// Hint:  To replay a song, consider what happened when we went back and forth from a city before we
// started tracking the direction we were going.
// As an optional extra, provide an option to remove the current song from the playlist
// (hint: listiterator.remove()

package com.timbuchalka;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();
    private static LinkedList<Song> playlist = new LinkedList<Song>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        LinkedList<Song> playlist = new LinkedList<Song>();
        addAlbum("Rodeo");
        addAlbum("Astroworld");
        addAlbum("Birds in the trap");

        albums.get(0).addSong("90210", "5:39");
        albums.get(0).addSong("Antidote", "4:22");
        albums.get(0).addSong("Maria I'm Drunk", "5:50");

        albums.get(1).addSong("SICKO MODE", "5:13");
        albums.get(1).addSong("WAKE UP", "3:52");
        albums.get(1).addSong("YOSEMITE", "2:30");

        albums.get(2).addSong("coordinate", "3:46");
        albums.get(2).addSong("goosebumps", "4:04");
        albums.get(2).addSong("lose", "3:21");
        albums.get(2).addSong("lose", "3:21");

        albums.get(0).printAlbum();
        albums.get(1).printAlbum();
        albums.get(2).printAlbum();

        albums.get(0).addSongToPlaylist("lose", playlist);

        System.out.println(playlist.get(0).getTitle());
    }

    private static void addAlbum(String albumName) {
        Album newAlbum = new Album(albumName);
        albums.add(newAlbum);
    }
}
