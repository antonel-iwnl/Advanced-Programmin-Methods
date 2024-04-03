package ui;
import domain.Car;
import repository.Repository;
import service.CarReservations;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private CarReservations SERVICE;
    private Scanner SCANNER;

    public UserInterface(CarReservations carService){
        this.SERVICE = carService;
        this.SCANNER = new Scanner(System.in);
    }

    public void Start() {
        while (true) {
            System.out.println("Welcome to the Car Rental Agency !");
            System.out.println("1. Add a new car");
            System.out.println("2. Update an existing car");
            System.out.println("3. Delete an existing car");
            System.out.println("4. Add damage to a car");
            System.out.println("5. Remove damage from a car");
            System.out.println("6. List all cars");
            System.out.println("7. Find cars by MAKE");
            System.out.println("8. Make a reservation");
            System.out.println("9. Cancel a reservation");
            System.out.println("10. List all reservations");
            System.out.println("11. EXIT");
            System.out.print("Enter your choice: ");
            int CHOICE = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (CHOICE){
                case 1:
                    System.out.print("Enter car ID: ");
                    String CAR_ID = SCANNER.nextLine();
                    System.out.print("Enter car manufacturer: ");
                    String MANUFACTURER = SCANNER.nextLine();
                    System.out.print("Enter car model: ");
                    String MODEL = SCANNER.nextLine();
                    System.out.print("Enter car year: ");
                    int YEAR = SCANNER.nextInt();

                    Car NEW_CAR = new Car(CAR_ID, MANUFACTURER, MODEL, YEAR);

                    Repository.addCar(NEW_CAR);
                    System.out.println("Car added successfully.");
                    break;

                case 2:
                    System.out.print("Enter car ID: ");
                    String CAR_ID_TO_UPDATE = SCANNER.nextLine();
                    System.out.print("Enter car manufacturer: ");
                    String MANUFACTURER_TO_UPDATE = SCANNER.nextLine();
                    System.out.print("Enter car model: ");
                    String MODEL_TO_UPDATE = SCANNER.nextLine();
                    System.out.print("Enter car year: ");
                    int YEAR_TO_UPDATE = SCANNER.nextInt();
                    Car UPDATED_CAR = new Car(CAR_ID_TO_UPDATE, MANUFACTURER_TO_UPDATE, MODEL_TO_UPDATE, YEAR_TO_UPDATE);
                    Repository.update(UPDATED_CAR);
                    System.out.println("Car updated successfully.");
                    break;

                case 3:
                    System.out.print("Enter car ID: ");
                    String CAR_TO_DELETE = SCANNER.nextLine();
                    Repository.delete(CAR_TO_DELETE);
                    System.out.println("Car deleted successfully.");
                    break;

                case 4:
                    System.out.print("Enter car ID: ");
                    String CID_ADD_DAMAGE = SCANNER.nextLine();
                    System.out.print("Enter damage description: ");
                    String DAMAGE_DESC = SCANNER.nextLine();
                    Repository.addDamage(CID_ADD_DAMAGE, DAMAGE_DESC);
                    System.out.println("Damage added successfully.");
                    break;

                case 5:
                    System.out.print("Enter car ID: ");
                    String CID_REMOVE_DAMAGE = SCANNER.nextLine();
                    System.out.print("Enter damage description: ");
                    String DAMAGE_DESC_REMOVE = SCANNER.nextLine();
                    Repository.removeDamage(CID_REMOVE_DAMAGE, DAMAGE_DESC_REMOVE);
                    System.out.println("Damage removed successfully.");
                    break;

                case 6:
                    List<Car> ALL_CARS = Repository.getAll();
                    if (ALL_CARS.isEmpty()) {
                        System.out.println("No cars found in the repository.");
                    } else {
                        System.out.println("List of all cars:");
                        for (Car car : ALL_CARS) {
                            System.out.println("ID: " + car.getID());
                            System.out.println("Manufacturer: " + car.getMANUFACTURER());
                            System.out.println("Model: " + car.getMODEL());
                            System.out.println("Year: " + car.getYEAR());
                            if (car.hasDamages()) {
                                System.out.println("Damages:");
                                String[] DAMAGES = car.getDAMAGES();
                                for (String damage : DAMAGES) {
                                    System.out.println("- " + damage);
                                }
                            } else {
                                System.out.println("No Damages");
                            }

                            System.out.println();
                        }
                    }
                    break;

                case 7:
                    System.out.print("Enter car manufacturer: ");
                    String MANUF_TO_FIND = SCANNER.nextLine();

                    List<Car> CARS_BY_MANUF = Repository.findByManufacturer(MANUF_TO_FIND);

                    if (CARS_BY_MANUF.isEmpty()) {
                        System.out.println("No cars found for manufacturer: " + MANUF_TO_FIND);
                    } else {
                        System.out.println("List of cars with manufacturer " + MANUF_TO_FIND + ":");
                        for (Car car : CARS_BY_MANUF) {
                            System.out.println("ID: " + car.getID());
                            System.out.println("Manufacturer: " + car.getMANUFACTURER());
                            System.out.println("Model: " + car.getMODEL());
                            System.out.println("Year: " + car.getYEAR());
                            System.out.println();
                        }
                    }
                    break;

                case 8:
                    // Logic to make a reservation
                    System.out.print("Enter car ID for reservation: ");
                    String CID_TO_RESERVE = SCANNER.nextLine();
                    boolean RESERV_SUCCESS = SERVICE.createReservation(CID_TO_RESERVE);

                    if (RESERV_SUCCESS) {
                        System.out.println("Reservation made successfully.");
                    } else {
                        System.out.println("Reservation failed. Car not found or already reserved.");
                    }
                    break;

                case 9:
                    // Logic to cancel a reservation
                    System.out.print("Enter car ID for cancelling a reservation: ");
                    String CID_TO_CANCEL = SCANNER.nextLine();
                    boolean CANCEL_SUCCESS = SERVICE.cancelReservation(CID_TO_CANCEL);

                    if (CANCEL_SUCCESS) {
                        System.out.println("Reservation canceled successfully.");
                    } else {
                        System.out.println("Cancellation failed. Car not found or not reserved.");
                    }
                    break;

                case 10:
                    List<Car> RESERVED_CARS = SERVICE.getRESERVED_CARS();

                    if (RESERVED_CARS.isEmpty()) {
                        System.out.println("No reservations found.");
                    } else {
                        System.out.println("List of all reservations:");
                        for (Car car : RESERVED_CARS) {
                            System.out.println("Car ID: " + car.getID());
                            System.out.println("Manufacturer: " + car.getMANUFACTURER());
                            System.out.println("Model: " + car.getMODEL());
                            System.out.println("Year: " + car.getYEAR());
                            System.out.println();
                        }
                    }
                    break;

                case 11:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

            }
        }
    }
}
