package com.c2w.controller;

import com.c2w.firebaseConfig.DataService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignupController {

    private LoginController loginController;

    public SignupController(LoginController loginController) {
        this.loginController = loginController;
    }

    public Scene createSignupScene() {
         String signupPageurl = "https://api.unsplash.com/photos/qbcAfJApLbE?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug";
        StackPane root = new StackPane();
        Label userLabel = new Label("Username:");
        userLabel.setStyle("-fx-font-weight:bold;-fx-font-size:18;");
        TextField userTextField = new TextField();
        userTextField.setStyle("-fx-font-size: 18px;  -fx-background-color: #FFFFFF;-fx-background-radius: 25px");
        Label passLabel = new Label("Password:");
        passLabel.setStyle("-fx-font-weight:bold;-fx-font-size:18;");
        PasswordField passField = new PasswordField();
        passField.setStyle("-fx-font-size: 18px;  -fx-background-color: #FFFFFF;-fx-background-radius: 25px");
        Button signupButton = new Button("Signup");

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                loginController.showLoginScene();
            }

        });
        Label []labels={userLabel,passLabel};
        for (Label lbl : labels) {
            lbl.setStyle("-fx-font-size:28;-fx-font-weight:bold;-fx-text-fill: white;");
        }
        Button[] btn = { signupButton,backButton};

        for (Button btns : btn) {
            btns.setStyle("-fx-font-size:16;-fx-font-weight:bold;-fx-background-color:green;-fx-text-fill: white;-fx-background-radius: 25px;");
                 btns.setOnMouseEntered(e -> {
            // Add hover effect - example: drop shadow and scale animation
            btns.setEffect(new DropShadow());
            btns.setScaleX(1.1);
            btns.setScaleY(1.1);
        });
        btns.setOnMouseExited(e -> {
            // Remove hover effect
            btns.setEffect(null);
            btns.setScaleX(1.0);
            btns.setScaleY(1.0);
        });
        }




        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);
        VBox fieldBox2 = new VBox(10, passLabel, passField);
        fieldBox2.setMaxSize(300, 30);
        HBox buttonBox = new HBox(50, signupButton, backButton);
        buttonBox.setMaxSize(350, 30);
        buttonBox.setAlignment(Pos.CENTER);

        signupButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleSignup( userTextField.getText(), passField.getText());
            }

        });

        VBox vbox = new VBox(20, fieldBox1, fieldBox2, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        
         try {
            Dataurls.imageData(signupPageurl);
            Image mainimage = new Image(Dataurls.imgurl);
            ImageView mainview = new ImageView(mainimage);
          
            mainview.setPreserveRatio(true);
            BoxBlur bblur=new BoxBlur();
            mainview.setEffect(bblur);
            bblur.setHeight(10);
            bblur.setWidth(10);
            bblur.setIterations(3);

            root.getChildren().addAll(mainview, vbox);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(root,1733,980);
    }

 private void handleSignup( String username, String password) {
 DataService dataService;
 try {
 dataService = new DataService();
 Map<String, Object> data = new HashMap<>();
 data.put("password", password);
 data.put("username", username);
 dataService.addData("users", username, data);
 System.out.println("User registered successfully");

 loginController.showLoginScene();
 } catch (Exception ex) {
 ex.printStackTrace();
 }
 }

    
}
