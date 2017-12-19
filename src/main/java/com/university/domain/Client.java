package com.university.domain;

/**
 * Created by redric on 09.11.17.
 * Entity for storing in repository
 */
public class Client {
    private int id;
    private String firstName;
    private String middleName;
    private String surname;
    private String telephoneNumber;

    public Client() {}

    public Client(int id, String firstName, String middleName, String surname, String telephoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }

    @Override
    public boolean equals(Object o) {
        Client another = (Client) o;
        boolean isEqual = this.getFirstName() == another.getFirstName()
                && this.getSurname() == another.getSurname()
                && this.getTelephoneNumber() == another.getTelephoneNumber();
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
