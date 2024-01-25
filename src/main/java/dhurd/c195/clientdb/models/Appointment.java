package dhurd.c195.clientdb.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
    private Integer apptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private LocalDate endDate;
    private LocalDateTime endTime;
    private Integer customerID;
    private Integer userID;
    private Integer contactID;
    private String contactName;
    private String stringStart;
    private String stringEnd;

    public Appointment (Integer apptID,
     String title,
     String description,
     String location,
     String type,
    LocalDate startDate,
     String stringStart,
     String stringEnd,
     LocalDateTime startTime,
     LocalDate endDate,
     LocalDateTime endTime,
     Integer customerID,
     Integer userID,
     Integer contactID,
     String contactName ) {
        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.stringStart = stringStart;
        this.stringEnd = stringEnd;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contactName = contactName;
    }

    public Integer getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setStringStart (String stringStart) {
        this.stringStart = stringStart;
    }

    public String getStringStart() {return stringStart;}

    public void setStringEnd (String stringEnd) {
        this.stringEnd = stringEnd;
    }

    public String getStringEnd() {return stringEnd;}

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
