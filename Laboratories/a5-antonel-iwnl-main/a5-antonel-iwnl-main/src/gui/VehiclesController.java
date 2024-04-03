package gui;

import domain.CarRental;
import domain.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.w3c.dom.events.MouseEvent;
import repository.DuplicateEntityException;
import service.Service;

public class VehiclesController {

    private Service service;

    public VehiclesController(Service service) {
        this.service = service;
    }

    @FXML
    private Button addRentalBttn;

    @FXML
    private Button addVehicleBttn;

    @FXML
    private Button delRentalBttn;

    @FXML
    private Button delVehicleBttn;

    @FXML
    private ListView<Vehicle> filterListView;

    @FXML
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private TextField input3;

    @FXML
    private TextField input4;

    @FXML
    private TextField input5;

    @FXML
    private TextField input6;

    @FXML
    private TextField input7;

    @FXML
    private TextField input8;

    @FXML
    private ListView<CarRental> rentalsListView;

    @FXML
    private Button updRentalBttn;

    @FXML
    private Button updVehicleBttn;

    @FXML
    private Button vehFilter1;

    @FXML
    private Button vehFilter2;

    @FXML
    private Button vehFilter3;

    @FXML
    private Button vehFilter4;

    @FXML
    private Button vehFilter5;

    @FXML
    private ListView<Vehicle> vehiclesListView;

    void populateList() {
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(service.getAllVehicles());
        vehiclesListView.setItems(vehicles);
        ObservableList<CarRental> rentals = FXCollections.observableArrayList(service.getAllCarRentals());
        rentalsListView.setItems(rentals);
    }

    public void initialize() {
        populateList();
    }

    @FXML
    void addRentalClick() {
        try {
            Integer id = Integer.parseInt(input1.getText());
            Integer idVehicle = Integer.parseInt(input3.getText());
            Integer idCustomer = Integer.parseInt(input4.getText());
            Integer days = Integer.parseInt(input5.getText());
            Integer kilometers = Integer.parseInt(input6.getText());
            Integer price = Integer.parseInt(input7.getText());
            CarRental carRental = new CarRental(id, idVehicle, idCustomer, days, kilometers, price);
            service.addCarRental(carRental);
            populateList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Something went wrong");
            alert.showAndWait();
        }

    }

    @FXML
    void addVehicleClick() {
        try {
            Integer id = Integer.parseInt(input1.getText());
            String name = input2.getText();
            String type = input3.getText();
            String color = input4.getText();
            Integer year = Integer.parseInt(input5.getText());
            Integer price = Integer.parseInt(input6.getText());
            Integer mileage = Integer.parseInt(input7.getText());
            Boolean isRented = Boolean.parseBoolean(input8.getText());
            Vehicle vehicle = new Vehicle(id, name, type, color, year, price, mileage, isRented);
            service.addVehicle(vehicle);
            populateList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Something went wrong");
            alert.showAndWait();
        }
    }

    @FXML
    void delRentalClick() {
        try {
            Integer id = Integer.parseInt(input1.getText());
            service.deleteCarRental(id);
            populateList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Something went wrong");
            alert.showAndWait();
        }
    }

    @FXML
    void delVehicleClick() {
        try {
            Integer id = Integer.parseInt(input1.getText());
            service.deleteVehicle(id);
            populateList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Something went wrong");
            alert.showAndWait();
        }
    }

    @FXML
    void getVehMileLess() {
        try {
            Integer mileage = Integer.parseInt(input7.getText());
            ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(service.getAllVehiclesWithMileageLowerThan(mileage));
            filterListView.setItems(vehicles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void getVehNotRentedClick() {
        try {
            ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(service.getAllAvailableVehicles());
            filterListView.setItems(vehicles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void getVehPriceLess() {
        try {
            Integer price = Integer.parseInt(input6.getText());
            ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(service.getAllVehiclesWithPriceLessThan(price));
            filterListView.setItems(vehicles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void getVehRentedByType() {
        try {
            String type = input3.getText();
            ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(service.getAllVehiclesRentedByType(type));
            filterListView.setItems(vehicles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void getVehRentedClick() {
        try {
            ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(service.getAllRentedVehicles());
            filterListView.setItems(vehicles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void updRentalClick() {
        try {
            Integer id = Integer.parseInt(input1.getText());
            Integer idVehicle = Integer.parseInt(input3.getText());
            Integer idCustomer = Integer.parseInt(input4.getText());
            Integer days = Integer.parseInt(input5.getText());
            Integer kilometers = Integer.parseInt(input6.getText());
            Integer price = Integer.parseInt(input7.getText());
            CarRental carRental = new CarRental(id, idVehicle, idCustomer, days, kilometers, price);
            service.updateCarRental(carRental);
            populateList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Something went wrong");
            alert.showAndWait();
        }
    }

    @FXML
    void updVehicleClick() {
        try {
            Integer id = Integer.parseInt(input1.getText());
            String name = input2.getText();
            String type = input3.getText();
            String color = input4.getText();
            Integer year = Integer.parseInt(input5.getText());
            Integer price = Integer.parseInt(input6.getText());
            Integer mileage = Integer.parseInt(input7.getText());
            Boolean isRented = Boolean.parseBoolean(input8.getText());
            Vehicle vehicle = new Vehicle(id, name, type, color, year, price, mileage, isRented);
            service.updateVehicle(vehicle);
            populateList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Something went wrong");
            alert.showAndWait();
        }
    }


}
