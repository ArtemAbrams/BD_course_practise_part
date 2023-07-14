package com.example.bdpractise.dao;

import com.example.bdpractise.entity.Student;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    Student findStudentById(Long id) throws SQLException;

    List<Student> findStudentsByName(String name);

    Student saveNewStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Student student);

    List<Student> findAll();

    List<Student> findListPaging(Long limit, Long offset);

    List<Student> findAllStudent(Pageable pageable);

    List<Student> findAllSortByFirstName(Pageable pageable);
}
