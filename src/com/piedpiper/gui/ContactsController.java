/*
 * MIT License
 *
 * Copyright (c) 2019 Nailah Azeez, Jaskiran Lamba, Sandeep Suri, Kent Tsuenchy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.piedpiper.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 * <p>
 * @author Nailah Azeez
 */
public class ContactsController implements Initializable {

  private static final String ERROR_ADD_CONTACT = "Please enter the name of the contact you would like to add";
  private static final String ERROR_DELETE_CONTACT = "Please enter the name of the contact your would like to delete";
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private static final String ERROR_CONTACT_CHAR = "Only letters are accepted, please try again";

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
  private ListView<String> contactList;
  

  private addContact showContact(ResultSet rs) {
      addContact p = new addContact();
      try {
         p.setId(rs.getInt("id"));
         p.setEmail(rs.getString("contact_email"));
      } catch (SQLException ex) {
      }
      return p;
   }
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    try{
      //Database Connection
      Connection db = SQLiteDatabaseManager.getConnection();
      ResultSet rs = null;
      PreparedStatement ps = null;
      
      try{
        String sqlAddContact = "SELECT contact_email FROM user_contacts";
        Statement sm = db.createStatement();
        
        rs =  sm.executeQuery(sqlAddContact);    

        while(rs.next()) {
          String contacts = rs.getString("contact_email");
//          contactList = new ListView<>();
//          contactList.getItems().addAll(contacts);
          ObservableList<String> items = FXCollections.observableArrayList(contacts);
          contactList.getItems().addAll(items);
        }
        
        
      } catch (SQLException e){
        throw e;
      }
      
    } catch (ClassNotFoundException ex){
      Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  @FXML
  private void doneButtonAction(ActionEvent event) throws IOException {
    Parent mainPage = FXMLLoader.load(getClass().getResource("layouts/mainPage.fxml"));
    Scene main_page = new Scene(mainPage);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(main_page);
    app_stage.show();
  }

  @FXML
  protected void addButtonAction(ActionEvent event) throws IOException, Exception {
    String AlertTitle = "Adding Contact Error!";

    Window owner = addButton.getScene().getWindow();
    if (addContact.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_ADD_CONTACT);
      return;
    }

    String contact = addContact.getText();
    if (!contact.matches(EMAIL_PATTERN)) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_CONTACT_CHAR);
      return;
    }
    
    //Database Connection
    Connection db = SQLiteDatabaseManager.getConnection();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String userEmail = addContact.getText().toString();
        
    try{
        String sqlAddContact = "INSERT INTO user_contacts(contact_email) VALUES(?)";
        ps = db.prepareStatement(sqlAddContact);
        ps.setString(1, userEmail);
        
        int rowAfffected = ps.executeUpdate();    
        rs = ps.getGeneratedKeys();

        if(rowAfffected < 0)
            throw new Exception("User not found in database!", null);

        db.commit();
    } catch (SQLException e){
        throw e;
    }
  }

  @FXML
  protected void deleteButtonAction(ActionEvent event) throws IOException, SQLException, Exception {
    String AlertTitle = "Deleting Contact Error!";

    //Database Connection
    Connection db = SQLiteDatabaseManager.getConnection();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String userEmail = deleteContact.getText().toString();
        
    try{
        String sqlAddContact = "DELETE FROM user_contacts WHERE contact_email = ?";
        ps = db.prepareStatement(sqlAddContact);
        ps.setString(1, userEmail);
        
        int rowAfffected = ps.executeUpdate();    
        rs = ps.getGeneratedKeys();


        db.commit();
    } catch (SQLException e){
        throw e;
    }
    
    Window owner = deleteButton.getScene().getWindow();
    if (deleteContact.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_DELETE_CONTACT);
      return;
    }

    String contact = deleteContact.getText();
    if (!contact.matches(EMAIL_PATTERN)) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_CONTACT_CHAR);
      return;
    }
  }
}
