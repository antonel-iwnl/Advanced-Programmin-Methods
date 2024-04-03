package repository;

public class NonExistingEntityException extends Exception {
    public NonExistingEntityException(String message)
    {
        super(message);
    }
}
