package dhurd.c195.clientdb.helper;
import dhurd.c195.clientdb.controllers.*;
import dhurd.c195.clientdb.models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryQuery {

    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int countryID = resultSet.getInt("Country_ID");
            String name = resultSet.getString("Country");

            Country country = new Country(countryID, name);
            allCountries.add(country);
        }
        return allCountries;
    }



}
