package dhurd.c195.clientdb.helper;
import dhurd.c195.clientdb.controllers.*;
import dhurd.c195.clientdb.models.Country;
import dhurd.c195.clientdb.models.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionQuery {
    public static ObservableList<Division> getAllDivisions() throws SQLException {
        ObservableList<Division> allDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int divisionID = resultSet.getInt("Division_ID");
            String name = resultSet.getString("Division");
            int countryID = resultSet.getInt("Country_ID");

            Division division = new Division(divisionID, name, countryID);
            allDivisions.add(division);
        }
        return allDivisions;
    }
}

  /**  public static ObservableList<Division> getDivByCountry(int countryID) throws SQLException {
        Country country = (Country) CountryQuery.getAllCountries();
        ObservableList<Division> divByCountry = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID=?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, idCountry);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int divisionID = resultSet.getInt("Division_ID");
            String name = resultSet.getString("Division");
            int countryID = resultSet.getInt("Country_ID");

            Division division = new Division(divisionID, name, countryID);
            allDivisions.add(division);
        }
        return allDivisions;
    }
}
*/