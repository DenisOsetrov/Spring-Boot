package ua.com.owu.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.springboot.models.Car;

import java.util.List;

@Repository
public interface CarDAO extends JpaRepository<Car, Integer> {

    List<Car> findByYear(int year);

    List<Car> findByBrand(String brand);

    List<Car> findByModel(String model);

    List<Car> findByPriceBetween(double minPrice, double maxPrice);
}
