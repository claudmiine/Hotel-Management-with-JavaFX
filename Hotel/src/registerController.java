import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;


public class registerController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private ImageView logoHotel;
    @FXML
    private Label labelMessage;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private Label registerMessageLabel;
    private Database database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File logoFile = new File("Hotel/images/logo.jpg");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoHotel.setImage(logoImage);
//        registerMessagelabel.setText("");

    }
    public void confirmButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()) {
//            validateLogin();
        } else {
            labelMessage.setText("Please enter username and password.");
        }
    }
    public void backButtonOnAction(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerUser(){
        database = new Database();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String address = addressTextField.getText();
        String telephone = telephoneTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmpassword = confirmPasswordTextField.getText();
        String email = emailTextField.getText();

        String insertFields = "INSERT INTO user (firstname, lastname, address, telephone, username, password, confirmpassword, email) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + address + "','" + telephone + "','" + username + "','" + password + "','" + confirmpassword + "','" + email + "')";
        String insertToRegister = insertFields + insertValues;

        try{
//            database.statement = database.conn.createStatement();
//            database.resultSet = database.statement.executeUpdate(insertToRegister);
//            registerMessagelabel.setText("User has been registered successfully");
            database.statement = database.conn.createStatement();
            database.statement.executeUpdate(insertToRegister);
            registerMessageLabel.setText("User has been registered successfully! Now please go back to log in.");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
