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

import com.piedpiper.communication.ClientConnectionManager;
import com.piedpiper.model.UserProfile;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * <p>
 * @author Nailah Azeez
 */
public class ChatAreaController implements Initializable {
  private ClientConnectionManager client;
  private ObservableList<String> contactsList;
  @FXML private ListView<String> contactsListView;
  private ObservableList<String> messageList;
  @FXML private ListView<String> messageListView;
  @FXML private TextArea messageTextArea;
  private UserProfile profile;
  private String recipient;
  @FXML private Button returnMainPgButton;
  @FXML private Button sendMessageButton;

  public void initData(UserProfile profile, ClientConnectionManager client) {
    this.profile = profile;
    this.client = client;
    this.contactsList = FXCollections.observableArrayList(profile.getContacts().toArray());
    this.messageList = FXCollections.observableArrayList();
    this.contactsListView.setItems(this.contactsList);
    this.messageListView.setItems(this.messageList);
    this.contactsListView.setOnMouseClicked((MouseEvent e) -> {
      this.recipient = this.contactsListView.getFocusModel().getFocusedItem();
    });
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  //click "Main Page" button to return to the main page
  @FXML
  private void mainPgButtonAction(ActionEvent event) throws IOException {
    FXMLLoader mainPage = new FXMLLoader(getClass().getResource("layouts/mainPage.fxml"));
    Scene main_page = new Scene(mainPage.load());

    mainPage.<MainPageController>getController().initData(this.profile, this.client);

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(main_page);
    app_stage.show();
  }

  @FXML
  private void sendMessageButtonAction(ActionEvent event) {
    String message = this.messageTextArea.getText();
    this.messageTextArea.clear();

    this.client.sendMessage(this.recipient, message);
    this.messageList.add(this.profile.getEmail() + ": " + message);
  }

  public void updateMessageList(String sender, String message) {
    this.messageList.add(sender + ": " + message);
  }
}
