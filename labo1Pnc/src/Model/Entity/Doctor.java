package Model.Entity;

import java.time.LocalDate;

public class Doctor extends Person {
    private String speciality;
    private LocalDate recruitmentDate;
    private String code;

    public Doctor(String firstName, String lastName, String dui, LocalDate dateOfBirth, String speciality, LocalDate recruitmentDate) {
        super(firstName, lastName, dui, dateOfBirth);
        this.speciality = speciality;
        this.recruitmentDate = recruitmentDate;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public LocalDate getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(LocalDate recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
