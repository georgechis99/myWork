package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Record {

    private SimpleStringProperty artist;
    private SimpleStringProperty album;
    private SimpleIntegerProperty track;
    private SimpleStringProperty title;

    public Record(){
        this.album = new SimpleStringProperty();
        this.artist = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
        this.track = new SimpleIntegerProperty();
    }

    public String getArtist() {
        return artist.get();
    }

    public SimpleStringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public String getAlbum() {
        return album.get();
    }

    public SimpleStringProperty albumProperty() {
        return album;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public int getTrack() {
        return track.get();
    }

    public SimpleIntegerProperty trackProperty() {
        return track;
    }

    public void setTrack(int track) {
        this.track.set(track);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
}
