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

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments); // devolvemos una copia por seguridad
    }

    public List<Appointment> getAppointmentsByDoctorCode(String code) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().getCode().equalsIgnoreCase(code.trim())) {
                result.add(appointment);
            }
        }
        return result;
    }

    public List<Appointment> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (!appointment.getDate().isBefore(start) && appointment.getDate().isBefore(end)) {
                result.add(appointment);
            }
        }
        return result;
    }

    public Appointment getAppointmentById(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null;
    }

    public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId() == updatedAppointment.getId()) {
                appointments.set(i, updatedAppointment);
                System.out.println("La cita fue actualizada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró la cita para actualizar.");
    }


}
