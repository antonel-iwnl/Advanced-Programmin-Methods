package repository;

import domain.Identifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoryRepository<ID, Rp extends Identifiable<ID>> implements RIdentifiable<ID, Rp> {
    HashMap<ID, Rp> entities = new HashMap<>();

    @Override
    public void add(Rp object) throws DuplicateEntityException {
        if (entities.containsKey(object.getId())) {
            return;
        }
        entities.put(object.getId(), object);
    }

    @Override
    public Rp delete(ID id) throws NonExistingEntityException {
        if (entities.containsKey(id)) {
            return entities.remove(id);
        }
        return null;
    }

    @Override
    public Rp update(Rp object) {
        if (entities.containsKey(object.getId())) {
            entities.put(object.getId(), object);
            return null;
        }
        return object;
    }

    @Override
    public Rp findById(ID id) {
        return entities.get(id);
    }

    @Override
    public List<Rp> getAll() {
        return new ArrayList<>(entities.values());
    }

}
