package com.example.bdpractise.entityRelation;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Address {
    private String country;
    private String city;
    private String street;
}
