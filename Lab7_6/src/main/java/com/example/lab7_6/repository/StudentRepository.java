package com.example.lab7_6.repository;

import com.example.lab7_6.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> findOlderThan(int age);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.ieltsScore = :score")
    int countIeltsScore(float score);

    @Query("SELECT s FROM Student s WHERE lower(s.name) LIKE lower(concat('%', :name, '%'))")
    List<Student> hasNameContains(String name);
}

