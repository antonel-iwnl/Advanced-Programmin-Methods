package repository;

import java.sql.SQLException;

public class DuplicateEntityException extends Exception {
    public DuplicateEntityException(SQLException message)
    {
        super(message);
    }
}
