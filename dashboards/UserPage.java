/*package com.c2w.dashboards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.c2w.controller.LoginController;
import com.c2w.firebaseConfig.DataService;
import com.google.cloud.firestore.DocumentSnapshot;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserPage {

    private DataService dataService; // DataService for Firestore

    private Label dataMsg; // Label to display status messages
    private ComboBox<String> selectTrekComboBox; // ComboBox to select trek
    private Label[] trekCountLabels;

    public UserPage(DataService dataService) {
        this.dataService = dataService;
    }

    // Method to create and return the user interface VBox for project
    public VBox createUserScene(Runnable logoutHandler) {
        
    
        // UI elements for entering project details
        Label message = new Label("MONSOON SPECIAL GATEWAYS FROM PUNE...!");
        message.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-font-style: italic; -fx-text-fill:#DAA520;");
        HBox anim = new HBox(10, message);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(message.rotateProperty(), -5)),
                new KeyFrame(Duration.seconds(1), new KeyValue(message.rotateProperty(), 5)),
                new KeyFrame(Duration.seconds(2), new KeyValue(message.rotateProperty(), -5))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

        Label nameLabel = new Label("Enter your name:");
        nameLabel.setStyle("-fx-font-size:18;-fx-font-weight:bold;-fx-text-fill: black;");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        VBox nameBox = new VBox(10, nameLabel, nameField);
        nameBox.setMaxSize(400, 20);

        Label mobileNumber = new Label("Enter mobile number:");
        mobileNumber.setStyle("-fx-font-size:18;-fx-font-weight:bold;-fx-text-fill: black;");
        TextField mobTextField = new TextField();
        mobTextField.setPromptText("Mobile number");
        VBox mobBox = new VBox(10, mobileNumber, mobTextField);
        mobBox.setMaxSize(400, 20);

        Label ageLabel = new Label("Enter Age:");
        ageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;-fx-text-fill: black;");
        Spinner<Integer> ageSpinner = new Spinner<>(0, 120, 25);
        ageSpinner.setStyle("-fx-font-size: 14px;-fx-text-fill: black;");
        VBox ageBox = new VBox(10, ageLabel, ageSpinner);
        ageBox.setMaxSize(400, 20);

        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;-fx-text-fill: black;");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        maleRadio.setStyle("-fx-font-size: 18px;-fx-text-fill: black;");
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        femaleRadio.setStyle("-fx-font-size: 18px;-fx-text-fill: black;");
        RadioButton otherRadio = new RadioButton("Other");
        otherRadio.setToggleGroup(genderGroup);
        otherRadio.setStyle("-fx-font-size: 18px;-fx-text-fill: black;");
        HBox genderBox = new HBox(10, genderLabel, maleRadio, femaleRadio, otherRadio);
        genderBox.setMaxSize(400, 20);

        Label healthLabel = new Label("Enter Health Issues:");
        healthLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; ;-fx-text-fill: black;");
        TextField healthField = new TextField();
        healthField.setPromptText("Health issues");
        healthField.setStyle("-fx-font-size: 18px; -fx-border-color: #2F4F4F; -fx-background-color: #FFFFFF;");
        VBox healthBox = new VBox(10, healthLabel, healthField);
        healthBox.setMaxSize(400, 20);

        Label selectTrekLabel = new Label("Select Trek:");
        selectTrekLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; ;-fx-text-fill: black;");

        selectTrekComboBox = new ComboBox<>(
                FXCollections.observableArrayList(
                        "Raigad Fort", "Rajgad Fort", "Harihar Fort", "Harishchandragad Fort",
                        "Visapur Fort", "Lohagad Fort", "Koraigad Fort", "Rajmachi Fort",
                        "Sudhagad Fort", "Jivdhan Fort"));
        selectTrekComboBox.setPromptText("Select Trek");

        VBox trekBox = new VBox(10, selectTrekLabel, selectTrekComboBox);
        trekBox.setMaxSize(400, 20);

        Label selectDateLabel = new Label("Select Date:");
        selectDateLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; ;-fx-text-fill: black;");

        DatePicker datePicker = new DatePicker();
        datePicker.setDayCellFactory(getDayCellFactory());
        datePicker.setPromptText("Select date");
        VBox dateBox = new VBox(10, selectDateLabel, datePicker);
        dateBox.setMaxSize(400, 20);

        Label payLabel = new Label("Rs.1500 per Person");
        payLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; ;-fx-text-fill: black;");
        Button addButton = new Button("Pay");
        addButton.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-background-color:#4CBB17;-fx-text-fill:white;");
        HBox buttonBox = new HBox(10, payLabel, addButton);
        buttonBox.setAlignment(Pos.CENTER);

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-font-size:12;-fx-font-weight:bold;-fx-background-color:red;-fx-text-fill: white;");

       addButton.setOnAction(event -> {
    if (validateForm(nameField, mobTextField, ageSpinner, genderGroup, healthField, selectTrekComboBox, datePicker)) {
        String selectedTrek = selectTrekComboBox.getValue();
        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameField.getText());
        data.put("age", ageSpinner.getValue());
        data.put("gender", ((RadioButton) genderGroup.getSelectedToggle()).getText());
        data.put("mobileNum", mobTextField.getText());
        data.put("health", healthField.getText());
        data.put("trek", selectedTrek);
        data.put("date", datePicker.getValue().toString());
        data.put("timestamp", LocalDateTime.now());

        // Show alert to enter amount
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Enter Amount");
        alert.setHeaderText("Please enter the amount of Rs. 1500");
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        alert.getDialogPane().setContent(amountField);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String amount = amountField.getText();
                if (amount.equals("1500")) {
                    try {
                        dataService.addData("collectionName", selectedTrek, data);
                       
                        nameField.clear();
                        ageSpinner.getValueFactory().setValue(25);
                        genderGroup.selectToggle(null);
                        mobTextField.clear();
                        healthField.clear();
                        selectTrekComboBox.getSelectionModel().clearSelection();
                        datePicker.setValue(null);

                       

                        // Show success alert
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Payment successful.");
                    } catch (ExecutionException | InterruptedException ex) {
                        dataMsg=new Label("Something went wrong");
                        ex.printStackTrace();
                        // Show error alert
                        showAlert(Alert.AlertType.ERROR, "Error", "Payment Failed: " + ex.getMessage());
                    }
                } else {
                    // Show error alert
                    showAlert(Alert.AlertType.ERROR, "Error", "Please enter the correct amount of Rs. 1500");
                }
            }
        });
    } else {
        dataMsg.setText("Please fill all fields.");
        showAlert(Alert.AlertType.WARNING, "Warning", "Please fill all fields.");
    }
});*/
package com.c2w.dashboards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.c2w.controller.LoginController;
import com.c2w.firebaseConfig.DataService;
import com.google.cloud.firestore.DocumentSnapshot;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class UserPage {

    private DataService dataService; // DataService for Firestore

    private Label dataMsg; // Label to display status messages
    private ComboBox<String> selectTrekComboBox; // ComboBox to select trek
    private Label[] trekCountLabels;

    public UserPage(DataService dataService) {
        this.dataService = dataService;
    }

    // Method to create and return the user interface VBox for project
    public VBox createUserScene(Runnable logoutHandler) {
        
        // UI elements for entering project details
        Label message = new Label("MONSOON SPECIAL GATEWAYS FROM PUNE...!");
        message.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-font-style: italic; -fx-text-fill:#AF005F;");
        HBox anim = new HBox(10, message);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(message.rotateProperty(), -5)),
                new KeyFrame(Duration.seconds(1), new KeyValue(message.rotateProperty(), 5)),
                new KeyFrame(Duration.seconds(2), new KeyValue(message.rotateProperty(), -5))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();


        Label nameLabel = new Label("Enter your name:");
        nameLabel.setStyle("-fx-font-size:18;-fx-font-weight:bold;-fx-text-fill: black;");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setStyle("-fx-font-size: 16px;-fx-text-fill: black;-fx-background-radius: 25px");
        VBox nameBox = new VBox(10, nameLabel, nameField);
        nameBox.setMaxSize(400, 20);

        Label mobileNumber = new Label("Enter mobile number:");
        mobileNumber.setStyle("-fx-font-size:18;-fx-font-weight:bold;-fx-text-fill: black;");
        TextField mobTextField = new TextField();
        mobTextField.setStyle("-fx-font-size: 16px;-fx-text-fill: black;-fx-background-radius: 25px");
        mobTextField.setPromptText("Mobile number");
        VBox mobBox = new VBox(10, mobileNumber, mobTextField);
        mobBox.setMaxSize(400, 20);

        Label ageLabel = new Label("Enter Age:");
        ageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;-fx-text-fill: black;");
        Spinner<Integer> ageSpinner = new Spinner<>(0, 120, 25);
        ageSpinner.setStyle("-fx-font-size: 14px;-fx-text-fill: black;-fx-background-radius: 25px");
        VBox ageBox = new VBox(10, ageLabel, ageSpinner);
        ageBox.setMaxSize(400, 20);

        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;-fx-text-fill: black;");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        maleRadio.setStyle("-fx-font-size: 18px;-fx-text-fill: black;");
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        femaleRadio.setStyle("-fx-font-size: 18px;-fx-text-fill: black;");
        RadioButton otherRadio = new RadioButton("Other");
        otherRadio.setToggleGroup(genderGroup);
        otherRadio.setStyle("-fx-font-size: 18px;-fx-text-fill: black;");
        HBox genderBox = new HBox(10, genderLabel, maleRadio, femaleRadio, otherRadio);
        genderBox.setMaxSize(400, 20);

        Label healthLabel = new Label("Enter Health Issues:");
        healthLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; ;-fx-text-fill: black;");
        TextField healthField = new TextField();
        healthField.setPromptText("Health issues");
        healthField.setStyle("-fx-font-size: 18px;  -fx-background-color: #FFFFFF;-fx-background-radius: 25px");
        VBox healthBox = new VBox(10, healthLabel, healthField);
        healthBox.setMaxSize(400, 20);

        Label selectTrekLabel = new Label("Select Trek:");
        selectTrekLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; ;-fx-text-fill: black;");

        selectTrekComboBox = new ComboBox<>(
                FXCollections.observableArrayList(
                        "Raigad Fort", "Rajgad Fort", "Harihar Fort", "Harishchandragad Fort",
                        "Visapur Fort", "Lohagad Fort", "Koraigad Fort", "Rajmachi Fort",
                        "Sudhagad Fort", "Jivdhan Fort"));
        selectTrekComboBox.setPromptText("Select Trek");

        VBox trekBox = new VBox(10, selectTrekLabel, selectTrekComboBox);
        trekBox.setMaxSize(400, 20);

        Label selectDateLabel = new Label("Select Date:");
        selectDateLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; ;-fx-text-fill: black;");

        DatePicker datePicker = new DatePicker();
        datePicker.setDayCellFactory(getDayCellFactory());
        datePicker.setPromptText("Select date");
        VBox dateBox = new VBox(10, selectDateLabel, datePicker);
        dateBox.setMaxSize(400, 20);

        Label payLabel = new Label("Rs.1500 per Person");
        payLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; ;-fx-text-fill: black;");
        Button addButton = new Button("Pay");
        addButton.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-background-color:green;-fx-background-radius: 25px;-fx-text-fill:black;");
        HBox buttonBox = new HBox(10, payLabel, addButton);
        buttonBox.setAlignment(Pos.CENTER);

        

        Button logoutButton = new Button("");
        Image logbutton = new Image("images//logout.png");
        ImageView imageView = new ImageView(logbutton);
        logoutButton.setStyle("-fx-background-color: transparent;");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        logoutButton.setGraphic(imageView);
        logoutButton.setAlignment(Pos.TOP_LEFT);
       
        logoutButton.setOnAction(event -> logoutHandler.run());
        HBox lb=new HBox();
        lb.getChildren().addAll(logoutButton);
       

     Button[] btneffects = {logoutButton, addButton};
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



        addButton.setOnAction(event -> {
            if (validateForm(nameField, mobTextField, ageSpinner, genderGroup, healthField, selectTrekComboBox, datePicker)) {
                String selectedTrek = selectTrekComboBox.getValue();
                Map<String, Object> data = new HashMap<>();
                data.put("Name", nameField.getText());
                data.put("age", ageSpinner.getValue());
                data.put("gender", ((RadioButton) genderGroup.getSelectedToggle()).getText());
                data.put("mobileNum", mobTextField.getText());
                data.put("health", healthField.getText());
                data.put("trek", selectedTrek);
                data.put("date", datePicker.getValue().toString());
                data.put("timestamp", LocalDateTime.now());

                // Show alert to enter amount
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enter Amount");
                alert.setHeaderText("Please enter the amount of Rs. 1500");
                TextField amountField = new TextField();
                amountField.setPromptText("Enter amount");
                alert.getDialogPane().setContent(amountField);

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        String amount = amountField.getText();
                        if (amount.equals("1500")) {
                            try {
                                dataService.addData("collectionName", selectedTrek, data);
                                dataMsg = new Label("Payment successful.");
                                nameField.clear();
                                ageSpinner.getValueFactory().setValue(25);
                                genderGroup.selectToggle(null);
                                mobTextField.clear();
                                healthField.clear();
                                selectTrekComboBox.getSelectionModel().clearSelection();
                                datePicker.setValue(null);
                                showAlert(Alert.AlertType.CONFIRMATION, "Success", "Payment Successful: " );
                            } catch (ExecutionException | InterruptedException ex) {
                                dataMsg = new Label("Something went wrong");
                                ex.printStackTrace();
                                // Show error alert
                                showAlert(Alert.AlertType.ERROR, "Error", "Payment Failed: " + ex.getMessage());
                            }
                        } else {
                            // Show error alert
                            showAlert(Alert.AlertType.ERROR, "Error", "Please enter the correct amount of Rs. 1500");
                        }
                    }
                });
            } else {
                dataMsg = new Label("Please fill all fields.");
                showAlert(Alert.AlertType.WARNING, "Warning", "Please fill all fields.");
            }
        });

      
        

        VBox dataBox = new VBox(25, anim, nameBox, mobBox, ageBox, genderBox, healthBox, trekBox, dateBox,
                buttonBox);
        dataBox.setAlignment(Pos.CENTER);
        dataBox.setLayoutX(100);
        dataBox.setMinWidth(700);
        dataBox.setStyle("-fx-background-color: #99EDC3; -fx-border-color: #3DED97; -fx-border-width: 1px; -fx-border-radius: 20px; -fx-effect: dropshadow(gaussian, #34495e, 10, 0.5, 0, 0);");

        
        VBox img=new VBox(40);
        Image img1= new Image("images//meals.png");
        ImageView imv1= new ImageView(img1);
        Image img2= new Image("images//bus.png");
        ImageView imv2= new ImageView(img2);
        Image img3= new Image("images//medication.png");
        ImageView imv3= new ImageView(img3);
        Image img4= new Image("images//mantrek.png");
        ImageView imv4= new ImageView(img4);
        img.getChildren().addAll(imv1,imv2,imv3,imv4);
        img.setPadding(new Insets(0,0,0,20));
        ImageView [] imv={imv1,imv2,imv3,imv4};
        for(ImageView i:imv){
            i.setFitHeight(100);
            i.setFitWidth(100);
        }

        // Create the image grid
        GridPane imageGrid = createImageGrid();

        HBox mainContent = new HBox(img,dataBox, imageGrid);
        
        mainContent.setSpacing(20);
        mainContent.setAlignment(Pos.CENTER);

        VBox mainContainer = new VBox(20,lb,mainContent);
        //mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color:  #FAF2E9;");

        return mainContainer;
    }

    private boolean validateForm(TextField nameField, TextField mobTextField, Spinner<Integer> ageSpinner, ToggleGroup genderGroup, TextField healthField, ComboBox<String> selectTrekComboBox, DatePicker datePicker) {
        return !nameField.getText().isEmpty() &&
               !mobTextField.getText().isEmpty() &&
               ageSpinner.getValue() != null &&
               genderGroup.getSelectedToggle() != null &&
               !healthField.getText().isEmpty() &&
               selectTrekComboBox.getValue() != null &&
               datePicker.getValue() != null;
    }



    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.getDayOfWeek().getValue() < 6); // Disable days that are not Saturday or Sunday
            }
        };
    }

    // Method to create the image grid
    public GridPane createImageGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setPadding(new Insets(0, 0, 0, 100));

        String[] imageNames = {"Raigad Fort", "Rajgad Fort", "Harihar Fort", "Harishchandragad Fort", "Visapur Fort",
                "Lohagad Fort", "Ratangad Fort", "Sudhagad Fort", "Torna Fort"};

        // Calculate number of rows needed
        int numRows = (int) Math.ceil(imageNames.length / 3.0);

        int col = 0;
        int row = 0;

        for (String imageName : imageNames) {
            Image image = new Image("images//" + imageName.toLowerCase() + ".jpeg");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(250);
            imageView.setFitHeight(250);
            Rectangle clip = new Rectangle(400, 400);
            clip.setArcWidth(40);
            clip.setArcHeight(40);
            imageView.setClip(clip);

            Label label = new Label(imageName);
            label.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

            StackPane imageBox = new StackPane(imageView, label);
            imageBox.setAlignment(Pos.CENTER);

            imageBox.setOnMouseEntered(e -> {
                // Add hover effect - example: drop shadow and scale animation
                imageBox.setEffect(new DropShadow());
                imageView.setScaleX(1.1);
                imageView.setScaleY(1.1);
            });
            imageBox.setOnMouseExited(e -> {
                // Remove hover effect
                imageBox.setEffect(null);
                imageView.setScaleX(1.0);
                imageView.setScaleY(1.0);
            });

            gridPane.add(imageBox, col, row);

            // Increment column and row
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        return gridPane;
    }
}
