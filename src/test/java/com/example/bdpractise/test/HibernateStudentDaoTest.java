package com.example.bdpractise.test;

import com.example.bdpractise.dao.StudentDaoHibernateImpl;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(value = {"example.bdpractise.dao"})
@Import(StudentDaoHibernateImpl.class)
public class HibernateStudentDaoTest {
    @MockBean
    Flyway flyway;
    @Autowired
    private StudentDaoHibernateImpl studentDaoHibernate;

    @Test
    void findAll(){

        var list = studentDaoHibernate.findAll();

        assertThat(list).isNotNull();

        assertThat(list.size()).isGreaterThan(0);

    }
    @Test
    void findAllStudent(){

        var list = studentDaoHibernate.findAllStudent(PageRequest.of(2,3));

        assertThat(list).isNotNull();

        assertThat(list.size()).isEqualTo(3);

    }
    @Test
    void findAllStudentPAging2(){

        var list = studentDaoHibernate.findListPaging(3L,2L);

        assertThat(list).isNotNull();

        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void findAllStudentSorting(){

        var list = studentDaoHibernate.findAllSortByFirstName(PageRequest.of(0,2, Sort.by(Sort.Order.desc("name"))));

        var list_2 = studentDaoHibernate.findAllSortByFirstName(PageRequest.of(0,2, Sort.by("name").descending()));

        assertThat(list).isNotNull();


    }


}
