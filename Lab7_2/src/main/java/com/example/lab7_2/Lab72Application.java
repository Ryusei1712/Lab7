package com.example.lab7_2;

import com.example.lab7_2.entity.Student;
import com.example.lab7_2.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Lab72Application {

    public static void main(String[] args) {

        SpringApplication.run(Lab72Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            studentRepository.save(new Student(1, "Bruv", 20, "bruv@example.com", 7.0f, formattedDateTime));
            studentRepository.save(new Student(2, "Lmao", 22, "lmao@example.com", 8.0f, formattedDateTime));
            studentRepository.save(new Student(3, "Dude", 21, "dude@example.com", 6.5f, formattedDateTime));

            Iterable<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            Student update = studentRepository.findById(2).orElse(null);
            if (update != null) {
                update.setName("Lmao");
                update.setIeltsScore(8.5f);
                update.setStatus(formattedDateTime);
                studentRepository.save(update);
                System.out.println("Updated Student: \n" + update);
            }

            studentRepository.deleteById(1);
            System.out.println("Deleted student with ID 1.");

            students = studentRepository.findAll();
            students.forEach(System.out::println);
        };
    }
}
