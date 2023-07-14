package com.example.bdpractise.test;

import com.example.bdpractise.Enum.Status;
import com.example.bdpractise.entityRelation.Address;
import com.example.bdpractise.entityRelation.DebitCard;
import com.example.bdpractise.repository.DebitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DebitCardTest {
    @Autowired
    private DebitRepository debitRepository;

    @Test
    void testSaveDebit(){
        DebitCard debitCard = new DebitCard();
        debitCard.setMoney(134554);
        var saved = debitRepository.save(debitCard);

        assertNotNull(saved);
        assertNotNull(saved.getId());

        var fetched = debitRepository.findById(saved.getId());

        var fetchedCard = fetched.get();

        assertNotNull(fetchedCard);
        assertNotNull(fetchedCard.getId());
        assertNotNull(fetchedCard.getCreateDate());
    }

    @Test
    void testAddress(){
        DebitCard debitCard = new DebitCard();
        debitCard.setMoney(1345544);

        var address = Address.builder()
                .country("Ukraine")
                .city("Kyiv")
                .street("Bankova")
                .build();

        debitCard.setAddress(address);

        var saved = debitRepository.save(debitCard);

        assertNotNull(saved);

        var fetchedAddress = saved.getAddress();

        assertNotNull(fetchedAddress);

        assertArrayEquals(new Object[]{fetchedAddress.getCountry(), fetchedAddress.getCity(),
                fetchedAddress.getStreet()}, new Object[]{"Ukraine", "Kyiv","Bankova"}, () -> "Not equal");

        saved.setCvv(132L);

        var updateCard = debitRepository.save(saved);

        assertNotNull(updateCard.getUpdateDate());
    }

    @Test
    void testEnum(){
        DebitCard debitCard = new DebitCard();
        debitCard.setMoney(1345544);

        Status status = Status.Unblocked;
        debitCard.setStatus(status);
        var saved = debitRepository.save(debitCard);

        var savedStatus = saved.getStatus();

        assertNotNull(savedStatus);

        var value = savedStatus.name();

        assertNotNull(value);

        assertSame(Status.Unblocked.name(), savedStatus.name());
    }
}