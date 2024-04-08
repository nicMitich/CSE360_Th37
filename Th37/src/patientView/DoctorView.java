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
	
	// Create a BorderPane as the root layout

	    

		private BorderPane root = new BorderPane();

	// Create a TextField for entering patient's name

	    private TextField patientNameField = new TextField();

	// Create buttons for different functionalities

	    private Button physicalTestButton = new Button("Physical Test");

	    private Button prescriptionButton = new Button("Prescription");

	    private Button patientInfoButton = new Button("Patient Information");

	    private Button messagesButton = new Button("Messages");

	            // Create a TextArea for entering test results and prescriptions

	    private TextArea testResultsArea = new TextArea();

	    private TextArea prescriptionArea = new TextArea();

	// Constructor to initialize the Doctor View page

	    public DoctorView() {

	        // Set the patient's name TextField at the center of the root layout

	        root.setCenter(patientNameField);

	// Create an HBox to hold the buttons on the left side

	        HBox leftButtons = new HBox(physicalTestButton);

	// Create an HBox to hold the buttons on the right side

	        HBox rightButtons = new HBox(prescriptionButton, messagesButton, patientInfoButton);

	// Create a VBox to hold the left and right button groups

	        VBox topButtons = new VBox(leftButtons, rightButtons);

	// Set the top buttons group at the top of the root layout

	        root.setTop(topButtons);

	 // Set the test results area on the left side of the root layout

	        root.setLeft(testResultsArea);

	// Set the prescription area on the right side of the root layout

	        root.setRight(prescriptionArea);

	// Register event handlers for button clicks

	        registerButtonHandlers();

	    }



	    // Method to register event handlers for button clicks

	    private void registerButtonHandlers() {

	        // Register event handler for the Physical Test button

	        physicalTestButton.setOnAction(event -> handlePhysicalTestButtonClick());



	        // Register event handler for the Prescription button

	        prescriptionButton.setOnAction(event -> handlePrescriptionButtonClick());



	        // Register event handler for the Patient Information button

	        patientInfoButton.setOnAction(event -> handlePatientInfoButtonClick());



	        // Register event handler for the Messages button

	        messagesButton.setOnAction(event -> handleMessagesButtonClick());

	    }



	    // Method to handle the Physical Test button click event

	    private void handlePhysicalTestButtonClick() {

	        // Code to handle the Physical Test button click event

	        // Open a new window or dialog for entering test results

	        // Store the test results in the testResultsArea

	        // ...

	    }



	    // Method to handle the Prescription button click event

	    private void handlePrescriptionButtonClick() {

	        // Code to handle the Prescription button click event

	        // Open a new window or dialog for prescribing medication

	        // Store the prescription in the prescriptionArea

	        // Send the prescription to the patient's preferred pharmacy

	        // ...

	    }



	    // Method to handle the Patient Information button click event

	    private void handlePatientInfoButtonClick() {

	        // Code to handle the Patient Information button click event

	        // Retrieve and display the patient's medical history, previous health issues,

	        // prescribed medications, and immunizations

	        // ...

	    }



	    // Method to handle the Messages button click event

	    private void handleMessagesButtonClick() {

	        // Code to handle the Messages button click event

	        // Open the messaging system

	        // Display alerts for new messages received from patients

	        // Provide options to respond with a text message

	        // ...

	    }

	 // Getter method to retrieve the root layout

	    public BorderPane getRoot() {

	        return root;

	    }

	 // Method to create and show the Doctor View scene

	    public void showDoctorView() {

	        // Create a Scene with the root layout

	        Scene scene = new Scene(root);

	// Create a new Stage (window) and set the scene

	        // Stage stage = new Stage();

	        // stage.setScene(scene);

	        // stage.show();

	    }

	}


