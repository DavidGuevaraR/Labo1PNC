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

        System.out.println("Doctor First Name: ");
        String firstName = sc.nextLine();

        System.out.println("Doctor Last Name: ");
        String lastName = sc.nextLine();

        System.out.println("Doctor Birth Date: (dd/MM/yyyy)");
        String birthDate = sc.nextLine();
        try {
            bDate = LocalDate.parse(birthDate, dtf);
        } catch (Exception e) {
            System.out.println("Invalid birth date");
        }

        System.out.println("Doctor DUI: ");
        String dui = sc.nextLine();

        System.out.println("Doctor Speciality: ");
        String speciality = sc.nextLine();

        System.out.println("Doctor Recruitment Date: (dd/MM/yyyy)");
        String recruitmentDate = sc.nextLine();
        try {
            rDate = LocalDate.parse(recruitmentDate, dtf);
        } catch (Exception e) {
            System.out.println("Invalid Recruitment date");
        }

        String code = CodeGenerator();

        doctorService.addDoctor(firstName, lastName, dui, bDate, speciality, rDate, code);

    }

    public String CodeGenerator() {
        Random rand = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String XAX = rand.nextInt(10) + "" + letters.charAt(rand.nextInt(letters.length()))+rand.nextInt(10);

        String AX = letters.charAt(rand.nextInt(letters.length()))+""+ rand.nextInt(10);

        return "ZNA-" + XAX + "-MD-" + AX;
    }

}
