package domain;

public class RespiratoryDisease extends Disease {
    private Integer Severity;

    public RespiratoryDisease(String Name, String Symptomps, Integer Severity) {
        super(Name, Symptomps);
        this.Severity = Severity;
    }

    @Override
    public Integer treatmentCost() {
        return (Severity > 2) ? 100 + 50 * (Severity - 2) : 100;
    }

    @Override
    public String treatment() {
        return (Severity < 3) ? "medication" : "might need ventilation";
    }

    @Override
    public String toString() {
        return super.toString() + ", Severity: " + Severity + ", Treatment Cost: " + this.treatmentCost() + ", Treatment: " + this.treatment();
    }
}
