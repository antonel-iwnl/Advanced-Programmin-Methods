package repository;

import domain.Identifiable;
import domain.Vehicle;

import java.util.List;

public abstract class FileRepository<ID, Rp extends Identifiable<ID>> extends MemoryRepository<ID,Rp> {
    protected String fileName;
    public FileRepository(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }
    protected abstract void readFromFile();
    protected abstract void writeToFile();
    @Override
    public void add(Rp elem) throws DuplicateEntityException {
        super.add(elem);
        writeToFile();
    }
    @Override
    public Rp delete(ID id) throws NonExistingEntityException {
        Rp elem = super.delete(id);
        writeToFile();
        return elem;
    }
}
