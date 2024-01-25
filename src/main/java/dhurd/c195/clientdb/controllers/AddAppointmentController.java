package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.Main;
import dhurd.c195.clientdb.helper.AppointmentQuery;
import dhurd.c195.clientdb.helper.UserQuery;
import dhurd.c195.clientdb.models.*;
import dhurd.c195.clientdb.helper.ContactsQuery;
import dhurd.c195.clientdb.helper.CustomerQuery;
import dhurd.c195.clientdb.models.Contact;
import dhurd.c195.clientdb.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class AddAppointmentController implements Initializable {
    public Label addApptIDLbl;
    public Label addApptTitleLbl;
    public Label addApptDescLbl;
    public Label addApptLocLbl;
    public Label addApptContactLbl;
    public Label addApptStartDateLbl;
    public Label addApptStartTimeLbl;
    public TextField addApptIDTxt;
    public TextField addApptTitleTxt;
    public TextField addApptDescTxt;
    public TextField addApptLocTxt;
    public DatePicker addApptStartDatePicker;
    public ComboBox<String> addApptStartTimeBox;
    public Label addApptEndDateLbl;
    public Label addApptCustIDLbl;
    public Label addApptUserIDLbl;
    public DatePicker addApptEndDatePicker;
    public ComboBox<Integer> addApptCustIDBox;
    public ComboBox<Integer> addApptUserIDBox;
    public ComboBox<Contact> addApptContactBox;
    public Label AddApptTypeLbl;
    public TextField addApptTypeTxt;
    public Button addApptSaveBtn;
    public Button addApptCancelBtn;
    public ComboBox<String> addApptEndTimeBox;
    public Label addApptEndTimeLbl;



    private void timeBoxes() {
        ObservableList<String> times = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(0, 0, 00);
        LocalTime end = LocalTime.of(23, 45, 00);
        times.add(start.toString());
        while (start.isBefore(end)) {
            start = start.plusMinutes(15);
            times.add(start.toString());
        }
        addApptStartTimeBox.setItems(times);
        addApptEndTimeBox.setItems(times);
    }

    private void contactBox() {
        ObservableList<Contact> allContacts = null;
        try {
            allContacts = ContactsQuery.getAllContacts();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addApptContactBox.setItems(allContacts);
    }

    private void customerIDBox() {
        ObservableList<Integer> customersIDList = FXCollections.observableArrayList();

        try {
            ObservableList<Customer> allCustomers = CustomerQuery.getAllCustomers();
            for (Customer customer: allCustomers) {
                customersIDList.add(customer.getCustomerID());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addApptCustIDBox.setItems(customersIDList);
    }

    private void userIDBox() {
        ObservableList<Integer> usersIDList = FXCollections.observableArrayList();

        try {
            ObservableList<User> allUsers = UserQuery.getAllUsers();
            for (User user : allUsers) {
                usersIDList.add(user.getUserID());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addApptUserIDBox.setItems(usersIDList);
    }

    public void addApptSave(ActionEvent actionEvent) throws IOException, SQLException {

        try {
            if (addApptTitleTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a title");
                alert.showAndWait();
            }
            else if (addApptDescTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a description");
                alert.showAndWait();
            }
            else if (addApptLocTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a location");
                alert.showAndWait();
            }
            else if (addApptContactBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a contact");
                alert.showAndWait();
            }
            else if (addApptTypeTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a type");
                alert.showAndWait();
            }
            else if (addApptStartDatePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a start date");
                alert.showAndWait();
            }
            else if (addApptStartTimeBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a start time");
                alert.showAndWait();
            }
            else if (addApptEndDatePicker.getValue()== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an end date");
                alert.showAndWait();
            }
            else if (addApptEndTimeBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an end time");
                alert.showAndWait();
            }
            else if (addApptCustIDBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a customer ID");
                alert.showAndWait();
            }
            else if (addApptUserIDBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a user ID");
                alert.showAndWait();
            }
            else if (addApptEndDatePicker.getValue().isBefore(addApptStartDatePicker.getValue())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a start date that is before the end date");
                alert.showAndWait();
            }

            else if (!timeDateCheck()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please ensure the start time is after the end time, and the start date is the same as the end date");
                alert.showAndWait();
            }
            else if (!overlapAppt()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please ensure these appointment times do not overlap existing appointment times with customer");
                alert.showAndWait();
            }
            else if (!businessHours()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please ensure appointment times are within 8AM - 10PM EST");
                alert.showAndWait();
            }

            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Create new appointment?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {

                    AppointmentQuery.newAppointment(addApptContactBox.getSelectionModel().getSelectedItem().toString(), addApptTitleTxt.getText(), addApptDescTxt.getText(),
                            addApptLocTxt.getText(), addApptTypeTxt.getText(), LocalDateTime.of(addApptStartDatePicker.getValue(), LocalTime.parse(addApptStartTimeBox.getSelectionModel().getSelectedItem())),
                            addApptStartTimeBox.getSelectionModel().getSelectedItem(), addApptEndTimeBox.getSelectionModel().getSelectedItem(),
                            LocalDateTime.of(addApptEndDatePicker.getValue(), LocalTime.parse(addApptEndTimeBox.getSelectionModel().getSelectedItem())),
                            addApptCustIDBox.getSelectionModel().getSelectedItem(), addApptUserIDBox.getSelectionModel().getSelectedItem());

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                    Stage stage = (Stage) addApptTitleTxt.getScene().getWindow();
                    stage.setTitle("Appointments");
                    stage.setScene(scene);
                    stage.show();
                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean overlapAppt() throws SQLException {
        LocalTime startTime = LocalTime.parse(addApptStartTimeBox.getSelectionModel().getSelectedItem());
        LocalTime endTime = LocalTime.parse(addApptEndTimeBox.getSelectionModel().getSelectedItem());
        LocalDate startDate = addApptStartDatePicker.getValue();
        LocalDate endDate = addApptEndDatePicker.getValue();
        LocalDateTime start = startDate.atTime(startTime);
        LocalDateTime end = endDate.atTime(endTime);
        LocalDateTime convStart;
        LocalDateTime convEnd;
        ObservableList<Appointment> appointments = AppointmentQuery.getAppointmentsByCustomerID(addApptCustIDBox.getSelectionModel().getSelectedItem());
        for (Appointment appointment: appointments) {
            convStart = appointment.getStartDate().atTime(appointment.getStartTime().toLocalTime());
            convEnd = appointment.getEndDate().atTime(appointment.getEndTime().toLocalTime());

            if (convStart.isAfter(start) && convStart.isBefore(end)) {
                return false;
            }
            else if (convEnd.isAfter(start) && convEnd.isBefore(end)) {
                return false;
            }
        }
        return true;
    }

    private boolean timeDateCheck() {
        LocalTime startTime = LocalTime.parse(addApptStartTimeBox.getSelectionModel().getSelectedItem());
        LocalTime endTime = LocalTime.parse(addApptEndTimeBox.getSelectionModel().getSelectedItem());
        LocalDate startDate = addApptStartDatePicker.getValue();
        LocalDate endDate = addApptEndDatePicker.getValue();
        if (startTime.isAfter(endTime)) {
            return false;
        }
        if (!startDate.isEqual(endDate)) {
            return false;
        }
        return true;
    }

    private boolean businessHours() {
        LocalDateTime startDateTime = LocalDateTime.of(addApptStartDatePicker.getValue(), LocalTime.parse(addApptStartTimeBox.getSelectionModel().getSelectedItem()));
        ZonedDateTime estStartDateTime = ZonedDateTime.of(startDateTime, ZoneId.systemDefault());
        LocalDateTime endDateTime = LocalDateTime.of(addApptEndDatePicker.getValue(), LocalTime.parse(addApptEndTimeBox.getSelectionModel().getSelectedItem()));
        ZonedDateTime estEndDateTime = ZonedDateTime.of(endDateTime, ZoneId.systemDefault());
        ZonedDateTime businessOpen = ZonedDateTime.of(addApptStartDatePicker.getValue(), LocalTime.of(8,0), ZoneId.of("America/New_York"));
        ZonedDateTime businessClose = ZonedDateTime.of(addApptEndDatePicker.getValue(), LocalTime.of(22,0), ZoneId.of("America/New_York"));

        if (estStartDateTime.isBefore(businessOpen) || estStartDateTime.isAfter(businessClose) ||
            estEndDateTime.isAfter(businessClose) || estEndDateTime.isBefore(businessOpen)) {
            return false;
        }
        return true;
    }

    public void addApptCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel making appointment?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = (Stage) addApptTitleTxt.getScene().getWindow();
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeBoxes();
        contactBox();
        customerIDBox();
        userIDBox();
    }
}
