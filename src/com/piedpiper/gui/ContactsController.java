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
 * <p>
 * @author Nailah Azeez
 */
public class ContactsController implements Initializable {

  private static final String ERROR_ADD_CONTACT = "Please enter the name of the contact you would like to add";
  private static final String ERROR_DELETE_CONTACT = "Please enter the name of the contact your would like to delete";
  private static final String NAME_PATTERN = "[a-zA-Z]*";
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

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
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
  protected void addButtonAction(ActionEvent event) throws IOException {
    String AlertTitle = "Adding Contact Error!";

    Window owner = addButton.getScene().getWindow();
    if (addContact.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_ADD_CONTACT);
      return;
    }

    String contact = addContact.getText();
    if (!contact.matches(NAME_PATTERN)) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_CONTACT_CHAR);
      return;
    }
  }

  @FXML
  protected void deleteButtonAction(ActionEvent event) throws IOException {
    String AlertTitle = "Deleting Contact Error!";

    Window owner = deleteButton.getScene().getWindow();
    if (deleteContact.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_DELETE_CONTACT);
      return;
    }

    String contact = deleteContact.getText();
    if (!contact.matches(NAME_PATTERN)) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_CONTACT_CHAR);
      return;
    }
  }
}
