package ua.com.owu.springboot.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.springboot.models.Car;

import java.util.List;

public interface CarDAO extends JpaRepository<Car, Integer> {

    @Query("select c from Car c where c.powerCar=:xxx")
    List<Car> customFindCarsByPower(@Param("xxx") int powerCar);

    List<Car> findByProducer(String producer);

    List<Car> findByPower(int power);
}