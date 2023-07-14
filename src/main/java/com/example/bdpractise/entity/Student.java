package com.example.bdpractise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "students")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String lastname;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<Mark> marks = new ArrayList<>();
}
