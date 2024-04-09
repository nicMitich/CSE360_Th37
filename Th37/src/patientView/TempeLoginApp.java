package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class TempeLoginApp extends Application {

    private Stage primaryStage;
    private GridPane loginForm;
    private GridPane patientForm;
    private GridPane signUpForm;
    private GridPane doctorForm;
    private GridPane nurseForm;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createLoginForm();
    }

    private void createLoginForm() {
        loginForm = new GridPane();
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setHgap(10);
        loginForm.setVgap(10);
        loginForm.setPadding(new Insets(25, 25, 25, 25));

        Label titleLabel = new Label("Tempe Medical Hospital");
        loginForm.add(titleLabel, 0, 0, 2, 1);

        Label roleLabel = new Label("Select Role:");
        loginForm.add(roleLabel, 0, 1);

        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Patient", "Nurse", "Doctor");
        loginForm.add(roleComboBox, 1, 1);

        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> handleLoginSelection(roleComboBox.getValue()));
        loginForm.add(loginButton, 0, 2, 2, 1);

        Scene scene = new Scene(loginForm, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tempe Medical Hospital");
        primaryStage.show();
    }

    private void handleLoginSelection(String role) {
        if (role.equals("Patient")) {
            createPatientForm();
        } else if (role.equals("Nurse")) {
            createNurseForm();
        } else if (role.equals("Doctor")) {
            createDoctorForm();
        }
    }

    private void createPatientForm() {
        patientForm = new GridPane();
        patientForm.setAlignment(Pos.CENTER);
        patientForm.setHgap(10);
        patientForm.setVgap(10);
        patientForm.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("Patient ID:");
        patientForm.add(usernameLabel, 0, 0);

        TextField usernameField = new TextField();
        patientForm.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        patientForm.add(passwordLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        patientForm.add(passwordField, 1, 1);

    
        
        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> handlePatientLogin(usernameField.getText(), passwordField.getText()));
        patientForm.add(loginButton, 0, 2, 2, 1);

        Button signUpButton = new Button("Sign up here");
        signUpButton.setOnAction(e -> createSignUpForm());
        VBox signUpBox = new VBox(signUpButton);
        signUpBox.setAlignment(Pos.CENTER);
        signUpBox.setPadding(new Insets(10, 0, 0, 0));
        patientForm.add(signUpBox, 0, 3, 2, 1);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> createLoginForm());
        HBox buttonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        patientForm.add(buttonBox, 0, 4, 2, 1);

        Scene scene = new Scene(patientForm, 400, 400);
        primaryStage.setScene(scene);
    }
    private String loggedInUsername;
    
    private void handlePatientLogin(String username, String password) {
        if (isValidPatientCredentials(username, password)) {
            // Login successful, navigate to the appropriate page
            // Replace this with the actual navigation logic
            System.out.println("Login successful!");
            loggedInUsername = username;
            createPatientHomepage();
        } else {
            // Display an error message
            Label errorLabel = new Label("UserID/ Password not found");
            patientForm.add(errorLabel, 0, 5, 2, 1);
        }
    }
    private void createPatientHomepage() {
        GridPane patientHomepage = new GridPane();
        patientHomepage.setAlignment(Pos.CENTER);
        patientHomepage.setHgap(10);
        patientHomepage.setVgap(10);
        patientHomepage.setPadding(new Insets(25, 25, 25, 25));

        Label titleLabel = new Label("Patient Homepage");
        patientHomepage.add(titleLabel, 0, 0, 2, 1);

        Button previousVisitsButton = new Button("Previous Visits");
        patientHomepage.add(previousVisitsButton, 0, 3, 2, 1);

        Button settingsButton = new Button("Settings/Profile");
        settingsButton.setOnAction(e -> openSettings());
        patientHomepage.add(settingsButton, 0, 4, 2, 1);
        
        
        
        Button messagesButton = new Button("Messages");
        messagesButton.setOnAction(e -> MessageView());
        patientHomepage.add(messagesButton, 0, 6, 2, 1);

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> returnToLoginForm());
        patientHomepage.add(logoutButton, 0,9,3, 1);
        
        Scene scene = new Scene(patientHomepage, 400, 400);
        primaryStage.setScene(scene);
    }

    private void openSettings() {
        GridPane settingsPage = new GridPane();
        settingsPage.setAlignment(Pos.CENTER);
        settingsPage.setHgap(10);
        settingsPage.setVgap(10);
        settingsPage.setPadding(new Insets(25, 25, 25, 25));

        Label settingsLabel = new Label("Settings");
        settingsPage.add(settingsLabel, 0, 0, 2, 1);

        Button editProfileButton = new Button("Edit Profile");
        editProfileButton.setOnAction(e -> editProfile(loggedInUsername));
        settingsPage.add(editProfileButton, 0, 1);

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> returnToLoginForm());
        settingsPage.add(logoutButton, 1, 1);

        Button backButton = new Button("Back to Homepage");
        backButton.setOnAction(e -> returnToHomepage());
        settingsPage.add(backButton, 0, 2, 2, 1);

        Scene settingsScene = new Scene(settingsPage, 400, 400);
        primaryStage.setScene(settingsScene);
    }
    
    

    private void editProfile(String username) {
        // Retrieve user's existing information

    	try (BufferedReader reader = new BufferedReader(new FileReader("patient_data.txt"))) {
            String line;
            String[] userInfo = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[4].equals(username)) {
                    userInfo = parts;
                    break;
                }
            }

            if (userInfo != null) {
                GridPane editProfileForm = new GridPane();
                editProfileForm.setAlignment(Pos.CENTER);
                editProfileForm.setHgap(10);
                editProfileForm.setVgap(10);
                editProfileForm.setPadding(new Insets(25, 25, 25, 25));

                Label firstNameLabel = new Label("First Name:");
                editProfileForm.add(firstNameLabel, 0, 0);

                TextField firstNameField = new TextField(userInfo[0]); // Populate with existing first name
                editProfileForm.add(firstNameField, 1, 0);

                Label lastNameLabel = new Label("Last Name:");
                editProfileForm.add(lastNameLabel, 0, 1);

                TextField lastNameField = new TextField(userInfo[1]); // Populate with existing last name
                editProfileForm.add(lastNameField, 1, 1);

                Label dobLabel = new Label("Date of Birth:");
                editProfileForm.add(dobLabel, 0, 2);

                DatePicker dobPicker = new DatePicker(LocalDate.parse(userInfo[2])); // Populate with existing date of birth
                editProfileForm.add(dobPicker, 1, 2);

                Label mobileNumberLabel = new Label("Mobile Number:");
                editProfileForm.add(mobileNumberLabel, 0, 3);

                TextField mobileNumberField = new TextField(userInfo[3]); // Populate with existing mobile number
                editProfileForm.add(mobileNumberField, 1, 3);

                Label usernameLabel = new Label("Username:");
                editProfileForm.add(usernameLabel, 0, 4);

                TextField usernameField = new TextField(userInfo[4]); // Populate with existing username
                usernameField.setEditable(false); // Username cannot be edited
                editProfileForm.add(usernameField, 1, 4);

                Label passwordLabel = new Label("Password:");
                editProfileForm.add(passwordLabel, 0, 5);

                PasswordField passwordField = new PasswordField();
                passwordField.setText(userInfo[5]); // Populate with existing password
                editProfileForm.add(passwordField, 1, 5);

                Button saveButton = new Button("Save");
                saveButton.setOnAction(e -> saveProfileChanges(firstNameField.getText(), lastNameField.getText(), dobPicker.getValue(), mobileNumberField.getText(), passwordField.getText(), username));
                editProfileForm.add(saveButton, 0, 6, 2, 1);

                Button cancelButton = new Button("Cancel");
                cancelButton.setOnAction(e -> returnToHomepage());
                HBox buttonBox = new HBox(cancelButton);
                buttonBox.setAlignment(Pos.CENTER);
                buttonBox.setPadding(new Insets(10, 0, 0, 0));
                editProfileForm.add(buttonBox, 0, 7, 2, 1);

                Scene editProfileScene = new Scene(editProfileForm, 400, 400);
                primaryStage.setScene(editProfileScene);
            } else {
                // Display an error message if the user's information is not found
                Label errorLabel = new Label("User information not found.");
                patientForm.add(errorLabel, 0, 6, 3, 2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  

    private void saveProfileChanges(String firstName, String lastName, LocalDate dob, String mobileNumber, String password, String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("patient_data.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp_patient_data.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[4].equals(username)) {
                    // Write the new user information
                    writer.write(firstName + "," + lastName + "," + dob + "," + mobileNumber + "," + username + "," + password);
                    found = true;
                } else {
                    // Write the other user's information
                    writer.write(line);
                }
                writer.newLine();
            }

            if (!found) {
                // If the user's information was not found, write the new user information
                writer.write(firstName + "," + lastName + "," + dob + "," + mobileNumber + "," + username + "," + password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Rename the temp file to replace the original file
        File file = new File("patient_data.txt");
        File tempFile = new File("temp_patient_data.txt");
        tempFile.renameTo(file);

        // Navigate back to the patient homepage
        createPatientHomepage();
    }
    private boolean isValidPatientCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("patient_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[4].equals(username) && parts[5].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Display an error message, as the patient data file was not found
            Label errorLabel = new Label("Patient data file not found");
            patientForm.add(errorLabel, 0, 6, 3, 2);
        }
        return false;
    }
    
    private void returnToHomepage() {
        createPatientHomepage();
    }


    private void returnToLoginForm() {
        createLoginForm();
    }
  

    private void createSignUpForm() {
        signUpForm = new GridPane();
        signUpForm.setAlignment(Pos.CENTER);
        signUpForm.setHgap(10);
        signUpForm.setVgap(10);
        signUpForm.setPadding(new Insets(25, 25, 25, 25));

        Label firstNameLabel = new Label("First Name:");
        signUpForm.add(firstNameLabel, 0, 0);

        TextField firstNameField = new TextField();
        signUpForm.add(firstNameField, 1, 0);

        Label lastNameLabel = new Label("Last Name:");
        signUpForm.add(lastNameLabel, 0, 1);

        TextField lastNameField = new TextField();
        signUpForm.add(lastNameField, 1, 1);

        Label dobLabel = new Label("Date of Birth:");
        signUpForm.add(dobLabel, 0, 2);

        DatePicker dobPicker = new DatePicker();
        signUpForm.add(dobPicker, 1, 2);

        Label mobileNumberLabel = new Label("Mobile Number:");
        signUpForm.add(mobileNumberLabel, 0, 3);

        TextField mobileNumberField = new TextField();
        signUpForm.add(mobileNumberField, 1, 3);

        Label usernameLabel = new Label("Username:");
        signUpForm.add(usernameLabel, 0, 4);

        TextField usernameField = new TextField();
        signUpForm.add(usernameField, 1, 4);

        Label passwordLabel = new Label("Password:");
        signUpForm.add(passwordLabel, 0, 5);

        PasswordField passwordField = new PasswordField();
        signUpForm.add(passwordField, 1, 5);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> handleSignUp(firstNameField.getText(), lastNameField.getText(), dobPicker.getValue(), mobileNumberField.getText(), usernameField.getText(), passwordField.getText()));
        signUpForm.add(signUpButton, 0, 6, 2, 1);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> createPatientForm());
        HBox buttonBox = new HBox(cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        signUpForm.add(buttonBox, 0, 7, 2, 1);

        Scene scene = new Scene(signUpForm, 400, 400);
        primaryStage.setScene(scene);
    }

    
    private void handleSignUp(String firstName, String lastName, LocalDate dob, String mobileNumber, String username, String password) {
        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || dob == null || mobileNumber.isEmpty() || username.isEmpty() || password.isEmpty()) {
            // Display an error message
            Label errorLabel = new Label("Please fill in all the fields.");
            signUpForm.add(errorLabel, 0, 8, 4, 1);
            return;
        }

        // Check if mobile number is valid
        if (!isValidMobileNumber(mobileNumber)) {
            // Display an error message
            Label errorLabel = new Label("Please enter a valid mobile number.");
            signUpForm.add(errorLabel, 0, 9, 4, 1);
            return;
        }
     // Check if the username or password already exists
        if (isCredentialTaken(username, password)) {
            // Display an error message
            Label errorLabel = new Label("Username or password already taken. Please choose another one.");
            signUpForm.add(errorLabel, 0, 7, 3, 1);
            return;
        }

        // Save the user's information to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patient_data.txt", true))) {
            writer.write(firstName + "," + lastName + "," + dob.toString() + "," + mobileNumber + "," + username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Navigate to the patient form or display a success message
        createPatientForm();
    }

    private boolean isValidMobileNumber(String mobileNumber) {
        // Basic validation: check if mobile number contains only digits and has correct length
        return mobileNumber.matches("\\d{10}"); // Assuming mobile number should be 10 digits long
    }
    
    private boolean isCredentialTaken(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("patient_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && (parts[2].equals(username) || parts[3].equals(password))) {
                    return true; // Username or password found in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Username or password not found in the file
    }

    

    
    
    private void MessageView() {
        // Implement opening the messaging interface here
    }

    private void createDoctorForm() {
        // Implement doctor form creation here
    }

    private void createNurseForm() {
        // Implement nurse form creation here
    }

    
}
