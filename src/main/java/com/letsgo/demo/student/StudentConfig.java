package com.letsgo.demo.student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {


    //The whole purpuse of this section is to create some dummy database rows for our endpoint the momment our application starts

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
           studentRepository.saveAll( List.of(
                   new Student( "Matias", "mcuervo@lesko.com.ar", LocalDate.of(1997, Month.MARCH, 20)),
                   new Student( "Alba", "ajames@lesko.com.ar", LocalDate.of(1996, Month.SEPTEMBER, 5)),
                   new Student( "Adela", "adelgadillo@lesko.com.ar", LocalDate.of(2000, Month.MAY, 23))
           ));
        };


    }



}