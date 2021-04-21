import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;

public class loginController implements Initializable {

    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView lobbyImageView;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button backButton;
    @FXML
    private Button cancelButton;

    // This is telling the loginController class file to use the database file when you do database.conn.#####
    private Database database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File lobbyFile = new File("Hotel/images/lobbyHotel.jpg");
        Image lobbyImage = new Image(lobbyFile.toURI().toString());
        lobbyImageView.setImage(lobbyImage);
        loginMessageLabel.setText("");

    }

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password.");
        }
    }


    public void validateLogin() {
        //telling the loginController to "run" the database class file
        database = new Database();

        try {
            // putting all of your database statements within the try and also simplifying it so you can get everything
            // you need from that specific query, such as the customer ID
            database.statement = database.conn.createStatement();
            database.resultSet = database.statement.executeQuery("SELECT * FROM user");

            while (database.resultSet.next()) {
                String username = database.resultSet.getString(2);
                String password = database.resultSet.getString(3);

                if ((username.equals(usernameField.getText())) &
                        (password.equals(passwordField.getText()))) {
                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Stage loginStage = new Stage();
                        loginStage.initStyle(StageStyle.UNDECORATED);
                        loginStage.setScene(new Scene(root, 600, 400));
                        loginStage.show();
                        Stage stage = (Stage) backButton.getScene().getWindow();
                        stage.close();
                    } catch(Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                    //make sure you use return; at the end of if statements otherwise it will show both the if and the else
                    return;

                } else {
                    loginMessageLabel.setText("Invalid login. Try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void backButtonOnAction(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();
            Stage stage = (Stage) backButton.getScene().getWindow();
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
}

