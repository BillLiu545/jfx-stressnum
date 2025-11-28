
import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;

public class StressDetector2 extends Application
{
    private final Stress stack1 = new Stress(), stack2 = new Stress();
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
        Label negCountNum = new Label(stack1.getStressLevel().toString());
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
        Label posCountNum = new Label(stack2.getStressLevel().toString());
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
            stack1.pushStress();
            Integer level = stack1.getStressLevel();
            negCountNum.setText(level.toString());
        });
        negDown.setOnAction((event)->{
            stack1.popStress();
            Integer level = stack1.getStressLevel();
            negCountNum.setText(level.toString());
        });
        // Positive
        posUp.setOnAction((event)->{
            stack2.pushStress();
            Integer level = stack2.getStressLevel();
            posCountNum.setText(level.toString());
        });
        posDown.setOnAction((event)->{
            stack2.popStress();
            Integer level = stack2.getStressLevel();
            posCountNum.setText(level.toString());
        });
        
        // Top menu for functions
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
            if (stack1.getStressLevel() > stack2.getStressLevel()) {
                info.setContentText("Stressful");
            }
            else if (stack1.getStressLevel() < stack2.getStressLevel())
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
            stack1.clearStress();
            stack2.clearStress();
            negCountNum.setText(stack1.getStressLevel().toString());
            posCountNum.setText(stack2.getStressLevel().toString());
        }));
        // Close item
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.setOnAction((e->{
            mainStage.close();
            System.exit(0);
        }));
        // Add all menu items in
        fileMenu.getItems().addAll(condItem, resetItem, closeItem);
        
        // Show all elements once added
        mainStage.show();
    }
}
