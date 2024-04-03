package service;
import domain.Vehicle;
import domain.CarRental;
import domain.Customer;
import repository.*;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

public class Service {
    private RIdentifiable<Integer, Vehicle> vehicleRepository;
    private RentalsRepository rentalsRepository;
    private VehicleRepositoryBinaryFile vehicleRepositoryBinaryFile;
    private VehicleRepositoryTextFile vehicleRepositoryTextFile;
    private void initService() {
        try (FileReader fr = new FileReader("settings.properties")) {
            Properties properties = new Properties();
            properties.load(fr);
            String vehicleRepoType = properties.getProperty("vehicleRepoType");
            String filePath = properties.getProperty("Vehicles");
            switch (vehicleRepoType) {
                case "memory":
                    vehicleRepository = new VehicleRepository();
                    rentalsRepository = new RentalsRepository();
                    initVehicleRepo();
                    initCustomersAndRentals();
                    break;
                case "text":
                    vehicleRepository = new VehicleRepositoryTextFile(filePath);
                    rentalsRepository = new RentalsRepository();
                    initVehicleRepo();
                    initCustomersAndRentals();
                    break;
                case "binary":
                    vehicleRepository = new VehicleRepositoryBinaryFile(filePath);
                    rentalsRepository = new RentalsRepository();
                    initVehicleRepo();
                    initCustomersAndRentals();
                    break;
                case "database":
                    vehicleRepository = new VehicleRepositoryDatabase(filePath);
                    rentalsRepository = new RentalsRepository();
                    initCustomersAndRentals();
                    break;
                default:
                    System.out.println("Invalid vehicleRepoType: " + vehicleRepoType);
                    break;
            }
            } catch (Exception e) {
                e.printStackTrace();
        }

    }

    public void initVehicleRepo() throws DuplicateEntityException {
        vehicleRepository.add(new Vehicle(1, "Ford F150 Lightning", "Truck", "Red", 2022, 5400, 148700, true));
        vehicleRepository.add(new Vehicle(2, "Dacia Logan", "Sedan", "White", 2017, 9800, 30570, true));
        vehicleRepository.add(new Vehicle(3, "BMW X5", "SUV", "Black", 2019, 100000, 104000, false));
        vehicleRepository.add(new Vehicle(4, "Audi A4", "Sedan", "Silver", 2018, 31900, 67540, false));
        vehicleRepository.add(new Vehicle(5, "Volkswagen Golf", "Hatchback", "Yellow", 2016, 18750, 63256, false));
        vehicleRepository.add(new Vehicle(6, "Mercedes-Benz AMG E63S", "Sedan", "Black", 2019, 240000, 7500, true));
        vehicleRepository.add(new Vehicle(7, "Audi RSQ7", "SUV", "Carbon Red", 2017, 90000, 90000, false));
        vehicleRepository.add(new Vehicle(8, "BMW M340i", "Sedan", "Laguna Blue", 2018, 70000, 14500, false));
    }

    public void initCustomersAndRentals() throws DuplicateEntityException {
        Customer customer = new Customer(1, "Antonel Smecherescu", "Aleea Valeriu Bologa 3", "0740123456", "antonioadelin@gmail.com");
        Customer customer2 = new Customer(2, "Pista Matei Bomba", "Aleea Smecherilor 43", "0740123456", "pista_smek@yahoo.com");
        Customer customer3 = new Customer(3, "Mihai Pocinan", "Calea Victoriei 69", "0740123456", "mihai25@gmail.com");
        rentalsRepository.add(new CarRental(1, 1, 1, 5, 17300, 1555));
        rentalsRepository.add(new CarRental(2, 2, 2, 3, 24000, 1440));
        rentalsRepository.add(new CarRental(3, 6, 3, 7, 2500, 57320));
    }

    public Service() {
        initService();
    }
    
    public void addCarRental(CarRental carRental) throws DuplicateEntityException {
        rentalsRepository.add(carRental);
        vehicleRepository.findById(carRental.getIdVehicle()).setIsRented(true);
    }

    public void deleteVehicle(Integer id) throws NonExistingEntityException {
        vehicleRepository.delete(id);
    }

    public void addVehicle(Vehicle vehicle) throws DuplicateEntityException {
        vehicleRepository.add(vehicle);
    }

    public Vehicle findCarById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public CarRental findRentalById(Integer id) {
        return rentalsRepository.findById(id);
    }


    public void deleteCarRental(Integer id) throws NonExistingEntityException {
        vehicleRepository.findById(rentalsRepository.findById(id).getIdVehicle()).setIsRented(false);
        rentalsRepository.delete(id);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.update(vehicle);
    }

    public void updateCarRental(CarRental carRental) {
        rentalsRepository.update(carRental);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.getAll();
    }

    public List<CarRental> getAllCarRentals() {
        return rentalsRepository.getAll();
    }

    public void getAllRentedVehicles() {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getIsRented()).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
    }

    public void getAllAvailableVehicles() {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> !v.getIsRented()).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
    }

    public void getAllVehiclesWithPriceLessThan(Integer price) {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getPrice() < price).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
    }

    public void getAllVehiclesWithMileageLowerThan(Integer mileage) {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getMileage() < mileage).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
    }

    public void getAllVehiclesRentedByType(String type) {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getType().equals(type) && v.getIsRented()).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
    }
}
