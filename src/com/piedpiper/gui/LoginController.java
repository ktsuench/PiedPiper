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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Nailah Azeez
 */
public class LoginController implements Initializable {

  private MainPageController controller;

  @FXML
  private Button loginButton;

  @FXML
  private Button signUpButton;

  @FXML
  private TextField txtEmailC;
  @FXML
  private TextField txtEmailL;
  @FXML
  private TextField txtNameC;

  @FXML
  private PasswordField txtPasswordC;
  @FXML
  private PasswordField txtPasswordL;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  //Login verification
  @FXML
  protected void loginButtonAction(ActionEvent event) throws IOException {
    Window owner = loginButton.getScene().getWindow();
    if (txtEmailL.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error!",
          "Please enter your email address");
      return;
    }
    if (txtPasswordL.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error!",
          "Please enter your password");
      return;
    }

    //Once Login button is pressed, the scene will change the the main page
    Parent mainPage = FXMLLoader.load(getClass().getResource("layouts/mainPage.fxml"));
    Scene main_page = new Scene(mainPage);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(main_page);
    app_stage.show();

  }

  //Creating an account
  @FXML
  protected void signUpButtonAction(ActionEvent event) throws IOException {
    Window owner = signUpButton.getScene().getWindow();

    if (txtNameC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Create Account Error!",
          "Please enter your name");
      return;
    }
    if (txtEmailC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Create Account Error!",
          "Please enter your email address");
      return;
    }
    if (txtPasswordC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Create Account Error!",
          "Please create a password");
      return;
    }

    //Once Sign Up button is pressed, the scene will change the the main page
    Parent mainPage = FXMLLoader.load(getClass().getResource("layouts/mainPage.fxml"));
    Scene main_page = new Scene(mainPage);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(main_page);
    app_stage.show();
  }
}
