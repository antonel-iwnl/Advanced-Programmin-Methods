package CarRentals.Domain;

public class Car implements Identifiable<Integer> {
    private int id;
    private String BRAND;
    private String MODEL;
    private int YEAR;
    private float MILEAGE;
    private float COST;


    public Car(Integer id, String brand, String model, int year, float mileage, float cost)
    {
        this.id = id;
        this.BRAND = brand;
        this.MODEL = model;
        this.YEAR = year;
        this.MILEAGE = mileage;
        this.COST = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return id == car.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "id=" + id + ' ' + BRAND + ' ' + '\'' + MODEL + ' ' + '\'' + YEAR + ' ' + MILEAGE + "km " + COST + ' ' + "euroi";
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String brand) {
        this.BRAND = brand;
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

    public float getMILEAGE() {
        return MILEAGE;
    }

    public void setMILEAGE(float mileage) {
        this.MILEAGE = mileage;
    }

    public float getCOST() {
        return COST;
    }

    public void setCOST(float cost) {
        this.COST = cost;
    }
}
