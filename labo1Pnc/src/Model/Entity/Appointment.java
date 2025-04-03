package Model.Entity;

import java.time.LocalDateTime;

public class Appointment {
    private Doctor doctor;
    private Person person;
    private String speciality;
    private LocalDateTime date;
    private boolean attendance;

    public Appointment(Doctor doctor, Person person, String speciality, LocalDateTime date, boolean attendance) {
        this.doctor = doctor;
        this.person = person;
        this.speciality = speciality;
        this.date = date;
        this.attendance = attendance;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }
}
