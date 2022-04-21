package model;

public class Person {
    private Integer number;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String firstName;
    private Integer VATNumber;
    private Integer nbFidelityPoints;
    private Boolean hasBadge;
    private PersonType type;

    public Person(Integer number, String lastName, String phoneNumber, String email,
                  String firstName, Integer VATNumber, Integer nbFidelityPoints, Boolean hasBadge, PersonType type) {
        setNumber(number);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setFirstName(firstName);
        setVATNumber(VATNumber);
        setNbFidelityPoints(nbFidelityPoints);
        setHasBadge(hasBadge);
        setType(type);
    }

    public Person(Integer number, String lastName, String phoneNumber,
                  String email, Boolean hasBadge) {
        setNumber(number);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setHasBadge(hasBadge);
        setFirstName(null);
        setVATNumber(null);
        setNbFidelityPoints(null);
    }

    public Integer getNumber() {
        return number;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getVATNumber() {
        return VATNumber;
    }

    public Integer getNbFidelityPoints() {
        return nbFidelityPoints;
    }

    public Boolean getHasBadge() {
        return hasBadge;
    }

    public PersonType getType() {
        return type;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setVATNumber(Integer VATNumber) {
        this.VATNumber = VATNumber;
    }

    public void setNbFidelityPoints(Integer nbFidelityPoints) {
        this.nbFidelityPoints = nbFidelityPoints;
    }

    public void setHasBadge(Boolean hasBadge) {
        this.hasBadge = hasBadge;
    }

    public void setType(PersonType type) {
        this.type = type;
    }
}
