package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    private static final String FIND_ALL_CARS_SQL = "SELECT * FROM car";
    private static final String DELETE_ALL_CARS_SQL = "DELETE FROM car";
    private static final String FIND_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement deleteByIdPreStmt;
    private final PreparedStatement findAllPreStmt;
    private final PreparedStatement deleteAllPreStmt;
    private final PreparedStatement findByModelPreStmt;
    private PreparedStatement createAllPreStmt;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.findAllPreStmt = connection.prepareStatement(FIND_ALL_CARS_SQL);
        this.deleteAllPreStmt = connection.prepareStatement(DELETE_ALL_CARS_SQL);
        this.findByModelPreStmt = connection.prepareStatement(FIND_CAR_BY_MODEL);
    }

    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        return null;
    }

    @Override
    public Set<Car> findAll() {
        return null;
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    /**
     * Delete car by id
     * @param id - car id
     * @return true - if car has been deleted, false - if not
     * @throws SQLException
     */
    @Override
    public Boolean deleteById(String id) throws SQLException {
        deleteByIdPreStmt.setString(1, id);
        int affectedRows = deleteByIdPreStmt.executeUpdate();
        return affectedRows > 0;
    }

    @Override
    public Boolean deleteAll() {
        return null;
    }

    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    /**
     * Add cars to DB
     * @param cars - cars to add
     * @return Cars set which has been added to DB
     * @throws SQLException
     */
    @Override
    public Set<Car> createAll(Collection<Car> cars) throws SQLException {

        Set<Car> createdCars = new HashSet<>();
        if (cars.isEmpty())
            return createdCars;

        StringBuilder query = new StringBuilder("INSERT INTO car (id, model) VALUES");

        cars.forEach(car -> {
                query.append(" (" + "'" + car.getId() + "'" + ", " + "'" + car.getModel() + "'" + "),");
        });
        query.deleteCharAt(query.length()-1);
        createAllPreStmt = connection.prepareStatement(query.toString());
        createAllPreStmt.executeUpdate();

        /* Вариант 2. Спам кучей запросов на создание
        cars.forEach(car -> {
            try {
                createOrUpdate(car);
                createdCars.add(car);
            } catch (SQLException e) {
                System.out.print("log note:");
                System.out.print("createAll failed on car = " + car + " by reason " + e.getMessage());
                throw new RuntimeException(e);
            }
        });*/

        return new HashSet<>(cars);
    }

    /**
     * Find and return all cars
     * @return All cars set in DB
     * @throws SQLException
     */
    @Override
    public Set<Car> findAll() throws SQLException {
        Set<Car> allCars = new HashSet<>();
        ResultSet resultSet = findAllPreStmt.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String model = resultSet.getString("model");
            allCars.add(new Car(id, model));
        }
        return allCars;
    }

    /**
     * Delete all cars from DB
     * @return true - if DB has been empty, false - if DB hasn't been empty
     * @throws SQLException
     */
    @Override
    public Boolean deleteAll() throws SQLException {
        deleteAllPreStmt.executeUpdate();
        return findAll().isEmpty();
    }

    /**
     * Find and return cars by model
     * @param model - car model
     * @return Cars set with the transmitted model
     * @throws SQLException
     */
    @Override
    public Set<Car> findByModel(String model) throws SQLException {
        findByModelPreStmt.setString(1, model);
        ResultSet resultSet = findByModelPreStmt.executeQuery();
        Set<Car> carsWithModel = new HashSet<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            carsWithModel.add(new Car(id, model));
        }
        return carsWithModel;
    }
}
