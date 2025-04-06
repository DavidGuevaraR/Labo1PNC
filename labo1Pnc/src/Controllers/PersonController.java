package Controllers;

import Service.PersonService;
import Model.Entity.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public void addingPerson() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("===== Registro de Paciente =====");

        String firstName;
        do {
            System.out.print("Pacient First Name (solo letras, 2-50 caracteres): ");
            firstName = sc.nextLine().trim();
            if (!isValidName(firstName)) {
                System.out.println("Nombre inválido. Use solo letras, entre 2 y 50 caracteres.");
            }
        } while (!isValidName(firstName));

        String lastName;
        do {
            System.out.print("Pacient Last Name (solo letras, 2-50 caracteres): ");
            lastName = sc.nextLine().trim();
            if (!isValidName(lastName)) {
                System.out.println("Apellido inválido. Use solo letras, entre 2 y 50 caracteres.");
            }
        } while (!isValidName(lastName));

        // El formato del DUI lo dej asi: 12345678-9)
        String dui;
        do {
            System.out.print("Pacient DUI (formato: 12345678-9): ");
            dui = sc.nextLine().trim();
            if (!isValidDui(dui)) {
                System.out.println("DUI inválido. Use el formato 12345678-9.");
            } else if (personService.findPersonByDui(dui) != null) {
                System.out.println("Este DUI ya está registrado. Ingrese uno diferente.");
            }
        } while (!isValidDui(dui) || personService.findPersonByDui(dui) != null);

        LocalDate dateOfBirth = null;
        while (dateOfBirth == null) {
            System.out.print("Pacient Birth Date (dd/MM/yyyy): ");
            String dobInput = sc.nextLine().trim();
            try {
                dateOfBirth = LocalDate.parse(dobInput, formatter);
                if (dateOfBirth.isAfter(LocalDate.now())) {
                    System.out.println("La fecha de nacimiento no puede ser futura.");
                    dateOfBirth = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use dd/MM/yyyy.");
            }
        }

        Person person = new Person(firstName, lastName, dui, dateOfBirth);
        personService.addPerson(person);
        System.out.println("Paciente agregado exitosamente.");
    }

    // Metodo para validar nombres (solo letras, 2-50 caracteres)
    private boolean isValidName(String name) {
        return name != null && Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$", name);
    }

    // Metodo para validar DUI (formato 12345678-9)
    private boolean isValidDui(String dui) {
        return dui != null && Pattern.matches("^\\d{8}-\\d$", dui);
    }
}