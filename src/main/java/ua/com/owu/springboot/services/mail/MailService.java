package ua.com.owu.springboot.services.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.owu.springboot.models.User;

@Service
@AllArgsConstructor

public class MailService {

    private JavaMailSender javaMailSender;

    @SneakyThrows
    public void sentEmailToUser(User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText("<h3>Hello user" + user.getName() + "</h3>" + "to activate your account click <a href='http://localhost:8080/users/activate/" + user.getId() + "'>here</a>",true);   // Впровадження тексту/ 1 параметр html teg + text, 2- true - дозвіл на тег
                                 // http://localhost:8080/users/activate/ - для Постмена Гетовий запит
        mimeMessageHelper.setFrom("dvosetrov@gmail.com"); // при проблемі з сертифікатами Віндовс
        javaMailSender.send(mimeMessage);
    }


}












