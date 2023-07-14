package com.example.bdpractise.entityRelation;

import com.example.bdpractise.callbackclasses.DebitCardCallBackListener;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "debit")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(DebitCardCallBackListener.class)
public class DebitCard extends Account  {
    private int money;
    @Embedded
    private Address address;

    @ManyToOne
    private Owner owner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bought_products",
    joinColumns = @JoinColumn(name = "debit_id"),
    inverseJoinColumns = @JoinColumn(name = "product_Id"))
    private List<Electronic> electronicsList = new ArrayList<>();
}
