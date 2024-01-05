package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
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

    public void weekView(ActionEvent actionEvent) {
    }

    public void monthView(ActionEvent actionEvent) {
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
