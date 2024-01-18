package dhurd.c195.clientdb.helper;

import dhurd.c195.clientdb.models.Contact;
import dhurd.c195.clientdb.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {

    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM users";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int userID = resultSet.getInt("User_ID");
            String name = resultSet.getString("User_Name");
            String password = resultSet.getString("Password");

            User user = new User(userID, name, password);
            allUsers.add(user);
        }
        return allUsers;
    }
}
