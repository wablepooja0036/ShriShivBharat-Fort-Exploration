package com.c2w.dashboards;

import java.io.IOException;

import com.c2w.controller.Dataurls;
import com.c2w.controller.Searchcontrol;
import com.c2w.initialize.InitializeApp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Landingpage extends Application {
    static Image mainImage;

    @Override
    public void start(Stage primaryStage) {
        Landingpage landingPage = new Landingpage();
        landingPage.init(primaryStage);
        }
        //this method contain all view which needs to show on landing page
        private void init(Stage primaryStage){
        String StartPageurl = "https://api.unsplash.com/photos/upq5LE9gASU?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug";
        StackPane root = new StackPane();

        // Top Bar
        HBox topBar = new HBox(10);
        //topBar.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 0;");
        //topBar.setAlignment(Pos.CENTER);
        topBar.minHeight(100);
        topBar.setPadding(new Insets(20,0,0,0));

      
       

        // Dropdown Menu for "Call Us"
        Label callUsMenu = new Label("+919307396830");
        callUsMenu.setTextFill(Color.WHITE);
        callUsMenu.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        Button aboutus = new Button("About us");
        aboutus.setStyle("-fx-background-color: transparent;");
        aboutus.setTextFill(Color.WHITE);
        aboutus.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        

        // MenuBar
        ImageView phoneIcon = new ImageView(new Image("images//call.png"));
        phoneIcon.setFitHeight(40);
        phoneIcon.setFitWidth(40);
        phoneIcon.setLayoutX(900);
        
        

        // Center element
        HBox centerElements = new HBox(20);
        centerElements.setAlignment(Pos.CENTER);

        // Left side elements
        HBox leftElements = new HBox(20);
        leftElements.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = new ImageView(new Image("images//symb2.jpeg"));
        Circle circleClip = new Circle(100, 100, 100);
        logo.setClip(circleClip);
        logo.setFitHeight(200);
        logo.setFitWidth(200);

        

        ImageView emailIcon = new ImageView(new Image("images//mail.png"));
        emailIcon.setFitHeight(40);
        emailIcon.setFitWidth(40);

        Label email = new Label("shrishivbharat@gmail.com");
        email.setTextFill(Color.WHITE);
        email.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        leftElements.getChildren().addAll(email, emailIcon);
        leftElements.setPadding(new Insets(0,0,0,30));

        HBox menuBar = new HBox(10,phoneIcon,callUsMenu,aboutus);
        menuBar.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18px;");
        menuBar.setAlignment(Pos.CENTER_LEFT);
       // menuBar.setPadding(new Insets(0,30,0,0));

        // Exchange the places of callUsMenu and callUs
        topBar.getChildren().addAll(leftElements, menuBar);
        HBox.setHgrow(leftElements, Priority.ALWAYS);

        // Overlay Text
        Label Apptitle = new Label("SHRISHIVBHARAT");
        Apptitle.setTextFill(Color.BLANCHEDALMOND);
        Apptitle.setFont(Font.font("Arial", FontWeight.BOLD, 92));
        Apptitle.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 10, 0.5, 0.0, 0.0);");

        Label title = new Label("From the SAHYADRI MOUNTAINS to the shores of the Arabian Sea ");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 10, 0.5, 0.0, 0.0);");

        Label toursInfo = new Label("Maharashtra’s beauty knows no bounds");
        toursInfo.setTextFill(Color.WHITE);
        toursInfo.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        toursInfo.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 10, 0.5, 0.0, 0.0);");

        Button viewForts = new Button("VIEW FORTS");
        viewForts.setStyle("-fx-background-color: #4CBB17; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 26px;-fx-background-radius: 25px;");
        Button bookings = new Button("BOOK TREK");
        bookings.setStyle("-fx-background-color: #4CBB17; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 26px;-fx-background-radius: 25px;");
      


           Button[] buttons = {viewForts,bookings};

        for (Button btns : buttons) {
           
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

        VBox overlayText = new VBox(10,logo, Apptitle, title, toursInfo, viewForts, bookings);
        overlayText.setAlignment(Pos.CENTER);

        // Combine the elements
        BorderPane content = new BorderPane();
        content.setTop(topBar);
        content.setCenter(overlayText);

        StackPane.setAlignment(topBar, Pos.TOP_CENTER);
        StackPane.setAlignment(overlayText, Pos.CENTER);

        try {
            Dataurls.imageData(StartPageurl);
            Image mainimage = new Image(Dataurls.imgurl);
            ImageView mainview = new ImageView(mainimage);
            mainview.fitWidthProperty().bind(primaryStage.widthProperty());
            mainview.fitHeightProperty().bind(primaryStage.heightProperty());
            mainview.setPreserveRatio(false);
            //BoxBlur bblur=new BoxBlur();
            //mainview.setEffect(bblur);
           // bblur.setHeight(10);
            //bblur.setWidth(10);
            //bblur.setIterations(3);

            root.getChildren().addAll(mainview, content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //on click viewforts it redirects to searchdistrict page
        viewForts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Searchdist pg1 = new Searchdist();
                pg1.page1view(primaryStage);
            }
        });
        aboutus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                about pg1 = new about();
                pg1.createAboutScene(primaryStage);;
            }
        });
        //on click booking it redirects to InitializeApp page that is loginstance
        bookings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InitializeApp booking = new InitializeApp();
                try {
                    booking.bookingtreks(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(root);
        primaryStage.setTitle("SHRISHIVBHARAT");
        Image icon = new Image("images//symb2.jpeg");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
