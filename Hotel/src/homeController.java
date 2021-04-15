import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

public class homeController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private ImageView TwinRoom;
    @FXML
    private ImageView SingleRoom;
    @FXML
    private ImageView DoubleRoom;
    @FXML
    private ImageView logoHotel;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File TwinRoomFile = new File("Hotel/images/twinroom.jpg");
        Image TwinRoomImage = new Image(TwinRoomFile.toURI().toString());
        TwinRoom.setImage(TwinRoomImage);

        File SingleRoomFile = new File("Hotel/images/singleroom.jpg");
        Image SingleRoomImage = new Image(SingleRoomFile.toURI().toString());
        SingleRoom.setImage(SingleRoomImage);

        File DoubleRoomFile = new File("Hotel/images/doubleroom.jpg");
        Image DoubleRoomImage = new Image(DoubleRoomFile.toURI().toString());
        DoubleRoom.setImage(DoubleRoomImage);

        File logoHotelFile = new File("Hotel/images/logo.jpg");
        Image logoHotelImage = new Image(logoHotelFile.toURI().toString());
        logoHotel.setImage(logoHotelImage);

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event) {
        goToLoginForm();
    }


    public void goToLoginForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}