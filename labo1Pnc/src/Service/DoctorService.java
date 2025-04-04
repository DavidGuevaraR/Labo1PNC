package Service;

import Model.Entity.Doctor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private List<Doctor> doctors;

    public DoctorService() {
        this.doctors = new ArrayList<>();
    }

    public void AddDoctor(String firstName, String lastName, String dui, LocalDate dateOfBirth, String speciality, LocalDate recruitmentDate, String code) {

        Doctor newDoctor = new Doctor(firstName, lastName, dui, dateOfBirth, speciality, recruitmentDate, code);

        doctors.add(newDoctor);

        System.out.println("Doctor added");
    }

    public void ListDoctors() {
        if (doctors.size() > 0) {
            for (Doctor doctor : doctors) {
                System.out.println("Doctor " + doctor.getFirstName() + " " + doctor.getLastName() + " Especialidad " + doctor.getSpeciality() + " Codigo " + doctor.getCode());
            }
        }else{
            System.out.println("No Doctors found");
        }
    }

}
