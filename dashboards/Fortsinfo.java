/* 
package com.c2w.dashboards;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.c2w.controller.Searchcontrol;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fortsinfo {
    public static Button hillButton;
    public static Button seaButton;
    public static Button landButton;
    public static VBox forts;
    public static VBox forts_info;
    public static VBox fortanim;

    public static void thirdpage(String district, Stage thirdStage) {

        
        Scene scene = new Scene(new VBox(), 1733, 980);
        forts = new VBox(0);
        forts.setPrefHeight(300);
        forts.setMaxWidth(300);

        forts_info = createFortsInfoBox();

        HBox topBar = new HBox(150);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: rgba(128, 128, 128, 0.5); -fx-padding: 0;");

        Button backButton = new Button("");
        Image backbutton = new Image("images//bckbtn.png");
        ImageView imageView = new ImageView(backbutton);
        backButton.setStyle("-fx-background-color: transparent;");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        backButton.setGraphic(imageView);

        Button MAPButton = new Button("");
        Image Location = new Image("images//location.png");
        ImageView locview = new ImageView(Location);
        locview.setFitWidth(50);
        locview.setFitHeight(50);
        MAPButton.setGraphic(locview);

      

        hillButton = new Button("Hill Fort");
        seaButton = new Button("Sea Fort");
        landButton = new Button("Land Fort");

        Button[] buttons = {hillButton, seaButton, landButton};

        for (Button btns : buttons) {
            btns.setPrefSize(300, 70);
            btns.setStyle("-fx-background-color: transparent;-fx-font-weight: bold; -fx-font-size: 26px; -fx-font-family: 'Montserrat';-fx-text-fill:BLANCHEDALMOND ;");
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

       

        Searchcontrol.displayFortTypes(district);
        System.out.println(district);

        topBar.getChildren().addAll(backButton, hillButton, seaButton, landButton);

        VBox sideBar = createSideBar();
        HBox hb = createHBox(sideBar, forts_info, fortanim);

        VBox root = (VBox) scene.getRoot();
        
        root.getChildren().addAll(topBar, hb);
        root.setStyle("-fx-background-color: black;");
        sideBar.setLayoutX(0);
        sideBar.setLayoutY(50);
        thirdStage.setMaximized(true);
        thirdStage.setScene(scene);
        thirdStage.show();
        backButton.setOnAction(e -> {
            try {
                Searchdist.districtField.setText(Searchdist.district);
                Searchdist pg1=new Searchdist();
                pg1.page1view(thirdStage);    
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

   
    }

    private static VBox createFortsInfoBox() {
        VBox forts_info = new VBox(0);
        forts_info.setPadding(new Insets(70, 0, 0, 50));
        forts_info.setMaxHeight(900);
        forts_info.setMinWidth(880);
        
        return forts_info;
    }



    private static VBox createSideBar() {
        VBox sideBar = new VBox(20);
        sideBar.setPadding(new Insets(10));
        sideBar.setStyle("-fx-background-color: #2c3e50; -fx-border-color: #6c7a89; -fx-border-width: 1px; -fx-border-radius: 20px; -fx-effect: dropshadow(gaussian, #34495e, 10, 0.5, 0, 0);");

        sideBar.setPrefWidth(400);
        sideBar.setPrefHeight(1400);

        Label sideBarLabel = new Label("If I could travel the world\n \nI would simply travel SAHYADRI\n \nI've heard that there's soil is\n\n sacered  by feet touch of\n\n CHHATRAPATI SHIVAJI MAHARAJ...");
        sideBarLabel.setTextFill(Color.BLANCHEDALMOND);
        sideBarLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        /*forts.setOnMouseEntered(e -> {
        forts.setStyle("-fx-background-color: #34495e; -fx-border-color: #95a5a6; -fx-border-width: 1px; -fx-border-radius: 20px; -fx-effect: dropshadow(gaussian, #34495e, 10, 0.8, 0, 0);");
        });

        forts.setOnMouseExited(e -> {
        forts.setStyle("-fx-background-color: #2c3e50; -fx-border-color: #6c7a89; -fx-border-width: 1px; -fx-border-radius: 20px; -fx-effect: dropshadow(gaussian, #34495e, 10, 0.5, 0, 0);");
        });*/

       /*  sideBarLabel.setPadding(new Insets(100, 0, 0, 30));

        ImageView sideBarImage = new ImageView(new Image("images//mountain.png"));
        sideBarImage.setFitWidth(150);
        sideBarImage.setFitHeight(150);

        sideBar.getChildren().addAll(forts, sideBarLabel, sideBarImage);
        return sideBar;
    }
       public static void openGoogleMaps(String location) {
        try {
            String encodedQuery = URLEncoder.encode(location, StandardCharsets.UTF_8.toString());
            String url = "https://www.google.com/maps?q=" + encodedQuery;
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop not supported. Can't open browser.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static HBox createHBox(VBox sideBar, VBox forts_info, VBox fortanim) {
        HBox hb = new HBox();
        hb.getChildren().addAll(sideBar, forts_info);
        
        return hb;
    }

    
    
    
}*/
package com.c2w.dashboards;

