package UI;

import domain.CardiovascularDisease;
import domain.RespiratoryDisease;
import service.Service;

import java.util.Scanner;

public class UI {
    private Service serv = new Service();
    public UI(Service serv) {
        this.serv = serv;
    }

    public void start() {
        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    this.askUserForDiseaseInput();
                    break;
                case 2:
                    this.serv.showAll();
                    break;
                case 3:
                    this.askUserForTreatmentCost();
                    break;
                case 4:
                    this.serv.saveDiseasesToFile();
                    break;
                case 10:
                    System.out.println("Thank you for using our app!");
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    public void askUserForDiseaseInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add a cardiovascular disease or a respiratory disease?");
        System.out.println("1. Cardiovascular disease");
        System.out.println("2. Respiratory disease");
        int option = scanner.nextInt();
        if (option == 1) {
            System.out.println("Enter the name of the disease: ");
            String name = scanner.next();
            System.out.println("Enter the Symptoms of the disease: ");
            String Symptoms = scanner.next();
            System.out.println("Enter the riskfactor of the disease:");
            String RiskFactor = scanner.next();
            System.out.println("Enter the subtype of the disease:");
            String subtype = scanner.next();
            serv.add(new CardiovascularDisease(name, Symptoms, RiskFactor, subtype));
        }
        else if (option == 2) {
            System.out.println("Enter the name of the disease: ");
            String name = scanner.next();
            System.out.println("Enter the Symptoms of the disease: ");
            String Symptoms = scanner.next();
            System.out.println("Enter the severity of the disease:");
            Integer severity = scanner.nextInt();
            serv.add(new RespiratoryDisease(name, Symptoms, severity));
        }
        else {
            System.out.println("Invalid option!");
        }
    }

    public void askUserForTreatmentCost() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input a treatment cost: ");
        Integer treatmentCost = scanner.nextInt();
        serv.getDiseasesWithMedication(treatmentCost);
    }

    public static void printMenu() {
        System.out.println("1. Add a new disease");
        System.out.println("2. Show all Diseases");
        System.out.println("3. Show all diseases which can be treated wih only medication, cost lowered than input, asc by name");
        System.out.println("4. Save to a text file diseases");
        System.out.println("10. Exit");
    }
}
