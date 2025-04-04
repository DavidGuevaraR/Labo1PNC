package Controllers;

import Model.Entity.Doctor;
import Model.Entity.Person;
import Service.AppointmentService;
import Service.DoctorService;
import Service.PersonService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void createAppointment(/*PersonService personService,*/ DoctorService doctorService) {

        Scanner sc = new Scanner(System.in);
        Person patient = null;
        List<Doctor> doctorsSpeciality = null;
        LocalDateTime date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Patient DUI: ");
        String dui = sc.nextLine();
        //patient = searchPerson(personService.listPerson(), dui);

        System.out.println("Choose Doctor Speciality:");
        String speciality = sc.nextLine();
        doctorsSpeciality = searchDoctorSpeciality(doctorService.getDoctors(), speciality);

        if(doctorsSpeciality.isEmpty()){
            System.out.println("No doctors found with this speciality");
            return;
        }

        System.out.println("Available Doctors: ");
        for (Doctor doctor : doctorsSpeciality) {
            System.out.println("Code: " + doctor.getCode() + ", Doctor: " + doctor.getFirstName().charAt(0) + ". " + doctor.getLastName());
        }

        Doctor selectedDoctor = null;
        while(selectedDoctor == null){
            System.out.println("Select Doctor Code: ");
            String doctorCode = sc.nextLine();

            selectedDoctor = searchDoctor(doctorsSpeciality, doctorCode);

            if(selectedDoctor == null){
                System.out.println("Doctor not found");
            }

        }

        System.out.println("Appointment Date And Hour: (dd/MM/yyyy HH:mm)");
        String appointmentDate = sc.nextLine();
        try {
            date = LocalDateTime.parse(appointmentDate, dtf);
        } catch (Exception e) {
            System.out.println("Invalid Appointment date");
        }

        Boolean assistence = false;

        //Agregar el service de apointmet para guardar en una lista
        appointmentService.addAppointment(selectedDoctor, patient, speciality, date, assistence);

    }

    public static List<Doctor> searchDoctorSpeciality(List<Doctor> doctors, String speciality) {
        List<Doctor> doctorsFound = new java.util.ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpeciality().equals(speciality)) {
                doctorsFound.add(doctor);
            }
        }
        return doctorsFound;
    }

    public static Person searchPerson(List<Person> persons, String dui){
        for (Person person : persons) {
            if (person.getDui().equals(dui)) {
                return person;
            }
        }
        return null;
    }

    public static Doctor searchDoctor(List<Doctor> doctors, String code){
        for (Doctor doctor : doctors) {
            if (doctor.getCode().equalsIgnoreCase(code.trim())) {
                return doctor;
            }
        }
        return null;
    }

}
