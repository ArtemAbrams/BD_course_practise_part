package com.example.bdpractise.test;
import com.example.bdpractise.entity.Mark;
import com.example.bdpractise.repository.MarkRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BDTest {

    // тут створена заглушка
    @MockBean
    private Flyway flyway;
    @Autowired
    private MarkRepository markRepository;

    @Commit
    @Order(1)
    @Test
    void compareCountOfRecords(){
        long count = markRepository.count();

        var mark = Mark.builder()
                .description("rerewr")
                .number(10)
                .build();

        markRepository.save(mark);

        var countAfter = markRepository.count();

        assertThat(countAfter).isGreaterThan(count);
    }

    @Test
    @Order(2)
    void countAfterAcceptNewElement() {
        long count = markRepository.count();

        assertThat(count).isEqualTo(1);
    }
}
