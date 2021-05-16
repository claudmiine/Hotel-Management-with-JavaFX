import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import java.io.File;
import java.net.URL;
import java.sql.Statement;
import java.time.LocalDate;
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
    private ComboBox<String> roomTypeCombo;
    @FXML
    private DatePicker checkinDatePicker;
    @FXML
    private DatePicker checkoutDatePicker;
    @FXML
    private ComboBox<Integer> numberOfNightsCombo;
    @FXML
    private ComboBox<String> breakfastCombo;
    @FXML
    private ComboBox<String> paymentMethodCombo;
    private Integer user_id;
    private Database database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceLabel.setText("");
        this.user_id = loginController.user_id;
        roomTypeCombo.getItems().removeAll(roomTypeCombo.getItems());
        checkoutDatePicker.setDisable(true);
        roomTypeCombo.getItems().addAll("Single", "Double", "Twin");
        roomTypeCombo.getSelectionModel().select("Select Room Size");

        numberOfNightsCombo.getItems().removeAll(numberOfNightsCombo.getItems());
        numberOfNightsCombo.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        numberOfNightsCombo.getSelectionModel().select(0);

        breakfastCombo.getItems().removeAll(breakfastCombo.getItems());
        breakfastCombo.getItems().addAll("Yes", "No");
        breakfastCombo.getSelectionModel().select("Food service?");

        try {
            database = new Database();
            database.statement = database.conn.createStatement();

            database.resultSet = database.statement.executeQuery("SELECT * FROM user WHERE user_id =" + user_id);

            while (database.resultSet.next()) {
//                String payment = database.resultSet.getString(10);

                paymentMethodCombo.getItems().removeAll(paymentMethodCombo.getItems());
                paymentMethodCombo.getItems().addAll( "Pay On Arrival", "Visa", "Mastercard");
                paymentMethodCombo.getSelectionModel().select("Select Payment Method");

            }
        } catch (Exception e) {
            e.getStackTrace();
            e.getCause();
        }
    }
    public void NightsAction() {
        Integer nights = numberOfNightsCombo.getValue();
        LocalDate date = checkinDatePicker.getValue().plusDays(nights);
        checkoutDatePicker.setValue(date);
    }

    public void CalcPrice() {
        Integer nights = numberOfNightsCombo.getValue();
        String roomSize = roomTypeCombo.getValue();
        String breakfast = breakfastCombo.getValue();

//        if (roomSize.equals("Single") && breakfast.equals("Yes")) {
//            int calculation = (nights * (20 + 5));
//            priceLabel.setText("£" + calculation);
//        } else {
//            int calculation = (nights * 20);
//            priceLabel.setText("£" + calculation);
//        }
//
//        if (roomSize.equals("Double") && breakfast.equals("Yes")) {
//            int calculation = (nights * (45 + 5));
//            priceLabel.setText("£" + calculation);
//        } else {
//            int calculation = (nights * 45);
//            priceLabel.setText("£" + calculation);
//        }
//
//        if (roomSize.equals("Twin") && breakfast.equals("Yes")) {
//            int calculation = (nights * (35 + 5));
//            priceLabel.setText("£" + calculation);
//        } else {
//            int calculation = (nights * 35);
//            priceLabel.setText("£" + calculation);
//        }

        if (roomSize.equals("Single") && breakfast.equals("Yes")) {
            int calculation = (nights * (20 + 5));
            priceLabel.setText("£" + calculation);
        } else if((roomSize.equals("Single") && breakfast.equals("No")) ) {
            int calculation = (nights * 20);
            priceLabel.setText("£" + calculation);
        } else if (roomSize.equals("Double") && breakfast.equals("Yes")) {
            int calculation = (nights * (45 + 5));
            priceLabel.setText("£" + calculation);
        } else if (roomSize.equals("Double") && breakfast.equals("No")){
            int calculation = (nights * 45);
            priceLabel.setText("£" + calculation);
        } else if (roomSize.equals("Twin") && breakfast.equals("Yes")) {
            int calculation = (nights * (35 + 5));
            priceLabel.setText("£" + calculation);
        } else if (roomSize.equals("Twin") && breakfast.equals("No")){
            int calculation = (nights * 35);
            priceLabel.setText("£" + calculation);
        }





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
