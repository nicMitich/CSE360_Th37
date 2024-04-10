
package nurseview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NurseView extends Application {
	private Scene vitalScene;
	private Scene questionnaireScene;
	public void start(Stage primaryStage) {
		/*
		 * Nurse View Main Page
		 */
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
		
		Label homeLabel = new Label("Home");
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
			//If button is clicked and Name text field isn't empty go to vital scene
			if(patientNameField.getText().isBlank())
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Patient Name is required.");
				alert.showAndWait();
			}
			else
			{
				primaryStage.setScene(vitalScene);
			}
		});
		
		Button questionnaireButton = new Button("Questionnaire");
		questionnaireButton.setOnAction(event -> {
			//If button is clicked goes to questionnaire view
			//Navigation logic goes here
			if(patientNameField.getText().isBlank())
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Patient Name is required.");
				alert.showAndWait();
			}
			else
			{
				primaryStage.setScene(questionnaireScene);
			}
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
		Scene scene = new Scene(root, 400, 250);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Nurse View");
		primaryStage.show();
		/*
		 * Vitals Page
		 */
		GridPane vitals = new GridPane();
		//Title label
		Label vitalsTitle = new Label("Vitals Form");
		vitalsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		vitalsTitle.setAlignment(Pos.CENTER);
		
		//TextFields
		TextField heightField  = new TextField();
		TextField weightField  = new TextField();
		TextField temperatureField = new TextField();
		TextField bloodPressureField  = new TextField();
		
		//Buttons and actions
		Button saveVitals = new Button("Save");
		saveVitals.setOnAction(e -> {
			//check to see if any text field is empty
			if(heightField.getText().isEmpty() || weightField.getText().isEmpty() || 
					temperatureField.getText().isEmpty() || bloodPressureField.getText().isEmpty()) {
				//Alert user that all fields are required
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("All text fields are required.");
				alert.showAndWait();
				return;
			}
			//create string that have text fields text
			String height = heightField.getText();
			String weight = weightField.getText();
			String temperature = temperatureField.getText();
			String bloodPressure = bloodPressureField.getText();
			String patientName = patientNameField.getText();
			
			//Create new file
			File file = new File(patientName.toLowerCase() + "Vitals.txt");
			//try to write info into file
			try (FileWriter writer = new FileWriter(file)){
				//write info into file
				writer.write("Patient Name: " + patientName + "\n");
				writer.write("Height: " + height + "\n");
				writer.write("Weight: " + weight + "\n");
				writer.write("Temperature: " + temperature + "\n");
				writer.write("Blood Pressure: " + bloodPressure);
				//close file writer
				writer.close();
				//Let the user know the results have been saved successfully
				Alert alert = new Alert(Alert.AlertType.INFORMATION); 
				alert.setTitle("Patient's Vitals Saved");
				alert.setHeaderText(null);
				alert.setContentText("Patient's vitals saved successfully. Patient Name: " + patientName);
				alert.showAndWait();
			}
			//catch IOException
			catch(IOException ex) {
				ex.printStackTrace();
			}
			//clear text fields
			heightField.clear();
			weightField.clear();
			temperatureField.clear();
			bloodPressureField.clear();
		});
		Button backVitals = new Button("Back");
		backVitals.setOnAction(e -> primaryStage.setScene(scene));
		
		
		//Add labels, text fields and buttons to vitals grid
		vitals.add(vitalsTitle, 1, 0);
		vitals.add(new Label("Height: "), 0, 1);
		vitals.add(heightField, 1, 1);
		vitals.add(new Label("Weight: "), 0, 2);
		vitals.add(weightField, 1, 2);
		vitals.add(new Label("Temperature: "), 0, 3);
		vitals.add(temperatureField, 1, 3);
		vitals.add(new Label("Blood Pressure: "), 0, 4);
		vitals.add(bloodPressureField, 1, 4);
		vitals.add(backVitals, 0, 5);
		vitals.add(saveVitals, 1, 5);
		//set vitals grid to a scene
		vitalScene = new Scene(vitals, 300, 200);
		
		/*
		 * Questionnaire Page
		 */
		GridPane questionnaire = new GridPane();
		Label questionnaireTitle = new Label("Questionnaire");
		vitalsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		vitalsTitle.setAlignment(Pos.CENTER);
		
		//TextFields
		TextField allergiesField  = new TextField();
		TextField conditionsField  = new TextField();
		TextField otherField = new TextField();
		
		//Buttons and actions
		Button saveQuestionnaire = new Button("Save");
		saveQuestionnaire.setOnAction(e -> {
			//check to see if any text field is empty
			if(allergiesField.getText().isEmpty() || conditionsField.getText().isEmpty() || 
					otherField.getText().isEmpty()) {
				//Alert user that all fields are required
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("All text fields are required.");
				alert.showAndWait();
				return;
			}
			//create string that have text fields text
			String allergies = allergiesField.getText();
			String conditions = conditionsField.getText();
			String other = otherField.getText();
			String patientName = patientNameField.getText();
			
			//Create new file
			File file = new File(patientName.toLowerCase() + "Questionnaire.txt");
			//try to write info into file
			try (FileWriter writer = new FileWriter(file)){
				//write info into file
				writer.write("Patient Name: " + patientName + "\n");
				writer.write("Allergies: " + allergies + "\n");
				writer.write("Conditions: " + conditions + "\n");
				writer.write("Other: " + other);
				//close file writer
				writer.close();
				//Let the user know the results have been saved successfully
				Alert alert = new Alert(Alert.AlertType.INFORMATION); 
				alert.setTitle("Patient's Vitals Saved");
				alert.setHeaderText(null);
				alert.setContentText("Patient's questionnare saved successfully. Patient Name: " + patientName);
				alert.showAndWait();
			}
			//catch IOException
			catch(IOException ex) {
				ex.printStackTrace();
			}
			//clear text fields
			allergiesField.clear();
			conditionsField.clear();
			otherField.clear();
		});
		Button backQuestionnaire = new Button("Back");
		backQuestionnaire.setOnAction(e -> primaryStage.setScene(scene));
		
		
		//Add labels, text fields and buttons to questionnaire grid
		questionnaire.add(questionnaireTitle, 1, 0);
		questionnaire.add(new Label("Allergies: "), 0, 1);
		questionnaire.add(allergiesField, 1, 1);
		questionnaire.add(new Label("Conditions: "), 0, 2);
		questionnaire.add(conditionsField, 1, 2);
		questionnaire.add(new Label("Other: "), 0, 3);
		questionnaire.add(otherField, 1, 3);
		questionnaire.add(backQuestionnaire, 0, 4);
		questionnaire.add(saveQuestionnaire, 1, 4);
		
		//set questionnaire grid to a scene
		questionnaireScene = new Scene(questionnaire, 300, 200);
		
		/*
		 * Patient Information Tab
		 */
		BorderPane patientInformationPane = new BorderPane();
		//Title label
		Label patientInfoLbl = new Label("Patient Information");
		patientInfoLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		patientInfoLbl.setAlignment(Pos.CENTER);
		//Text Field and Text Area
		TextField patientNameField2 = new TextField();
		patientNameField2.setPromptText("Patient Name");
		TextArea display = new TextArea();
		display.setEditable(false);
		//Button and action
		Button search = new Button("Search");
		search.setOnAction(e -> {
			String patientName = patientNameField2.getText();
			//String builder content
			StringBuilder content = new StringBuilder();
			
			File vitalsFile = new File(patientName.toLowerCase() + "Vitals.txt");
			//if the result file exists
			if(vitalsFile.exists()) {
				//try to read the file
				try (BufferedReader reader = new BufferedReader(new FileReader(vitalsFile))) {
					content.append("Vitals\n");
					//create string line
					String line;
					//while line doesn't equal null
					while((line = reader.readLine())!= null)
					{
						content.append(line).append("\n");
					}
				}
				//catch IOException
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			//else alert the user with this message
			else {
				content.append("Vitals\n");
				content.append("No data found\n");
			}
			
			File questionnaireFile = new File(patientName.toLowerCase() + "Questionnaire.txt");
			//if the result file exists
			if(questionnaireFile.exists()) {
				//try to read the file
				try (BufferedReader reader2 = new BufferedReader(new FileReader(questionnaireFile))) {
					//String builder content
					content.append("\nQuestionnaire\n");
					//create string line
					String line2;
					//while line doesn't equal null
					while((line2 = reader2.readLine())!= null)
					{
						content.append(line2).append("\n");
					}
				}
				//catch IOException
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			//else alert the user with this message
			else {
				content.append("\nQuestionnaire\n");
				content.append("No Data Found\n");
			}
			/*
			 * Add more code to get more information from patient 
			 */
			display.setText(content.toString());
		});
		//Create top grid
		GridPane top = new GridPane();
		//Add labels, text fields, and buttons to top
		top.add(patientInfoLbl, 0, 0);
		top.add(patientNameField2, 0, 1);
		top.add(search, 1, 1);
		//Add top and text area to patient info pane
		patientInformationPane.setTop(top);
		patientInformationPane.setBottom(display);
		//set patient info pane to patient info tab
		patientInfoTab.setContent(patientInformationPane);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}