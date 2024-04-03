package domain;

public class CardiovascularDisease extends Disease {
    private String RiskFactor;
    private String Subtype;

    public CardiovascularDisease(String Name, String Symptomps, String RiskFactor, String Subtype) {
        super(Name, Symptomps);
        this.RiskFactor = RiskFactor;
        this.Subtype = Subtype;
    }

    @Override
    public Integer treatmentCost() {
        return ("Coronary Artery Disease".equalsIgnoreCase(Subtype)) ? 600 : 300;
    }

    @Override
    public String treatment() {
        return ("Coronary Artery Disease".equalsIgnoreCase(Subtype)) ? "surgery" : "medication";
    }

    @Override
    public String toString() {
        return super.toString() + ", RiskFactor: " + RiskFactor + ", Subtype: " + Subtype + "Treatment type: " + this.treatmentCost() + "Treatment: " + this.treatment();
    }
}