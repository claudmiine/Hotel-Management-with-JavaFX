import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import java.io.File;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javax.swing.*;

public class makeBookingController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button bookButton;
    @FXML
    private Label priceLabel;
    @FXML
    private JComboBox roomTypeCombo;
    @FXML
    private JComboBox checkinDatePicker;
    @FXML
    private JComboBox numberOfNightsCombo;
    @FXML
    private JComboBox breakfastCombo;
    @FXML
    private JComboBox paymentMethodCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceLabel.setText("");
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
}
