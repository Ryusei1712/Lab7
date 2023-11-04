package com.example.lab7_6.repository;


import com.example.lab7_6.entity.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepositoryClone extends PagingAndSortingRepository<Student, Integer> {
}
