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
	private String recipient;
	private Date timestamp;
	private String content;
	private boolean isRead;

	//Constructor for Message Class
	public Message(String str1, String str2, String str3) {
		this.sender = str1;
		this.recipient = str2;
		//this.timestamp = date;
		this.content = str3;
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
	private Stage newMsgStage;
	private Scene mainMsgScene;
	private Scene newMsgScene;
	private Scene viewMsgScene;
	private BorderPane mainLayout;
	private BorderPane newLayout;
	private BorderPane viewLayout;
	private VBox patientMsg;
	private VBox nurseMsg;
	private VBox doctorMsg;
	private HBox mainBtnRow;
	private VBox newMsgBox;
	private HBox newBtnRow;
	
	//Create ListView Objects to show a list of messages from Paitents, Doctors, and Nursed
	private ListView<Message> patientList; 
	private ListView<Message> doctorList;
	private ListView<Message> nurseList;

	//Create TextArea object to enable user text input
	private TextArea messageEntry;
	private TextField recipient;
	private TextField sender;

	//Create Buttons to perform required functions of sending, deleting, saving, and marking messages
	private Button newMsgBtn;
	private Button saveBtn;
	private Button delBtn;
	private Button readBtn;
	private Button backBtn;
	private Button sendBtn;
	private Button cancelBtn;

	//Create Labels
	private static Label mainTitle = new Label("Messaging System");
	private static Label patientLbl = new Label("Patients");
	private static Label doctorLbl = new Label("Doctors");
	private static Label nurseLbl = new Label("Nurse");
	private static String newTitle = "New Message";
	private static Label senderLbl = new Label("Sender:");
	private static Label recipientLbl = new Label("Recipient:");
	private static Label messageLbl = new Label("Message Body:");

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
		mainLayout = new BorderPane();
		patientMsg = new VBox();
		nurseMsg = new VBox();
		doctorMsg = new VBox();
		mainBtnRow = new HBox();
		patientList = new ListView();
		nurseList = new ListView();
		doctorList = new ListView();
		
		newMsgBtn = new Button("Create New Message");
		saveBtn = new Button("Save Message");
		delBtn = new Button("Delete Message");
		readBtn = new Button("Mark As Read");
		backBtn = new Button("Back");
			
		//Define method calls for each button click
		newMsgBtn.setOnAction((event)->{createMessage();});
		saveBtn.setOnAction((event)->{saveMessage();});
		delBtn.setOnAction((event)->{delMessage();});
		readBtn.setOnAction((event)->{readMessage();});
		backBtn.setOnAction((event)->{backToMain();});

		//Create V and H Boxes to organize on-screen elements
		patientMsg.getChildren().addAll(patientLbl,patientList);
		nurseMsg.getChildren().addAll(nurseLbl,nurseList);
		doctorMsg.getChildren().addAll(doctorLbl,doctorList);
		mainBtnRow.getChildren().addAll(newMsgBtn,delBtn,readBtn,backBtn);
			
		//Place boxes within each border section
		mainLayout.setTop(mainTitle);
		mainLayout.setLeft(patientMsg);
		mainLayout.setCenter(nurseMsg);
		mainLayout.setRight(doctorMsg);
		mainLayout.setBottom(mainBtnRow);

		//Define the main message scene
		mainMsgScene = new Scene(mainLayout,800,500);
			
		newLayout = new BorderPane();
		newMsgBox = new VBox();
		newBtnRow = new HBox();
		recipient = new TextField();
		sender = new TextField();
		messageEntry = new TextArea();
		sendBtn = new Button("Send");
		cancelBtn = new Button("Cancel");
		
		sendBtn.setOnAction((event)->{sendMessage();});
		cancelBtn.setOnAction((event)->{cancel();});
		
		
		newBtnRow.getChildren().addAll(sendBtn,cancelBtn);
		newMsgBox.getChildren().addAll(recipientLbl,recipient,senderLbl,sender,messageLbl,messageEntry,newBtnRow);
		
		newLayout.setCenter(newMsgBox);
		newMsgScene = new Scene(newLayout,400,300);
		
		newMsgStage = new Stage();
		newMsgStage.setTitle(newTitle);
		newMsgStage.setScene(newMsgScene);
	}
	
	private void backToMain() {
		
	}
	
	private void cancel() {
		this.newMsgStage.hide();
		this.clear();
	}
	
	private void clear() {
		this.recipient.clear();
		this.messageEntry.clear();	
	}
	
	private void createMessage() {
		newMsgStage.show();
	}
	
	private void delMessage(){
		//Call delete method on curMessage
	}
	
	private void readMessage(){
		//Call markAsRead function on curMessage
	}

	private void saveMessage(){
		//Call save method on curMessage

	}
	
	private void sendMessage(){
		//Create message object from selected recipients and input from text field
		//Use 3rd Party API to send a secure, encrypted message over the internet
		Message msg = new Message(sender.getText(),recipient.getText(),messageEntry.getText());
		
		this.clear();
		this.newMsgStage.hide();		
	}

	public void show(Stage stage){
		/**************************************************
		-Set the scene of the given stage to mainMsgScene
		-Show the given Stage
		**************************************************/
		stage.setScene(mainMsgScene);
		stage.show();
	}
}
