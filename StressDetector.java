
import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.Alert.*;
import java.util.*;

public class StressDetector extends Application
{
    private Integer negLevel = 0;
    private Integer posLevel = 0;
    public void start(Stage mainStage)
    {
        // Set the main layout and scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600,500);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainStage.setTitle("Stress Number Detector");
        
        // Negative Level Counter & Buttons
        VBox negLevelBox = new VBox();
        negLevelBox.setAlignment(Pos.CENTER);
        // Count Label
        Label negCountLabel = new Label("Negative Level");
        negCountLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        Label negCountNum = new Label(negLevel.toString());
        negCountNum.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        // Button Box
        HBox negButtonBox = new HBox();
        negButtonBox.setSpacing(10);
        negButtonBox.setAlignment(Pos.CENTER);
        negButtonBox.setStyle("-fx-font-size: 15px;");
        Button negUp = new Button("Negative Up");
        Button negDown = new Button("Negative Down");
        negButtonBox.getChildren().addAll(negUp, negDown);
        // Add all elements into specific container
        negLevelBox.setSpacing(5);
        negLevelBox.getChildren().addAll(negCountLabel, negCountNum, negButtonBox);
        
        // Positive Level Counter & Buttons
        VBox posLevelBox = new VBox();
        posLevelBox.setAlignment(Pos.CENTER);
        // Count Label
        Label posCountLabel = new Label("Positive Level");
        posCountLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        Label posCountNum = new Label(negLevel.toString());
        posCountNum.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        // Button Box
        HBox posButtonBox = new HBox();
        posButtonBox.setSpacing(10);
        posButtonBox.setAlignment(Pos.CENTER);
        posButtonBox.setStyle("-fx-font-size: 15px;");
        Button posUp = new Button("Positive Up");
        Button posDown = new Button("Positive Down");
        posButtonBox.getChildren().addAll(posUp, posDown);
        // Add all elements into specific container
        posLevelBox.setSpacing(5);
        posLevelBox.getChildren().addAll(posCountLabel, posCountNum, posButtonBox);
        
        // Add both containers for negative and positive in, and the stress condition label
        mainLayout.getChildren().addAll(negLevelBox, posLevelBox);
        
        // Functionality for Negative and Positive Up/Down
        // Negative
        negUp.setOnAction((event)->{
            if (negLevel < 10) {
                negLevel++;
                negCountNum.setText(negLevel.toString());
            }
        });
        negDown.setOnAction((event)->{
            if (negLevel > 0)
            {
                negLevel--;
                negCountNum.setText(negLevel.toString());
            }
        });
        // Positive
        posUp.setOnAction((event)->{
            if (posLevel < 10) {
                posLevel++;
                posCountNum.setText(posLevel.toString());
            }
        });
        posDown.setOnAction((event)->{
            if (posLevel > 0)
            {
                posLevel--;
                posCountNum.setText(posLevel.toString());
            }
        });
        
        // Top menu for gene functions
        MenuBar topMenu = new MenuBar();
        topMenu.setStyle("-fx-font-size: 15px;");
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        // Menu Item - Check Stress Condition
        MenuItem condItem = new MenuItem("Check Stress Condition");
        condItem.setOnAction((e->{
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("Your Stress Condition Info");
            info.setHeaderText("Your Stress Condition is...");
            if (negLevel > posLevel) {
                info.setContentText("Stressful");
            }
            else if (negLevel < posLevel)
            {
                info.setContentText("Relieved");
            }
            else
            {
                info.setContentText("Neutral");
            }
            info.showAndWait();
        }));
        // Menu Item - Reset
        MenuItem resetItem = new MenuItem("Reset");
        resetItem.setOnAction((e->{
            negLevel = 0;
            posLevel = 0;
            negCountNum.setText(negLevel.toString());
            posCountNum.setText(posLevel.toString());
        }));
        // Close item
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.setOnAction((e->{
            mainStage.close();
            System.exit(0);
        }));
        // Add all menu items in
        fileMenu.getItems().addAll(condItem, resetItem, closeItem);
        mainStage.show();
    }
}
