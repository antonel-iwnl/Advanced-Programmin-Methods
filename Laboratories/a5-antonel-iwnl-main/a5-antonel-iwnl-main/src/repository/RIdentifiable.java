package repository;

import domain.Identifiable;

import java.util.List;

public interface RIdentifiable<ID, Rp extends Identifiable<ID>>  {
    void add(Rp object) throws DuplicateEntityException;

    Rp delete(ID id) throws NonExistingEntityException;

    Rp update(Rp object);

    Rp findById(ID id);

    List<Rp> getAll();

}
