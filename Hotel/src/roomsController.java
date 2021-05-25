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
import java.util.ResourceBundle;
import java.net.URL;

public class roomsController implements Initializable {
    @FXML
    private ImageView twinRoom;
    @FXML
    private ImageView singleRoom;
    @FXML
    private ImageView doubleRoom;
    @FXML
    private Button backButton;
    @FXML
    private ImageView logoHotel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File TwinRoomFile = new File("Hotel/images/twinroom.jpg");
        Image TwinRoomImage = new Image(TwinRoomFile.toURI().toString());
        twinRoom.setImage(TwinRoomImage);

        File SingleRoomFile = new File("Hotel/images/singleroom.jpg");
        Image SingleRoomImage = new Image(SingleRoomFile.toURI().toString());
        singleRoom.setImage(SingleRoomImage);

        File DoubleRoomFile = new File("Hotel/images/doubleroom.jpg");
        Image DoubleRoomImage = new Image(DoubleRoomFile.toURI().toString());
        doubleRoom.setImage(DoubleRoomImage);

        File logoHotelFile = new File("Hotel/images/logo.jpg");
        Image logoImage = new Image(logoHotelFile.toURI().toString());
        logoHotel.setImage(logoImage);

    }


    public void backButtonOnAction(ActionEvent event){
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

        }

    }

