package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.helper.UserQuery;
import dhurd.c195.clientdb.models.*;
import dhurd.c195.clientdb.helper.ContactsQuery;
import dhurd.c195.clientdb.helper.CustomerQuery;
import dhurd.c195.clientdb.models.Contact;
import dhurd.c195.clientdb.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    private ZonedDateTime toEST(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }

    private void timeBoxes() {
        ObservableList<String> times = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(0, 0);
        LocalTime end = LocalTime.of(23, 45);
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

    public void addApptSave(ActionEvent actionEvent) {
    }

    public void addApptCancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeBoxes();
        contactBox();
        customerIDBox();
        userIDBox();
    }
}
