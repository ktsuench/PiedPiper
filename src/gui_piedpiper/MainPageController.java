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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nailah Azeez
 */
public class MainPageController implements Initializable {

    @FXML
    private Button contactsButton;
    
    @FXML
    private Button messageButton;
    
    @FXML
    private Button notificationButton;
    
    @FXML
    private void contactsButtonAction(ActionEvent event) throws IOException {
        Parent contactsPage = FXMLLoader.load(getClass().getResource("Contacts.fxml"));
        Scene contacts_page = new Scene(contactsPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(contacts_page);
        app_stage.show();
    }
    
    @FXML
    private void messageButtonAction(ActionEvent event) throws IOException {
        Parent chatPage = FXMLLoader.load(getClass().getResource("ChatArea.fxml"));
        Scene chat_page = new Scene(chatPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(chat_page);
        app_stage.show();
    
    }
    
    @FXML
    private void notificationButtonAction(ActionEvent event) throws IOException {
    //Pending for now
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
