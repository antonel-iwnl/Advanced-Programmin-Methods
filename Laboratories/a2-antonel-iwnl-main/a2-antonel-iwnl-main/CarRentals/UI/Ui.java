package CarRentals.UI;

import CarRentals.Domain.Car;
import CarRentals.Domain.CarReservation;
import CarRentals.Domain.Person;
import CarRentals.Service.Service;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class Ui {
    private Service service = new Service();
    final private String menuMessage = "Car Rentals:\n" +
                                "0) Exit\n" +
                                "1) View Available Cars.\n" +
                                "2) View Reservations.\n" +
                                "3) Add available car.\n" +
                                "4) Remove available car.\n" +
                                "5) Update available car.\n" +
                                "6) Add reservation.\n" +
                                "7) Remove reservation.\n" +
                                "8) Update reservation.\n";

    public Ui() throws LoginException {
    }

    private static class PrimitiveReservation
    {
        public Car RENTED_CAR;
        public Person CUSTOMER;

        PrimitiveReservation(Car rentedCar, Person customer){
            this.RENTED_CAR = rentedCar;
            this.CUSTOMER = customer;
        }
    }


    private Car askForFreeCar() throws IOException, RuntimeException
    {
        if (service.noOfFreeCars() == 0) {
            throw new RuntimeException("No free cars available!");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Car car = null;
        boolean failed;
        do
        {
            try {
                System.out.print("Enter car id:\n>>\t");
                int carId = Integer.parseInt(br.readLine());

                car = service.getFreeCarById(carId);

                failed = false;
            } catch (NoSuchElementException exception) {
                System.out.println("Could not find a free car by that id.");
                failed = true;
            } catch (NumberFormatException exception)
            {
                System.out.println("Could not convert input into integer.");
                failed = true;
            }

        }while (failed);

        return car;
    }

    class PrimitiveCar
    {
        public String BRAND, MODEL;
        public int YEAR;
        public float MILEAGE, COST;

        public PrimitiveCar() {}
        public PrimitiveCar(String brand, String model, int year, float mileage, float cost) {
            this.BRAND = brand;
            this.MODEL = model;
            this.YEAR = year;
            this.MILEAGE = mileage;
            this.COST = cost;
        }
    }
    private PrimitiveCar askUserForNewCarData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PrimitiveCar car = new PrimitiveCar();

        System.out.print("Enter car brand:\n>>\t");
        car.BRAND = br.readLine();

        System.out.print("Enter car model:\n>>\t");
        car.MODEL = br.readLine();

        boolean failed;
        do {
            try {
                System.out.print("Enter car year:\n>>\t");
                car.YEAR = Integer.parseInt(br.readLine());

                failed = false;
            } catch (NumberFormatException exception) {
                System.out.println("Could not convert input into integer.");
                failed = true;
            }
        } while (failed);


        do {
            try {
                System.out.print("Enter car mileage:\n>>\t");
                car.MILEAGE = Float.parseFloat(br.readLine());

                failed = false;
            } catch (NumberFormatException exception) {
                System.out.println("Could not convert input into float!");
                failed = true;
            }
        } while (failed);

        do {
            try {
                System.out.print("Enter car cost:\n>>\t");
                car.COST = Float.parseFloat(br.readLine());

                failed = false;
            } catch (NumberFormatException exception) {
                System.out.println("Could not convert input into float!");
                failed = true;
            }
        } while (failed);

        return car;
    }

    private CarReservation askUserForReservation() throws IOException {
        if (service.noOfReservations() == 0) {
            throw new RuntimeException("There are no reservations!");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        CarReservation reservation = null;
        boolean failed;
        do {
            try {
                System.out.print("Enter reservation id:\n>>\t");
                int reservationId = Integer.parseInt(br.readLine());

                reservation= service.getReservation(reservationId);
                failed = false;

            } catch (NumberFormatException exception) {
                System.out.println("Could not convert input into integer!");
                failed = true;
            } catch (NoSuchElementException exception)
            {
                System.out.println("Could not find a reservation with the given id!");
                failed = true;
            }
        } while (failed);

        return reservation;
    }
    private PrimitiveReservation askUserForNewReservationData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Car car = null;
        try {
            car = askForFreeCar();
        } catch (RuntimeException exception)
        {
            //System.out.print("No free cars!");
            throw exception;
        }

        System.out.print("Enter customer's family name:\n>>\t");
        String familyName = br.readLine();

        System.out.print("Enter customer's first name:\n>>\t");
        String firstName = br.readLine();

        System.out.print("Enter customer's id:\n>>\t");
        String id = br.readLine();

        Person person = new Person(familyName, firstName, id);

        return new PrimitiveReservation(car, person);
    }
    private String viewReservations()
    {
        StringBuilder result = new StringBuilder();

        for (CarReservation reservation : service.reservations())
            result.append(reservation.toString()).append("\n");

        if (result.isEmpty())
            return "There are no reservations!";
        else
            return result.toString();
    }
    private String addNewReservation() throws IOException {
        try {
            PrimitiveReservation primitiveReservation = askUserForNewReservationData();

            CarReservation reservation = service.makeReservation(primitiveReservation.RENTED_CAR.getId(),
                    primitiveReservation.CUSTOMER);

            return "Made reservation:\n\t" + reservation.toString() + "\n";

        } catch (Exception exception)
        {
            // askUserForNewReservationData throws an exception if freeCars is empty
            return exception.getMessage();
        }

    }
    private String removeReservation() throws IOException {

        try {
            CarReservation reservation = askUserForReservation();

            service.removeReservation(reservation.getId());

            return "Removed " + reservation.toString() + "\n";
        } catch (RuntimeException exception)
        {
            return exception.getMessage();
        }

    }

    private String updateReservation() throws IOException {
        try {
            CarReservation reservation = askUserForReservation();
            service.removeReservation(reservation.getId());
            String oldReservationStr = reservation.toString();


            return addNewReservation() + "\n Old reservation was: " + oldReservationStr + "\n";

        } catch (Exception exception)
        {
            // askUserForReservation throws an exception if there are no reservations;
            return exception.getMessage();
        }
    }

    private String viewCars() throws IOException{
        StringBuilder result = new StringBuilder();

        for (Car car : service.freeCars())
            result.append(car.toString()).append("\n");

        if (result.isEmpty())
            return  "There are no free cars!";
        else
            return result.toString();
    }
    private String addCar() throws IOException{
        PrimitiveCar primitiveCar = askUserForNewCarData();
        Car car = service.addNewFreeCar(primitiveCar.BRAND, primitiveCar.MODEL, primitiveCar.YEAR,
                                        primitiveCar.MILEAGE, primitiveCar.COST);

        return "Added " + car.toString() + "\n";
    }

    private String removeCar(){
        try {
            Car chosenCar = askForFreeCar();

            service.removeFreeCar(chosenCar.getId());
            return "Removed " + chosenCar.toString();
        } catch (Exception exception)
        {
            return exception.getMessage();
        }

    }

    private String updateCar() throws IOException{
        try {
            Car chosenCar = askForFreeCar();
            String oldCarStr = chosenCar.toString();

            PrimitiveCar updatedCar = askUserForNewCarData();
            chosenCar.setBRAND(updatedCar.BRAND);
            chosenCar.setMODEL(updatedCar.MODEL);
            chosenCar.setYEAR(updatedCar.YEAR);
            chosenCar.setMILEAGE(updatedCar.MILEAGE);
            chosenCar.setCOST(updatedCar.COST);

            return "Updated " + oldCarStr + " to\n " + chosenCar.toString();
        } catch (Exception exception)
        {
            return exception.getMessage();
        }
    }
    public void startUi() throws IOException {
        boolean exit = false;

        String resultMessage = "";
        while(!exit)
        {

            System.out.print(menuMessage);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            if (!resultMessage.isEmpty())
                System.out.println("Result:\n" + resultMessage);

            System.out.print("Enter your option:\n>>\t");
            String option = br.readLine();

            switch (option)
            {
                case "0":
                    exit = true;
                    resultMessage = "Thank you for using my application!";
                    break;
                case "1":
                    resultMessage = viewCars();
                    break;
                case "2":
                    resultMessage = viewReservations();
                    break;
                case "3":
                    resultMessage = addCar();
                    break;
                case "4":
                    resultMessage = removeCar();
                    break;
                case "5":
                    resultMessage = updateCar();
                    break;
                case "6":
                    resultMessage = addNewReservation();
                    break;
                case "7":
                    resultMessage = removeReservation();
                    break;
                case "8":
                    resultMessage = updateReservation();
                    break;
                default:
                    resultMessage = "Please input an available option!";
            }
        }
    }

}
