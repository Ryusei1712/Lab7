package com.example.lab7_6;

import com.example.lab7_6.entity.Student;
import com.example.lab7_6.repository.StudentRepositoryClone;
import com.example.lab7_6.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class Lab76Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab76Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                               StudentRepositoryClone pagingAndSortingStudentRepository) {
        return args -> {

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            // Thêm sinh viên
            studentRepository.save(new Student(1, "Bruv", 20, "bruv@example.com", 7.0f, "none"));
            studentRepository.save(new Student(2, "Lmao", 22, "lmao@example.com", 8.0f, "none"));
            studentRepository.save(new Student(3, "Dude", 21, "dude@example.com", 6.5f, "none"));
            studentRepository.save(new Student(4, "Bruv2", 20, "bruv@example.com", 7.0f, "none"));
            studentRepository.save(new Student(5, "Lmao2", 22, "lmao@example.com", 7.0f, "none"));
            studentRepository.save(new Student(6, "Dude2", 21, "dude@example.com", 6.5f, "none"));
            studentRepository.save(new Student(7, "Bruv3", 20, "bruv@example.com", 7.0f, "none"));
            studentRepository.save(new Student(8, "Lmao3", 22, "lmao@example.com", 8.0f, "none"));
            studentRepository.save(new Student(9, "Dude3", 21, "dude@example.com", 6.5f, "none"));
            studentRepository.save(new Student(10, "Bruv4", 20, "bruv@example.com", 7.0f, "none"));
            studentRepository.save(new Student(11, "Lmao4", 22, "lmao@example.com", 7.0f, "none"));
            studentRepository.save(new Student(12, "Dude4", 21, "dude@example.com", 6.5f, "none"));

            // In danh sách sinh viên
            Iterable<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            // Cập nhật sinh viên
            Student update = studentRepository.findById(2).orElse(null);
            if (update != null) {
                update.setName("Lmao");
                update.setIeltsScore(8.5f);
                update.setStatus(formattedDateTime);
                studentRepository.save(update);
                System.out.println("Updated Student: \n" + update);
            }

            // Xóa sinh viên
            studentRepository.deleteById(1);
            System.out.println("Deleted student with ID 1.");

            // Đọc danh sách sinh viên tuổi >= 21
            System.out.println("Find students older or equal 21");
            List<Student> studentsWithAgeGreaterThan21 = studentRepository.findOlderThan(21);
            studentsWithAgeGreaterThan21.forEach(System.out::println);

            // Đếm số lượng sinh viên có điểm IELTS là 7.0
            int countStudentsWithIeltsScore7 = studentRepository.countIeltsScore(7.0f);
            System.out.println("Number of students with IELTS score 7.0: " + countStudentsWithIeltsScore7);

            // Tìm danh sách sinh viên có tên chứa "Lmao"
            System.out.println("Find students whose name contains Lmao");
            List<Student> studentsWithNameContainingLmao = studentRepository.hasNameContains("Lmao");
            studentsWithNameContainingLmao.forEach(System.out::println);

            // Sắp xếp và phân trang
            System.out.println("Sorting");
            Sort ageDescIeltsAscSort = Sort.by(Sort.Direction.DESC, "age")
                    .and(Sort.by(Sort.Direction.ASC, "ieltsScore"));
            PageRequest pageRequest = PageRequest.of(0, 10, ageDescIeltsAscSort);
            Page<Student> sortedStudents = pagingAndSortingStudentRepository.findAll(pageRequest);
            sortedStudents.forEach(System.out::println);
            // In sinh viên 4-5-6
            System.out.println("Print student 4 5 6");
            List<Student> studentsToPrint = sortedStudents.getContent().subList(3, 6);
            studentsToPrint.forEach(System.out::println);
        };
    }
}
