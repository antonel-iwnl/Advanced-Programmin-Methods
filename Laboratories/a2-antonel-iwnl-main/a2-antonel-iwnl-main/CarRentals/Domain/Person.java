package CarRentals.Domain;

import java.util.Objects;

public class Person implements Identifiable<String>{

    private String FAMILY_NAME, FIRST_NAME;
    private String id;

    public Person(String family_name, String fisrt_name, String id)
    {
        this.FAMILY_NAME = family_name;
        this.FIRST_NAME = fisrt_name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client: " +
                "Nume='" + FAMILY_NAME + '\'' +
                ", Prenume='" + FIRST_NAME + '\'' +
                ", id='" + id + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getFAMILY_NAME() {
        return FAMILY_NAME;
    }

    public void setFAMILY_NAME(String familyName) {
        this.FAMILY_NAME = familyName;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String firstName) {
        this.FIRST_NAME = firstName;
    }

}
