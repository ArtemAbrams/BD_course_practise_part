package com.example.bdpractise.inheritance.joinTable;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Helicopter extends Vehicle {
    private String serialNumber;
    private Boolean isMilitary;

}
