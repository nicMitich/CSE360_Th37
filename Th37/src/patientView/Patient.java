package patientView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Patient {

	public void start(Stage primaryStage) {
		//Top tabs
		TabPane tabPane = new TabPane();
		Tab homeTab = new Tab("Home");
		Tab profileTab = new Tab("Profile");
		Tab settingsTab = new Tab("Settings");
		//Make tabs closable to false
		homeTab.setClosable(false);
		profileTab.setClosable(false);
		settingsTab.setClosable(false);
		
		//Home tab content
		BorderPane homePane = new BorderPane();
		
		Label homeLabel = new Label("Patient Homepage");
		homeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		homeLabel.setAlignment(Pos.CENTER);
		
		
		
		Button messageButton = new Button("Messages");
		messageButton.setPrefSize(175,50);
		messageButton.setOnAction(event -> {
			//If button is clicked go to message view
			//Navigation logic goes here
		});
		
		HBox topBox = new HBox(10, homeLabel, messageButton);
		topBox.setAlignment(Pos.CENTER);
		
		Button previousVisitsButton = new Button("Previous Visits");
		previousVisitsButton.setOnAction(event -> {
			//If button is clicked goes to vital checks view
			//Navigation logic goes here
		});
		
		HBox bottomBox = new HBox(10, previousVisitsButton);
		bottomBox.setAlignment(Pos.CENTER);
		homePane.setTop(topBox);
		homePane.setBottom(bottomBox);
		homeTab.setContent(homePane);
		
		//Add tabs to tabPane
		tabPane.getTabs().addAll(homeTab, profileTab, settingsTab);
		
		homeLabel.prefWidthProperty().bind(homePane.widthProperty());
		messageButton.setMaxWidth(Double.MAX_VALUE);
		topBox.prefWidthProperty().bind(homePane.widthProperty());
		bottomBox.prefWidthProperty().bind(homePane.widthProperty());
		
		//Set scene
		BorderPane root = new BorderPane(tabPane);
		Scene scene = new Scene(root, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Nurse View");
		primaryStage.show();
	}
	
}
