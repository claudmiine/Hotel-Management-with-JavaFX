import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;

public class loginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    // This is telling the loginController class file to use the database file when you do database.conn.#####
    private Database database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/hotel.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File logoFile = new File("images/logohotel.jpg");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(brandingImage);

    }

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password.");
        }
    }


    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
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
                    loginMessageLabel.setText("Congrats!");
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
}

