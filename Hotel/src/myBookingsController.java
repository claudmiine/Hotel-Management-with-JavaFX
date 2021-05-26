import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView ;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class myBookingsController implements Initializable {
    private int user_id;

    @FXML
    private ImageView logoImageView;
    @FXML
    private Label customerBookingLabel;
    private String firstname;
    @FXML
    private Button backButton;
    @FXML
    private TableView tableBooking;
    @FXML
    private TableColumn<Booking,Integer> bookingIdColumn;
    @FXML
    private TableColumn<Booking, LocalDate> checkInColumn;
    @FXML
    private TableColumn<Booking, LocalDate> checkOutColumn;
    @FXML
    private TableColumn<Booking, LocalDate> nightsColumn;
    @FXML
    private TableColumn<Booking, LocalDate> roomTypeColumn;
    @FXML
    private TableColumn<Booking, String> breakfastColumn;
    @FXML
    private TableColumn<Booking, String> totalCostColumn;
    @FXML
    private TableColumn<Booking,String> paymentColumn;





    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("Hotel/images/logo.jpg");
        javafx.scene.image.Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
        getCustomerBooking();

        user_id=loginController.user_id;
        firstname=loginController.firstname;
        customerBookingLabel.setText("Your previous bookings, " + firstname );

        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("check_in_date"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("check_out_date"));
        nightsColumn.setCellValueFactory(new PropertyValueFactory<>("nights"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("total_cost"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        breakfastColumn.setCellValueFactory(new PropertyValueFactory<>("breakfast"));

    }

    public void getCustomerBooking(){
        user_id = loginController.user_id;

        ObservableList<Booking> data = FXCollections.observableArrayList();
        try{

            Database database = new Database();

            String sql = "SELECT * FROM booking WHERE user_id = " + user_id;

            database.statement = database.conn.createStatement();
            database.resultSet = database.statement.executeQuery(sql);

            while (database.resultSet.next()) {
                data.add(new Booking(
                        database.resultSet.getInt("booking_id"),
                        database.resultSet.getInt("user_id"),
                        database.resultSet.getDate("checkinDate").toLocalDate(),
                        database.resultSet.getDate("checkoutDate").toLocalDate(),
                        database.resultSet.getInt("nights"),
                        database.resultSet.getString("room_type"),
                        database.resultSet.getString("total_cost"),
                        database.resultSet.getString("payment"),
                        database.resultSet.getString("breakfast")
                ));
//

            }
            tableBooking.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

//    Deleting booking from database
    public void deleteBooking() {
//Select the specified booking with booking_id
        Booking selectedBooking = (Booking) tableBooking.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            int booking_id = selectedBooking.getBooking_id();
//            Display the banner to ensure the user
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure to delete booking with id " + booking_id + "?" , ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
//                Update the db with deleted booking
                try {
                    Database database = new Database();
                    database.statement = database.conn.createStatement();
                    database.statement.executeUpdate("DELETE FROM booking WHERE booking_id = " + booking_id);
                } catch (Exception e) {
                    e.getStackTrace();
                    e.getCause();
                }
                getCustomerBooking();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No booking selected.");
            alert.show();
        }
    }

//Function to go back to the previous fxml file of the program
    public void backButtonOnAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    }




