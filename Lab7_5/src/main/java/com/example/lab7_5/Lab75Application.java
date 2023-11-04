package com.example.lab7_5;

import com.example.lab7_5.entity.Student;
import com.example.lab7_5.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class Lab75Application {

    public static void main(String[] args) {

        SpringApplication.run(Lab75Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            //add
            studentRepository.save(new Student(1, "Bruv", 20, "bruv@example.com", 7.0f, "none"));
            studentRepository.save(new Student(2, "Lmao", 22, "lmao@example.com", 8.0f, "none"));
            studentRepository.save(new Student(3, "Dude", 21, "dude@example.com", 6.5f, "none"));
            studentRepository.save(new Student(4, "Bruv2", 20, "bruv@example.com", 7.0f, "none"));
            studentRepository.save(new Student(5, "Lmao2", 22, "lmao@example.com", 7.0f, "none"));
            studentRepository.save(new Student(6, "Dude2", 21, "dude@example.com", 6.5f, "none"));

            //print console
            Iterable<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            //update
            Student update = studentRepository.findById(2).orElse(null);
            if (update != null) {
                update.setName("Lmao");
                update.setIeltsScore(8.5f);
                update.setStatus(formattedDateTime);
                studentRepository.save(update);
                System.out.println("Updated Student: \n" + update);
            }

            //delete
            studentRepository.deleteById(2);
            System.out.println("Deleted student with ID 2.");

            //Read a list of students whose age is greater than or equal to x, where x is the input parameter of the method.
            System.out.println("Find students older or equal 21");
            List<Student> studentsWithAgeGreaterThan21 = studentRepository.findOlderThan(21);
            studentsWithAgeGreaterThan21.forEach(System.out::println);

            //Count the number of students whose ielts score is equal to x, where x is an input parameter of the method
            int countStudentsWithIeltsScore7 = studentRepository.countIeltsScore(7.0f);
            System.out.println("Number of students with IELTS score 7.0: " + countStudentsWithIeltsScore7);

            //Find the list of students whose name contains the word xxx passed as a parameter. The string comparison method is case-insensitive.
            System.out.println("Find students whose name contains Lmao");
            List<Student> studentsWithNameContainingDude = studentRepository.hasNameContains("Lmao");
            studentsWithNameContainingDude.forEach(System.out::println);
        };
    }


}
