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
import javafx.scene.control.ListView;
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
import java.lang.String;
import java.util.Date;

class Message{
	private String sender;
	private Date timestamp;
	private String content;
	private boolean isRead;

	//Constructor for Message Class
	public Message(String str1, Date date, String str2) {
		this.sender = str1;
		this.timestamp = date;
		this.content = str2;
		this.isRead = false; //Default status of new message is not read
	}

	//Change isRead flag so that message will not show as a notification
	public void markAsRead(){
		//Set isRead to true
		this.isRead = true;
	}

	public void save(){
		//Database will store this message with the other saved messages of each user
	}

	public void delete(){
		//The message object will be deleted from the database
	}
}

public class MessagingView{
	//Create BorderPane object for Message Screen layout
	private Scene mainMsgScene;
	private Scene newMsgScene;
	private Scene viewMsgScene;
	private BorderPane mainLayout;
	private BorderPane newLayout;
	private BorderPane viewLayout;
	private VBox patientMsg;
	private VBox nurseMsg;
	private VBox doctorMsg;
	private HBox buttonRow;
	
	//Create ListView Objects to show a list of messages from Paitents, Doctors, and Nursed
	private ListView<Message> patientList; 
	private ListView<Message> doctorList;
	private ListView<Message> nurseList;

	//Create TextArea object to enable user text input
	private TextArea messageEntry = new TextArea();

	//Create Buttons to perform required functions of sending, deleting, saving, and marking messages
	private Button sendButton = new Button("Send Message");
	private Button saveButton = new Button("Save Message");
	private Button delButton = new Button("Delete Message");
	private Button readButton = new Button("Mark As Read");

	//Create Labels
	private static Label title = new Label("Messaging System");
	private static Label patientLbl = new Label("Patients");
	private static Label doctorLbl = new Label("Doctors");
	private static Label nurseLbl = new Label("Nurse");

	//Create Message object to reference the selected message from list
	//private Message curMessage = /*currently selected message*/;

	//Constructor to initialize MessagingView- arrange layout
	public MessagingView(){
		/**************************************************
		-Set title in the top of the layout
		-Set patientList in the left side of layout w/ title above
		-Set nurseList in the center of layout w/ title above
		-Set doctorList in the right side of layout w/ title above
		-Put row of buttons on bottom of layout
		-Register event handlers for button clicks
		***************************************************/
		msgLayout = new BorderPane();
		patientMsg = new VBox();
		nurseMsg = new VBox();
		doctorMsg = new VBox();
		buttonRow = new HBox();
		patientList = new ListView();
		nurseList = new ListView();
		doctorList = new ListView();
			
		//Create V and H Boxes to organize on-screen elements
		patientMsg.getChildren().addAll(patientLbl,patientList);
		nurseMsg.getChildren().addAll(nurseLbl,nurseList);
		doctorMsg.getChildren().addAll(doctorLbl,doctorList);
		buttonRow.getChildren().addAll(sendButton,saveButton,delButton,readButton);
			
		//Place boxes within each border section
		msgLayout.setTop(title);
		msgLayout.setLeft(patientMsg);
		msgLayout.setCenter(nurseMsg);
		msgLayout.setRight(doctorMsg);
		msgLayout.setBottom(buttonRow);

		//Define the main message scene
		mainMsgScene = new Scene(mainLayout,800,500);
			
		//Define method calls for each button click
		sendButton.setOnAction((event)->{sendMessage();});
		saveButton.setOnAction((event)->{saveMessage();});
		delButton.setOnAction((event)->{delMessage();});
		readButton.setOnAction((event)->{readMessage();});
	}

	private void sendMessage(){
		//Create message object from selected recipients and input from text field
		//Use 3rd Party API to send a secure, encrypted message over the internet
	}

	private void saveMessage(){
		//Call save method on curMessage
	}

	private void delMessage(){
		//Call delete method on curMessage
	}

	private void readMessage(){
		//Call markAsRead function on curMessage
	}

	public void show(){
		/**************************************************
		-Create Scene with msgLayout as layout
		-Create new Stage with above scene
		-Show the above Stage
		**************************************************/		
	}
}
