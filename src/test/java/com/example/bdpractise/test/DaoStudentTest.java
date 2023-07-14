package com.example.bdpractise.test;
import com.example.bdpractise.dao.StudentDaoJDBCImpl;
import com.example.bdpractise.entity.Student;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(value = {"example.bdpractise.dao"})
@Import(StudentDaoJDBCImpl.class)
public class DaoStudentTest {
    @MockBean
    private Flyway flyway;
    @Autowired
    private StudentDaoJDBCImpl studentDao;

    @Test
    void findStudentByIdTest() {
        var student = studentDao.findStudentById(7L);

        assertThat(student).isNotNull();

    }
    @Test
    void findStudentsByName(){
        var studentsTable = studentDao.findStudentsByName("Artem");

        assertThat(studentsTable).isNotNull();

        var count = studentsTable.size();

        assertThat(count).isGreaterThan(2);

    }
    @Test
    void saveNewStudent(){
        var student = Student.builder()
                .name("Artem")
                .lastname("Rommel")
                .build();
        var result = studentDao.saveNewStudent(student);

        assertThat(result).isNotNull();

        assertThat(result).hasNoNullFieldsOrPropertiesExcept("marks");

    }

    @Test
    void updateStudent(){
        var student = Student.builder()
                .name("Henrich")
                .lastname("Adenauer")
                .build();

        var saved = studentDao.saveNewStudent(student);

        saved.setLastname("Himmler");

        var update = studentDao.updateStudent(saved);

        assertThat(update).isNotNull();

        assertThat(update).hasNoNullFieldsOrPropertiesExcept("marks");
    }

    @Test
    void deleteStudent(){
        var student = Student.builder()
                .name("Adolf")
                .lastname("Shwarzneger")
                .build();

        var saved = studentDao.saveNewStudent(student);

        studentDao.deleteStudent(saved);

        var delete = studentDao.findStudentById(saved.getId());

        assertThat(delete).isNull();
    }
}
