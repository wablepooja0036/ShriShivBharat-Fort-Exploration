
/*package com.c2w.dashboards;

import com.c2w.controller.LoginController;
import com.c2w.firebaseConfig.DataService;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AdminPage {
    private final DataService dataService;
    private final LoginController loginController;
    private VBox adminDashboard;
    
    

    public AdminPage(LoginController loginController, DataService dataService) {
        this.loginController = loginController;
        this.dataService = dataService;
    }

    public VBox createAdminDashboard(Runnable logoutHandler) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-font-size:12;-fx-font-weight:bold;-fx-background-color:red;-fx-text-fill: white;");

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logoutHandler.run();
            }
        });

        adminDashboard = new VBox();
        adminDashboard.setSpacing(10);
        adminDashboard.getChildren().addAll(loadProjectDetails());

        adminDashboard.getChildren().add(logoutButton);
        vbox.getChildren().addAll(logoutButton,adminDashboard);
        vbox.setStyle("-fx-background-color:#f0f0f0;");
        return vbox;
    }

    private VBox loadProjectDetails() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        try {
            vbox.getChildren().clear();
            List<Map<String, Object>> projectDetails = dataService.getDataInDescendingOrder("collectionName", "timestamp");
            for (Map<String, Object> projectDetail : projectDetails) {
                HBox projectCard = createProjectCard(projectDetail);
                vbox.getChildren().add(projectCard);
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return vbox;
    }

    private HBox createProjectCard(Map<String, Object> projectDetail) {
        HBox card = new HBox();
        card.setPadding(new Insets(10));
        card.setSpacing(10);
        card.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color:#f0f0f0;");

        VBox projectVBox = new VBox();
        projectVBox.setSpacing(5);

        if (projectDetail != null) {
            if (projectDetail.get("Name") != null) {
                Text nameText = new Text("Name: ");
                nameText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label nameLabel = new Label(projectDetail.get("Name").toString());
                HBox nameBox = new HBox(nameText, nameLabel);
                nameBox.setSpacing(5);
                projectVBox.getChildren().add(nameBox);
            }
            if (projectDetail.get("age") != null) {
                Text ageText = new Text("Age: ");
                ageText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label ageLabel = new Label(projectDetail.get("age").toString());
                HBox ageBox = new HBox(ageText, ageLabel);
                ageBox.setSpacing(5);
                projectVBox.getChildren().add(ageBox);
            }
            if (projectDetail.get("gender") != null) {
                Text genderText = new Text("Gender: ");
                genderText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label genderLabel = new Label(projectDetail.get("gender").toString());
                HBox genderBox = new HBox(genderText, genderLabel);
                genderBox.setSpacing(5);
                projectVBox.getChildren().add(genderBox);
            }
            if (projectDetail.get("mobileNum") != null) {
                Text mobileText = new Text("Mobile: ");
                mobileText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label mobileLabel = new Label(projectDetail.get("mobileNum").toString());
                HBox mobileBox = new HBox(mobileText, mobileLabel);
                mobileBox.setSpacing(5);
                projectVBox.getChildren().add(mobileBox);
            }
            if (projectDetail.get("health") != null) {
                Text healthText = new Text("Health Issues: ");
                healthText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label healthLabel = new Label(projectDetail.get("health").toString());
                HBox healthBox = new HBox(healthText, healthLabel);
                healthBox.setSpacing(5);
                projectVBox.getChildren().add(healthBox);
            }
            if (projectDetail.get("trek") != null) {
                Text trekText = new Text("Trek: ");
                trekText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label trekLabel = new Label(projectDetail.get("trek").toString());
                HBox trekBox = new HBox(trekText, trekLabel);
                trekBox.setSpacing(5);
                projectVBox.getChildren().add(trekBox);
            }
        }

        // Create Edit and Delete buttons
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size:12;-fx-font-weight:bold;-fx-background-color:red;-fx-text-fill: white;");
        editButton.setStyle("-fx-font-size:12;-fx-font-weight:bold;-fx-background-color:green;-fx-text-fill: white;");

        // Set actions for Edit and Delete buttons
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleEdit(projectDetail);
            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDelete(projectDetail, card);
            }
        });

        VBox buttonsVBox = new VBox(5, editButton, deleteButton);

        card.getChildren().addAll(projectVBox, buttonsVBox);
        return card;
    }

    private void handleEdit(Map<String, Object> projectDetail) {
        if (loginController != null) {
            VBox editScene = createUserScene(projectDetail, () -> {
                adminDashboard.getChildren().clear();
                adminDashboard.getChildren().addAll(loadProjectDetails());
                loginController.returnToAdminView();
            });

            loginController.setPrimaryStage(new Scene(editScene, 700, 700));
        } else {
            System.err.println("Login controller is not initialized.");
        }
    }

    private void handleDelete(Map<String, Object> projectDetail, HBox card) {
        try {
            dataService.deleteProject("collectionName", projectDetail.get("Name").toString());

            VBox parentVBox = (VBox) card.getParent();
            parentVBox.getChildren().remove(card);

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private VBox createUserScene(Map<String, Object> projectDetail, Runnable returnToAdminView) {
        Label nameLabel = new Label("Enter your name:");
        nameLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill:white;");

        TextField nameField = new TextField((String) projectDetail.getOrDefault("Name", ""));
        nameField.setPromptText("Name");
        VBox nameBox = new VBox(10, nameLabel, nameField);
        nameBox.setMaxSize(300, 20);

        Label ageLabel = new Label("Enter Age:");
        ageLabel.setStyle("-fx-font-size:14;-fx-font-weight:bold; -fx-text-fill:white;");
        Spinner<Integer> ageSpinner = new Spinner<>(0, 120, projectDetail.get("age") != null ? Integer.parseInt(projectDetail.get("age").toString()) : 25);
        ageSpinner.setStyle("-fx-font-size:14;-fx-border-color:#2F4F4F;");
        VBox ageBox = new VBox(10, ageLabel, ageSpinner);

        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-font-size:14;-fx-font-weight:bold; -fx-text-fill:white;");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        maleRadio.setStyle("-fx-font-size:14; -fx-text-fill:white;");
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        femaleRadio.setStyle("-fx-font-size:14; -fx-text-fill:white;");
        RadioButton otherRadio = new RadioButton("Other");
        otherRadio.setToggleGroup(genderGroup);
        otherRadio.setStyle("-fx-font-size:14; -fx-text-fill:white;");
        if (projectDetail.get("gender") != null) {
            String gender = projectDetail.get("gender").toString();
            if (gender.equalsIgnoreCase("male")) {
                maleRadio.setSelected(true);
            } else if (gender.equalsIgnoreCase("female")) {
                femaleRadio.setSelected(true);
            } else {
                otherRadio.setSelected(true);
            }
        }
        HBox genderBox = new HBox(10, maleRadio, femaleRadio, otherRadio);

        Label mobileLabel = new Label("Enter mobile number:");
        mobileLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold; -fx-text-fill:white;");
        TextField mobileField = new TextField((String) projectDetail.getOrDefault("mobileNum", ""));
        mobileField.setPromptText("Mobile number");
        VBox mobileBox = new VBox(10, mobileLabel, mobileField);
        mobileBox.setMaxSize(300, 20);

        Label healthLabel = new Label("Enter health issue:");
        healthLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold; -fx-text-fill:white;");
        TextField healthField = new TextField((String) projectDetail.getOrDefault("health", ""));
        healthField.setPromptText("Health issue");
        VBox healthBox = new VBox(10, healthLabel, healthField);
        healthBox.setMaxSize(300, 20);

        Label trekLabel = new Label("Enter trek name:");
        trekLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold; -fx-text-fill:white;");
        TextField trekField = new TextField((String) projectDetail.getOrDefault("trek", ""));
        trekField.setPromptText("Trek name");
        VBox trekBox = new VBox(10, trekLabel, trekField);
        trekBox.setMaxSize(300, 20);

        Button updateButton = new Button("Update Data");
        updateButton.setStyle("-fx-font-size:12;-fx-font-weight:bold; -fx-text-fill:white;");
        HBox buttonBox = new HBox(updateButton);
        buttonBox.setAlignment(Pos.CENTER);

        Button returnButton = new Button("Return to Admin View");
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                returnToAdminView.run();
            }
        });

        VBox dataBox = new VBox(25, nameBox, ageBox, genderLabel, genderBox, mobileBox, healthBox, trekBox, buttonBox, returnButton);
        dataBox.setAlignment(Pos.CENTER);
        dataBox.setStyle("-fx-background-color: black;");

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Map<String, Object> updatedData = new HashMap<>();
                updatedData.put("Name", nameField.getText());
                updatedData.put("age", ageSpinner.getValue().toString());
                updatedData.put("gender", ((RadioButton) genderGroup.getSelectedToggle()).getText());
                updatedData.put("mobileNum", mobileField.getText());
                updatedData.put("health", healthField.getText());
                updatedData.put("trek", trekField.getText());
                updatedData.put("timestamp", LocalDateTime.now());

                try {
                    dataService.updateData("collectionName", projectDetail.get("Name").toString(), updatedData);
                } catch (ExecutionException | InterruptedException ex) {
                    ex.printStackTrace();
                }

                adminDashboard.getChildren().clear();
                adminDashboard.getChildren().addAll(loadProjectDetails());

                returnToAdminView.run();
            }
        });

        return dataBox;
    }



    
}*/
/*package com.c2w.dashboards;

import com.c2w.controller.LoginController;
import com.c2w.firebaseConfig.DataService;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AdminPage {
    private final DataService dataService;
    private final LoginController loginController;
    private VBox adminDashboard;

    public AdminPage(LoginController loginController, DataService dataService) {
        this.loginController = loginController;
        this.dataService = dataService;
    }

    public VBox createAdminDashboard(Runnable logoutHandler) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-font-size:12;-fx-font-weight:bold;-fx-background-color:red;-fx-text-fill: white;");

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logoutHandler.run();
            }
        });

        adminDashboard = new VBox();
        adminDashboard.setSpacing(10);
        adminDashboard.getChildren().addAll(loadProjectDetails());
        adminDashboard.setStyle("-fx-background-color:#99EDC3;");

        adminDashboard.getChildren().add(logoutButton);
        vbox.getChildren().addAll(logoutButton, adminDashboard);
        vbox.setStyle("-fx-background-color:#FAF2E9;");
        return vbox;
    }

    private VBox loadProjectDetails() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        try {
            vbox.getChildren().clear();
            List<Map<String, Object>> projectDetails = dataService.getDataInDescendingOrder("collectionName", "timestamp");
            for (Map<String, Object> projectDetail : projectDetails) {
                HBox projectCard = createProjectCard(projectDetail);
                vbox.getChildren().add(projectCard);
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return vbox;
    }

    private HBox createProjectCard(Map<String, Object> projectDetail) {
        HBox card = new HBox();
        card.setPadding(new Insets(10));
        card.setSpacing(10);
        card.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color:#f0f0f0;");

        VBox projectVBox = new VBox();
        projectVBox.setSpacing(5);

        if (projectDetail != null) {
            if (projectDetail.get("Name") != null) {
                Text nameText = new Text("Name: ");
                nameText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label nameLabel = new Label(projectDetail.get("Name").toString());
                HBox nameBox = new HBox(nameText, nameLabel);
                nameBox.setSpacing(5);
                projectVBox.getChildren().add(nameBox);
            }
            if (projectDetail.get("age") != null) {
                Text ageText = new Text("Age: ");
                ageText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label ageLabel = new Label(projectDetail.get("age").toString());
                HBox ageBox = new HBox(ageText, ageLabel);
                ageBox.setSpacing(5);
                projectVBox.getChildren().add(ageBox);
            }
            if (projectDetail.get("gender") != null) {
                Text genderText = new Text("Gender: ");
                genderText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label genderLabel = new Label(projectDetail.get("gender").toString());
                HBox genderBox = new HBox(genderText, genderLabel);
                genderBox.setSpacing(5);
                projectVBox.getChildren().add(genderBox);
            }
            if (projectDetail.get("mobileNum") != null) {
                Text mobileText = new Text("Mobile: ");
                mobileText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label mobileLabel = new Label(projectDetail.get("mobileNum").toString());
                HBox mobileBox = new HBox(mobileText, mobileLabel);
                mobileBox.setSpacing(5);
                projectVBox.getChildren().add(mobileBox);
            }
            if (projectDetail.get("health") != null) {
                Text healthText = new Text("Health Issues: ");
                healthText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label healthLabel = new Label(projectDetail.get("health").toString());
                HBox healthBox = new HBox(healthText, healthLabel);
                healthBox.setSpacing(5);
                projectVBox.getChildren().add(healthBox);
            }
            if (projectDetail.get("trek") != null) {
                Text trekText = new Text("Trek: ");
                trekText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label trekLabel = new Label(projectDetail.get("trek").toString());
                HBox trekBox = new HBox(trekText, trekLabel);
                trekBox.setSpacing(5);
                projectVBox.getChildren().add(trekBox);
            }
            if (projectDetail.get("date") != null) {
                Text dateText = new Text("Date: ");
                dateText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label dateLabel = new Label(projectDetail.get("date").toString());
                HBox dateBox = new HBox(dateText, dateLabel);
                dateBox.setSpacing(5);
                projectVBox.getChildren().add(dateBox);
            }
        }

     
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size:12;-fx-font-weight:bold;-fx-background-color:red;-fx-text-fill: white;");
       
    
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDelete(projectDetail, card);
            }
        });

        VBox buttonsVBox = new VBox(5, deleteButton);

        card.getChildren().addAll(projectVBox, buttonsVBox);
        return card;
    }



    private void handleDelete(Map<String, Object> projectDetail, HBox card) {
        try {
            dataService.deleteProject("collectionName", projectDetail.get("Name").toString());

            VBox parentVBox = (VBox) card.getParent();
            parentVBox.getChildren().remove(card);

            // Re-fetch data after deletion to ensure synchronization
            adminDashboard.getChildren().clear();
            adminDashboard.getChildren().addAll(loadProjectDetails());

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
*/
   

