/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_piedpiper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Nailah Azeez
 */
public class ContactsController implements Initializable {
    
    
    @FXML
    private Button addButton;
    
    @FXML
    private TextField addContact;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private TextField deleteContact;

    @FXML
    private Button doneButton;
    
    @FXML
    protected void addButtonAction(ActionEvent event) throws IOException {
       Window owner = addButton.getScene().getWindow();
        if(addContact.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Adding Contact Error!", 
                    "Please enter the name of the contact you would like to add");
            return;
        }     
        
    }
    
    @FXML
    protected void deleteButtonAction(ActionEvent event) throws IOException {
        Window owner = deleteButton.getScene().getWindow();
        if(deleteContact.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Deleting Contact Error!", 
                    "Please enter the name of the contact your would like to delete");
            return;
        }
        
    }
    
    @FXML
    private void doneButtonAction(ActionEvent event) throws IOException {
       Parent mainPage = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
       Scene main_page = new Scene(mainPage);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(main_page);
       app_stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
