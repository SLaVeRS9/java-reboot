package ru.sberbank.edu;


import org.h2.tools.Server;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CarBootstrap {
    public static void main(String[] args) throws Exception {
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();

        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);

            carService.addCar("777", "Lada");
            carService.addCar("111", "Mers");
            carService.addCar("222", "BMV");
            carService.addCar("333", "AUDI");

            // Test check start
            System.out.println("deleteCar check");
            carService.deleteCar("222");
            System.out.println("After del" + carRepository.findAll());

            System.out.println();
            System.out.println("deleteAllCars check");
            System.out.println("deleteAllCars = " + carRepository.deleteAll());
            System.out.println("findAllCars" + carRepository.findAll());

            System.out.println();
            System.out.println("createAllCars check");
            Collection<Car> newCars = new ArrayList<>();
            newCars.add(new Car("000", "WOW_CAR"));
            newCars.add(new Car("999", "Nissan"));
            newCars.add(new Car("888", "Lexus"));
            newCars.add(new Car("888", "Lexus"));
            System.out.println("createAllCars " + carRepository.createAll(newCars));




            /*String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String model = resultSet.getString(2);
                System.out.println("id=" + id + "; model=" + model);
            }*/
            // Test end
        }
        server.stop();
    }
}
