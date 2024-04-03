package CarRentals.Domain;


public class CarReservation implements Identifiable<Integer>{
    private int id;
    private Car RENTED_CAR;
    private Person CUSTOMER;



    public CarReservation(int id, Car rentedCar, Person customer)
    {
        this.id = id;
        this.RENTED_CAR = rentedCar;
        this.CUSTOMER = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarReservation that = (CarReservation) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Rezervare: " +
                "id=" + id +
                ", Car{" + RENTED_CAR + "\n" + CUSTOMER + '\'' + "}" + "\n";
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Car getRENTED_CAR() {
        return RENTED_CAR;
    }

    public void setRENTED_CAR(Car rentedCar) {
        this.RENTED_CAR = rentedCar;
    }

    public Person getCUSTOMER() {
        return CUSTOMER;
    }

    public void setCUSTOMER(Person customer) {
        this.CUSTOMER = customer;
    }


}
