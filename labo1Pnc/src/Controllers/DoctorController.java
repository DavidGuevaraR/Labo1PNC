package Controllers;

import Service.DoctorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void addingDoctor() {

        Scanner sc = new Scanner(System.in);
        LocalDate bDate = null;
        LocalDate rDate = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String firstName;
        do {
            System.out.print("Ingrese el primer nombre del doctor: ");
            firstName = sc.nextLine();
            if (firstName.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (firstName.isEmpty());

        String lastName;
        do {
            System.out.print("Ingrese el primer apellido del doctor: ");
            lastName = sc.nextLine();
            if (lastName.isEmpty()) {
                System.out.println("El apellido no puede estar vacío.");
            }
        } while (lastName.isEmpty());

        String birthDate;
        do {
            System.out.print("Ingrese la fecha de nacimiento (dd/MM/yyyy): ");
            birthDate = sc.nextLine();
            try {
                bDate = LocalDate.parse(birthDate, dtf);
            } catch (Exception e) {
                System.out.println("Fecha de nacimiento inválida.");
                bDate = null;
            }
        } while (bDate == null);

        String dui;
        boolean validDui = false;
        do {
            System.out.print("Ingrese el DUI del doctor (########-#): ");
            dui = sc.nextLine();

            final String currentDui = dui;

            if (!dui.matches("\\d{8}-\\d")) {
                System.out.println("El DUI debe tener el formato ########-#");
            } else if (doctorService.getDoctors().stream().anyMatch(d -> d.getDui().equals(currentDui))) {
                System.out.println("Ya existe un doctor registrado con ese DUI.");
            } else {
                validDui = true;
            }

        } while (!validDui);


        String speciality;
        do {
            System.out.print("Ingrese la especialidad del doctor: ");
            speciality = sc.nextLine();
            if (speciality.isEmpty()) {
                System.out.println("La especialidad no puede estar vacía.");
            }
        } while (speciality.isEmpty());

        String recruitmentDate;
        do {
            System.out.print("Ingrese la fecha de contratación (dd/MM/yyyy): ");
            recruitmentDate = sc.nextLine();
            try {
                rDate = LocalDate.parse(recruitmentDate, dtf);
            } catch (Exception e) {
                System.out.println(" Fecha de contratación inválida.");
                rDate = null;
            }
        } while (rDate == null);

        String code;
        boolean isUnique;

        do {
            code = CodeGenerator();
            final String generatedCode = code;

            isUnique = doctorService.getDoctors().stream()
                    .noneMatch(d -> d.getCode().equals(generatedCode));

        } while (!isUnique);


        doctorService.addDoctor(firstName, lastName, dui, bDate, speciality, rDate, code);
        System.out.println("Doctor agregado exitosamente con código único: " + code);

    }

    public String CodeGenerator() {
        Random rand = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String XAX = rand.nextInt(10) + "" + letters.charAt(rand.nextInt(letters.length()))+rand.nextInt(10);

        String AX = letters.charAt(rand.nextInt(letters.length()))+""+ rand.nextInt(10);

        return "ZNA-" + XAX + "-MD-" + AX;
    }

}
