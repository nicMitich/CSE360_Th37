package patientView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PatientLoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Login");

        // Creating labels
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        // Creating text fields
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Creating login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Here you can implement your authentication logic
            // For simplicity, let's just print the entered username and password
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        });

        // Creating layout and adding components
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        GridPane.setConstraints(usernameLabel, 0, 0);
        GridPane.setConstraints(usernameField, 1, 0);
        GridPane.setConstraints(passwordLabel, 0, 1);
        GridPane.setConstraints(passwordField, 1, 1);
        GridPane.setConstraints(loginButton, 1, 2);

        grid.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
