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
                        database.resultSet.getString("payment")
                ));
//

            }
            tableBooking.setItems(data);
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




