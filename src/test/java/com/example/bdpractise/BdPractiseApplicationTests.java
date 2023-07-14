package com.example.bdpractise;

import com.example.bdpractise.repository.MarkRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BdPractiseApplicationTests {
    @Autowired
    private MarkRepository markRepository;

    @MockBean
    private Flyway flyway;

    @MockBean
    private EntityManager entityManager;
    @Test
    void countTest(){
        var count = markRepository.count();
        assertThat(count).isEqualTo(0);
    }
    @Test
    void contextLoads() {
    }

}
