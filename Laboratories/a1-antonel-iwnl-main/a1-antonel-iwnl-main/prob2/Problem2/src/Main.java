
import domain.Car;
import repository.Repository;
import service.CarReservations;
import ui.UserInterface;

public class Main {
    public static void main(String[] args){
        Repository REPOSITORY = new Repository();
        CarReservations SERVICE = new CarReservations(REPOSITORY);
        // Add five car entities to the repository
        Repository.addCar(new Car("1", "Lamborghini", "Aventador SVJ", 2022));
        Repository.addCar(new Car("2", "BMW", "M340i", 2020));
        Repository.addCar(new Car("3", "Mercedes", "C300d", 2011));
        Repository.addCar(new Car("4", "Hyundai", "Elantra N", 2023));
        Repository.addCar(new Car("5", "Audi", "RS5", 2022));
        UserInterface userInterface = new UserInterface(SERVICE);
        userInterface.Start();
    }
}