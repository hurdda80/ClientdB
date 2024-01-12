package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.Main;
import dhurd.c195.clientdb.helper.CountryQuery;
import dhurd.c195.clientdb.helper.CustomerQuery;
import dhurd.c195.clientdb.helper.DivisionQuery;
import dhurd.c195.clientdb.helper.JDBC;
import dhurd.c195.clientdb.models.Country;
import dhurd.c195.clientdb.models.Division;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    public Label addCustLbl;
    public Label addCustIDLbl;
    public Label addCustNameLbl;
    public Label addCustPhoneLbl;
    public Label addCustAddressLbl;
    public Label addCustCountryLbl;
    public Label addCustStateLbl;
    public Label addCustPostalLbl;
    public TextField addCustIDTxt;
    public TextField addCustNameTxt;
    public TextField addCustPhoneTxt;
    public TextField addCustAddressTxt;
    public ComboBox<Country> addCustCountryBox;
    public ComboBox<Division> addCustStateBox;
    public Button addCustSaveBtn;
    public Button addCustCancelBtn;
    public TextField addCustPostalTxt;

    public Country selectedCountry;



    public void addCustSave(ActionEvent actionEvent) throws IOException {

        try {
        /**    String name = addCustNameTxt.getText();
            String phone = addCustPhoneTxt.getText();
            String address = addCustAddressTxt.getText();
            String state = addCustStateBox.getSelectionModel().getSelectedItem().toString();
            String postal = addCustPostalTxt.getText();
            CustomerQuery.newCustomer(name, address, postal, phone, state);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        if (addCustNameTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter a name");
            alert.showAndWait();
        }
        else if (addCustPhoneTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter a phone number");
            alert.showAndWait();
        }
        else if (addCustAddressTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter an address");
            alert.showAndWait();
        }
        else if (addCustStateBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a State or Division");
            alert.showAndWait();
        }
        else if (addCustPostalTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter a postal code");
            alert.showAndWait();
        }
        else {
        CustomerQuery.newCustomer(addCustNameTxt.getText(), addCustAddressTxt.getText(),addCustPostalTxt.getText(),
                addCustPhoneTxt.getText(), String.valueOf(addCustStateBox.getValue()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Customer Added");
            alert.setContentText("New Customer Added");
            alert.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = (Stage) addCustPostalTxt.getScene().getWindow();
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
    }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        public void addCustCancel(ActionEvent actionEvent) {
    }

    public void selectCountry(ActionEvent actionEvent) throws SQLException {
        selectedCountry = addCustCountryBox.getSelectionModel().getSelectedItem();
        int idCountry = selectedCountry.getCountryID();
        ObservableList<Division> divByCountry = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID=?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, idCountry);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Division division = new Division(resultSet.getInt("Division_ID"), resultSet.getString("Division"), resultSet.getInt("Country_ID"));
            divByCountry.add(division);
        }

        addCustStateBox.setItems(divByCountry);


        System.out.println(divByCountry.size());
    }

    public void selectDivision(ActionEvent actionEvent) throws SQLException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Country> allCountries = null;
        try {
            allCountries = CountryQuery.getAllCountries();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addCustCountryBox.setItems(allCountries);

        ObservableList<Division> allDivisions = null;
        try {
            allDivisions = DivisionQuery.getAllDivisions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addCustStateBox.setItems(allDivisions);

    }
}