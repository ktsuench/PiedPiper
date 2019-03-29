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

import com.piedpiper.model.UserProfile;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

  @FXML private Button loginButton;
  @FXML private Button signUpButton;
  @FXML private TextField txtEmailC;
  @FXML private TextField txtEmailL;
  @FXML private TextField txtFirstNameC;
  @FXML private TextField txtLastNameC;
  @FXML private PasswordField txtPasswordC;
  @FXML private PasswordField txtPasswordL;

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

    String email = txtEmailL.getText();
    String password = txtPasswordL.getText();
    String loginInfo = "SELECT * FROM user_login WHERE user_email = ? AND user_pass = ?";
    String contactListQuery = "SELECT contact_email FROM addContact, user_contacts WHERE userLoginId = ?"
        + " AND contactId = user_contacts.id";

    try {
      ps = db.prepareStatement(loginInfo);
      ps.setString(1, email);
      ps.setString(2, password);

      rs = ps.executeQuery();

      if (rs.next()) {
        ps = db.prepareStatement(contactListQuery);
        ps.setInt(1, rs.getInt("id"));

        ResultSet contactsRS = ps.executeQuery();
        ArrayList<String> contacts = new ArrayList<>();

        while(contactsRS.next()) {
          contacts.add(contactsRS.getString("contact_email"));
        }

        UserProfile profile = new UserProfile(
            rs.getString("user_first_name"),
            rs.getString("user_last_name"),
            rs.getString("user_email"),
            contacts
        );

        //Once Login button is pressed, the scene will change the the main page
        FXMLLoader mainPage = new FXMLLoader(getClass().getResource(LAYOUT_MAIN_PAGE));
        Scene main_page = new Scene(mainPage.load());

        mainPage.<MainPageController>getController().initData(profile);

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
  protected void signUpButtonAction(ActionEvent event) throws IOException, SQLException, Exception {
    Window owner = signUpButton.getScene().getWindow();
    String AlertTitle = "Create Account Error!";

    //conditions for Name field
    if (txtFirstNameC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_NAME);
      return;
    }
    if (txtFirstNameC.getText().length() < 3) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_MIN_CHAR_NAME);
      return;
    }

    if (txtLastNameC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_NAME);
      return;
    }
    if (txtLastNameC.getText().length() < 3) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_MIN_CHAR_NAME);
      return;
    }

    //conditions for Email field
    if (txtEmailC.getText().isEmpty()) {
      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_EMAIL);
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

    //Database connection
    Connection db = SQLiteDatabaseManager.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String firstName = txtFirstNameC.getText();
    String lastName = txtLastNameC.getText();
    String email = txtEmailC.getText();
    String password = txtPasswordC.getText();
    String createLogin = "INSERT INTO user_login(user_first_name, user_last_name, user_email, user_pass) VALUES(?, ?, ?, ?)";

    try {
      ps = db.prepareStatement(createLogin);
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ps.setString(3, email);
      ps.setString(4, password);

      int rowAfffected = ps.executeUpdate();
      rs = ps.getGeneratedKeys();

      db.commit();

      if (!firstName.matches(NAME_PATTERN)) {
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_NAME_CHAR);
        return;
      }

      if (!lastName.matches(NAME_PATTERN)) {
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_NAME_CHAR);
        return;
      }

      if (!email.matches(EMAIL_PATTERN)) {
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, AlertTitle, ERROR_EMAIL_FORMAT);
        return;
      }

      if (rs.next()) {
        UserProfile profile = new UserProfile(
            firstName,
            lastName,
            email,
            new ArrayList<>()
        );

        //Once Login button is pressed, the scene will change the the main page
        FXMLLoader mainPage = new FXMLLoader(getClass().getResource(LAYOUT_MAIN_PAGE));
        Scene main_page = new Scene(mainPage.load());

        mainPage.<MainPageController>getController().initData(profile);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_page);
        app_stage.show();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
