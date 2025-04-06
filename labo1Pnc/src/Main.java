import Controllers.AppointmentController;
import Controllers.DoctorController;
import Controllers.PersonController;
import Service.AppointmentService;
import Service.DoctorService;
import Service.PersonService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu(){

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        DoctorService doctorService = new DoctorService();
        PersonService personService = new PersonService();
        AppointmentService appointmentService = new AppointmentService();
        DoctorController doctorController = new DoctorController(doctorService);
        PersonController personController = new PersonController(personService);
        AppointmentController appointmentController = new AppointmentController(appointmentService);

        while (flag) {

            System.out.println("---------------------------");
            System.out.println("1. Citas Actuales");
            System.out.println("2. Nueva Cita");
            System.out.println("3. Agregar Paciente");
            System.out.println("4. Agregar Doctor");
            System.out.println("5. Mundo Salva Vidas");
            System.out.println("6. Salir");

            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    //Funcion de listar citas  con sus opciones
                    appointmentController.appointmentMenu(doctorService);

                    break;
                case 2:
                    //Función de crear nueva cita
                    appointmentController.newAppointmentMenu(personService, doctorService);
                    break;
                case 3:
                    personController.addingPerson();
                    break;
                case 4:
                    //Service de add doctor
                    doctorController.addingDoctor();
                    break;
                case 5:
                    System.out.println("¡No sirve de nada, pero se ve bonito!");
                    break;
                case 6:
                    System.out.println("Gracias por usar nuestro sistema");
                    flag = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        }



    }

}