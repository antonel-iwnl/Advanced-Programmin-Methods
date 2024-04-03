package repository;

import domain.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleRepository extends MemoryRepository<Integer, Vehicle> {
    public VehicleRepository() {
        super();
    }

    @Override
    public Vehicle update(Vehicle object) {
        if (entities.containsKey(object.getId())) {
            entities.put(object.getId(), object);
            return null;
        }
        return object;
    }
}
