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
    public void appointmentMenu(DoctorService doctorService) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== Submenú de Citas =====");
            System.out.println("1 Mostrar todas las citas");
            System.out.println("2 Filtrar por doctor");
            System.out.println("3 Filtrar por fecha");
            System.out.println("4 Confirmar cita");
            System.out.println("5. Mostrar día con más citas");
            System.out.println("0. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (option) {
                case 1:
                    mostrarTodasLasCitas();
                    break;
                case 2:
                    filtrarPorDoctor(doctorService);
                    break;
                case 3:
                    filtrarPorFecha();
                    break;
                case 4:
                    actualizarCita();
                    break;
                case 5:
                    mostrarDiaConMasCitas();
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (option != 0);
    }
    public void mostrarDiaConMasCitas() {
        appointmentService.getDayWithMostAppointments();
    }

    public void newAppointmentMenu(PersonService personService, DoctorService doctorService){
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int opt;

        do {
            System.out.println("==== Nueva cita ====");
            System.out.println("1. Crear cita para hoy");
            System.out.println("2. Crear cita Programada");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opt = scanner.nextInt();
            scanner.nextLine();

            switch(opt){
                case 1:
                    createDayAppointment(personService, doctorService);
                    flag = false;
                    break;
                case 2:
                    createScheduleAppointment(personService, doctorService);
                    flag = false;
                    break;
                case 0:
                    System.out.println("Regresando Menu Principal...");
                    flag = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        }while(flag);

    }

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void createScheduleAppointment(PersonService personService, DoctorService doctorService) {

        Scanner sc = new Scanner(System.in);
        Person patient = null;
        List<Doctor> doctorsSpeciality = null;
        LocalDateTime date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Ingrese DUI del paciente: ");
        String dui = sc.nextLine();
        patient = personService.findPersonByDui(dui);
        if (patient == null) {
            System.out.println("Paciente no encontrado. Por favor, registre al paciente primero.");
            return;
        }

        System.out.println("Escribe la especialidad del doctor:");
        String speciality = sc.nextLine();
        doctorsSpeciality = searchDoctorSpeciality(doctorService.getDoctors(), speciality);

        if(doctorsSpeciality.isEmpty()){
            System.out.println("No hay doctores disponibles en esta especialidad");
            return;
        }

        System.out.println("Doctores de la especialidad: ");
        for (Doctor doctor : doctorsSpeciality) {
            System.out.println("Code: " + doctor.getCode() + ", Doctor: " + doctor.getFirstName().charAt(0) + ". " + doctor.getLastName());
        }

        Doctor selectedDoctor = null;
        while(selectedDoctor == null){
            System.out.println("Escribe el codigo del doctor deseado: ");
            String doctorCode = sc.nextLine();

            selectedDoctor = searchDoctor(doctorsSpeciality, doctorCode);

            if(selectedDoctor == null){
                System.out.println("Doctor no encontrado");
            }

        }

        System.out.println("Fecha y hora a inscribir: (dd/MM/yyyy HH:mm)");
        String appointmentDate = sc.nextLine();
        try {
            date = LocalDateTime.parse(appointmentDate, dtf);
        } catch (Exception e) {
            System.out.println("Invalida fecha de cita");
        }

        boolean assistence = false;

        //Agregar el service de apointmet para guardar en una lista
        if(appointmentService.addScheduleAppointment(selectedDoctor, patient, speciality, date, assistence)){
            System.out.println("Cita agregada correctamente");
        }else{
            System.out.println("Error al agregar la cita");
        }

    }

    public void createDayAppointment(PersonService personService, DoctorService doctorService) {
        Scanner sc = new Scanner(System.in);
        Person patient = null;
        List<Doctor> doctorsSpeciality = null;
        LocalDateTime today = LocalDateTime.now();

        System.out.println("Escribe el DUI del paciente: ");
        String dui = sc.nextLine();
        patient = personService.findPersonByDui(dui);
        if (patient == null) {
            System.out.println("Paciente no encontrado. Por favor, registre al paciente primero.");
            return;
        }

        System.out.println("Escribe la especialidad del doctor:");
        String speciality = sc.nextLine();
        doctorsSpeciality = searchDoctorSpeciality(doctorService.getDoctors(), speciality);

        if(doctorsSpeciality.isEmpty()){
            System.out.println("No doctores disponibles en esta especialidad");
            return;
        }

        System.out.println("Doctores disponibles: ");
        for (Doctor doctor : doctorsSpeciality) {
            System.out.println("Code: " + doctor.getCode() + ", Doctor: " + doctor.getFirstName().charAt(0) + ". " + doctor.getLastName());
        }

        Doctor selectedDoctor = null;
        while(selectedDoctor == null){
            System.out.println("Escribe el codigo del doctor a elegir: ");
            String doctorCode = sc.nextLine();

            selectedDoctor = searchDoctor(doctorsSpeciality, doctorCode);

            if(selectedDoctor == null){
                System.out.println("Doctor no encontrado");
            }

        }

        LocalDateTime availableHours = appointmentService.getAvailableHours(selectedDoctor, today);

        boolean assistence = false;

        if (appointmentService.addDayAppointment(selectedDoctor, patient, speciality, availableHours, assistence)){
            System.out.println("Cita agregada correctamente");
        }else {
            System.out.println("Error al agregar la cita");
        }
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
    private void mostrarTodasLasCitas() {
        List<Model.Entity.Appointment> appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            for (Model.Entity.Appointment a : appointments) {
                System.out.println(a);
            }
        }
    }

    private void filtrarPorDoctor(DoctorService doctorService) {
        Scanner sc = new Scanner(System.in);
        List<Doctor> doctors = doctorService.getDoctors();

        if (doctors.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }

        System.out.println("Doctores disponibles:");
        for (Doctor d : doctors) {
            System.out.println("Código: " + d.getCode() + " | " + d.getFirstName() + " " + d.getLastName() + " - " + d.getSpeciality());
        }

        System.out.print("Ingrese el código del doctor: ");
        String code = sc.nextLine();

        List<Model.Entity.Appointment> result = appointmentService.getAppointmentsByDoctorCode(code);
        if (result.isEmpty()) {
            System.out.println("No hay citas para este doctor.");
        } else {
            result.forEach(System.out::println);
        }
    }

    private void filtrarPorFecha() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
        String dateInput = sc.nextLine();

        try {
            LocalDateTime start = LocalDateTime.parse(dateInput + " 00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            LocalDateTime end = start.plusDays(1);

            List<Model.Entity.Appointment> result = appointmentService.getAppointmentsByDateRange(start, end);

            if (result.isEmpty()) {
                System.out.println("No hay citas en esa fecha.");
            } else {
                result.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.out.println("Formato de fecha inválido.");
        }
    }

    private void actualizarCita() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.print("Ingrese el ID de la cita a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Model.Entity.Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            System.out.println("No se encontró la cita.");
            return;
        }

        System.out.println("Cita actual: " + appointment);

        System.out.print("¿El paciente asistió? (true/false): ");
        boolean asistio = sc.nextBoolean();
        sc.nextLine();

        try {
            appointment.setAttendance(asistio);
            appointmentService.updateAppointment(appointment);

            System.out.println("Cita actualizada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar la cita.");
        }
    }

}
