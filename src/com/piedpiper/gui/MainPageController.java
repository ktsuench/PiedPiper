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

import com.piedpiper.communication.ChannelSelectorCannotStartException;
import com.piedpiper.communication.ClientConnectionManager;
import com.piedpiper.model.UserProfile;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 * <p>
 * @author Nailah Azeez
 */
public class MainPageController implements Initializable {
  private ClientConnectionManager client;
  @FXML private Button contactsButton;
  @FXML private Button logOutButton;
  @FXML private Button messageButton;
  @FXML private Button notificationButton;
  private UserProfile profile;

  public void cleanup() {
    this.client.cleanup();
  }

  public void initData(UserProfile profile) {
    this.profile = profile;

    try {
      this.client = new ClientConnectionManager(this.profile.getEmail());
    } catch (IOException | ChannelSelectorCannotStartException ex) {
      // TODO show user an error
      System.out.println("Failed to start the client connection manager");
      Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void initData(UserProfile profile, ClientConnectionManager client) {
    this.profile = profile;
    this.client = client;
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void contactsButtonAction(ActionEvent event) throws IOException {
    FXMLLoader contactsPage = new FXMLLoader(getClass().getResource("layouts/Contacts.fxml"));
    Scene contacts_page = new Scene(contactsPage.load());

    contactsPage.<ContactsController>getController().initData(this.profile, this.client);

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(contacts_page);
    app_stage.show();
  }

  @FXML
  private void logOutButtonAction(ActionEvent event) throws IOException {
    Parent loginPage = FXMLLoader.load(getClass().getResource("layouts/Login.fxml"));
    Scene login_page = new Scene(loginPage);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(login_page);
    app_stage.show();

  }

  @FXML
  private void messageButtonAction(ActionEvent event) throws IOException {
    FXMLLoader chatPage = new FXMLLoader(getClass().getResource("layouts/ChatArea.fxml"));
    Scene chat_page = new Scene(chatPage.load());

    chatPage.<ChatAreaController>getController().initData(this.profile, this.client);

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(chat_page);
    app_stage.show();
  }

  @FXML
  private void notificationButtonAction(ActionEvent event) throws IOException {
    Window owner = notificationButton.getScene().getWindow();

    //Pending for now
    AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Coming soon", "This feature has not yet been implemented.");
  }
}
