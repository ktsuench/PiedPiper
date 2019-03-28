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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private static final String ERROR_EMAIL = "Please enter your email address";
  private static final String ERROR_EMAIL_FORMAT = "The format of the email address you have enters is incorrect (eg. Correct format: user@gmail.com)";
  private static final String ERROR_MIN_CHAR_NAME = "Please ensure you have entered you name correctly, a minimum of 3 characters is required";
  private static final String ERROR_MIN_CHAR_PASSWORD = "Please create a password with a minimum of 8 characters";
  private static final String ERROR_NAME = "Please enter your name";
  private static final String ERROR_NAME_CHAR = "Name field only accepts letters, please try again";
  private static final String ERROR_PASSWORD = "Please enter your password";
  private static final String LAYOUT_MAIN_PAGE = "layouts/mainPage.fxml";
  private static final String NAME_PATTERN = "[a-zA-Z]*";

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
  protected void loginButtonAction(ActionEvent event) throws IOException, SQLException, Exception {
    Window owner = loginButton.getScene().getWindow();
    String AlertTitle = "Login Error!";

    if (txtEmailL.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_EMAIL);
      return;
    }
    if (txtPasswordL.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_PASSWORD);
      return;
    }
    
    //Database connection
    Connection db = SQLiteDatabaseManager.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String email = txtEmailL.getText().toString();
    String password = txtPasswordL.getText().toString();
    String loginInfo = "SELECT * FROM user_login WHERE user_email = ? AND user_pass = ?";
    
    try {
      ps = db.prepareStatement(loginInfo);
      ps.setString(1, email);
      ps.setString(2, password);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        //Once Login button is pressed, the scene will change the the main page
          Parent mainPage = FXMLLoader.load(getClass().getResource(LAYOUT_MAIN_PAGE));
          Scene main_page = new Scene(mainPage);
          Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          app_stage.setScene(main_page);
          app_stage.show();
        
      } else {
         AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_PASSWORD);
        return;
      }
      
      
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    

  }

  //Creating an account
  @FXML
  protected void signUpButtonAction(ActionEvent event) throws IOException {
    Window owner = signUpButton.getScene().getWindow();
    String AlertTitle = "Create Account Error!";

    //conditions for Name field
    if (txtNameC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_NAME);
      return;
    }
    if (txtNameC.getText().length() < 3) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_MIN_CHAR_NAME);
      return;
    }
    String name = txtNameC.getText();
    if (!name.matches(NAME_PATTERN)) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_NAME_CHAR);
      return;
    }

    //conditions for Email field
    if (txtEmailC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_EMAIL);
      return;
    }
    String email = txtEmailC.getText();
    if (!email.matches(EMAIL_PATTERN)) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_EMAIL_FORMAT);
      return;
    }

    //conditions for Password field
    if (txtPasswordC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_PASSWORD);
      return;
    }
    if (txtPasswordC.getText().length() < 8) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_MIN_CHAR_PASSWORD);
      return;
    }

    //Once Sign Up button is pressed, the scene will change the the main page
    Parent mainPage = FXMLLoader.load(getClass().getResource(LAYOUT_MAIN_PAGE));
    Scene main_page = new Scene(mainPage);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(main_page);
    app_stage.show();
  }
}
