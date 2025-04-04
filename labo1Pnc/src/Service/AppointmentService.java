package Service;

import Model.Entity.Appointment;
import Model.Entity.Doctor;
import Model.Entity.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private List<Appointment> appointments;

    public AppointmentService() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Doctor doctor, Person person, String speciality, LocalDateTime date, boolean attendance){

        Appointment newAppointment = new Appointment(doctor, person, speciality, date, attendance);

        appointments.add(newAppointment);

        System.out.println("Appointment added");

    }

    public void ListAppointments(){
        if(!appointments.isEmpty()){
            for(Appointment appointment : appointments){
                System.out.println("Doctor: " + appointment.getDoctor().getFirstName().charAt(0) + ". " + appointment.getDoctor().getLastName() + ", Patient: " + appointment.getPerson().getFirstName().charAt(0) + ". " + appointment.getPerson().getLastName() + ", Speciality: " + appointment.getSpeciality() + ", Date: " + appointment.getDate().toString() + ", Attendance: " + appointment.isAttendance());
            }
        }else{
            System.out.println("No appointments found");
        }
    }

    public List<Appointment> getAppointments(){
        return appointments;
    }

}
