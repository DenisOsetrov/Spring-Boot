package ua.com.owu.springboot.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.owu.springboot.dao.CarDAO;
import ua.com.owu.springboot.models.Car;

import java.util.List;


@RestController
@RequestMapping(value = "/cars")
@AllArgsConstructor
public class CarController {

      private CarDAO carDAO;

      // get /car/ all
      @GetMapping()
      public ResponseEntity<List<Car>> getAllCars() {
            return new ResponseEntity<>(carDAO.findAll(), HttpStatusCode.valueOf(200));
      }

      //    get /cars/{id}
      @GetMapping("/{id}")
      public ResponseEntity<Car> getById(@PathVariable("id") int id) {
            return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatusCode.valueOf(200));
      }

      // Це приклад з перевіркою на отримання об'єкта при можливій його відсутності
//      @GetMapping("/{id}")
//      public ResponseEntity<Car> getById(@PathVariable("id") int id) {
//            Optional<Car> optionalCar = carDAO.findById(id);
//            Car car = optionalCar.orElseGet(Car::new); // Встановлення значення за замовчуванням, якщо Optional порожній
//            return new ResponseEntity<>(car, HttpStatus.OK);
//      }



      //    post /cars
      @PostMapping()
      public ResponseEntity<Car> saveCar(@RequestBody Car car) { // id 0
            carDAO.save(car); // id =1
            return new ResponseEntity<>(car, HttpStatusCode.valueOf(201));
      }

      //    delete /cars/{id}
      @DeleteMapping("/{id}")
      @ResponseStatus(HttpStatus.GONE)
      public void deleteCar(@PathVariable int id) {
            carDAO.deleteById(id);
      }

      //    get cars/powerCar/{value} (знайти всі по потужності)\
      @GetMapping("/power/{value}")
      public ResponseEntity<List<Car>> getAllCarsByPower(@PathVariable int value) {
            //#1
//        List<Car> collect = carDAO.findAll().stream().filter(car -> car.getPower() == value).collect(Collectors.toList());
//        return new ResponseEntity<>(collect, HttpStatusCode.valueOf(200));
            //#2
//        return new ResponseEntity<>(carDAO.customFindCarsByPower(value), HttpStatusCode.valueOf(200));
            //#3
            return new ResponseEntity<>(carDAO.findByPowerCar(value), HttpStatusCode.valueOf(200));
      }

      //    get cars/producer/{value} (знайти всі по виробнику)
      @GetMapping("/producer/{value}")
      public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable String value) {
            return new ResponseEntity<>(carDAO.findByProducer(value), HttpStatusCode.valueOf(200));
      }


}