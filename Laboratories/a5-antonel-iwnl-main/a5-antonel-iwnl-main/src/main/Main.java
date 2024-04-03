package main;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import domain.Vehicle;
import gui.VehiclesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.RIdentifiable;
import repository.VehicleRepositoryBinaryFile;
import repository.VehicleRepositoryTextFile;
import service.Service;
import tests.unitTesting;
import UI.UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Properties;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Service serv = new Service();
        VehiclesController controller = new VehiclesController(serv);
//        UI ui = new UI(serv);
//        ui.run();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/VehiclesGUI.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load()); // what the fuck
        stage.setScene(scene);
        stage.show();
    }
}