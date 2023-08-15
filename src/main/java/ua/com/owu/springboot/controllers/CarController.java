package ua.com.owu.springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.springboot.models.car.Car;
import ua.com.owu.springboot.services.car.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

      private final CarService carService;

      @GetMapping
      public ResponseEntity<List<Car>> getAllCars() {
            return carService.getAllCars();
      }

      @GetMapping("/{id}")
      public ResponseEntity<Car> getCarById(@PathVariable int id) {
            return carService.getCarById(id);
      }

      @PostMapping
      public ResponseEntity<Car> saveCar(@RequestBody Car car) {
            return carService.saveCar(car);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteCar(@PathVariable int id) {
            return carService.deleteCar(id);
      }

      @GetMapping("/byYear/{year}")
      public ResponseEntity<List<Car>> getCarsByYear(@PathVariable int year) {
            return carService.getCarsByYear(year);
      }

      @GetMapping("/byBrand/{brand}")
      public ResponseEntity<List<Car>> getCarsByBrand(@PathVariable String brand) {
            return carService.getCarsByBrand(brand);
      }

      @GetMapping("/byModel/{model}")
      public ResponseEntity<List<Car>> getCarsByModel(@PathVariable String model) {
            return carService.getCarsByModel(model);
      }

      @GetMapping("/byPriceBetween")
      public ResponseEntity<List<Car>> getCarsByPriceBetween(
              @RequestParam double minPrice,
              @RequestParam double maxPrice) {
            return carService.getCarsByPriceBetween(minPrice, maxPrice);
      }
}


















//import com.fasterxml.jackson.annotation.JsonView;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ua.com.owu.springboot.models.Car;
//import ua.com.owu.springboot.models.views.Views;
//import ua.com.owu.springboot.services.car.CarService;
//
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/cars")
//@AllArgsConstructor
//public class CarController {
//
//      private CarService carService;
//
//      @GetMapping()
//      @JsonView(Views.Level3.class)
//      public ResponseEntity<List<Car>> getAllCars() {
//            return carService.getAllCars();
//      }
//
//      //    get /cars/{id}
//      @GetMapping("/{id}")
//      @JsonView(value = Views.Level1.class)
//      public ResponseEntity<Car> getById(@PathVariable("id") int id) {
//            return carService.getById(id);
//      }
//
//      //    post /cars
//      @PostMapping()
//      public ResponseEntity<Car> saveCar(@RequestBody @Valid Car car) {
//
//            return carService.saveCar(car);
//      }
//
//      //    delete /cars/{id}
//      @DeleteMapping("/{id}")
//      @ResponseStatus(HttpStatus.GONE)
//      public void deleteCar(@PathVariable int id) {
//            carService.deleteCar(id);
//      }
//
//
//      @GetMapping("/power/{value}")
//      @JsonView(Views.Level2.class)
//      public ResponseEntity<List<Car>> getAllCarsByPower(@PathVariable int value) {
//            return carService.getAllCarsByPower(value);
//      }
//
//      //    get cars/producer/{value} (знайти всі по виробнику)
//      @GetMapping("/producer/{value}")
//      @JsonView(Views.Level2.class)
//      public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable String value) {
//            return carService.getCarsByProducer(value);
//      }
//
//
//}