package com.example.bdpractise.dao;

import com.example.bdpractise.entity.Student;
import com.example.bdpractise.jdbcMapper.RowMapperC;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentJDBCTemplateImpl implements StudentDao{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Student findStudentById(Long id) {
        var student = jdbcTemplate.queryForObject("select * from students where id = ?", getRowMapper(), id);
        return student;
    }

    @Override
    public List<Student> findStudentsByName(String name) {

        return jdbcTemplate.query("select * from students where first_name = ?", new Object[]{name},(resultSet, rowNum) -> {
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("first_name"));
            student.setLastname(resultSet.getString("last_name"));
            return student;
        });
    }

    @Override
    public Student saveNewStudent(Student student) {
        jdbcTemplate.update("insert into students(first_name, last_name) values (?, ?)", student.getName(), student.getLastname());

        var id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        return this.findStudentById(id);
    }

    @Override
    public Student updateStudent(Student student) {

        jdbcTemplate.update("update students set last_name =?, first_name =? where id =?", new Object[]{student.getLastname(), student.getName(), student.getId()});

        return this.findStudentById(student.getId());
    }

    @Override
    public void deleteStudent(Student student) {

        jdbcTemplate.update("delete from students where id = ?", student.getId());

    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from students", getRowMapper());
    }

    @Override
    public List<Student> findListPaging(Long limit, Long offset) {
        return jdbcTemplate.query("select * from students limit ? offset ?", getRowMapper(), limit, offset);
    }

    @Override
    public List<Student> findAllStudent(Pageable pageable) {
        return null;
    }

    @Override
    public List<Student> findAllSortByFirstName(Pageable pageable) {
        return null;
    }

    private RowMapper<Student> getRowMapper(){
        return new RowMapperC();
    }
}
