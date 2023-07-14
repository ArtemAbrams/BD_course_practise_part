package com.example.bdpractise.test;

import com.example.bdpractise.Enum.Status;
import com.example.bdpractise.entityRelation.DebitCard;
import com.example.bdpractise.repository.DebitRepository;
import com.example.bdpractise.services.EncryptionServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DebitRepositoryTest {
    @Autowired
    private DebitRepository debitRepository;
    @Autowired
    private EncryptionServiceImpl encryptionService;

    private final String CARD_NUMBER = "5432342167899567";
    @Test
    @Rollback(value = false)
    void savedAndStoredDebitCard(){
        var debit = new DebitCard();

        debit.setCvv(345L);

        debit.setStatus(Status.Unblocked);

        debit.setNumber(CARD_NUMBER);

        var saved = debitRepository.saveAndFlush(debit);

        var getCard = debitRepository.findById(saved.getId()).get();

        assertEquals(getCard.getNumber(), saved.getNumber());
        assertEquals(getCard.getNumber(), CARD_NUMBER);
        assertEquals(saved.getNumber(), CARD_NUMBER);
    }
}