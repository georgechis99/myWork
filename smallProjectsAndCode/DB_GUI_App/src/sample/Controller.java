package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import sample.model.Album;
import sample.model.Artist;
import sample.model.DataSource;
import sample.model.Record;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Controller {

    @FXML
    private TableView artistTable;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label currentCount;

    @FXML
    private Label totalCount;

    @FXML
    private Button loadMoreButton;

    @FXML
    private Button loadAllButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private TextField searchBar;

    @FXML
    private ChoiceBox searchCriteria;


    public SimpleBooleanProperty loadedAll = new SimpleBooleanProperty(false);
    public SimpleIntegerProperty searchCriteriaSelected = new SimpleIntegerProperty(0);

    @FXML
    public void initiate() {
        searchCriteria.getItems().add("Serach in Artists");
        searchCriteria.getItems().add("Serach in Albums");
        searchCriteria.getItems().add("Serach in Songs");

        searchCriteria.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldVal, Object newVal) {
                switch (newVal.toString()) {
                    case "Serach in Artists":
                    {
                        System.out.println("Artist selected");
                        searchCriteriaSelected.set(1);
                        break;
                    }
                    case "Serach in Albums":
                    {
                        System.out.println("Album selected");
                        searchCriteriaSelected.set(2);
                        break;
                    }
                    case "Serach in Songs":
                    {
                        System.out.println("Song selected");
                        searchCriteriaSelected.set(3);
                        break;
                    }
                    default:
                    {
                        System.out.println("Error choiceBox");
                        break;
                    }
                }
            }
        });

        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                List<Record> list = DataSource.getInstance().searchArtist(newValue);
//                switch (searchCriteriaSelected.intValue()){
//                    case 1:
//                    {
//                        System.out.println("Artist: " + newValue);
//                        Task<ObservableList<Record>> task = new SearchArtistTask(newValue);
//                        artistTable.itemsProperty().bind(task.valueProperty());
//
//                        progressBar.progressProperty().bind(task.progressProperty());
//                        progressBar.setVisible(true);
//                        task.setOnSucceeded(e -> progressBar.setVisible(false));
//                        task.setOnFailed(e -> progressBar.setVisible(false));
//
//                        new Thread(task).start();
//                        break;
//                    }
//                    case 2:
//                    {
//                        System.out.println("Album: " + newValue);
//                        Task<ObservableList<Record>> task = new SearchAlbumTask(newValue);
//                        artistTable.itemsProperty().bind(task.valueProperty());
//
//                        progressBar.progressProperty().bind(task.progressProperty());
//                        progressBar.setVisible(true);
//                        task.setOnSucceeded(e -> progressBar.setVisible(false));
//                        task.setOnFailed(e -> progressBar.setVisible(false));
//
//                        new Thread(task).start();
//                        break;
//                    }
//                    case 3:
//                    {
//                        System.out.println("Song: " + newValue);
//                        Task<ObservableList<Record>> task = new SearchSongTask(newValue);
//                        artistTable.itemsProperty().bind(task.valueProperty());
//
//                        progressBar.progressProperty().bind(task.progressProperty());
//                        progressBar.setVisible(true);
//                        task.setOnSucceeded(e -> progressBar.setVisible(false));
//                        task.setOnFailed(e -> progressBar.setVisible(false));
//
//                        new Thread(task).start();
//                        break;
//                    }
//                }
            }
        });

        Task<ObservableList<Record>> task = new InitiateMainWindow();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        currentCount.textProperty().bind(InitiateMainWindow.countProperty().asString());
        totalCount.textProperty().bind(new SimpleIntegerProperty(DataSource.getInstance().getViewCount()).asString());

        loadMoreButton.disableProperty().bind(Bindings.or(progressBar.visibleProperty(),loadedAll));

        progressBar.setVisible(true);
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void listRecords() {

        Task<ObservableList<Record>> task = new InitiateMainWindow();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        currentCount.textProperty().bind(InitiateMainWindow.countProperty().asString());
        totalCount.textProperty().bind(new SimpleIntegerProperty(DataSource.getInstance().getViewCount()).asString());

        loadMoreButton.disableProperty().bind(Bindings.or(progressBar.visibleProperty(),loadedAll));

        progressBar.setVisible(true);
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void load25MoreRecords() {
        InitiateMainWindow.setCount(InitiateMainWindow.getCount() + 25);
        listRecords();
    }

    @FXML
    public void loadAllRecords() {
        loadedAll.setValue(true);
        InitiateMainWindow.setCount(DataSource.getInstance().getViewCount());
        listRecords();
    }

    @FXML
    public void changeProfileImage() throws MalformedURLException {

        Scene scene = profileImage.getScene();
        Window window = scene.getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a profile picture");
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Images","*.jpg","*.jpeg","*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {

            URL path = selectedFile.toURL();
            profileImage.setImage(new Image(String.valueOf(path)));

        } else {
            System.out.println("Invalid file!");
        }
    }

    @FXML
    public void roundImageView(){   //had to do this manually because i could not get a way to do it in scenebuilder
        Rectangle clip = new Rectangle(
                profileImage.getFitWidth(), profileImage.getFitHeight()
        );
        clip.setArcWidth(50);
        clip.setArcHeight(50);
        profileImage.setClip(clip);

        // snapshot the rounded image
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = profileImage.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through
        profileImage.setClip(null);

        // apply a shadow effect.
        profileImage.setEffect(new DropShadow(10, Color.BLACK));

        // store the rounded image in the imageView.
        profileImage.setImage(image);
    }

//    @FXML
//    public void listAlbumsForArtist() {
//        try {
//            final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
//            if (artist == null) {
//                System.out.println("No artist selected!");
//            }
//
//            Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
//                @Override
//                protected ObservableList<Album> call() throws Exception {
//                    return FXCollections.observableArrayList(DataSource.getInstance().queryAlbumsForArtistId(artist.getId()));
//                }
//            };
//
//            artistTable.itemsProperty().bind(task.valueProperty());
//
//            new Thread(task).start();
//        } catch (RuntimeException e) {
//            System.out.println("That is not an artist...");
//        }
//    }
}

class InitiateMainWindow extends Task {

    private static SimpleIntegerProperty count = new SimpleIntegerProperty();

    public static int getCount() {
        return count.get();
    }

    public static SimpleIntegerProperty countProperty() {
        return count;
    }

    public static void setCount(int count) {
        InitiateMainWindow.count.set(count);
    }

    @Override
    protected Object call() throws Exception {
        return FXCollections.observableArrayList(DataSource.getInstance().queryView(getCount()));
    }
}

class SearchArtistTask extends Task {

    public SearchArtistTask(String artistName) {
        this.artistName = artistName;
    }

    public String artistName;

    @Override
    protected Object call() throws Exception {
        return FXCollections.observableArrayList(DataSource.getInstance().searchArtist(artistName));
    }
}

class SearchAlbumTask extends Task {

    public SearchAlbumTask(String albumName) {
        this.albumName = albumName;
    }

    public String albumName;

    @Override
    protected Object call() throws Exception {
        return FXCollections.observableArrayList(DataSource.getInstance().searchAlbum(albumName));
    }
}

class SearchSongTask extends Task {

    public SearchSongTask(String songName) {
        this.songName = songName;
    }

    public String songName;

    @Override
    protected Object call() throws Exception {
        return FXCollections.observableArrayList(DataSource.getInstance().searchSong(songName));
    }
}


