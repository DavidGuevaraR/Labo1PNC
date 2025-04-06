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

    public boolean addScheduleAppointment(Doctor doctor, Person person, String speciality, LocalDateTime date, boolean attendance){

        if(date.isBefore(LocalDateTime.now())){
            System.out.println("La fecha de la cita no puede ser anterior a la fecha actual.");
            return false;
        }

        int hour = date.getHour();
        if(hour < 8 || hour > 16){
            System.out.println("La cita debe estar entre las 8:00 y las 16:00.");
            return false;
        }

        for(Appointment appointment : appointments){
            if(appointment.getDoctor().getCode().equalsIgnoreCase(doctor.getCode()) && appointment.getDate().equals(date)){
                System.out.println("Ya existe una cita en esa fecha para este doctor.");
                return false;
            }
        }

        Appointment newAppointment = new Appointment(doctor, person, speciality, date, attendance);
        appointments.add(newAppointment);

        System.out.println("Appointment added");

        return true;
    }

    public boolean addDayAppointment(Doctor doctor, Person person, String speciality, LocalDateTime date, boolean attendance){
        Appointment newAppointment = new Appointment(doctor, person, speciality, date, attendance);
        appointments.add(newAppointment);
        System.out.println("Cita agregada correctamente a las: " + newAppointment.getDate().getHour());
        return true;
    }

    public LocalDateTime getAvailableHours(Doctor doctor, LocalDateTime day) {
        LocalDateTime availableHours = null;

        for (int hour = 8; hour < 16; hour++) {
            availableHours = LocalDateTime.of(day.getYear(), day.getMonth(), day.getDayOfMonth(), hour, 0);

            if (isTimeSlotAvailable(doctor, availableHours)) {
                return availableHours;
            }
        }

        System.out.println("No horas disponibles para el doctor.");
        return null;
    }

    private boolean isTimeSlotAvailable(Doctor doctor, LocalDateTime timeSlot) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().getCode().equalsIgnoreCase(doctor.getCode()) &&
                    appointment.getDate().equals(timeSlot)) {
                return false;
            }
        }
        return true;
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
                if (!appointments.get(i).isAttendance()) {
                    appointments.remove(i);
                    System.out.println("La cita fue eliminada correctamente.");
                    return;
                }
            }
        }
        System.out.println("No se encontró la cita para actualizar.");
    }


}
