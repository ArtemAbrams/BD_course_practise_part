package com.example.bdpractise.test;
import com.example.bdpractise.dao.StudentJDBCTemplateImpl;
import com.example.bdpractise.entity.Student;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(value = {"example.bdpractise.dao"})
@Import(StudentJDBCTemplateImpl.class)
public class TemplateStudentTest {
    @MockBean
    private Flyway flyway;
    @Autowired
    private StudentJDBCTemplateImpl studentTemplate;
    @Test
    void findStudentById(){
        var student = studentTemplate.findStudentById(7L);

        assertThat(student).isNotNull();
    }
    @Test
    void findStudentsByName(){
        var student = studentTemplate.findStudentsByName("Artem");

        assertThat(student).isNotNull();

    }

    @Test
    void findAll(){

        var students = studentTemplate.findAll();

        assertThat(students).isNotNull();

    }

    @Test
    void saveStudent(){

        var student = Student.builder()
                        .name("Adolf")
                        .lastname("Shteiner")
                        .build();

        var saved = studentTemplate.saveNewStudent(student);

        assertThat(saved).isNotNull();

    }
    @Test
    void updateStudent(){

        var student = Student.builder()
                .name("Adolf")
                .lastname("Muller")
                .build();

        var saved = studentTemplate.saveNewStudent(student);

        saved.setLastname("Friedman");

        var update = studentTemplate.updateStudent(saved);

        assertThat(update).isNotNull();

        assertThat(update).hasNoNullFieldsOrPropertiesExcept("marks");

    }
    @Test
    void findListPaging2(){

        var students = studentTemplate.findAll();

        var studentsPaging = studentTemplate.findListPaging(2L, 2L);

        assertThat(studentsPaging).isNotNull();

        var count = studentsPaging.size();

        assertThat(count).isEqualTo(2);
    }

    @Test
    void deleteStudent(){

        var student = Student.builder()
                .name("Adolf")
                .lastname("Shteiner")
                .build();

        var saved = studentTemplate.saveNewStudent(student);

        studentTemplate.deleteStudent(saved);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            studentTemplate.findStudentById(saved.getId());
        });
    }

}
