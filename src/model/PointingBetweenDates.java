package model;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        date.setCalendar(new GregorianCalendar());

        return date.format(pointingDateHour.getTime());
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
