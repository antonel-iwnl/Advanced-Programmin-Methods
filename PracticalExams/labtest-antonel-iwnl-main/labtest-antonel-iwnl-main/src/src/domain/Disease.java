package domain;

public abstract class Disease {
    protected String Name;
    protected String Symptoms;

    public Disease(String Name, String Symptoms) {
        this.Name = Name;
        this.Symptoms = Symptoms;
    }

    public abstract Integer treatmentCost();

    public abstract String treatment();

    @Override
    public String toString() {
        return ("Disease: " + Name + ", Symptoms: " + Symptoms);
    }

    public String getName() {
        return this.Name;
    }

    public String getSymptoms() {
        return this.Symptoms;
    }

    public Integer getSymptomsNumber() {
        String[] stringArray = Symptoms.split(";");
        return stringArray.length;
    }

}