package com.c2w.dashboards;

import com.c2w.controller.LoginController;
import com.c2w.firebaseConfig.DataService;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AdminPage {
    private final DataService dataService;
    private final LoginController loginController;
    private VBox adminDashboard;
    
    

    public AdminPage(LoginController loginController, DataService dataService) {
        this.loginController = loginController;
        this.dataService = dataService;
    }

    public VBox createAdminDashboard(Runnable logoutHandler) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-font-size:16;-fx-font-weight:bold;-fx-background-color:red;-fx-text-fill: white;");

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logoutHandler.run();
            }
        });

        adminDashboard = new VBox();
        adminDashboard.setSpacing(10);
        adminDashboard.getChildren().addAll(loadProjectDetails());

        adminDashboard.getChildren().add(logoutButton);
        vbox.getChildren().addAll(logoutButton,adminDashboard);
        vbox.setStyle("-fx-background-color:#f0f0f0;");
        return vbox;
    }

    private VBox loadProjectDetails() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        try {
            vbox.getChildren().clear();
            List<Map<String, Object>> projectDetails = dataService.getDataInDescendingOrder("collectionName", "timestamp");
            for (Map<String, Object> projectDetail : projectDetails) {
                HBox projectCard = createProjectCard(projectDetail);
                vbox.getChildren().add(projectCard);
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return vbox;
    }

    private HBox createProjectCard(Map<String, Object> projectDetail) {
        HBox card = new HBox();
        card.setPadding(new Insets(10));
        card.setSpacing(10);
        

        VBox projectVBox = new VBox();
        projectVBox.setSpacing(5);
       

        if (projectDetail != null) {
            if (projectDetail.get("Name") != null) {
                Text nameText = new Text("Name: ");
                nameText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                Label nameLabel = new Label(projectDetail.get("Name").toString());
                HBox nameBox = new HBox(nameText, nameLabel);
                nameBox.setSpacing(5);
                projectVBox.getChildren().add(nameBox);
            }
            if (projectDetail.get("age") != null) {
                Text ageText = new Text("Age: ");
                ageText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                Label ageLabel = new Label(projectDetail.get("age").toString());
                HBox ageBox = new HBox(ageText, ageLabel);
                ageBox.setSpacing(5);
                projectVBox.getChildren().add(ageBox);
            }
            if (projectDetail.get("gender") != null) {
                Text genderText = new Text("Gender: ");
                genderText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                Label genderLabel = new Label(projectDetail.get("gender").toString());
                HBox genderBox = new HBox(genderText, genderLabel);
                genderBox.setSpacing(5);
                projectVBox.getChildren().add(genderBox);
            }
            if (projectDetail.get("mobileNum") != null) {
                Text mobileText = new Text("Mobile: ");
                mobileText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                Label mobileLabel = new Label(projectDetail.get("mobileNum").toString());
                HBox mobileBox = new HBox(mobileText, mobileLabel);
                mobileBox.setSpacing(5);
                projectVBox.getChildren().add(mobileBox);
            }
            if (projectDetail.get("health") != null) {
                Text healthText = new Text("Health Issues: ");
                healthText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                Label healthLabel = new Label(projectDetail.get("health").toString());
                HBox healthBox = new HBox(healthText, healthLabel);
                healthBox.setSpacing(5);
                projectVBox.getChildren().add(healthBox);
            }
            if (projectDetail.get("trek") != null) {
                Text trekText = new Text("Trek: ");
                trekText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                Label trekLabel = new Label(projectDetail.get("trek").toString());
                HBox trekBox = new HBox(trekText, trekLabel);
                trekBox.setSpacing(5);
                projectVBox.getChildren().add(trekBox);
            }
            if (projectDetail.get("date") != null) {
                Text dateText = new Text("date: ");
                dateText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
                Label dateLabel = new Label(projectDetail.get("date").toString());
                HBox dateBox = new HBox(dateText, dateLabel);
                dateBox.setSpacing(5);
                projectVBox.getChildren().add(dateBox);
            }
        }

        // Create Edit and Delete buttons
        
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size:12;-fx-font-weight:bold;-fx-background-color:red;-fx-text-fill: white;");
        
    
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDelete(projectDetail, card);
            }
        });

        VBox buttonsVBox = new VBox(5, deleteButton);

        card.getChildren().addAll(projectVBox, buttonsVBox);
        card.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color:#99EDC3;-fx-background-radius: 25px;");
        return card;
    }


    private void handleDelete(Map<String, Object> projectDetail, HBox card) {
      
        try {
            // Delete the project from the specified collection using the unique identifier (name)
            dataService.deleteProject("collectionName", projectDetail.get("Name").toString());


    
            // Remove the corresponding card from the parent VBox
            VBox parentVBox = (VBox) card.getParent();
            parentVBox.getChildren().remove(card);
    
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    

        

        

     
}








    




