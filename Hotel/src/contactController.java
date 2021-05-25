import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class contactController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView lobbyBackground;
    @FXML
    private ImageView callImage;
    @FXML
    private ImageView emailImage;
    @FXML
    private ImageView locationImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File logoFile = new File("Hotel/images/lobbyHotel.jpg");
        Image lobbyImage = new Image(logoFile.toURI().toString());
        lobbyBackground.setImage(lobbyImage);

        File callFile = new File("Hotel/images/call.png");
        Image callImageView = new Image(callFile.toURI().toString());
        callImage.setImage(callImageView);

        File locationFile = new File("Hotel/images/placeholder.png");
        Image locationImageView = new Image(locationFile.toURI().toString());
        locationImage.setImage(locationImageView);

        File emailFile = new File("Hotel/images/contact.png");
        Image emailImageView = new Image(emailFile.toURI().toString());
        emailImage.setImage(emailImageView);

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
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

}
