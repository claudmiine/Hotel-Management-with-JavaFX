// Importing essential packages
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
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

//FXML Loader call all the methods and classes defined in the controller
public class makeBookingController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label priceLabel;
    @FXML
    private Label confirmationLabel;
    @FXML
    private Label errorLabel;
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
//    importing classes from loginController
    private Integer user_id;
    private Database database;

    //FXML Loader call all the methods and classes defined in the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Set the Labels as empty
        priceLabel.setText("");
        confirmationLabel.setText("");
        errorLabel.setText("");
//        taking user_id from loginController
        this.user_id = loginController.user_id;
//        clear roomTypeCombo and set selection model to display
        roomTypeCombo.getItems().removeAll(roomTypeCombo.getItems());
        checkoutDatePicker.setDisable(true);
        roomTypeCombo.getItems().addAll("Single", "Double", "Twin");
        roomTypeCombo.getSelectionModel().select("");
        //clear numberOfNightdCombo and set selection model to display
        numberOfNightsCombo.getItems().removeAll(numberOfNightsCombo.getItems());
        numberOfNightsCombo.getItems().addAll( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

//        clear breakfastComgo and set selection model to display

        breakfastCombo.getItems().removeAll(breakfastCombo.getItems());
        breakfastCombo.getItems().addAll("Yes", "No");
        breakfastCombo.getSelectionModel().select("");

        try {
//          configure the database, selecting everything from specified user column
            database = new Database();
            database.statement = database.conn.createStatement();
            database.resultSet = database.statement.executeQuery("SELECT * FROM user WHERE user_id =" + user_id);

            while (database.resultSet.next()) {

                paymentMethodCombo.getItems().removeAll(paymentMethodCombo.getItems());
                paymentMethodCombo.getItems().addAll( "Pay On Arrival", "Visa", "Mastercard");
                paymentMethodCombo.getSelectionModel().select("");

            }
        } catch (Exception e) {
            e.getStackTrace();
            e.getCause();
        }
    }
    //      Calculating number of nights
    public void NightsAction() {
        Integer nights = numberOfNightsCombo.getValue();
        LocalDate date = checkinDatePicker.getValue().plusDays(nights);
        checkoutDatePicker.setValue(date);
    }

//    Calculating the predictable total cost
    public void CalcPrice() {
        Integer nights = numberOfNightsCombo.getValue();
        String roomSize = roomTypeCombo.getValue();
        String breakfast = breakfastCombo.getValue();

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

//    populate database with new bookings
    public void makeBooking(ActionEvent event) {
        database = new Database();

        Integer user_id = loginController.user_id;
//        taking values from combos from GUI fields
        String room_type = roomTypeCombo.getValue();
        Integer nights = numberOfNightsCombo.getValue();
        LocalDate checkinDate = checkinDatePicker.getValue();
        LocalDate checkoutDate = checkoutDatePicker.getValue();
        String breakfast = breakfastCombo.getValue();
        String payment = paymentMethodCombo.getValue();
        String total_cost = priceLabel.getText();

//Inserting data to database
        String insertFields = "INSERT INTO booking (user_id, room_type, nights, checkinDate, checkoutDate, breakfast, total_cost, payment) VALUES ('";
        String insertValues = user_id + "','" + room_type + "','" + nights + "','" + checkinDate + "','" + checkoutDate + "','" + breakfast + "','" + total_cost + "','" + payment + "')";
        String insertToBooking = insertFields + insertValues;

        if (!room_type.isEmpty()) {
            try{
                database.statement = database.conn.createStatement();
                database.statement.executeUpdate(insertToBooking);
                confirmationLabel.setText("Successfully made a booking!");
                errorLabel.setText("");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            return;
        } else {
//            Displaying the banner if the field is empty
            errorLabel.setText("Please fill all the fields.");
        }
    }

    //Creating a function for user to stop the process of the program with cancelButton via GUI
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

//Function to go back to the previous fxml file of the program
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
