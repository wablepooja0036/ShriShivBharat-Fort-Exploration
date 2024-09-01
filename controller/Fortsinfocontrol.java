package com.c2w.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.c2w.dashboards.Fortsinfo;
import com.c2w.dashboards.Searchdist;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
public class Fortsinfocontrol {
    static ResultSet rs;
//displaying forts name when clicked hiil,sea,land button of fortsinfo page 
//getting fort name from database and execution of query
    public static void displayForts(String district, String type) {
        
        Fortsinfo.forts.getChildren().clear();
        try (Connection conn = DriverManager.getConnection(Searchdist.JDBC_URL, Searchdist.JDBC_USER, Searchdist.JDBC_PASSWORD)) {
            String query = "SELECT name FROM fort WHERE district = ? AND id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, district);
                stmt.setString(2, type);
                ResultSet rs = stmt.executeQuery();
                 boolean hasForts = false;

                while (rs.next()) {
                    hasForts = true;
                    
                    String fortName = rs.getString("name");
                    if (fortName!="NULL"){

                    }
                  
                   
                   
                    Button fortButton = new Button(fortName);
                     Fortsinfo.forts.getChildren().add(fortButton);
                
                     fortButton.setOnAction(e -> {
                        // Handle button click if needed
                    fortsinfo(fortName); 
                        
                        
                        System.out.println("Clicked on " + fortName);
                    });
                   
                    fortButton.setPrefSize(300, 70);
                    fortButton.setStyle("-fx-background-color:#3DED97;-fx-font-weight: bold; -fx-font-size: 26px; -fx-font-family: 'Montserrat';   -fx-border-radius: 6px;-fx-text-fill: black;-fx-background-radius: 25px;");
                    fortButton.setOnMouseEntered(e -> {
                        fortButton.setEffect(new Glow());
                        fortButton.setScaleX(1.1);
                        fortButton.setScaleY(1.1);
                    });
                fortButton.setOnMouseExited(e -> {
                        fortButton.setEffect(null);
                        fortButton.setScaleX(1.0);
                        fortButton.setScaleY(1.0);
                    });

                
                }
                if (!hasForts) {
                    Label noFortsLabel = new Label("No Forts here!");
                    
                    Fortsinfo.forts.getChildren().add(noFortsLabel);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 //displaying of fortinformation when clicked on particular fortname
    static void fortsinfo(String fortName) {
        Fortsinfo.forts_info.getChildren().clear();
        try (Connection conn = DriverManager.getConnection(Searchdist.JDBC_URL, Searchdist.JDBC_USER, Searchdist.JDBC_PASSWORD)) {
            String query = "SELECT name, id, height, level, info, district FROM fort_info WHERE name=?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, fortName); // Set parameter index to 1, not 0

                 rs = stmt.executeQuery();

                if (rs.next() ) {
                    String FortName = rs.getString("name");
                    String fortId = rs.getString("id");
                    String fortHeight = rs.getString("height");
                    String fortLevel = rs.getString("level");
                    String fortInfo = rs.getString("info");
                    
                    String[] words = fortInfo.split(" ");
                    String infoText = "";
                    int wordCount = 0;
                    for (String word : words) {
                    infoText += word + " ";
                    wordCount++;
                    if (wordCount == 8) {
                    infoText = infoText.trim() + "\n";
                    wordCount = 0;
                        }
}
if (wordCount > 0) {
    infoText = infoText.trim() + "\n";
}
                    
                    String fortDistrict = rs.getString("district");
                    String fortimageurl="images//"+fortName+".jpeg";
                   Image fortimage =new Image(fortimageurl);
                   ImageView fortImageView=new ImageView(fortimage);
                   fortImageView.setFitHeight(280);
                   fortImageView.setFitWidth(350);
                   fortImageView.setLayoutX(30);
                   fortImageView.setStyle("-fx-background-radius:10px");

                    Label nameLabel = new Label("\n"+"Name : " + FortName+"\n");
                    Label idLabel = new Label("Type of fort : " + fortId + " Fort"+"\n");
                    Label heightLabel = new Label("Height : " + fortHeight+" metres"+"\n");
                    Label levelLabel = new Label("Level : " + fortLevel+"\n");
                    
                    Label infoLabel = new Label("Information : \n" + infoText+"\n");
                    Label districtLabel = new Label("District : " + fortDistrict+"\n");
            
                     Label [] lb = {nameLabel,idLabel,heightLabel,levelLabel,infoLabel,districtLabel};

        for (Label i : lb) {
            
           i.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-font-family: 'Montserrat';-fx-text-fill:BLACK ;");
            
        }
        
  Fortsinfo.forts_info.getChildren().addAll(fortImageView, nameLabel, districtLabel, idLabel, heightLabel, levelLabel, infoLabel);
                
                } else {
                    System.out.println("No data found for fort: " + fortName);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

 
}  


     
    

