import Controllers.DoctorController;
import Service.DoctorService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu(){

        Scanner sc = new Scanner(System.in);
        Boolean flag = true;
        DoctorService doctorService = new DoctorService();
        DoctorController doctorController = new DoctorController(doctorService);

        while (flag) {

            System.out.println("---------------------------");
            System.out.println("1. Citas Actuales");
            System.out.println("2. Nueva Cita");
            System.out.println("3. Agregar Paciente");
            System.out.println("4. Agregar Doctor");
            System.out.println("5. Salir");
            System.out.println("6. Lista doctores");

            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    //Funcion de listar citas  c3on sus opciones
                    break;
                case 2:
                    //Función de crear nueva cita
                    break;
                case 3:
                    //Service de add person
                    break;
                case 4:
                    //Service de add doctor
                    doctorController.addingDoctor();
                    break;
                case 5:
                    System.out.println("Gracias por usar nuestro sistema");
                    flag = false;
                    break;
                //Esto solo es pa ver si se agregan los doctores, borrar al final
                case 6:
                    doctorService.ListDoctors();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        }



    }

}