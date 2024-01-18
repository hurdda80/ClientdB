package dhurd.c195.clientdb.helper;

import dhurd.c195.clientdb.models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            int apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String loc = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime();
            int custID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");
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
            int apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String loc = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime();
            int custID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");
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
            int apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String loc = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime();
            int custID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");
            String contact = resultSet.getString("Contact_Name");

            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, startTime, endDate, endTime, custID, userID, contactID, contact);
            monthAppointments.add(appointment);
        }

        return monthAppointments;
    }

    public static void newAppointment(){

    }
}
