package ua.com.owu.springboot.services.car;

import org.springframework.http.ResponseEntity;
import ua.com.owu.springboot.models.Car;

import java.util.List;

public interface CarService {

    ResponseEntity<List<Car>> getAllCars();

    ResponseEntity<Car> getCarById(int id);

    ResponseEntity<Car> saveCar(Car car);

    ResponseEntity<Void> deleteCar(int id);

    ResponseEntity<List<Car>> getCarsByYear(int year);

    ResponseEntity<List<Car>> getCarsByBrand(String brand);

    ResponseEntity<List<Car>> getCarsByModel(String model);

    ResponseEntity<List<Car>> getCarsByPriceBetween(double minPrice, double maxPrice);

}
