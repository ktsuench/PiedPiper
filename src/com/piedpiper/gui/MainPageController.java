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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * <p>
 * @author Nailah Azeez
 */
public class MainPageController implements Initializable {

  @FXML
  private Button contactsButton;

  @FXML
  private Button messageButton;

  @FXML
  private Button notificationButton;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void contactsButtonAction(ActionEvent event) throws IOException {
    Parent contactsPage = FXMLLoader.load(getClass().getResource("layouts/Contacts.fxml"));
    Scene contacts_page = new Scene(contactsPage);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(contacts_page);
    app_stage.show();
  }

  @FXML
  private void messageButtonAction(ActionEvent event) throws IOException {
    Parent chatPage = FXMLLoader.load(getClass().getResource("layouts/ChatArea.fxml"));
    Scene chat_page = new Scene(chatPage);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(chat_page);
    app_stage.show();

  }

  @FXML
  private void notificationButtonAction(ActionEvent event) throws IOException {
    //Pending for now
  }
}
