package com.c2w.controller;
import org.json.JSONObject;

import com.c2w.dashboards.Fortsinfo;
import com.c2w.dashboards.Searchdist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Searchcontrol  {
    


         public static boolean districtExists(String district) {
        try (Connection conn = DriverManager.getConnection(Searchdist.JDBC_URL, Searchdist.JDBC_USER, Searchdist.JDBC_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM dist WHERE district = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, district);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

             public static  void displayFortTypes(String district) {
            Fortsinfo.hillButton.setOnAction(e -> Fortsinfocontrol.displayForts(district,"hill"));
            Fortsinfo.seaButton.setOnAction(e -> Fortsinfocontrol.displayForts(district, "sea"));
            Fortsinfo.landButton.setOnAction(e -> Fortsinfocontrol.displayForts(district, "land"));
        }
        

 
    }  
