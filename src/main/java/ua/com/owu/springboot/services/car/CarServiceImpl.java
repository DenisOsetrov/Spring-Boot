package ua.com.owu.springboot.services.car;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.springboot.dao.CarDAO;
import ua.com.owu.springboot.models.car.Car;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarDAO carDAO;

    @Override
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carDAO.findAll();
        return ResponseEntity.ok(cars);
    }

    @Override
    public ResponseEntity<Car> getCarById(int id) {
        Optional<Car> car = carDAO.findById(id);
        return car.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Car> saveCar(Car car) {
        Car savedCar = carDAO.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @Override
    public ResponseEntity<Void> deleteCar(int id) {
        Optional<Car> carToDelete = carDAO.findById(id);
        if (carToDelete.isPresent()) {
            carDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Car>> getCarsByYear(int year) {
        List<Car> cars = carDAO.findByYear(year);
        return ResponseEntity.ok(cars);
    }

    @Override
    public ResponseEntity<List<Car>> getCarsByBrand(String brand) {
        List<Car> cars = carDAO.findByBrand(brand);
        return ResponseEntity.ok(cars);
    }

    @Override
    public ResponseEntity<List<Car>> getCarsByModel(String model) {
        List<Car> cars = carDAO.findByModel(model);
        return ResponseEntity.ok(cars);
    }

    @Override
    public ResponseEntity<List<Car>> getCarsByPriceBetween(double minPrice, double maxPrice) {
        List<Car> cars = carDAO.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(cars);
    }
}






