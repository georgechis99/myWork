package sample;

import javafx.application.Platform;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import sample.datamodel.Contact;
import sample.datamodel.ContactData;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

public class Controller {

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    public TableView<Contact> tableView;

    @FXML
    TableColumn firstNameCol;
    @FXML
    TableColumn lastNameCol;
    @FXML
    TableColumn phoneNumberCol;
    @FXML
    TableColumn notesCol;

    public void initialize() {

        ContactData contactData = new ContactData();

        //setting up the context menu
        contextMenu = new ContextMenu();
        MenuItem deleteContact = new MenuItem("Delete");
        MenuItem editContact = new MenuItem("Edit");
        deleteContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Contact contact = tableView.getSelectionModel().getSelectedItem();
                deleteContact(contact);
            }
        });

        editContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showEditContactDialog();
            }
        });

        contextMenu.getItems().addAll(deleteContact);
        contextMenu.getItems().addAll(editContact);

        //to show the contextmenu on right-clicked mouse
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(tableView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            }
        });

        //to delete a row from tableview on DELETE key pressed
        tableView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                final Contact selectedContact = tableView.getSelectionModel().getSelectedItem();

                if (selectedContact != null) {
                    if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                        deleteContact(selectedContact);
                    }
                }
            }
        });

        //inatsntiate and update th columns in the tableView
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Contact, String>("firstName")
        );

        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Contact, String>("lastName")
        );

        phoneNumberCol.setCellValueFactory(
                new PropertyValueFactory<Contact, String>("phoneNumber")
        );

        notesCol.setCellValueFactory(
                new PropertyValueFactory<Contact, String>("notes")
        );

        SortedList<Contact> sortedContacts = new SortedList<Contact>(ContactData.getInstance().getContacts(), new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
            }
        });

        tableView.setItems(sortedContacts);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().selectFirst();
    }

    //this opens up a dialog to edit a contact
    @FXML
    public void showEditContactDialog() {

        Contact oldContact = tableView.getSelectionModel().getSelectedItem();

        if (oldContact != null) {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            dialog.setTitle("Edit Contact");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("EditContact.fxml"));


            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());

            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }

            EditContactDialogController controller = fxmlLoader.getController();


            controller.setFirstNameTextField(oldContact.getFirstName());
            controller.setLastNameTextField(oldContact.getLastName());
            controller.setPhoneNumberTextField(oldContact.getPhoneNumber());
            controller.setNotesTextArea(oldContact.getNotes());


            dialog.getDialogPane().getButtonTypes().add(ButtonType.FINISH);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.FINISH) {
                Contact newContact = controller.processResults(oldContact);
            }
        } else {
            System.out.println("Error");
        }
    }

    //this opens up the dialog to add a new contact
    @FXML
    public void showAddContactDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddContact.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            AddContactDialogController controller = fxmlLoader.getController();
            Contact newContact = controller.processResults();
        }

    }

    //delete an item by clicking the "Delete" option from the Contex Menu
    public void deleteContact(Contact contact) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Are you sure you want to delete " + contact.getFirstName() + " " +
                contact.getLastName() + " out of your contact list?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            ContactData.getInstance().deleteContact(contact);
        }
    }


    @FXML
    public void handleExit() {
        Platform.exit();
    }

}
