package com.example.lab7_3.repository;

import com.example.lab7_3.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findByAgeGreaterThanEqual(int age);

    int countByIeltsScore(float ieltsScore);

    List<Student> findByNameContainingIgnoreCase(String name);
}
