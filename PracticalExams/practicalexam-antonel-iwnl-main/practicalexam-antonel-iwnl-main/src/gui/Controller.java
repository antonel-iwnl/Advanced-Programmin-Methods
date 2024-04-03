package gui;

import domain.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import service.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

import service.Service;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Activity> ActivitiesListView;

    @FXML
    private Button FilterButton;

    @FXML
    private ListView<Activity> ActivitiesListFiltered;

    @FXML
    private TextField moveminutesValue;

    @FXML
    void FilterActivities(ActionEvent event) {
        ObservableList<Activity> activitiesFiltered = FXCollections.observableArrayList(service.getActivitiesFilterValue(moveminutesValue.getText()));
        ActivitiesListFiltered.setItems(activitiesFiltered);
        ActivitiesListFiltered.refresh();
    }

    public void initialize() {
        populateList();
    }

    void populateList() {
        ObservableList<Activity> activities = FXCollections.observableArrayList(service.getAllActivitiesSorted());
        ActivitiesListView.setItems(activities);
    }
}
