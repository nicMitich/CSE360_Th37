

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MessagingSystem{
	
	private TempeLoginApp mainView;
    private Stage stage;
    private ObservableList<String> messageList = FXCollections.observableArrayList();
    
    public MessagingSystem(TempeLoginApp mainView) {
        this.mainView = mainView;
        this.stage = new Stage();
        stage.setTitle("Messaging View");
    }

    public void show() { {
        stage.setTitle("Messaging System");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label recipientLabel = new Label("Recipient:");
        grid.add(recipientLabel, 0, 1);

        ComboBox<String> recipientComboBox = new ComboBox<>();
        recipientComboBox.getItems().addAll("Nurse", "Doctor", "Patient");
        grid.add(recipientComboBox, 1, 1);

        Label messageLabel = new Label("Message:");
        grid.add(messageLabel, 0, 2);

        TextArea messageTextArea = new TextArea();
        messageTextArea.setPrefHeight(100);
        grid.add(messageTextArea, 1, 2);

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            String recipient = recipientComboBox.getValue();
            String message = messageTextArea.getText();
            String fullMessage = "To " + recipient + ": " + message;
            messageList.add(fullMessage);
            saveMessage(fullMessage);
            messageTextArea.clear();
        });
        grid.add(sendButton, 1, 3);

        ListView<String> messageListView = new ListView<>(messageList);
        grid.add(messageListView, 1, 4);

        Scene scene = new Scene(grid, 400, 400);
        stage.setScene(scene);
        stage.show();

        loadMessages();
    }
    }

    private void saveMessage(String message) {
        try (FileWriter fw = new FileWriter("messages.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMessages() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("messages.txt"));
            messageList.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
