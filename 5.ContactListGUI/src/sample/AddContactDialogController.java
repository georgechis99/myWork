package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.Contact;
import sample.datamodel.ContactData;

public class AddContactDialogController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextArea notesTextArea;

    public Contact processResults() {
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        String notes = notesTextArea.getText().trim();

        Contact contact = new Contact(firstName,lastName,phoneNumber,notes);
        ContactData.getInstance().addContact(contact);

        return contact;
    }
}
