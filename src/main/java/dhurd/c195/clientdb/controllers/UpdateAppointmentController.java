package dhurd.c195.clientdb.controllers;

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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import dhurd.c195.clientdb.controllers.AddAppointmentController;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
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
    public ComboBox upApptStartTimeBox;
    public Label upApptEndDateLbl;
    public Label upApptEndTimeLbl;
    public Label upApptCustIDLbl;
    public Label upApptUserIDLbl;
    public DatePicker upApptEndDatePicker;
    public ComboBox upApptEndTimeBox;
    public ComboBox<Integer> upApptCustIDBox;
    public ComboBox upApptUserIDBox;
    public ComboBox upApptContactBox;
    public Label upApptTypeLbl;
    public TextField upApptTypeTxt;
    public Button upApptSaveBtn;
    public Button upApptCancelBtn;

    private void timeBoxes() {
        ObservableList<String> times = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(0, 0);
        LocalTime end = LocalTime.of(23, 45);
        times.add(start.toString());
        while (start.isBefore(end)) {
            start = start.plusMinutes(15);
            times.add(start.toString());
        }
        upApptStartTimeBox.setItems(times);
        upApptEndTimeBox.setItems(times);
    }

    private void contactBox() {
        ObservableList<Contact> allContacts = null;
        try {
            allContacts = ContactsQuery.getAllContacts();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        upApptContactBox.setItems(allContacts);
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
    }

    public void upApptCancel(ActionEvent actionEvent) {
    }

    public void passAppt(Appointment appointment) {
        upApptIDTxt.setText(String.valueOf(appointment.getApptID()));
        upApptTitleTxt.setText(appointment.getTitle());
        upApptDescTxt.setText(appointment.getDescription());
        upApptLocTxt.setText(appointment.getLocation());
        upApptContactBox.getSelectionModel().select(appointment.getContactName());
        upApptTypeTxt.setText(appointment.getType());
        upApptStartDatePicker.setValue(appointment.getStartDate());
        upApptStartTimeBox.getSelectionModel().select(appointment.getStartTime());
        upApptEndDatePicker.setValue(appointment.getEndDate());
        upApptEndTimeBox.getSelectionModel().select(appointment.getEndTime());
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
