package com.example.bdpractise.test;

import com.example.bdpractise.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringDataJPA {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findAllByLAstName() {
        var re = PageRequest.of(0, 4, Sort.by("name").ascending());

        var list = studentRepository.findAllStudentsByLastName("Rommel", re);

        var re_2 = PageRequest.of(0, 4, Sort.by("name").descending());

        var list_2 = studentRepository.findAllStudentsByLastName("Rommel", re_2);

        assertThat(list).isNotNull();

        assertThat(list_2).isNotNull();
    }

    @Test
    void findAllByLAstNameNat() {
        var re = PageRequest.of(0, 4, Sort.by("first_name").ascending());

        var list = studentRepository.findAllByLastnameNatQue("Rommel", re);

        var re_2 = PageRequest.of(0, 4, Sort.by("first_name").descending());

        var list_2 = studentRepository.findAllByLastnameNatQue("Rommel", re_2);

        assertThat(list).isNotNull();

        assertThat(list_2).isNotNull();
    }
}
