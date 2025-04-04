package Controllers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class DoctorController {

    public void DoctorController() {

        Scanner sc = new Scanner(System.in);
        LocalDate bDate = null;
        LocalDate rDate = null;
        LocalDate date = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Doctor First Name: ");
        String firstName = sc.nextLine();

        System.out.println("Doctor Last Name: ");
        String lastName = sc.nextLine();

        System.out.println("Doctor Birth Date: ");
        String birthDate = sc.nextLine();
        try {
            bDate = LocalDate.parse(birthDate, dtf);
        } catch (Exception e) {
            System.out.println("Invalid birth date");
        }

        int age = Period.between(bDate, date).getYears();
        if (age >= 18) {
            System.out.println("Doctor DUI: ");
            String dui = sc.nextLine();
        }else{
            String dui = "000000000";
        }

        System.out.println("Doctor Speciality: ");
        String speciality = sc.nextLine();

        System.out.println("Doctor Recruitment Date: ");
        String recruitmentDate = sc.nextLine();
        try {
            rDate = LocalDate.parse(birthDate, dtf);
        } catch (Exception e) {
            System.out.println("Invalid Recruitment date");
        }

        String code = CodeGenerator();

        

    }

    public String CodeGenerator() {
        Random rand = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String XAX = rand.nextInt(10) + "" + letters.charAt(rand.nextInt(letters.length()))+rand.nextInt(10);

        String AX = letters.charAt(rand.nextInt(letters.length()))+""+ rand.nextInt(10);

        return "ZNA-" + XAX + "MD" + AX;
    }

}
