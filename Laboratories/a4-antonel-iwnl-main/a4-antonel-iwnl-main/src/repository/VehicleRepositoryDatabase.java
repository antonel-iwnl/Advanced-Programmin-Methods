package repository;

import domain.Vehicle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryDatabase extends DatabaseRepository<Integer, Vehicle> {
public VehicleRepositoryDatabase(String tableName) {
        super(tableName);
        getData();
    }

    @Override
    public void getData() {
        try {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + ";";
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    String color = resultSet.getString("color");
                    int year = resultSet.getInt("year");
                    int price = resultSet.getInt("price");
                    int mileage = resultSet.getInt("mileage");
                    Boolean isRented = resultSet.getBoolean("isRented");
                    Vehicle vehicle = new Vehicle(id, name, type, color, year, price, mileage, isRented);
                    super.add(vehicle);
                }
            }
        } catch (SQLException | DuplicateEntityException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                System.out.println("Error closing connection.");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void add(Vehicle elem) throws DuplicateEntityException {
        try {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.setInt(1, elem.getId());
                ps.setString(2, elem.getName());
                ps.setString(3, elem.getType());
                ps.setString(4, elem.getColor());
                ps.setInt(5, elem.getYear());
                ps.setInt(6, elem.getPrice());
                ps.setInt(7, elem.getMileage());
                ps.setBoolean(8, elem.getIsRented());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DuplicateEntityException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Vehicle delete(Integer id) throws NonExistingEntityException {
        try {
            openConnection();
            String deleteString = "DELETE FROM " + tableName + " WHERE id = ?;";
            try (PreparedStatement ps = conn.prepareStatement(deleteString)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            return this.get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Integer id, Vehicle elem) {
        try {
            openConnection();
            String updateString = "UPDATE " + tableName + " SET name = ?, type = ?, color = ?, year = ?, price = ?, mileage = ?, isRented = ? WHERE id = ?;";
            try (PreparedStatement ps = conn.prepareStatement(updateString)) {
                ps.setString(1, elem.getName());
                ps.setString(2, elem.getType());
                ps.setString(3, elem.getColor());
                ps.setInt(4, elem.getYear());
                ps.setInt(5, elem.getPrice());
                ps.setInt(6, elem.getMileage());
                ps.setInt(7, id);
                ps.setBoolean(8, elem.getIsRented());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Vehicle get(Integer id) throws NonExistingEntityException {
        try {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + " WHERE id = ?;";
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    String color = resultSet.getString("color");
                    int year = resultSet.getInt("year");
                    int price = resultSet.getInt("price");
                    int mileage = resultSet.getInt("mileage");
                    Boolean isRented = resultSet.getBoolean("isRented");
                    Vehicle vehicle = new Vehicle(id, name, type, color, year, price, mileage, isRented);
                    return vehicle;
                } else {
                    throw new NonExistingEntityException("There is no vehicle with the given id to get.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> tempVehicleList = new ArrayList<>();
        try {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + ";";
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    String color = resultSet.getString("color");
                    int year = resultSet.getInt("year");
                    int price = resultSet.getInt("price");
                    int mileage = resultSet.getInt("mileage");
                    Boolean isRented = resultSet.getBoolean("isRented");
                    Vehicle vehicle = new Vehicle(id, name, type, color, year, price, mileage, isRented);
                    tempVehicleList.add(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return tempVehicleList;
    }
}
