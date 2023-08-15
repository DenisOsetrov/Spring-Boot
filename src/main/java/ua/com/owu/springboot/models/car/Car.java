package ua.com.owu.springboot.models.car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @JsonView(value = {Views.Level1.class})
    private int id;

    @NotBlank(message = "Brand cannot be blank")
    // @JsonView(value = {Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String brand;

    @NotBlank(message = "Model cannot be blank")
    @Size(min = 2, message = "Specify a different model")
    // @JsonView(value = {Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String model;

    @Positive(message = "Year must be a positive number")
    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    private int year;

    // getter for year
    public int getYear() {
        return year;
    }

    //  setter метод для перевірки і збереження року з валідацією. Якщо рік більший, ніж поточний рік, генерується виключення з відповідним повідомленням.
    public void setYear(int year) {
        int currentYear = java.time.Year.now().getValue();
        if (year <= currentYear) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Year must be less than or equal to the current year");
        }
    }

    @Positive(message = "Price must be a positive number")
    @DecimalMin(value = "1", inclusive = true, message = "Price must be greater than or equal to 1")
    @DecimalMax(value = "999999999", message = "Price must be less than or equal to 999999999")
    private double price;
}

