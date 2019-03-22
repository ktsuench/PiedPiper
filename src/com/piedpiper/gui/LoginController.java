/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piedpiper.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.piedpiper.gui.MainPageController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private TextField txtEmailL;

    @FXML
    private PasswordField txtPasswordL;
    
    @FXML 
    private Button signUpButton;
    
    @FXML
    private TextField txtNameC;
    
    @FXML
    private TextField txtEmailC;

    @FXML
    private PasswordField txtPasswordC;
    
    //Login verification
    @FXML
    protected void loginButtonAction(ActionEvent event) throws IOException {
        Window owner = loginButton.getScene().getWindow();
        if(txtEmailL.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error!", 
                    "Please enter your email address");
            return;
        }
        if(txtPasswordL.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error!", 
                    "Please enter your password");
            return;
        }
        
        //Once Login button is pressed, the scene will change the the main page
        Parent mainPage = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Scene main_page = new Scene(mainPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_page);
        app_stage.show();
        
    }
    
    
    //Creating an account
    @FXML
    protected void signUpButtonAction(ActionEvent event) throws IOException {
        Window owner = signUpButton.getScene().getWindow();
        
        if(txtNameC.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Create Account Error!", 
                    "Please enter your name");
            return;
        }
        if(txtEmailC.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Create Account Error!", 
                    "Please enter your email address");
            return;
        }
        if(txtPasswordC.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Create Account Error!", 
                    "Please create a password");
            return;
        }
        
        //Once Sign Up button is pressed, the scene will change the the main page
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
