package ua.com.owu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Створити модель
//Car
//    id
//    model
//    producer
//    power
//
//реалізувати запити
//get /cars
//get /cars/{id}
//post /cars
//delete /cars/{id}
//get cars/powerCar/{value} (знайти всі по потужності)
//get cars/producer/{value} (знайти всі по виробнику)

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
