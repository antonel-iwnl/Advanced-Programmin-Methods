package repository;
import domain.Car;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static List<Car> CARS = new ArrayList<Car>();

    // create a car
    public static void addCar(Car car){
        CARS.add(car);
    }

    //read a car by id
    public static Car getID(String id){
        for(Car car : CARS){
            if(car.getID().equals(id)){
                return car;
            }
        }
        return null;
    }

    //update a car
    public static void update(Car car){
        for(Car c : CARS){
            if(c.getID().equals(car.getID())){
                c.setMANUFACTURER(car.getMANUFACTURER());
                c.setMODEL(car.getMODEL());
                c.setYEAR(car.getYEAR());
            }
        }
    }

    //delete a car
    public static void delete(String id) {
        Car CAR_TO_REMOVE = null;
        for (Car car : CARS) {
            if (car.getID().equals(id)) {
                CAR_TO_REMOVE = car;
                break; // Exit the loop as we've found the car to delete
            }
        }

        if (CAR_TO_REMOVE != null) {
            CARS.remove(CAR_TO_REMOVE);
        }
    }


    //find a car by manufacturer
    public static List<Car> findByManufacturer(String manufacturer){
        List<Car> CARS_BY_MANUF = new ArrayList<Car>();
        for(Car car : CARS){
            if(car.getMANUFACTURER().equals(manufacturer)){
                CARS_BY_MANUF.add(car);
            }
        }
        return CARS_BY_MANUF;
    }

    // print all cars in the repository
    public static List<Car> getAll() {
        return CARS;
    }

    //add damage to a car with description
    public static void addDamage(String id, String description) {
        Car car = getID(id);
        if (car != null) {
            car.addDamage(description);
        }
    }

    public static void removeDamage(String id, String description) {
        Car car = getID(id);
        if (car != null) {
            car.removeDamage(description);
        }
    }

}


