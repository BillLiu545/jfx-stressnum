
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.TableColumn.*;
import javafx.util.converter.*;
import javafx.scene.chart.*;
import javafx.application.*;

public class StressPieDetector extends Application
{
    // Observable list required fort pie chart
    private final ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
    public void start(Stage mainStage) {
        // Set the main layout and scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600,600);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainStage.setTitle("Pie Chart Stress Detector");
        
        // Pie chart that displays percentage data of positive and negative
        PieChart chart = new PieChart();
        mainLayout.getChildren().add(chart);
        
        // Initialize the pie chart with data
        dataList.add(new PieChart.Data("Negative", 50));
        dataList.add(new PieChart.Data("Positive", 50));
        
        // link list to chart
        chart.setData(dataList);
        
        // chart formatting
        chart.setTitle("Pie Chart Stress Detector");
        chart.setLabelsVisible(true);
        chart.setLabelLineLength(50);
        chart.setLegendSide(Side.LEFT);
        
        // Buttons to increase/decrease negative or positive
        // Entire row for buttons
        HBox buttonRow = new HBox();
        mainLayout.getChildren().add(buttonRow);
        buttonRow.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(20);
        
        // HBox for negative side
        HBox negRow = new HBox();
        negRow.setSpacing(5);
        // Negative up
        Button negUp = new Button("Negative Up");
        negUp.setOnAction((event)->
        {
            Integer increased = 0;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Increase Negative");
            dialog.setContentText("Increase Negative");
            dialog.setContentText("Increase Negative by...");
            Optional<String> opt = dialog.showAndWait();
            String num = opt.get();
            try {
                int parsed = Integer.parseInt(num);
                if (parsed > 0 && parsed <= 50)
                {
                    increased = parsed;
                }
            }
            catch (NumberFormatException ex)
            {
                increased = 0;
            }
            Iterator<PieChart.Data> iter = dataList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (next.getName().equals("Negative"))
                {
                    double old_val = next.getPieValue();
                    double sum  = old_val + increased;
                    if ((sum <= 50))
                    {
                        next.setPieValue(sum);
                    }
                    break;
                }
            }
        });
        // Negative down
        Button negDown = new Button("Negative Down");
        negDown.setOnAction((event)->
        {
            Integer decreased = null;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Decrease Negative");
            dialog.setContentText("Decrease Negative");
            dialog.setContentText("Decrease Negative by...");
            Optional<String> opt = dialog.showAndWait();
            String num = opt.get();
            try {
                int parsed = Integer.parseInt(num);
                if (parsed > 0 && parsed <= 50)
                {
                    decreased = parsed;
                }
            }
            catch (NumberFormatException ex)
            {
                decreased = 0;
            }
            Iterator<PieChart.Data> iter = dataList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (next.getName().equals("Negative"))
                {
                    double old_val = next.getPieValue();
                    double diff  = old_val - decreased;
                    if ((diff >= 0))
                    {
                        next.setPieValue(diff);
                    }
                    break;
                }
            }
        });
        negRow.getChildren().addAll(negUp,negDown);
        buttonRow.getChildren().add(negRow);
         
        // HBox for positive side
        HBox posRow = new HBox();
        posRow.setSpacing(5);
        // Positive up
        Button posUp = new Button("Positive Up");
        posUp.setOnAction((event)->
        {
            Integer increased = 0;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Increase Positive");
            dialog.setContentText("Increase Positive");
            dialog.setContentText("Increase Positive by...");
            Optional<String> opt = dialog.showAndWait();
            String num = opt.get();
            try {
                int parsed = Integer.parseInt(num);
                if (parsed > 0 && parsed <= 50)
                {
                    increased = parsed;
                }
            }
            catch (NumberFormatException ex)
            {
                increased = 0;
            }
            Iterator<PieChart.Data> iter = dataList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (next.getName().equals("Positive"))
                {
                    double old_val = next.getPieValue();
                    double sum  = old_val + increased;
                    if ((sum <= 50))
                    {
                        next.setPieValue(sum);
                    }
                    break;
                }
            }
        });
        // Positive down
        Button posDown = new Button("Positive Down");
        posDown.setOnAction((event)->
        {
            Integer decreased = null;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Decrease Positive");
            dialog.setContentText("Decrease Positive");
            dialog.setContentText("Decrease Positive by...");
            Optional<String> opt = dialog.showAndWait();
            String num = opt.get();
            try {
                int parsed = Integer.parseInt(num);
                if (parsed > 0 && parsed <= 50)
                {
                    decreased = parsed;
                }
            }
            catch (NumberFormatException ex)
            {
                decreased = 0;
            }
            Iterator<PieChart.Data> iter = dataList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (next.getName().equals("Positive"))
                {
                    double old_val = next.getPieValue();
                    double diff  = old_val - decreased;
                    if ((diff >= 0))
                    {
                        next.setPieValue(diff);
                    }
                    break;
                }
            }
        });
        posRow.getChildren().addAll(posUp,posDown);
        buttonRow.getChildren().add(posRow);
        
        // Menu Bar
        MenuBar topMenu = new MenuBar();
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
            PieChart.Data pos = dataList.get(1);
            PieChart.Data neg = dataList.get(0);
            if (neg.getPieValue() > pos.getPieValue()) {
                info.setContentText("Stressful: " + pos.getPieValue() + "Positive/" + neg.getPieValue() + " Negative" );
            }
            else if (neg.getPieValue() < pos.getPieValue())
            {
                info.setContentText("Relieved: " + pos.getPieValue() + "Positive/" + neg.getPieValue() + " Negative" );
            }
            else
            {
                info.setContentText("Neutral: " + pos.getPieValue() + "Positive/" + neg.getPieValue() + " Negative" );
            }
            info.showAndWait();
        }));
        // Menu Item - Reset
        MenuItem resetItem = new MenuItem("Reset");
        resetItem.setOnAction((e->{
            dataList.clear();
            dataList.add(new PieChart.Data("Negative", 50));
            dataList.add(new PieChart.Data("Positive", 50));
        }));
        // Close item
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.setOnAction((e->{
            mainStage.close();
            System.exit(0);
        }));
        // Add all menu items in
        fileMenu.getItems().addAll(condItem, resetItem, closeItem);
        
        // Show all elements once they are added
        mainStage.show();
    }
}
