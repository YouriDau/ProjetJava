package model;

import java.sql.Time;
import java.util.GregorianCalendar;

public class PointingBetweenDates {
    private String lastName;
    private String firstName; // can be null
    private String personType;
    private GregorianCalendar pointingDate;
    private Time pointingHour;
    private String pointingType;

    public PointingBetweenDates(String lastName, String firstName, String personType, GregorianCalendar pointingDate,
                                Time pointingHour, String pointingType) {
        setLastName(lastName);
        setFirstName(firstName);
        setPersonType(personType);
        setPointingDate(pointingDate);
        setPointingHour(pointingHour);
        setPointingType(pointingType);
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

    public GregorianCalendar getPointingDate() {
        return pointingDate;
    }

    public void setPointingDate(GregorianCalendar pointingDate) {
        this.pointingDate = pointingDate;
    }

    public Time getPointingHour() {
        return pointingHour;
    }

    public void setPointingHour(Time pointingHour) {
        this.pointingHour = pointingHour;
    }

    public String getPointingType() {
        return pointingType;
    }

    public void setPointingType(String pointingType) {
        this.pointingType = pointingType;
    }
}
