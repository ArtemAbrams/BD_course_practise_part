package com.example.bdpractise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "marks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
