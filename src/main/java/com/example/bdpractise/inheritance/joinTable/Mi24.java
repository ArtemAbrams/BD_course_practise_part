package com.example.bdpractise.inheritance.joinTable;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mi24 extends Helicopter {
    private Long yearsOperation;
    private Boolean hasRockets;
}
