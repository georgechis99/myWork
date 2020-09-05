package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.Contact;
import sample.datamodel.ContactData;

import java.io.IOException;

public class EditContactDialogController{

    public void setFirstNameTextField(String firstNameTextField) {
        this.firstNameTextField.setText(firstNameTextField);
    }

    public void setLastNameTextField(String lastNameTextField) {
        this.lastNameTextField.setText(lastNameTextField);
    }

    public void setPhoneNumberTextField(String phoneNumberTextField) {
        this.phoneNumberTextField.setText(phoneNumberTextField);
    }

    public void setNotesTextArea(String notesTextArea) {
        this.notesTextArea.setText(notesTextArea);
    }

    //controls of the editContact dialog
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextArea notesTextArea;

    public Contact processResults(Contact oldContact) {
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        String notes = notesTextArea.getText().trim();

        Contact newContact = new Contact(firstName,lastName,phoneNumber,notes);
        ContactData.getInstance().editContact(oldContact,newContact);

        return newContact;
    }
}
