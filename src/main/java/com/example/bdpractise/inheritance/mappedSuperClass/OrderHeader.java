package com.example.bdpractise.inheritance.mappedSuperClass;

import com.example.bdpractise.entityRelation.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderHeader extends Based{
    @Embedded
    private Address address;
}
