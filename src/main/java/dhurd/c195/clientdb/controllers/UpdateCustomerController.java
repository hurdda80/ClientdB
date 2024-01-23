package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.Main;
import dhurd.c195.clientdb.helper.CountryQuery;
import dhurd.c195.clientdb.helper.CustomerQuery;
import dhurd.c195.clientdb.helper.DivisionQuery;
import dhurd.c195.clientdb.models.Country;
import dhurd.c195.clientdb.models.Customer;
import dhurd.c195.clientdb.models.Division;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {
    public Label upCustLbl;
    public Label upCustIDLbl;
    public Label upCustNameLbl;
    public Label upCustPhoneLbl;
    public Label upCustAddressLbl;
    public Label upCustCountryLbl;
    public Label upCustStateLbl;
    public Label upCustPostalLbl;
    public TextField upCustIDTxt;
    public TextField upCustNameTxt;
    public TextField upCustPhoneTxt;
    public TextField upCustPostalTxt;
    public TextField upCustAddressTxt;
    public ComboBox upCustCountryBox;
    public ComboBox upCustStateBox;
    public Button upCustSaveBtn;
    public Button upCustCancelBtn;

    public void updateCustSave(ActionEvent actionEvent) throws IOException {
        try {
            int id = Integer.parseInt(upCustIDTxt.getText());
            String name = upCustNameTxt.getText();
            String phone = upCustPhoneTxt.getText();
            String address = upCustAddressTxt.getText();
            String postal = upCustPostalTxt.getText();
            String division = String.valueOf(upCustStateBox.getValue());



            if (upCustNameTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please enter a name");
                alert.showAndWait();
            }
            else if (upCustPhoneTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please enter a phone number");
                alert.showAndWait();
            }
            else if (upCustAddressTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please enter an address");
                alert.showAndWait();
            }
          /**  else if (upCustStateBox.getSelectionModel().isEmpty()) {
                if (division == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Please select a State or Division");
                    alert.showAndWait();
                }
            } */
            else if (upCustPostalTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please enter a postal code");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Customer Updated");
                alert.setContentText("Customer Updated");
                alert.showAndWait();
                CustomerQuery.updateCustomer(id, name, address, postal, phone, division);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                Stage stage = (Stage) upCustPostalTxt.getScene().getWindow();
                stage.setTitle("Appointments");
                stage.setScene(scene);
                stage.show();
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateCustCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setContentText("Cancel Update Customer?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = (Stage) upCustStateBox.getScene().getWindow();
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void passCust (Customer customer) {
        upCustIDTxt.setText(String.valueOf(customer.getCustomerID()));
        upCustNameTxt.setText(customer.getCustomerName());
        upCustPhoneTxt.setText(customer.getPhone());
        upCustAddressTxt.setText(customer.getAddress());
        upCustCountryBox.getSelectionModel().select(customer.getCountry());
        upCustStateBox.getSelectionModel().select(customer.getDivision());
        upCustPostalTxt.setText(customer.getPostal());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Country> allCountries = null;
        try {
            allCountries = CountryQuery.getAllCountries();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        upCustCountryBox.setItems(allCountries);

        ObservableList<Division> allDivisions = null;
        try {
            allDivisions = DivisionQuery.getAllDivisions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        upCustStateBox.setItems(allDivisions);


    }

}

