package dhurd.c195.clientdb.helper;

import dhurd.c195.clientdb.models.Appointment;
import dhurd.c195.clientdb.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import static java.sql.Date.valueOf;
import static java.time.ZoneId.systemDefault;

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
            LocalDateTime startTime = resultSet.getTimestamp("Start").toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalDateTime endTime = resultSet.getTimestamp("End").toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
            Integer custID = resultSet.getInt("Customer_ID");
            Integer userID = resultSet.getInt("User_ID");
            Integer contactID = resultSet.getInt("Contact_ID");
            String contact = resultSet.getString("Contact_Name");
            ZonedDateTime time1 = resultSet.getTimestamp("Start").toInstant().atZone(ZoneId.systemDefault());
            ZonedDateTime time2 = resultSet.getTimestamp("End").toInstant().atZone(ZoneId.systemDefault());



            int zhours = time1.getHour();
            int zminutes = time1.getMinute();
            String ztime;
            String ztime2;
            if (zhours < 10) {
                ztime = "0" + String.valueOf(zhours);
            }
            else {
                ztime = String.valueOf(zhours); }

            if (zminutes == 0) {
                ztime2 = "00";
            } else {
                ztime2 = String.valueOf(zminutes);
            }

                String zzz = ztime + ":" + ztime2;

                int qhours = time2.getHour();
                int qminutes = time2.getMinute();
                String qtime;
                String qtime2;

                if (qhours < 10) {
                    qtime = "0" + (zhours);
                }
                else {
                    qtime = String.valueOf(qhours);
                }

                if (qminutes == 0) {
                    qtime2 = "00";
                } else {
                    qtime2 = String.valueOf(qminutes);
                }
                String qqq = qtime + ":" + qtime2;
                String stringStart = zzz;
                String stringEnd = qqq;

                Appointment appointment = new Appointment(apptID, title, description, loc, type, startDate, stringStart, stringEnd, startTime, endDate, endTime, custID, userID, contactID, contact);
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
            ZonedDateTime time1 = resultSet.getTimestamp("Start").toInstant().atZone(ZoneId.systemDefault());
            ZonedDateTime time2 = resultSet.getTimestamp("End").toInstant().atZone(ZoneId.systemDefault());

            int zhours = time1.getHour();
            int zminutes = time1.getMinute();
            String ztime;
            String ztime2;
            if (zhours < 10) {
                ztime = "0" + String.valueOf(zhours);
            }
            else {
                ztime = String.valueOf(zhours); }

            if (zminutes == 0) {
                ztime2 = "00";
            } else {
                ztime2 = String.valueOf(zminutes);
            }

            String zzz = ztime + ":" + ztime2;

            int qhours = time2.getHour();
            int qminutes = time2.getMinute();
            String qtime;
            String qtime2;

            if (qhours < 10) {
                qtime = "0" + (zhours);
            }
            else {
                qtime = String.valueOf(qhours);
            }

            if (qminutes == 0) {
                qtime2 = "00";
            } else {
                qtime2 = String.valueOf(qminutes);
            }
            String qqq = qtime + ":" + qtime2;
            String stringStart = zzz;
            String stringEnd = qqq;

            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, stringStart, stringEnd, startTime, endDate, endTime, custID, userID, contactID, contact);
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
            ZonedDateTime time1 = resultSet.getTimestamp("Start").toInstant().atZone(ZoneId.systemDefault());
            ZonedDateTime time2 = resultSet.getTimestamp("End").toInstant().atZone(ZoneId.systemDefault());


            int zhours = time1.getHour();
            int zminutes = time1.getMinute();
            String ztime;
            String ztime2;
            if (zhours < 10) {
                ztime = "0" + String.valueOf(zhours);
            }
            else {
                ztime = String.valueOf(zhours); }

            if (zminutes == 0) {
                ztime2 = "00";
            } else {
                ztime2 = String.valueOf(zminutes);
            }

            String zzz = ztime + ":" + ztime2;

            int qhours = time2.getHour();
            int qminutes = time2.getMinute();
            String qtime;
            String qtime2;

            if (qhours < 10) {
                qtime = "0" + (zhours);
            }
            else {
                qtime = String.valueOf(qhours);
            }

            if (qminutes == 0) {
                qtime2 = "00";
            } else {
                qtime2 = String.valueOf(qminutes);
            }
            String qqq = qtime + ":" + qtime2;
            String stringStart = zzz;
            String stringEnd = qqq;
            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, stringStart, stringEnd, startTime, endDate, endTime, custID, userID, contactID, contact);
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
            ZonedDateTime time1 = resultSet.getTimestamp("Start").toInstant().atZone(ZoneId.systemDefault());
            ZonedDateTime time2 = resultSet.getTimestamp("End").toInstant().atZone(ZoneId.systemDefault());

            int zhours = time1.getHour();
            int zminutes = time1.getMinute();
            String ztime;
            String ztime2;
            if (zhours < 10) {
                ztime = "0" + String.valueOf(zhours);
            }
            else {
                ztime = String.valueOf(zhours); }

            if (zminutes == 0) {
                ztime2 = "00";
            } else {
                ztime2 = String.valueOf(zminutes);
            }

            String zzz = ztime + ":" + ztime2;

            int qhours = time2.getHour();
            int qminutes = time2.getMinute();
            String qtime;
            String qtime2;

            if (qhours < 10) {
                qtime = "0" + (zhours);
            }
            else {
                qtime = String.valueOf(qhours);
            }

            if (qminutes == 0) {
                qtime2 = "00";
            } else {
                qtime2 = String.valueOf(qminutes);
            }
            String qqq = qtime + ":" + qtime2;
            String stringStart = zzz;
            String stringEnd = qqq;

            Appointment appointment = new Appointment(apptID, title, description, loc, type,startDate, stringStart, stringEnd, startTime, endDate, endTime, custID, userID, contactID, contact);
           appointments.add(appointment);
        }
        return appointments;

    }

    public static void newAppointment(String contactName, String title, String description, String loc, String type, LocalDateTime start, String stringStart, String stringEnd, LocalDateTime end, Integer customerID, Integer userID ) throws SQLException {

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