import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.c2w.controller.Searchcontrol;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fortsinfo {
    public static Button hillButton;
    public static Button seaButton;
    public static Button landButton;
    public static VBox forts;
    public static VBox forts_info;
    public static VBox fortanim;
    public static String googleMapsUrl;

    public void thirdpage(String district, Stage thirdStage)  {
        Scene scene = new Scene(new VBox(), 1733, 980);
        forts = new VBox(0);
        forts.setMinHeight(350);
        forts.setMinWidth(300);
        forts.setAlignment(Pos.CENTER);

        forts_info = createFortsInfoBox();
        
        HBox topBar = new HBox(150);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #3DED97; -fx-padding: 0;");
        
        Button backButton = new Button("");
        Image backbutton = new Image("images//backgreenbtn.png");
        ImageView imageView = new ImageView(backbutton);
        backButton.setStyle("-fx-background-color: transparent;");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        backButton.setGraphic(imageView);

        Button MAPButton = new Button("");
        Image Location = new Image("images//location.png");
        ImageView locview = new ImageView(Location);
        locview.setFitWidth(50);
        locview.setFitHeight(50);
        MAPButton.setGraphic(locview);

        hillButton = new Button("Hill Fort");
        seaButton = new Button("Sea Fort");
        landButton = new Button("Land Fort");

        Button[] buttons = {hillButton, seaButton, landButton,backButton};

        for (Button btns : buttons) {
            btns.setPrefSize(300, 70);
            btns.setStyle("-fx-background-color: transparent;-fx-font-weight: bold; -fx-font-size: 26px; -fx-font-family: 'Montserrat';-fx-text-fill:BLACK;");
            btns.setOnMouseEntered(e -> {
                btns.setEffect(new DropShadow());
                btns.setScaleX(1.1);
                btns.setScaleY(1.1);
            });
            btns.setOnMouseExited(e -> {
                btns.setEffect(null);
                btns.setScaleX(1.0);
                btns.setScaleY(1.0);
            });
        }

        Searchcontrol.displayFortTypes(district);
        System.out.println(district);

        topBar.getChildren().addAll(backButton, hillButton, seaButton, landButton);

        VBox sideBar = createSideBar();
       

        // Create a WebView for displaying Google Maps
        WebView webView = new WebView();
        
        try {
            googleMapsUrl = "https://www.google.com/maps?q=" + URLEncoder.encode(district, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        webView.getEngine().load(googleMapsUrl);
        HBox hb = createHBox(sideBar, forts_info, webView);

        VBox root = (VBox) scene.getRoot();
        root.getChildren().addAll(topBar, hb);
        root.setStyle("-fx-background-color: #FAF2E9;");
        sideBar.setLayoutX(0);
        sideBar.setLayoutY(50);
        
        thirdStage.setScene(scene);
        thirdStage.setMaximized(true);
        thirdStage.show();
        
        backButton.setOnAction(e -> {
            try {
               
                Searchdist pg1 = new Searchdist();
                Searchdist.districtField.setText(Searchdist.district);
                pg1.page1view(thirdStage);    
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private static VBox createFortsInfoBox() {
        VBox forts_info = new VBox(0);
        forts_info.setPadding(new Insets(70, 0, 0, 50));
        forts_info.setMaxHeight(900);
        forts_info.setMinWidth(680);
        return forts_info;
    }

    private static VBox createSideBar() {
        VBox sideBar = new VBox(0);
        sideBar.setPadding(new Insets(10));
        sideBar.setStyle("-fx-background-color: #99EDC3; -fx-border-color: #3DED97; -fx-border-width: 1px; -fx-border-radius: 20px; -fx-effect: dropshadow(gaussian, #34495e, 10, 0.5, 0, 0);-fx-font-weight: bold;");
        sideBar.setPrefWidth(400);
        sideBar.setPrefHeight(1500);

        Label sideBarLabel = new Label("Uncover the \nsecrets of \n महाराष्ट्र \nancient forts");
        sideBarLabel.setStyle("-fx-text-fill:black;-fx-font-size: 36px;");
        sideBarLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        sideBarLabel.setPadding(new Insets(100, 0, 0, 30));
        sideBarLabel.setMinHeight(300);

        ImageView sideBarImage = new ImageView(new Image("images//mountain.png"));
        sideBarImage.setFitWidth(200);
        sideBarImage.setFitHeight(200);

          Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), new KeyValue(sideBarImage.rotateProperty(), -10)),
            new KeyFrame(Duration.seconds(1), new KeyValue(sideBarImage.rotateProperty(), 10)),
            new KeyFrame(Duration.seconds(2), new KeyValue(sideBarImage.rotateProperty(), -10))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

        sideBar.getChildren().addAll(forts, sideBarLabel, sideBarImage);
        return sideBar;
    }

    private static HBox createHBox(VBox sideBar, VBox forts_info, WebView webView) {
        HBox hb = new HBox();
        hb.getChildren().addAll(sideBar, forts_info,webView);
        return hb;
    }

    public static void openGoogleMaps(String location) {
        try {
            String encodedQuery = URLEncoder.encode(location, StandardCharsets.UTF_8.toString());
            String url = "https://www.google.com/maps?q=" + encodedQuery;
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop not supported. Can't open browser.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

