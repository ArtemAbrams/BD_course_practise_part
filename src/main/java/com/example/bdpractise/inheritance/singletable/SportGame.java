package com.example.bdpractise.inheritance.singletable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "sport_game")
public class SportGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
