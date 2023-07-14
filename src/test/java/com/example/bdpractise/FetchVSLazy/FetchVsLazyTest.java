package com.example.bdpractise.FetchVSLazy;

import com.example.bdpractise.entityRelation.Product;
import com.example.bdpractise.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FetchVsLazyTest {
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void differ(){
        var countProducts =  ownerRepository.findAll()
                .stream()
                .flatMap(e -> e.getDebitCards().stream())
                .flatMap(e -> e.getElectronicsList().stream())
                .collect(Collectors.summarizingLong(Product::getId));

        System.out.println(countProducts);

    }
}
