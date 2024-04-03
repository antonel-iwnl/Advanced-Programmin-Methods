package domain;

import java.util.ArrayList;
import java.util.List;

public class Car implements Identifiable<String> {
    private String ID;
    private String MANUFACTURER;
    private String MODEL;
    private int YEAR;
    private boolean HAS_DAMAGE;
    private List<String> DAMAGES;
    private boolean RESERVED;

    public Car(String id, String manufacturer, String model, int year) {
        this.ID = id;
        this.MANUFACTURER = manufacturer;
        this.MODEL = model;
        this.YEAR = year;
        this.HAS_DAMAGE = false;
        this.DAMAGES = new ArrayList<>();
        this.RESERVED = false;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String id) {
        this.ID = id;
    }

    public String getMANUFACTURER() {
        return MANUFACTURER;
    }

    public void setMANUFACTURER(String manufacturer) {
        this.MANUFACTURER = manufacturer;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String model) {
        this.MODEL = model;
    }

    public int getYEAR() {
        return YEAR;
    }

    public void setYEAR(int year) {
        this.YEAR = year;
    }

    public boolean hasDamages() {
        return HAS_DAMAGE;
    }

    public String[] getDAMAGES() {
        return DAMAGES.toArray(new String[0]);
    }

    public void addDamage(String description) {
        DAMAGES.add(description);
        HAS_DAMAGE = true;
    }

    public void removeDamage(String description) {
        DAMAGES.remove(description);
        if (DAMAGES.isEmpty()) {
            HAS_DAMAGE = false;
        }
    }

    public boolean isRESERVED() {
        return RESERVED;
    }

    public void setRESERVED(boolean reserved) {
        this.RESERVED = reserved;
    }
}
