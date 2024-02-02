package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.Main;
import dhurd.c195.clientdb.helper.AppointmentQuery;
import dhurd.c195.clientdb.helper.ContactsQuery;
import dhurd.c195.clientdb.helper.CustomerQuery;
import dhurd.c195.clientdb.helper.UserQuery;
import dhurd.c195.clientdb.models.Appointment;
import dhurd.c195.clientdb.models.Contact;
import dhurd.c195.clientdb.models.Customer;
import dhurd.c195.clientdb.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import dhurd.c195.clientdb.controllers.AddAppointmentController;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
    public Label upApptLbl;
    public Label upApptIDLbl;
    public Label upApptTitleLbl;
    public Label upApptDescLbl;
    public Label upApptLocLbl;
    public Label upApptContactLbl;
    public Label upApptStartDateLbl;
    public Label upApptStartTimeLbl;
    public TextField upApptIDTxt;
    public TextField upApptTitleTxt;
    public TextField upApptDescTxt;
    public TextField upApptLocTxt;
    public DatePicker upApptStartDatePicker;
    public ComboBox<String> upApptStartTimeBox;
    public Label upApptEndDateLbl;
    public Label upApptEndTimeLbl;
    public Label upApptCustIDLbl;
    public Label upApptUserIDLbl;
    public DatePicker upApptEndDatePicker;
    public ComboBox<String> upApptEndTimeBox;
    public ComboBox<Integer> upApptCustIDBox;
    public ComboBox<Integer> upApptUserIDBox;
    public ComboBox<String> upApptContactBox;
    public Label upApptTypeLbl;
    public TextField upApptTypeTxt;
    public Button upApptSaveBtn;
    public Button upApptCancelBtn;



    private void timeBoxes() {
        ObservableList<String> times = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(0, 0, 00);
        LocalTime end = LocalTime.of(23, 45, 00);
        times.add(start.toString());
        while (start.isBefore(end)) {
            start = start.plusMinutes(15);
            times.add(start.toString());
        }
        upApptStartTimeBox.setItems(times);
        upApptEndTimeBox.setItems(times);
    }

    private void contactBox() {
        ObservableList<String> nameContacts = FXCollections.observableArrayList();
        try {
            ObservableList<Contact> contacts = ContactsQuery.getAllContacts();
            for (Contact contact : contacts) {
                if (!nameContacts.contains(contact.getContactName())) {
                    nameContacts.add(contact.getContactName());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        upApptContactBox.setItems(nameContacts);
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
        upApptCustIDBox.setItems(customersIDList);
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
        upApptUserIDBox.setItems(usersIDList);
    }

    public void upApptSave(ActionEvent actionEvent) {
        try {
            if (upApptTitleTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a title");
                alert.showAndWait();
            }
            else if (upApptDescTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a description");
                alert.showAndWait();
            }
            else if (upApptLocTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a location");
                alert.showAndWait();
            }
            else if (upApptContactBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a contact");
                alert.showAndWait();
            }
            else if (upApptTypeTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a type");
                alert.showAndWait();
            }
            else if (upApptStartDatePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a start date");
                alert.showAndWait();
            }
            else if (upApptStartTimeBox.getSelectionModel().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a start time");
                alert.showAndWait();
            }
            else if (upApptEndDatePicker.getValue()== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an end date");
                alert.showAndWait();
            }
            else if (upApptEndTimeBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an end time");
                alert.showAndWait();
            }
            else if (upApptCustIDBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a customer ID");
                alert.showAndWait();
            }
            else if (upApptUserIDBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a user ID");
                alert.showAndWait();
            }
            else if (upApptEndDatePicker.getValue().isBefore(upApptStartDatePicker.getValue())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a start date that is before the end date");
                alert.showAndWait();
            }

            else if (!timeDateCheck()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Correct the start/end time; and/or start/end on same day");
                alert.showAndWait();
            }
            else if (!overlapAppt()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment overlaps with existing appointment");
                alert.showAndWait();
            }
            else if (!businessHours()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please ensure appointment times are within 8AM - 10PM EST");
                alert.showAndWait();
            }

            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Update appointment?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {

                    AppointmentQuery.updateAppointment(Integer.valueOf(upApptIDTxt.getText()),upApptContactBox.getSelectionModel().getSelectedItem().toString(), upApptTitleTxt.getText(), upApptDescTxt.getText(),
                            upApptLocTxt.getText(), upApptTypeTxt.getText(), LocalDateTime.of(upApptStartDatePicker.getValue(), LocalTime.parse(upApptStartTimeBox.getSelectionModel().getSelectedItem())),
                            LocalDateTime.of(upApptEndDatePicker.getValue(), LocalTime.parse(upApptEndTimeBox.getSelectionModel().getSelectedItem())),
                            upApptCustIDBox.getSelectionModel().getSelectedItem(), upApptUserIDBox.getSelectionModel().getSelectedItem());

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                    Stage stage = (Stage) upApptTitleTxt.getScene().getWindow();
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
        LocalTime startTime = LocalTime.parse(upApptStartTimeBox.getSelectionModel().getSelectedItem());
        LocalTime endTime = LocalTime.parse(upApptEndTimeBox.getSelectionModel().getSelectedItem());
        LocalDate startDate = upApptStartDatePicker.getValue();
        LocalDate endDate =upApptEndDatePicker.getValue();
        LocalDateTime start = startDate.atTime(startTime);
        LocalDateTime end = endDate.atTime(endTime);
        LocalDateTime convStart;
        LocalDateTime convEnd;
        ObservableList<Appointment> appointments = AppointmentQuery.getAppointmentsByCustomerID(upApptCustIDBox.getSelectionModel().getSelectedItem());
        Appointment currentAppt = AppointmentQuery.getAppointmentsByAppointmentID(Integer.valueOf(upApptIDTxt.getText()));
        for (Appointment appointment: appointments) {
          if (appointment.getApptID().equals(currentAppt.getApptID())) {
              appointments.remove(appointment);
          }
        }
        for (Appointment appointment: appointments) {
            convStart = appointment.getStartDate().atTime(appointment.getStartTime().toLocalTime());
            convEnd = appointment.getEndDate().atTime(appointment.getEndTime().toLocalTime());

            if (convStart.isAfter(start) && convStart.isBefore(end) || convStart.isEqual(start) || convStart.isEqual(end)) {
                return false;
            }
            else if (convEnd.isAfter(start) && convEnd.isBefore(end) || convEnd.isEqual(end) || convEnd.isEqual(start)) {
                return false;
            }
        }
        return true;
    }

    private boolean timeDateCheck() {
        LocalTime startTime = LocalTime.parse(upApptStartTimeBox.getSelectionModel().getSelectedItem());
        LocalTime endTime = LocalTime.parse(upApptEndTimeBox.getSelectionModel().getSelectedItem());
        LocalDate startDate = upApptStartDatePicker.getValue();
        LocalDate endDate = upApptEndDatePicker.getValue();
        if (startTime.isAfter(endTime)) {
            return false;
        }
        if (!startDate.isEqual(endDate)) {
            return false;
        }
        return true;
    }

    private boolean businessHours() {
        LocalDateTime startDateTime = LocalDateTime.of(upApptStartDatePicker.getValue(), LocalTime.parse(upApptStartTimeBox.getSelectionModel().getSelectedItem()));
        ZonedDateTime estStartDateTime = ZonedDateTime.of(startDateTime, ZoneId.systemDefault());
        LocalDateTime endDateTime = LocalDateTime.of(upApptEndDatePicker.getValue(), LocalTime.parse(upApptEndTimeBox.getSelectionModel().getSelectedItem()));
        ZonedDateTime estEndDateTime = ZonedDateTime.of(endDateTime, ZoneId.systemDefault());
        ZonedDateTime businessOpen = ZonedDateTime.of(upApptStartDatePicker.getValue(), LocalTime.of(8,0), ZoneId.of("America/New_York"));
        ZonedDateTime businessClose = ZonedDateTime.of(upApptEndDatePicker.getValue(), LocalTime.of(22,0), ZoneId.of("America/New_York"));

        if (estStartDateTime.isBefore(businessOpen) || estStartDateTime.isAfter(businessClose) ||
                estEndDateTime.isAfter(businessClose) || estEndDateTime.isBefore(businessOpen)) {
            return false;
        }
        return true;
    }




    public void upApptCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel updating appointment?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = (Stage) upApptTitleTxt.getScene().getWindow();
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void passAppt(Appointment appointment) {
        upApptIDTxt.setText(String.valueOf(appointment.getApptID()));
        upApptTitleTxt.setText(appointment.getTitle());
        upApptDescTxt.setText(appointment.getDescription());
        upApptLocTxt.setText(appointment.getLocation());
        upApptContactBox.setValue(appointment.getContactName());
        upApptTypeTxt.setText(appointment.getType());
        upApptStartDatePicker.setValue(appointment.getStartDate());
        upApptStartTimeBox.getSelectionModel().select(appointment.getStringStart());
        upApptEndDatePicker.setValue(appointment.getEndDate());
        upApptEndTimeBox.getSelectionModel().select(appointment.getStringEnd());
        upApptCustIDBox.getSelectionModel().select(appointment.getCustomerID());
        upApptUserIDBox.getSelectionModel().select(appointment.getUserID());



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeBoxes();
        contactBox();
        customerIDBox();
        userIDBox();
    }
}
