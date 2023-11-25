package ra.academy.config;

import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import ra.academy.dao.ICatalogDao;
import ra.academy.dao.impl.catalog.CatalogDao;
import ra.academy.service.catalog.CatalogService;
import ra.academy.service.catalog.ICatalogService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ra.academy.controller"
        ,"ra.academy.service","ra.academy.dao","ra.academy.validate"})
public class WebConfig implements WebMvcConfigurer , ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
    }
    // cấu hình thyme leaf
    //Cấu hình Thymleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("UTF-8");
        return viewResolver;
    }

    // bean message source
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("application");
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // cấu hình đường dẫn resource
        registry.addResourceHandler("/css/**","/js/**","/img/**")
                .addResourceLocations("classpath:static/css/","classpath:static/js/","classpath:static/img/");
    }
    // cấu hình csdl
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/prj_04");
//        dataSource.setUsername("root");
//        dataSource.setPassword("18061999");
//        return dataSource;
//    }
    // cấu hình jdbc template
//    @Bean
//    public JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate(dataSource());
//    }

    // bean dao
//    @Bean
//    public ICatalogDao catalogDao(){
////      return new CatalogDao(jdbcTemplate());
//        CatalogDao cat = new CatalogDao();
//        cat.setJdbcTemplate(jdbcTemplate());
//        return cat;
//    }
//    @Bean
//    public ICatalogService catalogService(){
//        return new CatalogService(catalogDao());
//    }


    // mailsender
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);  // Use port 465 for SMTPS with SSL

        mailSender.setUsername("hunghx@rikkeisoft.com");
        mailSender.setPassword("nnwcpmhclzxvkysz"); // Replace with your Gmail app password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }
    @Bean
    public SessionFactory sessionFactory(){
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate-config.xml")
                .buildSessionFactory();
    }
    // quản lí thao tác với các entity
    @Bean
    public EntityManager entityManager(){
        return sessionFactory().createEntityManager();
    }

}
