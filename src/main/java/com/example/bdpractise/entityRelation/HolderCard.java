package com.example.bdpractise.entityRelation;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "holder_card")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolderCard extends Account{
    private int money;
    @Embedded
    private Address address;
}
