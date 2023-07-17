package ua.com.owu.springboot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.SqlReturnType;

@Data
@NoArgsConstructor
@Entity            // Аннотація @Entity використовується для позначення класу як сутності (entity). Сутність - це клас, який відображається на таблицю в базі даних.
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String producer;
    private int powerCar;
    private int year;
}
