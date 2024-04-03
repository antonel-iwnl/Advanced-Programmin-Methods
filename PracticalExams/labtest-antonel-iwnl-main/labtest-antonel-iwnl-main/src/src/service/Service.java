package service;

import domain.Disease;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Service {
    private List<Disease> diseaseList;

    public Service() {
        this.diseaseList = new ArrayList<>();
    }

    public void add(Disease disease) {
        diseaseList.add(disease);
    }

    public void showAll() {
        for (Disease disease : diseaseList) {
            System.out.println(disease);
        }
    }

    public void getDiseasesWithMedication(Integer cost) {
        List<Disease> result = diseaseList.stream()
                .filter(disease -> disease.treatmentCost() < cost && "medication".equalsIgnoreCase(disease.treatment()))
                .sorted(Comparator.comparing(Disease::getName))
                .collect(Collectors.toList());
        for (Disease disease : result) {
            System.out.println(disease);
        }
    }

    public void saveDiseasesToFile() {
        List<Disease> result = diseaseList.stream()
                .filter(disease -> disease.getSymptomsNumber() > 2)
                .sorted(Comparator.comparing(Disease::treatmentCost).reversed())
                .collect(Collectors.toList());
        try {
            FileReader fr = new FileReader("system.properties");
            Properties properties = new Properties();
            properties.load(fr);
            String txtPath = properties.getProperty("path");
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txtPath))) {
                for (Disease disease : result) {
                    bufferedWriter.write(disease.toString());
                    bufferedWriter.newLine();
                }
            } catch (Exception e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

}
