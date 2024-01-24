package dhurd.c195.clientdb.helper;

import dhurd.c195.clientdb.models.Appointment;
import dhurd.c195.clientdb.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.sql.Date.valueOf;

public class AppointmentQuery {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS b ON a.Contact_ID = b.Contact_ID";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String loc = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime();
            Integer custID = resultSet.getInt("Customer_ID");
            Integer userID = resultSet.getInt("User_ID");
            Integer contactID = resultSet.getInt("Contact_ID");
            String contact = resultSet.getString("Contact_Name");

            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, startTime, endDate, endTime, custID, userID, contactID, contact);
            allAppointments.add(appointment);
        }
        return allAppointments;
    }

    public static ObservableList<Appointment> getWeekAppointments() throws SQLException {
        ObservableList<Appointment> weekAppointments = FXCollections.observableArrayList();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime week = today.plusDays(7);
        String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS b ON a.Contact_ID = b.Contact_ID WHERE Start < ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setDate(1, valueOf(week.toLocalDate()));
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String loc = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime();
            Integer custID = resultSet.getInt("Customer_ID");
            Integer userID = resultSet.getInt("User_ID");
            Integer contactID = resultSet.getInt("Contact_ID");
            String contact = resultSet.getString("Contact_Name");

            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, startTime, endDate, endTime, custID, userID, contactID, contact);
            weekAppointments.add(appointment);
        }

        return weekAppointments;
    }

    public static ObservableList<Appointment> getMonthAppointments() throws SQLException {
        ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime month = today.plusDays(31);
        String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS b ON a.Contact_ID = b.Contact_ID WHERE Start < ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setDate(1, valueOf(month.toLocalDate()));
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String loc = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime();
            Integer custID = resultSet.getInt("Customer_ID");
            Integer userID = resultSet.getInt("User_ID");
            Integer contactID = resultSet.getInt("Contact_ID");
            String contact = resultSet.getString("Contact_Name");

            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, startTime, endDate, endTime, custID, userID, contactID, contact);
            monthAppointments.add(appointment);
        }

        return monthAppointments;
    }

    public static ObservableList<Appointment> getAppointmentsByCustomerID (Integer customerID) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS b ON a.Contact_ID=b.Contact_ID WHERE Customer_ID=?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerID);

        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String loc = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime();
            Integer custID = resultSet.getInt("Customer_ID");
            Integer userID = resultSet.getInt("User_ID");
            Integer contactID = resultSet.getInt("Contact_ID");
            String contact = resultSet.getString("Contact_Name");

            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, startTime, endDate, endTime, custID, userID, contactID, contact);
           appointments.add(appointment);
        }
        return appointments;

    }

    public static void newAppointment(String contactName, String title, String description, String loc, String type, LocalDateTime start, LocalDateTime end, Integer customerID, Integer userID ) throws SQLException {

        Contact contact = ContactsQuery.getContactID(contactName);
        String sql = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, loc);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerID);
        preparedStatement.setInt(8, userID);
        preparedStatement.setInt(9, contact.getContactID());

        preparedStatement.execute();
    }

    public static void updateAppointment(Integer appointmentID,String contactName, String title, String description, String loc, String type, LocalDateTime start, LocalDateTime end, Integer customerID, Integer userID  ) throws SQLException {

        Contact contact = ContactsQuery.getContactID(contactName);
        String sql = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, loc);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerID);
        preparedStatement.setInt(8, userID);
        preparedStatement.setInt(9, contact.getContactID());
        preparedStatement.setInt(10, appointmentID);

        preparedStatement.execute();
    }


}

