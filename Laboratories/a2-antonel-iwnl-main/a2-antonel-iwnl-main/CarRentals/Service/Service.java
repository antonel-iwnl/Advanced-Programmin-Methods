package CarRentals.Service;

import CarRentals.Domain.Car;
import CarRentals.Domain.CarReservation;
import CarRentals.Domain.Person;
import CarRentals.Repository.CarRepo;
import CarRentals.Repository.ReservationsRepo;

import javax.security.auth.login.LoginException;
import java.util.NoSuchElementException;

public class Service {
    //the id of a free car will be considered as the id of the reservation when that car is used
    private CarRepo FREE_CARS = new CarRepo();
    private int VALID_CAR_ID = 0;

    private ReservationsRepo reservations = new ReservationsRepo();

    private void initCars() {

        Car car1 = new Car(VALID_CAR_ID++,"Lamborghini", "Aventador SVJ", 2021, 7400, 450000);
        Car car2 = new Car(VALID_CAR_ID++,"Mercedes", "C300d", 2011, 114632, 17440);
        Car car3 = new Car(VALID_CAR_ID++,"BMW", "M340i", 2023, 17780, 36700);
        Car car4 = new Car(VALID_CAR_ID++,"Hyundai", "i30 N", 2020, 69000, 12350);
        Car car5 = new Car(VALID_CAR_ID++,"Lancia", "Appia Berlina", 1953, 1941478, 2500000);
        Car car6 = new Car(VALID_CAR_ID++,"Audi", "RS5", 2022, 13420, 50000);
        Car car7 = new Car(VALID_CAR_ID++,"Dacia", "Lodgy", 2021, 47834, 7500);
        Car car8 = new Car(VALID_CAR_ID++,"Honda", "Accord", 2004, 247839, 900);
        Car car9 = new Car(VALID_CAR_ID++,"KIA", "K5 GT-Line", 2022, 3000, 14000);
        Car car10 = new Car(VALID_CAR_ID++,"Ford", "Mustang GT ", 2009, 44320, 12400);


        FREE_CARS.add(car1);
        FREE_CARS.add(car2);
        FREE_CARS.add(car3);
        FREE_CARS.add(car4);
        FREE_CARS.add(car5);
        FREE_CARS.add(car6);
        FREE_CARS.add(car7);
        FREE_CARS.add(car8);
        FREE_CARS.add(car9);
        FREE_CARS.add(car10);
    }
    private void initReservations() throws LoginException {
        Person person1 = new Person("Smecherescu", "Antonel", "696969");
        Person person2 = new Person("Lucas", "George", "123123");
        Person person3 = new Person("Laurciuc", "Mihai", "4323123");
        Person person4 = new Person("Pista", "Matei", "42069");
        Person person5= new Person("Andrei", "Alexandru", "24512");


        makeReservation(0, person1);
        makeReservation(1, person2);
        makeReservation(2, person3);
        makeReservation(3, person4);
        makeReservation(4, person5);

    }
    private void initRepos() throws LoginException {
        initCars();
        initReservations();
    }

    public Service() throws LoginException {
        initRepos();
    }

    public Iterable<CarReservation> reservations()
    {
        return reservations.getAll();
    }
    public CarReservation makeReservation(int carId, Person renter)
            throws NoSuchElementException, RuntimeException {

        //the id of a car will coincide with the id of a reservation using that car
        if (reservations.findById(carId) != null)
            throw new RuntimeException("A reservation with the same id already exists!");

        //might throw NoSuchElementException
        Car rentedCar = removeFreeCar(carId);

        CarReservation reservation = new CarReservation(rentedCar.getId(), rentedCar, renter);
        reservations.add(reservation);
        return reservation;
    }

    public CarReservation removeReservation(int reservationId)
            throws NoSuchElementException
    {
        CarReservation reservation = reservations.findById(reservationId);

        if (reservation == null) {
            throw new NoSuchElementException("There is no reservation by that id!");
        }
        else {
            reservations.delete(reservationId);
            FREE_CARS.add(reservation.getRENTED_CAR());
            return reservation;
        }
    }
    public CarReservation getReservation(int reservationId)
            throws NoSuchElementException
    {
        CarReservation reservation = reservations.findById(reservationId);
        if (reservation == null)
            throw new NoSuchElementException("There is no reservation by that id!");
        else
            return reservation;
    }

    public int noOfReservations()
    {
        return reservations.size();
    }

    public Iterable<Car> freeCars()
    {
        return FREE_CARS.getAll();
    }
    public Car addNewFreeCar(String brand, String model, int year, float mileage, float cost)
    {
        Car car = new Car(VALID_CAR_ID++,brand, model, year, mileage, cost);
        FREE_CARS.add(car);
        return car;
    }

    public Car removeFreeCar(int carId)
            throws NoSuchElementException
    {
        Car car = FREE_CARS.findById(carId);
        if (car == null)
            throw new NoSuchElementException("There is no free car by that id!");
        else
            return FREE_CARS.delete(carId);
    }

    public Car getFreeCarById(int carId)
            throws NoSuchElementException
    {
        Car car = FREE_CARS.findById(carId);
        if (car == null)
            throw new NoSuchElementException("There is no free car by that id!");
        else
            return car;
    }

    public int noOfFreeCars()
    {
        return FREE_CARS.size();
    }

}
