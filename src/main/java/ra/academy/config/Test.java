package ra.academy.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ra.academy.service.MailService;

import java.util.Properties;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        String pass = "123456";
//        String passBcrypt = BCrypt.hashpw(pass,BCrypt.gensalt(12));
//        System.out.println(passBcrypt);
//        // giải mã
//        String p = new Scanner(System.in).nextLine();
//        System.out.println(BCrypt.checkpw(p,passBcrypt));
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);  // Use port 465 for SMTPS with SSL

        mailSender.setUsername("hunghx@rikkeisoft.com");
        mailSender.setPassword("nnwcpmhclzxvkysz"); // Replace with your Gmail app password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hunghx@rikkeisoft.com");
        message.setTo("devst2025@gmail.com");
        message.setSubject("no title");
        message.setText("no content");
        //sending message
        mailSender.send(message);
    }
}
