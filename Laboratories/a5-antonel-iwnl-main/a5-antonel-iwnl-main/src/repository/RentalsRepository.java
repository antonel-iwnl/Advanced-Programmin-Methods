package repository;

import domain.CarRental;

import java.util.List;

public class RentalsRepository extends MemoryRepository<Integer, CarRental> {
    public RentalsRepository() {
        super();
    }

    @Override
    public CarRental update(CarRental object) {
        if (entities.containsKey(object.getId())) {
            entities.put(object.getId(), object);
            return null;
        }
        return object;
    }

    @Override
    public List<CarRental> getAll() {
        return super.getAll();
    }
}
