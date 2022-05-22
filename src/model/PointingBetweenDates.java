package model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PointingBetweenDates {
    private String lastName;
    private String firstName; // can be null
    private String personType;
    private GregorianCalendar pointingDateHour;
    private String pointingType;

    public PointingBetweenDates(String lastName, String firstName, String personType, GregorianCalendar pointingDate, String pointingType) {
        setLastName(lastName);
        setFirstName(firstName);
        setPersonType(personType);
        setPointingDateHour(pointingDate);
        setPointingType(pointingType);
    }

    public PointingBetweenDates(String lastName, String personType, GregorianCalendar pointingDate, String pointingType) {
        this(lastName, null, personType, pointingDate, pointingType);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public GregorianCalendar getPointingDateHour() {
        return pointingDateHour;
    }

    public String getPointingDateHourStr() {
        int year = pointingDateHour.get(Calendar.YEAR);
        int month = pointingDateHour.get(Calendar.MONTH)+1;
        int day = pointingDateHour.get(Calendar.DAY_OF_MONTH);
        int hour = pointingDateHour.get(Calendar.HOUR);
        int minutes = pointingDateHour.get(Calendar.MINUTE);

        return year + "-" + month + "-" + day + " " + hour + ":" + minutes;
    }

    public void setPointingDateHour(GregorianCalendar pointingDate) {
        this.pointingDateHour = pointingDate;
    }

    public String getPointingType() {
        return pointingType;
    }

    public void setPointingType(String pointingType) {
        this.pointingType = pointingType;
    }
}
