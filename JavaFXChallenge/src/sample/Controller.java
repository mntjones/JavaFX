package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

//    private List<Contact> contacts;
//
    @FXML
    private BorderPane borderPane;
    @FXML
    private ContactData data;
    @FXML
    private TableView<Contact> tableView;

    public void initialize() {
        data = new ContactData();
        data.loadContacts();
        tableView.setItems(data.getContacts());

    }

    @FXML
    public void addNewContact() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Add New Contact");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("contactForm.fxml"));

        try{
            dialog.getDialogPane().setContent(loader.load());
        }
        catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            FormController controller = loader.getController();
            Contact newContact = controller.processResults();
            data.addContact(newContact);
            data.saveContacts();
        }
    }

    @FXML
    public void editContact() {
        Contact selected = tableView.getSelectionModel().getSelectedItem();
        if(selected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a contact to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Edit Contact");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("contactForm.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        }
        catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        FormController controller = loader.getController();
        controller.editContact(selected);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
             controller.updateContact(selected);
            data.saveContacts();
            tableView.refresh();
        }
    }

    @FXML
    public void deleteContact() {
        Contact selected = tableView.getSelectionModel().getSelectedItem();

        if(selected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a contact to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact?");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected contact? " +
                selected.getFirstName() + " " + selected.getLastName());
        Optional<ButtonType> result =  alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            data.deleteContact(selected);
            data.saveContacts();
            tableView.refresh();
        }
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }


}
