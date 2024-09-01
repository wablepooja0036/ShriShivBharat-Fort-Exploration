package com.c2w.initialize;

//import com.adminuser.*;
import com.c2w.controller.LoginController;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

 public class InitializeApp {


 public void bookingtreks(Stage primaryStage) throws Exception {
 LoginController loginController = new LoginController(primaryStage);

 primaryStage.setScene(loginController.getLoginScene());
 primaryStage.setMaximized(true);
 primaryStage.setTitle("Login");
 
 primaryStage.show();
 }

}
