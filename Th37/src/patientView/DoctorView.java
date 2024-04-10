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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

 
    
public class DoctorView {
	
	private Scene prescriptionScene;
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
		
		Label homeLabel = new Label("Doctor Homepage");
		homeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		homeLabel.setAlignment(Pos.CENTER);
		
		TextField patientNameField = new TextField();
		patientNameField.setAlignment(Pos.CENTER);
		patientNameField.setPromptText("Patient Name");
		patientNameField.setMaxWidth(150);
		
		
		
		Button PrescriptionButton = new Button("Physical Test and Prescriptions");
		PrescriptionButton.setOnAction(event -> {
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
				primaryStage.setScene(prescriptionScene);
			}
		});
		
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
		
		
		

		
		Button logOutButton = new Button("Logout");
		logOutButton.setPrefSize(175,50);
		
		logOutButton.setOnAction(event -> {
			//LOGS OUT OF ACCOUNT
		});
		HBox settingsBox = new HBox(logOutButton);
		settingsBox.setPadding(new Insets(50,0,0,100));
		settingsTab.setContent(settingsBox);
		
		HBox bottomBox = new HBox(10, PrescriptionButton);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(0,0,10,0));
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
		primaryStage.setTitle("Doctor View");
		primaryStage.show();
		
		/*
		 * Vitals Page
		 */
		GridPane prescription = new GridPane();
		//Title label
		Label prescriptionTitle = new Label("Physical & Prescription");
		prescriptionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		//prescriptionTitle.setAlignment(Pos.CENTER_LEFT);
		
		//TextFields
		TextField physicalField  = new TextField();
		TextField prescriptionField  = new TextField();
		
		
		//Buttons and actions
		Button saveVitals = new Button("Save");
		saveVitals.setOnAction(e -> {
			//check to see if any text field is empty
			if(physicalField.getText().isEmpty() || prescriptionField.getText().isEmpty() ) {
				//Alert user that all fields are required
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("All text fields are required.");
				alert.showAndWait();
				return;
			}
			//create string that have text fields text
			String medication = physicalField.getText();
			String reasoning = prescriptionField.getText();
			String patientName = patientNameField.getText();
			
			//Create new file
			File file = new File(patientName.toLowerCase() + "MedicalRecord.txt");
			//try to write info into file
			try (FileWriter writer = new FileWriter(file)){
				//write info into file
				writer.write("Patient Name: " + patientName + "\n");
				writer.write("Physical: " + medication + "\n");
				writer.write("Prescriptions: " + reasoning + "\n");
				//close file writer
				writer.close();
				//Let the user know the results have been saved successfully
				Alert alert = new Alert(Alert.AlertType.INFORMATION); 
				alert.setTitle("Patient's Data Saved");
				alert.setHeaderText(null);
				alert.setContentText("Patient's physical and prescription saved successfully. Patient Name: " + patientName);
				alert.showAndWait();
			}
			//catch IOException
			catch(IOException ex) {
				ex.printStackTrace();
			}
			//clear text fields
			physicalField.clear();
			prescriptionField.clear();
		});
		Button backVitals = new Button("Back");
		backVitals.setOnAction(e -> primaryStage.setScene(scene));
		
		
		//Add labels, text fields and buttons to vitals grid
		prescription.add(prescriptionTitle, 1, 0);
		prescription.add(new Label("Physical: "), 0, 1);
		prescription.add(physicalField, 1, 1);
		prescription.add(new Label("Prescription: "), 0, 2);
		prescription.add(prescriptionField, 1, 2);
		prescription.add(backVitals, 0, 5);
		prescription.add(saveVitals, 1, 5);
		//set vitals grid to a scene
		prescriptionScene = new Scene(prescription, 350, 200);
		
		
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
			
			File prescriptionFile = new File(patientName.toLowerCase() + "MedicalRecord.txt");
			//if the result file exists
			if(prescriptionFile.exists()) {
				//try to read the file
				try (BufferedReader reader = new BufferedReader(new FileReader(prescriptionFile))) {
					content.append("Information:\n");
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
				content.append("Prescription\n");
				content.append("No data found\n");
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


