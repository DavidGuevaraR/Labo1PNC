package Model.Entity;

import java.time.LocalDate;

public class Person {
    private String firstName;
    private String lastName;
    private String dui;
    private LocalDate dateOfBirth;

    public Person(String firstName, String lastName, String dui, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dui = dui;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
