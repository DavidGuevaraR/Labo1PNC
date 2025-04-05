package Controllers;

import Service.PersonService;
import Model.Entity.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public void addingPerson() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("===== Registro de Paciente =====");

        System.out.print("Pacient First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Pacient Last Name: ");
        String lastName = sc.nextLine();

        System.out.print("Pacient DUI: ");
        String dui = sc.nextLine();

        System.out.print("Pacient Birth Date: (dd/MM/yyyy): ");
        String dobInput = sc.nextLine();

        try {
            LocalDate dateOfBirth = LocalDate.parse(dobInput, formatter);

            Person person = new Person(firstName, lastName, dui, dateOfBirth);
            personService.addPerson(person);

            System.out.println("Paciente agregado exitosamente.");

        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use dd/MM/yyyy.");
        }
    }
}
