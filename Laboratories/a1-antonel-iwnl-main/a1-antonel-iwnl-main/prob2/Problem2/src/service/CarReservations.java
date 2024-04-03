package service;

import domain.Car;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class CarReservations {
    private List<Car> RESERVED_CARS;

    public CarReservations(Repository repository) {
        this.RESERVED_CARS = new ArrayList<>();
    }

    // Create a reservation for rental
    public boolean createReservation(String carId) {
        Car car = Repository.getID(carId);
        if (car != null) {
            if (!car.isRESERVED()) {
                car.setRESERVED(true);
                RESERVED_CARS.add(car);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // Cancel a reservation
    public boolean cancelReservation(String carId) {
        Car car = Repository.getID(carId);
        if (car != null) {
            if (car.isRESERVED()) { 
                car.setRESERVED(false);
                RESERVED_CARS.remove(car);
                return true;
            } else {
                return false; 
            }
        }
        return false; 
    }

    public List<Car> getRESERVED_CARS() {
        return RESERVED_CARS;
    }
}
