package com.example.lab7_2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    private int id;
    private String name;
    private int age;
    private String email;
    private float ieltsScore;
    private String status;

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", name='" + name + '\'' +
                        ", age=" + age +
                        ", email='" + email + '\'' +
                        ", ieltsScore=" + ieltsScore +
                        ", status='" + status + '\''
                ;
    }
}
