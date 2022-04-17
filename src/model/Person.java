package model;

public class Person {
    // Arno
    // Youri
    // Test
    // Licorne
    // Re-Licorne
    private Integer number;
    private String lastName;
    private Integer phoneNumber;
    private String email;
    private String firstName;
    private Integer VATNumber;
    private Integer nbFidelityPoints;
    private Boolean hasBadge;
    private PersonType type;

    public Person(Integer number, String lastName, Integer phoneNumber, String email,
                  String firstName, Integer VATNumber, Integer nbFidelityPoints, Boolean hasBadge, PersonType type) {
        this.number = number;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.firstName = firstName;
        this.VATNumber = VATNumber;
        this.nbFidelityPoints = nbFidelityPoints;
        this.hasBadge = hasBadge;
        this.type = type;
    }

    public Person(Integer number, String lastName, Integer phoneNumber, String email,
                  Boolean hasBadge) {
        this.number = number;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hasBadge = hasBadge;
        this.firstName = null;
        this.VATNumber = null;
        this.nbFidelityPoints = null;
    }

    public Integer getNumber() {
        return number;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getPhoneNumber() {
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
}
