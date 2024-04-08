
package patientView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NurseView extends Application {
	public void start(Stage primaryStage) {
		//Top tabs
		TabPane tabPane = new TabPane();
		Tab homeTab = new Tab("Home");
		Tab patientInfoTab = new Tab("Patient Information");
		Tab profileTab = new Tab("Profile");
		Tab settingsTab = new Tab("Settings");
		//Make tabs closable to false
		homeTab.setClosable(false);
		patientInfoTab.setClosable(false);
		profileTab.setClosable(false);
		settingsTab.setClosable(false);
		
		//Home tab content
		BorderPane homePane = new BorderPane();
		
		Label homeLabel = new Label("Nurse Homepage");
		homeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		homeLabel.setAlignment(Pos.CENTER);
		
		TextField patientNameField = new TextField();
		patientNameField.setAlignment(Pos.CENTER);
		patientNameField.setPromptText("Patient Name");
		patientNameField.setMaxWidth(150);
		
		Button messageButton = new Button("Messages");
		messageButton.setPrefSize(175,50);
		messageButton.setOnAction(event -> {
			//If button is clicked go to message view
			//Navigation logic goes here
		});
		
		HBox topBox = new HBox(10, homeLabel, messageButton);
		topBox.setAlignment(Pos.CENTER);
		
		VBox centerBox = new VBox(10, patientNameField);
		centerBox.setAlignment(Pos.CENTER);
		
		Button vitalChecksButton = new Button("Vital Checks");
		vitalChecksButton.setOnAction(event -> {
			//If button is clicked goes to vital checks view
			//Navigation logic goes here
		});
		
		Button questionnaireButton = new Button("Questionnaire");
		questionnaireButton.setOnAction(event -> {
			//If button is clicked goes to questionnaire view
			//Navigation logic goes here
		});
		
		HBox bottomBox = new HBox(10, vitalChecksButton, questionnaireButton);
		bottomBox.setAlignment(Pos.CENTER);
		homePane.setTop(topBox);
		homePane.setCenter(centerBox);
		homePane.setBottom(bottomBox);
		homeTab.setContent(homePane);
		
		//Add tabs to tabPane
		tabPane.getTabs().addAll(homeTab, patientInfoTab, profileTab, settingsTab);
		
		homeLabel.prefWidthProperty().bind(homePane.widthProperty());
		messageButton.setMaxWidth(Double.MAX_VALUE);
		patientNameField.prefWidthProperty().bind(homePane.widthProperty().multiply(0.8));
		topBox.prefWidthProperty().bind(homePane.widthProperty());
		bottomBox.prefWidthProperty().bind(homePane.widthProperty());
		
		//Set scene
		BorderPane root = new BorderPane(tabPane);
		Scene scene = new Scene(root, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Nurse View");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}