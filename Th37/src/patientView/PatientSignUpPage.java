package patientView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PatientSignUpPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Sign Up");

        // Creating labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label birthdayLabel = new Label("Birthday:");

        // Creating text fields
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();

        // Creating date picker for birthday
        DatePicker birthdayPicker = new DatePicker();

        // Creating sign up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String birthday = birthdayPicker.getValue().toString();
            // Here you can implement the sign-up logic
            // For simplicity, let's just print the entered data
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Birthday: " + birthday);
        });

        // Creating layout and adding components
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        GridPane.setConstraints(firstNameLabel, 0, 0);
        GridPane.setConstraints(firstNameField, 1, 0);
        GridPane.setConstraints(lastNameLabel, 0, 1);
        GridPane.setConstraints(lastNameField, 1, 1);
        GridPane.setConstraints(birthdayLabel, 0, 2);
        GridPane.setConstraints(birthdayPicker, 1, 2);
        GridPane.setConstraints(signUpButton, 1, 3);

        grid.getChildren().addAll(firstNameLabel, firstNameField, lastNameLabel, lastNameField,
                birthdayLabel, birthdayPicker, signUpButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
