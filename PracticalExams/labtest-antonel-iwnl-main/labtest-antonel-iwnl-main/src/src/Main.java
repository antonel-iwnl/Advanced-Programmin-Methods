import UI.UI;
import domain.CardiovascularDisease;
import domain.RespiratoryDisease;
import service.Service;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Service testServ = new Service();
        testServ.add(new CardiovascularDisease("Disease 1", "Fever;Bacteria;Yellowing", "high", "Coronary Artery Disease"));
        testServ.add(new CardiovascularDisease("Disease 2", "Fever", "low", "brain"));
        testServ.add(new RespiratoryDisease("Disease 3", "Fever;Coughing", 5));
        testServ.add(new RespiratoryDisease("Disease 4", "Fever", 2));
        UI ui = new UI(testServ);
        ui.start();
    }
}