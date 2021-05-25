import javafx.beans.property.*;

import java.time.LocalDate;

public class Booking {
    private SimpleIntegerProperty booking_id;
    private SimpleIntegerProperty user_id;
    private ObjectProperty<LocalDate> check_in_date;
    private ObjectProperty<LocalDate> check_out_date;
    private SimpleIntegerProperty nights;
    private SimpleStringProperty room_type;
    private SimpleStringProperty total_cost;
    private SimpleStringProperty payment;
    private SimpleStringProperty breakfast;


    Booking(int booking_id, int user_id, LocalDate check_in_date, LocalDate check_out_date, int nights, String room_type, String total_cost, String payment, String breakfast) {
        this.booking_id = new SimpleIntegerProperty(booking_id);
        this.user_id = new SimpleIntegerProperty(user_id);
        this.check_in_date = new SimpleObjectProperty(check_in_date);
        this.check_out_date = new SimpleObjectProperty(check_out_date);
        this.nights = new SimpleIntegerProperty(nights);
        this.room_type = new SimpleStringProperty(room_type);
        this.total_cost = new SimpleStringProperty(total_cost);
        this.payment = new SimpleStringProperty(payment);
        this.breakfast = new SimpleStringProperty(breakfast);
    }

    public int getBooking_id() {
        return booking_id.get();
    }

    public SimpleIntegerProperty booking_idProperty() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id.set(booking_id);
    }

    public int getUser_id() {
        return user_id.get();
    }

    public SimpleIntegerProperty user_idProperty() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }

    public LocalDate getCheck_in_date() {
        return check_in_date.get();
    }

    public ObjectProperty<LocalDate> check_in_dateProperty() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date.set(check_in_date);
    }

    public LocalDate getCheck_out_date() {
        return check_out_date.get();
    }

    public ObjectProperty<LocalDate> check_out_dateProperty() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date.set(check_out_date);
    }

    public int getNights() {
        return nights.get();
    }

    public SimpleIntegerProperty nightsProperty() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights.set(nights);
    }

    public String getRoom_type() {
        return room_type.get();
    }

    public SimpleStringProperty room_typeProperty() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type.set(room_type);
    }

    public String getTotal_cost() {
        return total_cost.get();
    }

    public SimpleStringProperty total_costProperty() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost.set(total_cost);
    }

    public String getPayment() {
        return payment.get();
    }

    public SimpleStringProperty paymentProperty() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment.set(payment);
    }

    public String getBreakfast() {
        return breakfast.get();
    }

    public SimpleStringProperty breakfastProperty() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast.set(breakfast);
    }

}
