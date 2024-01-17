package dhurd.c195.clientdb.helper;
import dhurd.c195.clientdb.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dhurd.c195.clientdb.helper.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery {

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers AS a INNER JOIN first_level_divisions AS b ON a.Division_ID = b.Division_ID INNER JOIN countries AS c ON c.Country_ID = b.Country_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int customerID = resultSet.getInt("Customer_ID");
            String name = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postal = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            int divID = resultSet.getInt("Division_ID");
            String div = resultSet.getString("Division");
            String country = resultSet.getString("Country");

            Customer customer = new Customer(customerID, name, address, postal, phone, div, country, divID);
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    public static void newCustomer(String name, String address, String postal, String phone, String division) throws SQLException {
        Division division1 = DivisionQuery.getDivbyName(division);
        String sql = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postal);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, division1.getDivisionID());

        preparedStatement.execute();

    }

    public static void updateCustomer(int customerID, String name, String address, String postal, String phone, String division) throws SQLException{
        Division division1 = DivisionQuery.getDivbyName(division);
        String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postal);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, division1.getDivisionID());
        preparedStatement.setInt(6, customerID);

        preparedStatement.execute();
    }

    public static void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE from customers WHERE Customer_Id=?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerID);

        preparedStatement.execute();
    }

}

