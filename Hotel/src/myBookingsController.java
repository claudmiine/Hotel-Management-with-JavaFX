import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView ;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class myBookingsController implements Initializable {
    private int user_id;

    @FXML
    private ListView<String> listViewBookings;
    @FXML
    private ImageView logoImageView;
    @FXML
    private Label customerBookingLabel;
    private String firstname;
    @FXML
    private Button backButton;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("Hotel/images/logo.jpg");
        javafx.scene.image.Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
        getCustomerBooking();

        user_id=loginController.user_id;
        firstname=loginController.firstname;
        customerBookingLabel.setText("Your previous bookings, " + firstname );
    }

    public void getCustomerBooking(){
        user_id = loginController.user_id;

        listViewBookings.getItems().clear();

        try{

            Database database = new Database();

            String sql = "SELECT * FROM booking WHERE user_id = " + user_id;

            database.statement = database.conn.createStatement();
            database.resultSet = database.statement.executeQuery(sql);

            while (database.resultSet.next()) {
                String booking_id = "Booking ID - " + database.resultSet.getString(1) + "   ";
//                    String bookingDate = "Booking Date: " + database.resultSet.getString(7) + ", ";
                String checkinDate = "Check-in Date: " + database.resultSet.getString(3) + "  ";
                String checkoutdate = "Check-out Date: " + database.resultSet.getString(7) + "  ";
                String room_type = "Room: " + database.resultSet.getString(2) + "  ";
//                    String roomSize = "Room Size: " + database.resultSet.getString(8) + ", ";
                String breakfast = "Breakfast Booked: " + database.resultSet.getString(5) + "  ";
//                    String dinner = "Dinners Booked: " + database.resultSet.getString(10) + ", ";
                String total_cost = "Total Cost: " + database.resultSet.getString(9) + "  ";
//                    String bookingStatus = "Booking Status: " + database.resultSet.getString(12) + ", ";
                String payment = "Payment: " + database.resultSet.getString(6) + "  ";
                String nights = "Number of nights: " + database.resultSet.getInt(4) + "  ";

                final String bookings = booking_id  + checkinDate + checkoutdate
                        + room_type  + breakfast  + total_cost  + payment + nights;

                listViewBookings.getItems().addAll(bookings);

            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

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




