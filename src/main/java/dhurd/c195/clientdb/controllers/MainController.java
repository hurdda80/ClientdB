package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.Main;
import dhurd.c195.clientdb.helper.AppointmentQuery;
import dhurd.c195.clientdb.helper.CustomerQuery;
import dhurd.c195.clientdb.models.Appointment;
import dhurd.c195.clientdb.models.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public HBox upcomingLbl;
    public RadioButton weekRadio;
    public RadioButton monthRadio;
    public ToggleGroup appointment;
    public TableView apptTable;
    public TableColumn apptIdCol;
    public TableColumn apptTitleCol;
    public TableColumn apptDescripCol;
    public TableColumn apptLocCol;
    public TableColumn apptTypeCol;
    public TableColumn apptStartCol;
    public TableColumn apptEndCol;
    public TableColumn apptContactCol;
    public TableColumn apptCustIdCol;
    public TableColumn apptUserIdCol;
    public Button apptNewBtn;
    public Button apptUpdateBtn;
    public Button apptDelBtn;
    public TableView custTable;
    public TableColumn custIdCol;
    public TableColumn custNameCol;
    public TableColumn custPhoneCol;
    public TableColumn custAddressCol;
    public TableColumn custStateCol;
    public TableColumn custPostalCol;
    public Button custNewBtn;
    public Button custUpdateBtn;
    public Button custDelBtn;
    public Button reportsBtn;
    public Button exitBtn;

    static ObservableList<Customer> allCustomers;
    static ObservableList<Appointment> allAppointments;
    static ObservableList<Appointment> appointments;
    public TableColumn custCountryCol;
    public RadioButton allRadio;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        try {
            allCustomers = CustomerQuery.getAllCustomers();
            custTable.setItems(allCustomers);
            custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            custAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            custStateCol.setCellValueFactory(new PropertyValueFactory<>("division"));
            custPostalCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
            custCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

            allAppointments = AppointmentQuery.getAllAppointments();
            apptTable.setItems(allAppointments);
            apptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
            apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            apptDescripCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            apptLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            apptStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            apptEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
            apptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));




    } catch (SQLException e) {
        throw new RuntimeException(e);
        }
    }

    public void weekView(ActionEvent actionEvent) throws SQLException {
        appointments = AppointmentQuery.getWeekAppointments();
        apptTable.setItems(appointments);
        apptTable.refresh();
    }

    public void monthView(ActionEvent actionEvent) throws SQLException {
        appointments = AppointmentQuery.getMonthAppointments();
        apptTable.setItems(appointments);
        apptTable.refresh();
    }
    public void allView(ActionEvent actionEvent) throws SQLException {
        appointments = AppointmentQuery.getAllAppointments();
        apptTable.setItems(appointments);
        apptTable.refresh();
    }

    public void newAppt(ActionEvent actionEvent) {
    }

    public void updateAppt(ActionEvent actionEvent) {
    }

    public void deleteAppt(ActionEvent actionEvent) {
    }

    public void newCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        Stage stage = (Stage) apptTable.getScene().getWindow();
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void updateCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UpdateCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        Stage stage = (Stage) apptTable.getScene().getWindow();
        stage.setTitle("Edit Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }

    public void reportsView(ActionEvent actionEvent) {
    }

    public void exitMain(ActionEvent actionEvent) {
    }


}
