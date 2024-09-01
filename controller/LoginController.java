package com.c2w.controller;

import com.c2w.dashboards.AdminPage;
import com.c2w.dashboards.Landingpage;
import com.c2w.dashboards.Searchdist;
import com.c2w.dashboards.UserPage;
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
import java.util.concurrent.ExecutionException;

public class LoginController {

    private Stage primaryStage;
    private Scene loginScene;
    private Scene userScene;
    private Scene adminScene;
    private DataService dataService;
    public static String key;
    private Label errorLabel;

    //login instance stage shows user option to login as user,admin and sigup option
    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        dataService = new DataService();
        initScenes();
    }

    private void initScenes() {
        initLoginScene();
    }

    private void initLoginScene() {
         String loginPageurl = "https://api.unsplash.com/photos/qbcAfJApLbE?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug";
        StackPane root = new StackPane();
        Label marathiLabel = new Label("भटक्यांचे अनोखे जग...");
        marathiLabel.setStyle("-fx-font-size:76;-fx-font-weight:bold;-fx-text-fill: white;");
        marathiLabel.setLayoutY(20);

        Label userLabel = new Label("Username");
        TextField userTextField = new TextField();
        userTextField.setStyle("-fx-font-size: 18px;  -fx-background-color: #FFFFFF;-fx-background-radius: 25px;");
        Label passLabel = new Label("Password");
        PasswordField passField = new PasswordField();
        passField.setStyle("-fx-font-size: 18px;  -fx-background-color: #FFFFFF;-fx-background-radius: 25px;");
        Label[] labels = {userLabel,passLabel};

        for (Label lbl : labels) {
            lbl.setStyle("-fx-font-size:28;-fx-font-weight:bold;-fx-text-fill: white;");
        }

        Button adminLoginButton = new Button("Admin Login");
        adminLoginButton.setMaxWidth(200);
        Button userLoginButton = new Button("User Login");
        userLoginButton.setMaxWidth(200);

        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(200);
        Button signupButton = new Button("Signup");
        signupButton.setMaxWidth(200);
        Button backButton = new Button("Back");
        backButton.setMaxWidth(200);

   

        Button[] butns = {adminLoginButton, userLoginButton, loginButton, signupButton,backButton};

        for (Button btns : butns) {
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

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);

        loginButton.setOnAction(event -> {
            if (userTextField.getText().isEmpty() || passField.getText().isEmpty()) {
                showError("Username and Password cannot be empty");
            } else {
                handleLogin(userTextField.getText(), passField.getText());
                userTextField.clear();
                passField.clear();
            }
        });

        signupButton.setOnAction(event -> {
            showSignupScene();
            userTextField.clear();
            passField.clear();
        });

        adminLoginButton.setOnAction(event -> {
            if (userTextField.getText().isEmpty() || passField.getText().isEmpty()) {
                showError("Username and Password cannot be empty");
            } else {
                handleAdminLogin(userTextField.getText(), passField.getText());
                userTextField.clear();
                passField.clear();
            }
        });

        userLoginButton.setOnAction(event -> {
            if (userTextField.getText().isEmpty() || passField.getText().isEmpty()) {
                showError("Username and Password cannot be empty");
            } else {
                handleUserLogin(userTextField.getText(), passField.getText());
                userTextField.clear();
                passField.clear();
            }
        });

       
        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(400, 30);
        VBox fieldBox2 = new VBox(10, passLabel, passField);
        fieldBox2.setMaxSize(400, 30);
        VBox buttonBox = new VBox(20, adminLoginButton, userLoginButton, signupButton,backButton);
        buttonBox.setMaxSize(450, 30);
        buttonBox.setAlignment(Pos.CENTER);

        userTextField.setStyle("-fx-set-pref-width:350");
        passField.setStyle("-fx-set-pref-width:350");

        VBox vbox = new VBox(20,marathiLabel, fieldBox1, fieldBox2, errorLabel, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        
        
 try {
            Dataurls.imageData(loginPageurl);
            Image mainimage = new Image(Dataurls.imgurl);
            ImageView mainview = new ImageView(mainimage);
            mainview.fitWidthProperty().bind(primaryStage.widthProperty());
            mainview.fitHeightProperty().bind(primaryStage.heightProperty());
            mainview.setPreserveRatio(false);
            BoxBlur bblur=new BoxBlur();
            mainview.setEffect(bblur);
            bblur.setHeight(10);
            bblur.setWidth(10);
            bblur.setIterations(3);

            root.getChildren().addAll(mainview, vbox);

        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        backButton.setOnAction(event -> {
            Landingpage lp=new Landingpage();
            lp.start(primaryStage); // Start the Landingpage again
        });
    
        loginScene = new Scene(root);
    

    }

    private void initUserScene() {
        UserPage userPage = new UserPage(dataService);
        userScene = new Scene(userPage.createUserScene(this::handleLogout));
    }

    private void initAdminScene() {
        AdminPage adminPage = new AdminPage(this, dataService);
        adminScene = new Scene(adminPage.createAdminDashboard(this::handleLogout));
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public void showLoginScene() {
        setPrimaryStage(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
   
    

    private void handleUserLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password) && !dataService.isAdmin(username)) {
                key = username;
                initUserScene();
                setPrimaryStage(userScene);
                primaryStage.setTitle("User Dashboard");
                errorLabel.setVisible(false);
            } else {
                showError("Invalid client credentials");
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void handleLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password)) {
                key = username;
                initUserScene();
                setPrimaryStage(userScene);
                primaryStage.setTitle("User Dashboard");
                errorLabel.setVisible(false);
            } else {
                showError("Invalid credentials");
                key = null;
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void handleAdminLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password) && dataService.isAdmin(username)) {
                initAdminScene();
                setPrimaryStage(adminScene);
                primaryStage.setTitle("Admin Dashboard");
                errorLabel.setVisible(false);
            } else {
                showError("Invalid admin credentials");
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void showSignupScene() {
        SignupController signupController = new SignupController(this);
        Scene signupScene = signupController.createSignupScene();
        setPrimaryStage(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.show();
    }


    private void handleLogout() {
        setPrimaryStage(loginScene);
        primaryStage.setTitle("Login");
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public void setPrimaryStage(Scene scene) {
        // Get the current width and height of the stage
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        // Set the new scene
        primaryStage.setScene(scene);

        // Restore the width and height to the previously obtained dimensions
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        // Ensure the stage is maximized
        primaryStage.setMaximized(true);
    }

    public void returnToAdminView() {
        setPrimaryStage(adminScene);
        primaryStage.setTitle("Admin Dashboard");
    }
}
