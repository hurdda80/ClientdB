package dhurd.c195.clientdb.helper;

import dhurd.c195.clientdb.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsQuery {

    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();

        String sql = "SELECT * FROM contacts";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int contactID = resultSet.getInt("Contact_ID");
            String name = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");

            Contact contact = new Contact(contactID, name, email);
            allContacts.add(contact);
        }
        return allContacts;
    }

    public static Contact getContactID(String contactName) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_Name=?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, contactName);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Contact contact1 = new Contact(resultSet.getInt("Contact_ID"), resultSet.getString("Contact_Name"),
                    resultSet.getString("Email"));

            return contact1;
        }
        return null;
    }
}
