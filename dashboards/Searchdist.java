package com.c2w.dashboards;
import java.io.IOException;

/* 
import com.c2w.controller.Dataurls;
import com.c2w.controller.Searchcontrol;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Searchdist {
    public static Stage primaryStage;
    public static TextField districtField;
    public static String district;
    public static String page1url1 = "https://api.unsplash.com/photos/tqUhnN2ke5g?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug";
    public static String page1url2 = "https://api.unsplash.com/photos/BKLHxgbYFDI?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug";
    public static String page1url3 = "https://api.unsplash.com/photos/h5wvMCdOV3w?client_id=27Z60AWgyDGvOUEy7nDEQv6wI7tCbU7zswYRaOspNHY";
    public static String page1url4 = "https://api.unsplash.com/photos/Nzn9RrlBfzI?client_id=27Z60AWgyDGvOUEy7nDEQv6wI7tCbU7zswYRaOspNHY";

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    VBox vi1Box;
    VBox vi2Box;

    public static String JDBC_URL = "jdbc:mysql://localhost:3306/app";
    public static String JDBC_USER = "root";
    public static String JDBC_PASSWORD = "root@123";

    public void page1view(Stage primaryStage) {
        Searchdist.primaryStage = primaryStage;

        primaryStage.setTitle("SHRISHIVBHARAT");
        Image icon = new Image("images//symb2.jpeg");
        primaryStage.getIcons().add(icon);

        //for image we have use gridpane here
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.setMinWidth(400);

        Button backButton = new Button("");
        backButton.setStyle("-fx-background-color: transparent;");
        Image image = new Image("images//backgreenbtn.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        backButton.setGraphic(imageView);
        GridPane.setConstraints(backButton, 0, 0);

        Label districtLabel = new Label("Enter District:");
        districtLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 56px; -fx-font-family: 'Abril Fatface';-fx-text-fill: black;");

        districtField = new TextField();
        districtField.setMaxHeight(40);

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: green;-fx-font-weight: bold; -fx-font-size: 26px; -fx-font-family: 'Montserrat';  -fx-border-color: white;-fx-border-width: 1px; -fx-border-radius: 6px;-fx-text-fill: white;");
        searchButton.getStyleClass().add("trendy-button");
        
        Button[] btneffects={searchButton,backButton};
        for(Button btns:btneffects){
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




        VBox searching = new VBox(20);
        searching.getChildren().addAll(districtLabel, districtField, searchButton);
        searching.setPadding(new Insets(200, 0, 0, 40));
        GridPane.setConstraints(searching, 0, 2);

        Label notFoundLabel = new Label();
        notFoundLabel.setText("District Not Found !");
        notFoundLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-font-family: 'Abril Fatface';-fx-text-fill: Red;");
        notFoundLabel.setVisible(false);
        notFoundLabel.setPadding(new Insets(50, 0, 0, 40));
        GridPane.setConstraints(notFoundLabel, 0, 3);

        gridPane.getChildren().addAll(backButton, searching, notFoundLabel);
        //loading image through api
        try {
            Dataurls.imageData(page1url1);
            Image backgroundImage1 = new Image(Dataurls.imgurl);
            imageView1 = new ImageView(backgroundImage1);

            Dataurls.imageData(page1url2);
            Image backgroundImage2 = new Image(Dataurls.imgurl);
            imageView2 = new ImageView(backgroundImage2);

            Dataurls.imageData(page1url3);
            Image backgroundImage3 = new Image(Dataurls.imgurl);
            imageView3 = new ImageView(backgroundImage3);

            Dataurls.imageData(page1url4);
            Image backgroundImage4 = new Image(Dataurls.imgurl);
            imageView4 = new ImageView(backgroundImage4);

            vi1Box = new VBox(10);
            vi2Box = new VBox(10);
            vi1Box.getChildren().addAll(imageView1, imageView2);
            vi2Box.getChildren().addAll(imageView3, imageView4);

            imageView1.setFitWidth(400);
            imageView1.setFitHeight(410);
            imageView1.setPreserveRatio(false);

            imageView2.setFitWidth(400);
            imageView2.setFitHeight(418);
            imageView2.setPreserveRatio(false);

            imageView3.setFitWidth(400);
            imageView3.setFitHeight(420);
            imageView3.setPreserveRatio(false);

            imageView4.setFitWidth(400);
            imageView4.setFitHeight(450);
            imageView4.setPreserveRatio(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HBox hb = new HBox(10);
        hb.getChildren().addAll(gridPane, vi1Box, vi2Box);
        vi1Box.setPadding(new Insets(50, 0, 0, 510));
        vi2Box.setPadding(new Insets(20, 0, 0, 0));
        hb.setPadding(new Insets(0, 5, 0, 0));

        Scene scene = new Scene(hb, primaryStage.getWidth(), primaryStage.getHeight());

    

        searchButton.setOnAction(e -> {
            district = districtField.getText();
            if (Searchcontrol.districtExists(district)) {
                
                Fortsinfo fi = new Fortsinfo();
                fi.thirdpage(district, primaryStage);
            } else {
                notFoundLabel.setVisible(true);
            }
        });

        backButton.setOnAction(e -> {
            Landingpage sApp = new Landingpage();
            sApp.start(primaryStage);
        });

        LinearGradient gradient = new LinearGradient(
            0, 0, 1, 1,
            true,
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.BLACK),
            new Stop(1, Color.BLACK)
        );

        hb.setBackground(new Background(new BackgroundFill(gradient, null, null)));
        hb.setStyle("-fx-background-color: #FAF2E9");

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    
}*/
import com.c2w.controller.Dataurls;
import com.c2w.controller.Searchcontrol;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Searchdist  {
    private static final int IMAGE_WIDTH = 600;
    private static final int IMAGE_HEIGHT = 470;
    private static final int SCROLL_SPEED = 500;
    private static ScrollPane scrollPane;
    private static VBox topImageBox;
    private static double position = 0.0;
    public static Stage primaryStage;
    public static TextField districtField;
    public static String district;
  //  public static String page1url1 = "https://api.unsplash.com/photos/tqUhnN2ke5g?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug";
    //public static String page1url2 = "https://api.unsplash.com/photos/BKLHxgbYFDI?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug";
    //public static String page1url3 = "https://api.unsplash.com/photos/h5wvMCdOV3w?client_id=27Z60AWgyDGvOUEy7nDEQv6wI7tCbU7zswYRaOspNHY";
    //public static String page1url4 = "https://api.unsplash.com/photos/Nzn9RrlBfzI?client_id=27Z60AWgyDGvOUEy7nDEQv6wI7tCbU7zswYRaOspNHY";

 

    public static String JDBC_URL = "jdbc:mysql://localhost:3308/app";
    public static String JDBC_USER = "root";
    public static String JDBC_PASSWORD = "root";

   
    public void page1view(Stage primaryStage) {
        Searchdist.primaryStage = primaryStage;
        topImageBox = new VBox(50);
            topImageBox.setPadding(new Insets(0));
            topImageBox.setAlignment(Pos.CENTER_LEFT);
            topImageBox.setStyle("fx-background-radius:20");

        String[] imageUrls = {"https://api.unsplash.com/photos/tqUhnN2ke5g?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug"
, "https://api.unsplash.com/photos/BKLHxgbYFDI?client_id=j4VDQqnya-TkPUaLfTByCdXRlKvmMFJh7ZhTqWSDpug",
    "https://api.unsplash.com/photos/h5wvMCdOV3w?client_id=27Z60AWgyDGvOUEy7nDEQv6wI7tCbU7zswYRaOspNHY",
    "https://api.unsplash.com/photos/Nzn9RrlBfzI?client_id=27Z60AWgyDGvOUEy7nDEQv6wI7tCbU7zswYRaOspNHY"
            
     };
     for (String url : imageUrls) {
        try {
            Dataurls.imageData(url);
        } catch (IOException e) {
        
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(new Image(Dataurls.imgurl));
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        //imageView.setPreserveRatio(true);
        imageView.setStyle("fx-background-radius:20");
        topImageBox.getChildren().add(imageView);
    }
    scrollPane = new ScrollPane(topImageBox);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("fx-background-radius:100");
    scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setPadding(new Insets(0,0,0,100));
    startAutomaticScrolling();


        primaryStage.setTitle("SHRISHIVBHARAT");
        Image icon = new Image("images//symb2.jpeg");
        primaryStage.getIcons().add(icon);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.setMinWidth(400);

        Button backButton = new Button("");
        backButton.setStyle("-fx-background-color: transparent;");
        Image backImage = new Image("images//backgreenbtn.png");
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitWidth(50);
        backImageView.setFitHeight(50);
        backButton.setGraphic(backImageView);
        GridPane.setConstraints(backButton, 0, 0);
      
        String sloganText = "If I could travel the world I would simply\n travel SAHYADRI I've heard that there's soil is \nsacered  by feet touch of \nCHATRAPATI SHIVAJI MAHARAJ.\n_________________________________________________";
        VBox sloganBox = new VBox(5);
        String[] lines = sloganText.split("\n");
        for (String line : lines) {
            HBox lineBox = new HBox(5);
            for (char c : line.toCharArray()) {
                Label letterLabel = new Label(String.valueOf(c));
                letterLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 26px; -fx-font-family: 'Abril Fatface';-fx-text-fill:#01796F;");
                addLetterAnimation(letterLabel);
                lineBox.getChildren().add(letterLabel);
            }
            sloganBox.getChildren().add(lineBox);
        }
        

        Label districtLabel = new Label("Enter District:");
        districtLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 56px; -fx-font-family: 'Abril Fatface';-fx-text-fill: black;");

        districtField = new TextField();
        districtField.setStyle("-fx-font-size: 18px;-fx-border-radius: 20px;  -fx-background-color: #FFFFFF;-fx-background-radius: 25px;-fx-border-color: green;");
        districtField.setMinHeight(40);
        districtField.setMaxWidth(300);
        
        

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color:#3DED97;-fx-font-weight: bold; -fx-font-size: 26px; -fx-font-family: 'Montserrat';   -fx-border-radius: 6px;-fx-text-fill: black;-fx-background-radius: 25px;");

        Button[] btneffects = {searchButton, backButton};
        for (Button btn : btneffects) {
            btn.setOnMouseEntered(e -> {
                btn.setEffect(new Glow());
                btn.setScaleX(1.1);
                btn.setScaleY(1.1);
            });
            btn.setOnMouseExited(e -> {
                btn.setEffect(null);
                btn.setScaleX(1.0);
                btn.setScaleY(1.0);
            });
        }
       

         

        VBox searching = new VBox(20);
        searching.getChildren().addAll(sloganBox,districtLabel, districtField, searchButton);
        
        searching.setPadding(new Insets(100, 0, 0, 100));
        GridPane.setConstraints(searching, 0, 2);

        Label notFoundLabel = new Label();
        notFoundLabel.setText("District Not Found !");
        notFoundLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-font-family: 'Abril Fatface';-fx-text-fill: Red;");
        notFoundLabel.setVisible(false);
        notFoundLabel.setPadding(new Insets(50, 0, 0, 100));
        GridPane.setConstraints(notFoundLabel, 0, 3);
   

        gridPane.getChildren().addAll(backButton, searching, notFoundLabel);


        
        HBox hb = new HBox(10);
        hb.getChildren().addAll(gridPane, scrollPane);
        hb.setPadding(new Insets(0, 5, 0, 0));

        Scene scene = new Scene(hb, primaryStage.getWidth(), primaryStage.getHeight());

        searchButton.setOnAction(e -> {
            district = districtField.getText();
            if (Searchcontrol.districtExists(district)) {
                Fortsinfo fi = new Fortsinfo();
                fi.thirdpage(district, primaryStage);
            } else {
                notFoundLabel.setVisible(true);
            }
        });

        backButton.setOnAction(e -> {
            Landingpage sApp = new Landingpage();
            sApp.start(primaryStage);
        });

        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.BLACK),
                new Stop(1, Color.BLACK)
        );

        hb.setBackground(new Background(new BackgroundFill(gradient, null, null)));
        hb.setStyle("-fx-background-color: #FAF2E9");

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        
    }
        private void addLetterAnimation(Label letterLabel) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), letterLabel);
        rotateTransition.setFromAngle(-10);
        rotateTransition.setToAngle(10);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();

      


    }

    private static void startAutomaticScrolling() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), new KeyValue(scrollPane.vvalueProperty(), 0)),
            new KeyFrame(Duration.seconds(10), new KeyValue(scrollPane.vvalueProperty(), 1))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
     


    }
    
   