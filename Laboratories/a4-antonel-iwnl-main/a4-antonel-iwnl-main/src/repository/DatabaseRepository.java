package repository;

import domain.Identifiable;
import domain.Vehicle;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseRepository<ID, Rp extends Identifiable<ID>> extends MemoryRepository<ID, Rp> {
    protected final String URL = "jdbc:sqlite:/Users/antor/IdeaProjects/a4-antonel-iwnl/src/data/test.db";
    protected String tableName;
    protected Connection conn = null;
    public DatabaseRepository(String tableName) {
        this.tableName = tableName;
    }
    public abstract void getData();
    public void openConnection() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(URL);
        if (conn == null || conn.isClosed())
            conn = dataSource.getConnection();
    }
    public void closeConnection() throws SQLException {
        conn.close();
    }

    public abstract void update(Integer id, Vehicle elem) throws NonExistingEntityException;

    public abstract Vehicle get(Integer id) throws NonExistingEntityException;
}
