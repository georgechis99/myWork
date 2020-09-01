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

import java.util.LinkedList;
import java.util.ListIterator;

public class Album {

    private String albumTitle;
    private LinkedList<Song> songs;

    public Album(String albumTitle) {
        this.albumTitle = albumTitle;
        this.songs = new LinkedList<Song>();
    }

    public Album(String albumTitle, LinkedList<Song> songs) {
        this.albumTitle = albumTitle;
        this.songs = songs;
    }

    public void addSong(String title, String duration) {
        if (!findSong(title)) {
            Song newSong = new Song(title, duration);
            System.out.println(title + " added successfully to " + albumTitle);
            this.songs.add(newSong);
        } else {
            System.out.println(title + " is already added to the album.");
        }
    }

    public boolean findSong(String title) {
        ListIterator<Song> i = this.songs.listIterator();

        while (i.hasNext()) {
            int comparison = i.next().getTitle().compareTo(title);
            if (comparison == 0) {
                return true;
            }
        }
        return false;
    }

    public Song findSong2(String title) {
        ListIterator<Song> i = this.songs.listIterator();

        while (i.hasNext()) {
            int comparison = i.next().getTitle().compareTo(title);
            i.previous();
            if (comparison == 0) {
                return i.next();
            }
        }
        return null;
    }

    public void printAlbum() {
        System.out.println("= " + albumTitle + " =");
        ListIterator<Song> i = this.songs.listIterator();
        int counter = 1;

        while (i.hasNext()) {
            System.out.print(counter + ". " + i.next().getTitle());
            i.previous();
            System.out.println(" - " + i.next().getDuration());
            counter++;
        }
        System.out.println();
    }

    public boolean addSongToPlaylist(String songName, LinkedList<Song> playlist) {
        Song newSong = findSong2(songName);
        if (newSong != null) {
            playlist.add(newSong);
            System.out.println(songName + " was successfully added to your playlist.");
            return true;
        }else{
            System.out.println(songName + " was not found on this album so it cannot be added to the playlist.");
            return false;
        }
    }

}
