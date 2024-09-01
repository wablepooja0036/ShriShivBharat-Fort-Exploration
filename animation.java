package com.c2w;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image1 = new Image("com//c2w//images//bckbtn.png");
        ImageView imageView1 = new ImageView(image1);

        Image image2 = new Image("com//c2w//images//bckbtn.png");
        ImageView imageView2 = new ImageView(image2);

        Image image3 = new Image("com//c2w//images//bckbtn.png");
        ImageView imageView3 = new ImageView(image3);

        Image image4 = new Image("com//c2w//images//bckbtn.png");
        ImageView imageView4 = new ImageView(image4);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);

        vbox.setSpacing(10);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        Duration duration = Duration.seconds(4);

        TranslateTransition transition1 = new TranslateTransition(duration, imageView1);
        TranslateTransition transition2 = new TranslateTransition(duration, imageView2);
        TranslateTransition transition3 = new TranslateTransition(duration, imageView3);
        TranslateTransition transition4 = new TranslateTransition(duration, imageView4);

        transition1.setFromY(0);
        transition1.setToY(-300); 
        transition2.setFromY(0);
        transition2.setToY(-300);
        transition3.setFromY(0);
        transition3.setToY(-300);
        transition4.setFromY(0);
        transition4.setToY(-600);

        transition1.play();
        transition2.play();
        transition3.play();
        transition4.play();

        KeyFrame keyFrame = new KeyFrame(duration, 
                new KeyValue(imageView1.translateYProperty(), -300), 
                new KeyValue(imageView2.translateYProperty(), -300), 
                new KeyValue(imageView3.translateYProperty(), -300), 
                new KeyValue(imageView4.translateYProperty(), -300));

        timeline.getKeyFrames().add(keyFrame);

        timeline.play();

        Scene scene = new Scene(vbox, 1566, 938);
        primaryStage.setTitle("Travel App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}