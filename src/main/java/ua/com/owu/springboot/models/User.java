package ua.com.owu.springboot.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.owu.springboot.models.views.Views;

@Entity
@Data
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @JsonIgnore // Ігновувати це поле під час створення JSON об'єкта
    @JsonView(Views.Level1.class) // анотація для розроділу доступу полів
    private int id;

    // validation на пусті поля
    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name>=3")
    @Size(max = 20, message = "name is too long")
    @JsonView({Views.Level1.class, Views.Level2.class}) // Це поле для доступу level 1,2, тому в масив {}
    private String name;

    public User(String name) {
        this.name = name;
    }
}


















