package com.example.bdpractise.FetchVSLazy;

import com.example.bdpractise.entityRelation.Address;
import com.example.bdpractise.entityRelation.DebitCard;
import com.example.bdpractise.entityRelation.Electronic;
import com.example.bdpractise.entityRelation.Owner;
import com.example.bdpractise.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class LoadDataTest {
    @Autowired
    private OwnerRepository ownerRepository;
    @Test
    void loadData(){
        int countOwners = 2000;
        for (int i = 0; i < countOwners; i++){
            var owner = Owner.builder()
                    .name("Artem " + i)
                    .surname("Friedman " + i)
                    .age((byte)12)
                    .build();
            creditCard(owner);
            ownerRepository.save(owner);
        }
    }
    @Transactional
    void creditCard(Owner owner){
        int countCard = 10;
        var address = Address.builder()
                .country("Ukraine")
                .city("Lviv")
                .street("Vsevolod")
                .build();
        for (int i =0; i<countCard; i++){
            var debitCard = DebitCard.builder()
                    .money(134564)
                    .address(address)
                    .electronicsList(new ArrayList<>())
                    .build();
            productData(debitCard);
            owner.addCard(debitCard);
        }
    }
    void productData(DebitCard debitCard){
        int countProduct = 5;
        for (int i =0; i<countProduct; i++){
            var product = Electronic.builder()
                            .material("metal")
                            .yearsOperation((byte) 5)
                            .build();

           var a =  debitCard.getElectronicsList().add(product);
        }

    }
}
