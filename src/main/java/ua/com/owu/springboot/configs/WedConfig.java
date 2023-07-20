package ua.com.owu.springboot.configs;


// lesson3 File and Mail

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WedConfig implements WebMvcConfigurer {

    // В класі шукаємо файл і дістаємо його користувачу (це ніби певний прошарок між збереженням і представленням файлу)
    // пістає файл за посиланням
    @Override  // Ctrl + I - імплементація методів
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "file:///" + System.getProperty("user.home") + File.separator + "MySaveToPrograming" + File.separator;
        registry
                // http://localhost:8080/av/Foto-avatar-lesson3.jpg - за цим запитом тепер Постмен віддасть забраження без використання жодного методу Контролера
                .addResourceHandler("/av/**") // /av - частина запиту на URL, /** - обов'язкова частина для пошуку, що вказує на динамічне ім'я файлу для пошуку
                .addResourceLocations(path);
    }
}
