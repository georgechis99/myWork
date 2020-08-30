package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.datamodel.ToDoData;
import sample.datamodel.ToDoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    private List<ToDoItem> toDoItems;

    //here all the instantiated objects are related to the fxml file and MUST have the exact same name as the fx:id of the controls
    @FXML
    private ListView<ToDoItem> toDoListView;

    @FXML
    private ListView<ToDoItem> recentlyDeleted;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private Button deleteButton;

    @FXML
    private ToggleButton filterToggleButton;

    @FXML
    private Button completedTaskButton;

    @FXML
    public void handleMouseEnter() {
        completedTaskButton.setScaleX(1.5);
        completedTaskButton.setScaleY(1.5);
    }

    @FXML
    public void handleMouseExit() {
        completedTaskButton.setScaleX(1.0);
        completedTaskButton.setScaleY(1.0);
    }

    private FilteredList<ToDoItem> filteredList;

    private Predicate<ToDoItem> wantAllItems;
    private Predicate<ToDoItem> wantTodaysItems;

    //method to load the already existing data and set up the UI
    public void initialize() {

        //some effects here...
        filterToggleButton.setEffect(new DropShadow());
        toDoListView.setTooltip(new Tooltip("These are your chores"));
        deleteButton.setTooltip(new Tooltip("Delete a To-do"));
        completedTaskButton.setTooltip(new Tooltip("Complete task"));
        //the delete button is initially disabled
        deleteButton.setDisable(true);
        completedTaskButton.setDisable(true);

        //adding a context menu for when the user right clicks on an item in the ListView ( for deleteing/editing etc. )
        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        MenuItem editMenuItem = new MenuItem("Edit...");
        MenuItem completedMenuItem = new MenuItem("Task Completed");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        completedMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
                completeItem(item);
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
                toDoListView.getSelectionModel().clearSelection();
                deleteButton.setDisable(true);
            }
        });

        completedTaskButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
                completeItem(item);
                toDoListView.getSelectionModel().clearSelection();
                completedTaskButton.setDisable(true);
            }
        });

        wantAllItems = new Predicate<ToDoItem>() {
            @Override
            public boolean test(ToDoItem toDoItem) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<ToDoItem>() {
            @Override
            public boolean test(ToDoItem toDoItem) {
                return (toDoItem.getDeadline().equals(LocalDate.now()));
            }
        };

        filteredList = new FilteredList<ToDoItem>(ToDoData.getInstance().getToDoItems(), wantAllItems);

        SortedList<ToDoItem> sortedList = new SortedList<ToDoItem>(filteredList, new Comparator<ToDoItem>() {
            @Override
            public int compare(ToDoItem t1, ToDoItem t2) {
                return t1.getDeadline().compareTo(t2.getDeadline());
            }
        });

        listContextMenu.getItems().addAll(deleteMenuItem);
        listContextMenu.getItems().addAll(editMenuItem);
        listContextMenu.getItems().addAll(completedMenuItem);

        //this line adds a listener to the listview and waits for any changes to be done to update stuff
        toDoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem oldValue, ToDoItem newValue) {
                //this if statement checks if there is any change in the state of the ListView (wether that is
                // a click on another item or simply scrolling through the items with the arrows)
                if (newValue != null) {

                    //the delete button is disabled until you select an item from the ListView
                    deleteButton.setDisable(false);

                    //when an item from the list is selected the details of it will be deisplayed in the TextArea
                    ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();

                    if(!(item.getShortDescription().contains("(Past due)"))){
                        completedTaskButton.setDisable(false);
                    }

                    itemDetailsTextArea.setText(item.getDetails());
                    //and the deadlineLabel in the bottom of the BorderPane will be set accordingly
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMMM, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        recentlyDeleted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem oldValue, ToDoItem newValue) {

                if (newValue != null) {

                    deleteButton.setDisable(true);
                    completedTaskButton.setDisable(true);

                    //when an item from the list is selected the details of it will be deisplayed in the TextArea
                    ToDoItem item = recentlyDeleted.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    //and the deadlineLabel in the bottom of the BorderPane will be set accordingly
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMMM, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        //updates the ListView
        toDoListView.setItems(sortedList);
        //updates the RecentlyDeleted
        recentlyDeleted.setItems(ToDoData.getInstance().getRecentlyDeleted());
        //only one item can be selected at a time
        toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        recentlyDeleted.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //the first element from the list is implicitly selected when opening the app
//        toDoListView.getSelectionModel().selectFirst();

        //this color red items that are due to today's date
        toDoListView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {
            @Override
            public ListCell<ToDoItem> call(ListView<ToDoItem> toDoItemListView) {
                ListCell<ToDoItem> cell = new ListCell<ToDoItem>() {
                    @Override
                    protected void updateItem(ToDoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getShortDescription());
                            if (item.getDeadline().isBefore(LocalDate.now())) {
                                StringBuilder newShortDescription = new StringBuilder();
                                newShortDescription.append(item.getShortDescription());
                                newShortDescription.append(" (Past due)");
                                setText(newShortDescription.toString());
                                setTextFill(Color.DARKGRAY); /// To-do that is past due
                                completedTaskButton.setDisable(true);
                            } else if (item.getDeadline().equals(LocalDate.now().plusDays(1))) {
                                StringBuilder newShortDescription = new StringBuilder();
                                newShortDescription.append(item.getShortDescription());
                                newShortDescription.append(" (Tommorow)");
                                setText(newShortDescription.toString());
                                setTextFill(Color.ORANGE);
                            } else if (item.getDeadline().equals(LocalDate.now())) {
                                StringBuilder newShortDescription = new StringBuilder();
                                newShortDescription.append(item.getShortDescription());
                                newShortDescription.append(" (Today)");
                                setText(newShortDescription.toString());
                                setTextFill(Color.RED);
                            } else {
                                setTextFill(Color.BLACK);
                            }

//                            String[] splitShortDescription = item.getShortDescription().split(" ");
//                            for(String s : splitShortDescription){
//                                if(s.equalsIgnoreCase("(completed)")){
//                                    setTextFill(Color.GREEN);
//                                }
//                            }

                            if(item.getShortDescription().contains("(completed)")){
                                setTextFill(Color.GREEN);
                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
                            }
                        }
                );

                return cell;
            }
        });

        recentlyDeleted.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {
            @Override
            public ListCell<ToDoItem> call(ListView<ToDoItem> toDoItemListView) {
                ListCell<ToDoItem> cell = new ListCell<ToDoItem>() {
                    @Override
                    protected void updateItem(ToDoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getShortDescription());
                        }
                    }
                };
                return cell;
            }
        });
    }

    //loading and assigning the dialog pane
    @FXML
    public void showNewItemDialog() {
        //this opens up the new dialog to add new To-do items when the File -> New... option is selected
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New To-do Item : ");
        dialog.setHeaderText("Use this dialog to create a new to-do item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("toDoItemDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        //adding a predifined OK and CANCEL button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            //.processResults() method is defined in the DialogController class
            ToDoItem newItem = controller.processResults();
            toDoListView.getSelectionModel().select(newItem);
        }
    }

    @FXML
    public void handleClickedListView() {
        ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }

    //delete an item by pressing the "delete" key on the keyboard once the item is selected
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        ToDoItem selectedItem = toDoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteItem(selectedItem);
            }
        }
    }

    //delete an item by clicking the "Delete" option from the Contex Menu
    public void deleteItem(ToDoItem item) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete To-do Item");
        alert.setHeaderText("Delete item : " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or CANCEL to back out.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            ToDoData.getInstance().deleteToDoItem(item);
        }
    }

    public void completeItem(ToDoItem item) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Complete To-do Item");
        alert.setHeaderText("Complete item : " + item.getShortDescription());
        alert.setContentText("Are you sure you completed this task? Press OK to confirm, or CANCEL to back out.");
        StringBuilder shortDescription = new StringBuilder();
        shortDescription.append(item.getShortDescription()).append(" (completed)");
        toDoListView.getSelectionModel().getSelectedItem().setShortDescription(shortDescription.toString());
    }

    @FXML
    public void handleFilterButton() {

        ToDoItem selectedItem = toDoListView.getSelectionModel().getSelectedItem();
        if (filterToggleButton.isSelected()) {
            filteredList.setPredicate(wantTodaysItems);
            if (filteredList.isEmpty()) {
                itemDetailsTextArea.clear();
                deadlineLabel.setText("");
            } else if (filteredList.contains(selectedItem)) {
                toDoListView.getSelectionModel().select(selectedItem);
            } else {
                toDoListView.getSelectionModel().selectFirst();
            }

        } else {
            filteredList.setPredicate(wantAllItems);
            toDoListView.getSelectionModel().select(selectedItem);
        }
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }
}
