import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.ResourceBundle;

import java.net.URL;

public class dashboardController implements Initializable {
    @FXML
    private ImageView logoHotel;
    @FXML
    private ImageView lobbyBackground;
    @FXML
    private Button viewRoomsButton;
    @FXML
    private Button bookRoomButton;
    @FXML
    private Button cancelEditButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label welcomeLabel;

    private Integer user_id;
    private String firstname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("Hotel/images/logo.jpg");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoHotel.setImage(logoImage);

        File lobbyHotelFile = new File("Hotel/images/lobbyHotel.jpg");
        Image lobbyHotelImage = new Image(lobbyHotelFile.toURI().toString());
        lobbyBackground.setImage(lobbyHotelImage);

        user_id= loginController.user_id;
        firstname= loginController.firstname;
        welcomeLabel.setText("Welcome " + firstname + "!");

    }

    public void logoutButtonOnAction(ActionEvent event){
        logout();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void logout(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void ViewRoomsOnAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("rooms.fxml"));
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

    public void makeBookingOnAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("makeBooking.fxml"));
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
}
